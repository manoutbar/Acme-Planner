package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String description, final String executionEnd, final String executionStart, final String isPublic, final String link, final String title, final String workload) {
		super.signIn("taskManager", "taskManager");
		super.clickOnMenu("Manager", "Create tasks");

		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("isPublic", isPublic);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("workload", workload);
		
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Manager", "List owned tasks");
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
	@CsvFileSource(resources = "/manager/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String description, final String executionEnd, final String executionStart, final String isPublic, final String link, final String title, final String workload) {
		super.signIn("taskManager", "taskManager");
		super.clickOnMenu("Manager", "Create tasks");

		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("isPublic", isPublic);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("workload", workload);
		
		super.clickOnSubmitButton("Create");

		super.checkErrorsExist();
		super.signOut();
	}
	
}
