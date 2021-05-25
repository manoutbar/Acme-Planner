package acme.testing.manager.workPlan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkPlanDeleteTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workPlan/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String title, final String description, final String executionStart, final String executionEnd, final String isPublic,final String published) {
		super.signIn("taskManager", "taskManager");
		super.clickOnMenu("Manager", "List my works plans");
		
		super.checkColumnHasValue(recordIndex, 0, published);
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
		
		super.clickOnSubmitButton("Delete");
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workPlan/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final String url, final String query) {
		super.navigate(url, query);

		super.checkErrorsExist();
	}

}
