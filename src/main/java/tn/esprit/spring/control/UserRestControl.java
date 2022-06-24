package tn.esprit.spring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IUserService;

@RestController // = @Controller + @ResponseBody 

public class UserRestControl {

	@Autowired 
	IUserService userService; 
 
	@GetMapping("/retrieve-all-users")
	public List<User> retrieveAllUsers() {
		return userService.retrieveAllUsers();
	}
 
	@GetMapping("/retrieve-user/{user-id}")
	public User retrieveUser(@PathVariable("user-id") String userId) {
		return userService.retrieveUser(userId);
	}

	@PostMapping("/add-user")
	public User addUser(@RequestBody User u) {
		return userService.addUser(u); 
	}

	
	@DeleteMapping("/remove-user/{user-id}") 
	public void removeUser(@PathVariable("user-id") String userId) { 
		userService.deleteUser(userId);
	} 

	@PutMapping("/modify-user") 
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	 
} 
 