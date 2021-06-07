package acme.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	
	private static final List<String> IGNORED_CHARACTERS = Arrays.asList(" ", "\n", "\r", ".", ",", ";", "-", "_", "!", "¡", "¿", "?");
	
	public String spamControl(final String text) {
		return this.spamControl(text, "master.form.error.marked-as-spam");
	}

	public String spamControl(final String text, final String error) {
		final Configuration configuration = this.configurationRepository.findMany().stream().findFirst().orElse(null);
		if (configuration == null) {
			return "";
		}
		final Integer totalCharacter = text.length() - this.countNumberOfOccurrences(Utils.IGNORED_CHARACTERS, text);
		
		final Integer spamCover = this.countNumberOfOccurrences(configuration.getSpamList(), text);
			//spamList.stream().map(s -> s.length()).collect(Collectors.summingInt(Integer::intValue));
		final Double spamPercent = spamCover.doubleValue() * 100 / totalCharacter.doubleValue();
		
		if(spamPercent > configuration.getThreshold()) {
			return error;
		}
		
		return "";
	}
	
	private Integer countNumberOfOccurrences(final List<String> words, final String text) {
		final String lowerCased = text.toLowerCase();
		return words.stream()
			.map(String::toLowerCase)
			.mapToInt(word -> word.length() * StringUtils.countMatches(lowerCased, word))
			.sum();
	}
}
