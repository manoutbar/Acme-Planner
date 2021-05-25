package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String description, final String executionEnd, final String executionStart, final String isPublic, final String link, final String title, final String workload) {
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
		
		super.clickOnSubmitButton("Delete");
		super.signOut();
	}

}
