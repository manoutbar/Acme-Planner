package acme.testing.manager.workPlan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanCreateTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workPlan/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String isPublic, final String selectTask, final String titleTask, final String executionStartTask, final String executionEndTask, final int recordIndexTask) {
		super.signIn("taskManager", "taskManager");
		super.clickOnMenu("Manager", "Create work plan");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("isPublic", isPublic);

		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Manager", "List my works plans");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("executionStart", executionStart);
		super.checkInputBoxHasValue("executionEnd", executionEnd);
		super.checkInputBoxHasValue("isPublic", isPublic);
		
		super.clickOnReturnButton("Manage tasks");
		super.clickOnReturnButton("Add");
		
		super.fillInputBoxIn("task.id", selectTask);
		
		super.clickOnSubmitButton("Create");
		
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnReturnButton("Manage tasks");
		
		super.checkColumnHasValue(recordIndexTask, 0, titleTask);
		super.checkColumnHasValue(recordIndexTask, 1, executionStartTask);
		super.checkColumnHasValue(recordIndexTask, 2, executionEndTask);
		
		super.clickOnListingRecord(recordIndexTask);
		
		super.fillInputBoxIn("task.id", selectTask);
		super.clickOnReturnButton("Back");
		super.clickOnReturnButton("Back");
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workPlan/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative( final String title, final String description, final String executionStart, final String executionEnd, final String isPublic) {
		super.signIn("taskManager", "taskManager");
		super.clickOnMenu("Manager", "Create tasks");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("isPublic", isPublic);

		super.clickOnSubmitButton("Create");

		super.checkErrorsExist();
		super.signOut();
	}
	
}
