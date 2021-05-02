package acme.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import acme.entities.configuration.Configuration;
import acme.features.configuration.ConfigurationRepository;

@Component
@Configurable
public class Utils {
	
	@Autowired
	private ConfigurationRepository configurationRepository;
	
	public String spamControl(final String text) {
		return this.spamControl(text, "supera el límite de palabras marcadas como spam.");
	}

	public String spamControl(final String text, final String error) {
		String result = "";
		final Configuration configuration = this.configurationRepository.findMany().stream().findFirst().orElse(null);
		
		final Integer totalCharacter = text.length();
		
		final List<String> spamList= configuration.getSpamList().stream().filter(text::contains).collect(Collectors.toList());
		Integer spamCover = 0;
		Double spamPercent = 0.0;
		spamCover = spamList.stream().map(s -> s.length()).collect(Collectors.summingInt(Integer::intValue));
		spamPercent = spamCover.doubleValue() * 100 / totalCharacter.doubleValue();
		if(spamPercent > configuration.getThreshold()) {
			result = error;
		}
		return result;
	}
}
