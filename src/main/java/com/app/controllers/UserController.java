package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.models.User;
import com.app.services.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public void UserControler(UserService userService){
		this.userService = userService;
	}
	
	@RequestMapping("/user/new")
	public String newUser(Model model){
		model.addAttribute("user", new User());
		return "userform";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String saveUser(User user){
		userService.saveUser(user);
		return "redirect:/user/show/"+user.getUserId();
	}
	
	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return "redirect:/users";
	}
	
	@RequestMapping("/user/edit/{id}")
	public String editUser(@PathVariable Integer id, Model model){
		model.addAttribute("user", userService.getUserById(id));
		return "userform";
	}
	
	@RequestMapping("/user/show/{id}")
	public String showUser(@PathVariable Integer id, Model model){
		model.addAttribute("user", userService.getUserById(id));
		return "usershow";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(Model model){
		model.addAttribute("users", userService.listAllUsers());
		return "users";
	}
	

}