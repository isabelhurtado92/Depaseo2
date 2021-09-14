package Depaseo.Depaseoproject;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/usercontroller")

public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	
//-----------CRUD----------------
		
	
	@RequestMapping("/allusers")
	public String getAllUsers(Model boxToView) {

		boxToView.addAttribute("userListfromControllerAndDB", 
				userRepository.findAll());

		return "user/users";
	}
	
	@RequestMapping("/userhome")
	public String userhome() {

		return "userhome.html";
	}
	
	//Create:
	
	@RequestMapping("/newuser")
	public String newUser() {

		return "newuser.html";
	}

	@RequestMapping("/adduser")
	public String insertUser(User user) {
		
		System.out.println(user);

		userRepository.save(user);

		return "home.html";
	}

	//update:
	
	@RequestMapping("/updateuser")
	public String updateUser(int id, Model model) {

		Optional<User> userFound = findOneUserById(id);

		if (userFound.isPresent()) {

			model.addAttribute("userfromController", userFound.get());
			return "user/updateuser";
		}

		else
			return "notFound.html";
	}

	@PostMapping("/replaceuser/{idFromView}")
	public String replaceUser(@PathVariable("idFromView") int id, User user) {

		Optional<User> userFound = findOneUserById(id);

		if (userFound.isPresent()) {

			if (user.getName() != null)
				userFound.get().setName(user.getName());
			if (user.getSurname() != null)
				userFound.get().setSurname(user.getSurname());
			if (user.getPassword() != null)
				userFound.get().setPassword(user.getPassword());
			if (user.getEmail() != null)
				userFound.get().setEmail(user.getEmail());
			
			
			userRepository.save(userFound.get());
			return "redirect:/user/allusers";

		} else
			return "notFound.html";

	}
	
	
	//delete:
	
		@RequestMapping("/deleteuser")
		public String removeUser(int id, Model model) {
			Optional<User> userFound = findOneUserById(id);


			if (userFound.isPresent()) {
				userRepository.deleteById(id);
				model.addAttribute("message", "done");
				model.addAttribute("userDeleted", userFound.get());
			}

			else {
				model.addAttribute("message", "error");
			}

			
			return "deleteduser.html";
		}

		@RequestMapping("/deleteallusers")
		public String deleteAllUsers () {

			
			userRepository.deleteAll();
			

			return "redirect:/allusers";

		}
	
	
	//Detail:
	@RequestMapping("/detailuser")
	public String detailUser(int id, Model model) {

		Optional<User> userFound = findOneUserById(id);

		if (userFound.isPresent()) {

			model.addAttribute("userfromController", userFound.get());
			
			return "employee/detailuser";
		}

		else
			return "notFound.html";
	}
	

	
//Service:

	public Optional<User> findOneUserById(int id) {

	
		Optional<User> userFound = userRepository.findById(id);
	
		return userFound;
	}

}