package Depaseo.Depaseoproject;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	public UserRepository userRepository;
	@Autowired
	public RouteRepository routeRepository;

	// ------------------------------home ---------------------------
	@RequestMapping({ "/home", "/" })
	public String home() {
		return "home";
	}

	// -------------------------- error path website ----------------------------
	@RequestMapping({ "*", "*/*", "*/*/*" })
	public String notFound(Model model) {

		String pattern = "yyyy-MM-dd HH:mm:ssZ";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		model.addAttribute("serverTime", simpleDateFormat.format(new Date()));

		return "notFound";
	}

}
