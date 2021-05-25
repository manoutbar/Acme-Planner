package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorConfigurationShowTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void show(final int recordIndex, final String spam, final String threshold) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "List all configuration");
		
		super.checkColumnHasValue(recordIndex, 0, spam);
		super.checkColumnHasValue(recordIndex, 1, threshold);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("spam", spam);
		super.checkInputBoxHasValue("threshold", threshold);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showNegative(final String url, final String query) {
		super.navigate(url, query);
		
		super.checkErrorsExist();
	}
	
}
