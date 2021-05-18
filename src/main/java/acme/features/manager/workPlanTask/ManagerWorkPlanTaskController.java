package acme.features.manager.workPlanTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.tasks.WorkPlanTask;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Manager;

@Controller
@RequestMapping("/manager/work-plan/{workPlanId}/tasks")
public class ManagerWorkPlanTaskController extends AbstractController<Manager, WorkPlanTask> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkPlanTaskListService	listService;
	
	@Autowired
	protected ManagerWorkPlanTaskCreateService	createService;

	@Autowired
	protected ManagerWorkPlanTaskShowService	showService;

	@Autowired
	protected ManagerWorkPlanTaskDeleteService	deleteService;
	

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
