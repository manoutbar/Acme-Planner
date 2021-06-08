package acme.testing.anonymous.workPlan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousWorkPlanListTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workPlan/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex, final String executionStart, final String executionEnd, final String title, final String description, final String isPublic, final String workload) {
		super.clickOnMenu("Anonymous", "List non-finished public work plans");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, executionStart);
		super.checkColumnHasValue(recordIndex, 2, executionEnd);
		super.checkColumnHasValue(recordIndex, 3, workload);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("isPublic", isPublic);
		super.checkInputBoxHasValue("executionStart", executionStart);
		super.checkInputBoxHasValue("executionEnd", executionEnd);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		
		super.clickOnReturnButton("Return");
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workPlan/list-all-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void listAllNegative(final String url) {
		super.navigate(url,"");
		
		super.checkErrorsExist();
	}
}
