package acme.testing.administrator.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorTaskShowTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/task/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void show(final String totalNumberOfPublicTasks, final String totalNumberOfPrivateTasks, final String totalNumberOfFinishedTasks, final String totalNumberOfUnfinishedTasks, 
		final String averageNumberOfTasksExecutionPeriod, final String deviationNumberOfTasksExecutionPeriod, final String minimumTaskExecutionPeriod, final String maximumTaskExecutionPeriod, 
		final String averageNumberOfTasksWorkload, final String deviationNumberOfTasksWorkload, final String minimumTasksWorkload, final String maximumTasksWorkload, final String totalNumberOfWorkPlans, 
		final String totalNumberOfPrivateWorkPlans, final String totalNumberOfFinishedWorkPlans, final String totalNumberOfNonFinishedWorkPlans, final String averageNumberOfWorkPlansExecutionPeriod, 
		final String deviationSumOfWorkPlansExecutionPeriod, final String minimumWorkPlansExecutionPeriod, final String maximumWorkPlansExecutionPeriod, final String averageNumberOfWorkPlansWorkloads, 
		final String deviationNumberOfWorkPlansWorkloads, final String minimumWorkPlanWorkload, final String maximumWorkPlanWorkload) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");
		
		super.checkInputBoxHasValue("totalNumberOfPublicTasks", totalNumberOfPublicTasks);
		super.checkInputBoxHasValue("totalNumberOfPrivateTasks", totalNumberOfPrivateTasks);
		super.checkInputBoxHasValue("totalNumberOfFinishedTasks", totalNumberOfFinishedTasks);
		super.checkInputBoxHasValue("totalNumberOfUnfinishedTasks", totalNumberOfUnfinishedTasks);
		super.checkInputBoxHasValue("averageNumberOfTasksExecutionPeriod", averageNumberOfTasksExecutionPeriod);
		super.checkInputBoxHasValue("deviationNumberOfTasksExecutionPeriod", deviationNumberOfTasksExecutionPeriod);
		super.checkInputBoxHasValue("minimumTaskExecutionPeriod", minimumTaskExecutionPeriod);
		super.checkInputBoxHasValue("maximumTaskExecutionPeriod", maximumTaskExecutionPeriod);
		super.checkInputBoxHasValue("averageNumberOfTasksWorkload", averageNumberOfTasksWorkload);
		super.checkInputBoxHasValue("deviationNumberOfTasksWorkload", deviationNumberOfTasksWorkload);
		super.checkInputBoxHasValue("minimumTasksWorkload", minimumTasksWorkload);
		super.checkInputBoxHasValue("maximumTasksWorkload", maximumTasksWorkload);
		super.checkInputBoxHasValue("totalNumberOfWorkPlans", totalNumberOfWorkPlans);
		super.checkInputBoxHasValue("totalNumberOfPrivateWorkPlans", totalNumberOfPrivateWorkPlans);
		super.checkInputBoxHasValue("totalNumberOfFinishedWorkPlans", totalNumberOfFinishedWorkPlans);
		super.checkInputBoxHasValue("totalNumberOfNonFinishedWorkPlans", totalNumberOfNonFinishedWorkPlans);
		super.checkInputBoxHasValue("averageNumberOfWorkPlansExecutionPeriod", averageNumberOfWorkPlansExecutionPeriod);
		super.checkInputBoxHasValue("deviationSumOfWorkPlansExecutionPeriod", deviationSumOfWorkPlansExecutionPeriod);
		super.checkInputBoxHasValue("minimumWorkPlansExecutionPeriod", minimumWorkPlansExecutionPeriod);
		super.checkInputBoxHasValue("maximumWorkPlansExecutionPeriod", maximumWorkPlansExecutionPeriod);
		super.checkInputBoxHasValue("averageNumberOfWorkPlansWorkloads", averageNumberOfWorkPlansWorkloads);
		super.checkInputBoxHasValue("deviationNumberOfWorkPlansWorkloads", deviationNumberOfWorkPlansWorkloads);
		super.checkInputBoxHasValue("minimumWorkPlanWorkload", minimumWorkPlanWorkload);
		super.checkInputBoxHasValue("maximumWorkPlanWorkload", maximumWorkPlanWorkload);
		
		super.signOut();
	}
	
}


/*"totalNumberOfPublicTasks", "totalNumberOfPrivateTasks",
"totalNumberOfFinishedTasks", "totalNumberOfUnfinishedTasks",
"averageNumberOfTasksExecutionPeriod", "deviationNumberOfTasksExecutionPeriod",
"minimumTaskExecutionPeriod", "maximumTaskExecutionPeriod",
"averageNumberOfTasksWorkload", "deviationNumberOfTasksWorkload",
"minimumTasksWorkload", "maximumTasksWorkload",
"totalNumberOfWorkPlans", "totalNumberOfPrivateWorkPlans",
"totalNumberOfFinishedWorkPlans", "averageNumberOfWorkPlansExecutionPeriod",
"deviationSumOfWorkPlansExecutionPeriod", "minimumWorkPlansExecutionPeriod",
"maximumWorkPlansExecutionPeriod", "averageNumberOfWorkPlansWorkloads",
"deviationNumberOfWorkPlansWorkloads", "minimumWorkPlanWorkload",
"maximumWorkPlanWorkload", "totalNumberOfPublicWorkPlans",
"totalNumberOfNonFinishedWorkPlans", "totalNumberOfPublishedWorkPlans"*/