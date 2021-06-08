package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void show(final String totalNumberOfPublicTasks, final String totalNumberOfPrivateTasks, final String totalNumberOfFinishedTasks, final String totalNumberOfUnfinishedTasks, 
		final String averageNumberOfTasksExecutionPeriod, final String deviationNumberOfTasksExecutionPeriod, final String minimumTaskExecutionPeriod, final String maximumTaskExecutionPeriod, 
		final String averageNumberOfTasksWorkload, final String deviationNumberOfTasksWorkload, final String minimumTasksWorkload, final String maximumTasksWorkload, final String totalNumberOfPublicWorkPlans, 
		final String totalNumberOfPrivateWorkPlans, final String totalNumberOfFinishedWorkPlans, final String totalNumberOfNonFinishedWorkPlans,
		final String averageNumberOfWorkPlansExecutionPeriod, final String deviationSumOfWorkPlansExecutionPeriod, final String minimumWorkPlansExecutionPeriod, final String maximumWorkPlansExecutionPeriod,
		final String averageNumberOfWorkPlansWorkloads, final String deviationNumberOfWorkPlansWorkloads, final String minimumWorkPlanWorkload, final String maximumWorkPlanWorkload) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");
		
		final String dashboardTableId = "dashboard-table";		
		
		
		super.checkTableColumnHasValue(dashboardTableId, 0, 0, totalNumberOfPublicTasks);
		super.checkTableColumnHasValue(dashboardTableId, 1, 0, totalNumberOfPrivateTasks);
		super.checkTableColumnHasValue(dashboardTableId, 2, 0, totalNumberOfFinishedTasks);
		super.checkTableColumnHasValue(dashboardTableId, 3, 0, totalNumberOfUnfinishedTasks);
		super.checkTableColumnHasValue(dashboardTableId, 4, 0, averageNumberOfTasksExecutionPeriod);
		super.checkTableColumnHasValue(dashboardTableId, 5, 0, deviationNumberOfTasksExecutionPeriod);
		super.checkTableColumnHasValue(dashboardTableId, 6, 0, minimumTaskExecutionPeriod);
		super.checkTableColumnHasValue(dashboardTableId, 7, 0, maximumTaskExecutionPeriod);
		super.checkTableColumnHasValue(dashboardTableId, 8, 0, averageNumberOfTasksWorkload);
		super.checkTableColumnHasValue(dashboardTableId, 9, 0, deviationNumberOfTasksWorkload);
		super.checkTableColumnHasValue(dashboardTableId, 10, 0, minimumTasksWorkload);
		super.checkTableColumnHasValue(dashboardTableId, 11, 0, maximumTasksWorkload);
		super.checkTableColumnHasValue(dashboardTableId, 12, 0, totalNumberOfPublicWorkPlans);
		super.checkTableColumnHasValue(dashboardTableId, 13, 0, totalNumberOfPrivateWorkPlans);
		super.checkTableColumnHasValue(dashboardTableId, 14, 0, totalNumberOfFinishedWorkPlans);
		super.checkTableColumnHasValue(dashboardTableId, 15, 0, totalNumberOfNonFinishedWorkPlans);
		super.checkTableColumnHasValue(dashboardTableId, 16, 0, averageNumberOfWorkPlansExecutionPeriod);
		super.checkTableColumnHasValue(dashboardTableId, 17, 0, deviationSumOfWorkPlansExecutionPeriod);
		super.checkTableColumnHasValue(dashboardTableId, 18, 0, minimumWorkPlansExecutionPeriod);
		super.checkTableColumnHasValue(dashboardTableId, 19, 0, maximumWorkPlansExecutionPeriod);
		super.checkTableColumnHasValue(dashboardTableId, 20, 0, averageNumberOfWorkPlansWorkloads);
		super.checkTableColumnHasValue(dashboardTableId, 21, 0, deviationNumberOfWorkPlansWorkloads);
		super.checkTableColumnHasValue(dashboardTableId, 22, 0, minimumWorkPlanWorkload);
		super.checkTableColumnHasValue(dashboardTableId, 23, 0, maximumWorkPlanWorkload);
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void testAllNegative() {
		// check restricted anonymous access
		super.navigate("/administrator/dashboard/show", "");
		super.checkErrorsExist();
				
		super.signIn("manager1", "manager1");
		super.navigate("/administrator/dashboard/show", "");
		super.checkErrorsExist();
		
		super.signOut();
	}
	
}