package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskUpdateTaskTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String description, final String executionEnd, final String executionStart, final String isPublic, final String link, final String title, final String workload) {
		super.signIn("taskManager", "taskManager");
		super.clickOnMenu("Manager", "List owned tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("isPublic", isPublic);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("workload", workload);
		
		super.clickOnSubmitButton("Update");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, executionStart);
		super.checkColumnHasValue(recordIndex, 2, executionEnd);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("isPublic", isPublic);		
		super.checkInputBoxHasValue("executionStart", executionStart);
		super.checkInputBoxHasValue("executionEnd", executionEnd);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("description", description);
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateNegative(final int recordIndex, final String description, final String executionEnd, final String executionStart, final String isPublic, final String link, final String title, final String workload) {
		super.signIn("taskManager", "taskManager");
		super.clickOnMenu("Manager", "List owned tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("isPublic", isPublic);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("workload", workload);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		super.signOut();
	}
}
