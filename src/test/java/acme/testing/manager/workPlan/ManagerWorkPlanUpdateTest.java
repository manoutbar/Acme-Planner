package acme.testing.manager.workPlan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanUpdateTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workPlan/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void update(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String isPublic) {
		super.signIn("taskManager", "taskManager");
		super.clickOnMenu("Manager", "List my works plans");

		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, executionStart);
		super.checkColumnHasValue(recordIndex, 3, executionEnd);
		super.checkColumnHasValue(recordIndex, 4, isPublic);
		
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("isPublic", isPublic);
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Manager", "List my works plans");
		
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, executionStart);
		super.checkColumnHasValue(recordIndex, 3, executionEnd);
		super.checkColumnHasValue(recordIndex, 4, isPublic);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("executionStart", executionStart);
		super.checkInputBoxHasValue("executionEnd", executionEnd);
		super.checkInputBoxHasValue("isPublic", isPublic);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workPlan/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateNegative(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String isPublic) {
		super.signIn("taskManager", "taskManager");
		super.clickOnMenu("Manager", "List my works plans");
		
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 4, isPublic);
		
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("executionStart", executionStart);
		super.fillInputBoxIn("executionEnd", executionEnd);
		super.fillInputBoxIn("isPublic", isPublic);
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
	}
}
