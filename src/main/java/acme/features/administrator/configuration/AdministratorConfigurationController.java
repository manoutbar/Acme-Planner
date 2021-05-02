package acme.features.administrator.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.configuration.Configuration;
import acme.features.anonymous.shout.AnonymousShoutCreateService;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/configuration/")
public class AdministratorConfigurationController extends AbstractController<Administrator, Configuration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigurationListService	listService;
	
	@Autowired
	protected AnonymousShoutCreateService	createService;
	
	@Autowired
	protected AdministratorConfigurationShowService showService;
	
	@Autowired
	protected AdministratorConfigurationUpdateService	updateService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCustomCommand(CustomCommand.LIST_ALL, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}


}
