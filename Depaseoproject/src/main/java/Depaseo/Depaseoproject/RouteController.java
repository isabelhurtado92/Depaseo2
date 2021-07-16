package Depaseo.Depaseoproject;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/routecontroller")

public class RouteController {
	
	@Autowired
	RouteRepository routeRepository;
	
	
//-----------CRUD----------------
		
	
	@RequestMapping("/allroutes")
	public String getAllUsers(Model boxToView) {

		boxToView.addAttribute("routesfromController", routeRepository.findAll());
		
		return "route/getallroutes"; //is it ok??

	}
	
	//Create:
	
	@RequestMapping("/newroute")
	public String newRoute() {
		
		return "route/newroute";

	}

	@RequestMapping("/addroute")
	public String addRoute(Route route) { 

		routeRepository.save(route);

		return "redirect:/route/allroutes";
	}

	//update:
	//2 steps: find and replace information
	@RequestMapping("/updateroute")
	public String updateRoute(int id, Model model) {
		
		Optional<Route> routeFound = findOneRouteById(id);
		
		if (routeFound.isPresent()) {
			
			model.addAttribute("routefromController", routeFound.get());
			return "updateroute";
		}
		
			return "home/notfound.html";
	}

	@PostMapping("/modifyroute/{idFromView}")
	public String modifyRoute(@PathVariable("idFromView") int id, Route route) {

		Optional<Route> routeFound = findOneRouteById(id);

		if (routeFound.isPresent()) {

			if (route.getName() != null)
				routeFound.get().setName(route.getName());
			if (route.getType() != null)
				routeFound.get().setType(route.getType());
			if (route.getLocation() != null)
				routeFound.get().setLocation(route.getLocation());
			
			routeRepository.save(routeFound.get());
			return "redirect:/route/allroutes";

		} 
			return "home/notfound.html";

	}
	
	
	//delete:
	//2 steps: find & delete
			
			@RequestMapping("/deleteroute")
			public String deleteroute(int id, Model model) {
				Optional<Route> routeFound = routeRepository.findOneRouteById(id);


				if (routeFound.isPresent()) {
					routeRepository.deleteById(id);
				model.addAttribute("message", "done");
				model.addAttribute("routeDeleted", routeFound.get());
			}

			else {
				model.addAttribute("message", "error");
			}

			
			return "route/deletedroute.html";
		}

		@RequestMapping("/deleteallroutes")
		public String deleteAllRoutes () {

			
			routeRepository.deleteAll();
			

			return "redirect:/route/allroutes";

		}
	
//Service:

		
		public Optional<Route> findOneRouteById(int id) {
			
			Optional<Route> routeFound = routeRepository.findById(id);
			
			return routeFound;
	}
		
		
		
		public String notFound (Model model) {
			
			return "notfound";
	
}


}