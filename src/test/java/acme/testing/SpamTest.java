/*
 * SignUpTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class SpamTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/spam/detection.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveSpamDetection(final String text) {
		super.clickOnMenu("Anonymous", "Shout!");

		super.fillInputBoxIn("author", "sample");
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", "http://example.org");

		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create tasks");

		super.fillInputBoxIn("title", "sample");
		super.fillInputBoxIn("executionStart", "2021/07/01 00:00");
		super.fillInputBoxIn("executionEnd", "2021/08/01 00:00");
		super.fillInputBoxIn("description", text);
		super.fillInputBoxIn("workload", "0.00");
		super.fillInputBoxIn("link", "http://example.org");

		super.clickOnSubmitButton("Create");

		super.checkErrorsExist();
		
		super.clickOnMenu("Manager", "Create work plan");
		
		super.fillInputBoxIn("title", "sample");
		super.fillInputBoxIn("description", text);
		super.fillInputBoxIn("executionStart", "2021/07/01 00:00");
		super.fillInputBoxIn("executionEnd", "2021/08/01 00:00");
		
		super.clickOnSubmitButton("Create");

		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/spam/detection.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void testConfigurationUpdatePositive(final String text) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "List all configuration");

		super.clickOnListingRecord(0);
		
		super.fillInputBoxIn("spam", "sex;sexo;palabrota");
		super.fillInputBoxIn("threshold", "50.00");

		super.clickOnSubmitButton("Update");
		
		super.checkColumnHasValue(0, 0, "sex;sexo;palabrota");
		super.checkColumnHasValue(0, 1, "50.00");
		
		super.signOut();		
	}
}
