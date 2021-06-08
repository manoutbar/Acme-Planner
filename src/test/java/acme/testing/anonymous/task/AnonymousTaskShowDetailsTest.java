package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskShowDetailsTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/show-details.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showDetails(final int recordIndex, final String title, final String isPublic, final String executionStart, final String executionEnd, final String workload, final String description) {
		super.clickOnMenu("Anonymous", "List non-finished public tasks");

		super.checkColumnHasValue(recordIndex, 0, executionStart);
		super.checkColumnHasValue(recordIndex, 1, executionEnd);
		super.checkColumnHasValue(recordIndex, 2, title);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("isPublic", isPublic);
		super.checkInputBoxHasValue("executionStart", executionStart);
		super.checkInputBoxHasValue("executionEnd", executionEnd);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		
		super.clickOnReturnButton("Return");
	}
	
	@Test
	@Order(20)
	public void testAllNegative() {
		super.navigate("/anonymous/task/show", "id=44");
		super.checkErrorsExist();

		super.signIn("manager1", "manager1");
		super.navigate("/anonymous/task/show", "id=40");
		super.checkErrorsExist();

		super.signOut();

		super.signIn("administrator", "administrator");
		super.navigate("/anonymous/task/show", "id=40");
		super.checkErrorsExist();

		super.signOut();
	}
	
}
