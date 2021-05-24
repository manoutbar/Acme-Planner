package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorConfigurationUpdateTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String spam, final String threshold) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "List all configuration");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("spam", spam);
		super.fillInputBoxIn("threshold", threshold);
		
		super.clickOnSubmitButton("Update");
		
		super.checkColumnHasValue(recordIndex, 0 , spam);
		super.checkColumnHasValue(recordIndex, 1, threshold);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("spam", spam);
		super.checkInputBoxHasValue("threshold", threshold);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/configuration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String spam, final String threshold) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "List all configuration");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("spam", spam);
		super.fillInputBoxIn("threshold", threshold);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
}
