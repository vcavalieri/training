package com.garage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.garage.exception.UserException;
import com.garage.model.User;
import com.garage.service.impl.UserServiceImpl;

@Controller
public class UserController {

	private static final Log log = LogFactory.getLog(UserController.class);

	@Autowired
	private ApplicationContext ctx;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUserController(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "firstname", required = false) String first,
			@RequestParam(value = "lastname", required = false) String last,
			@RequestParam(value = "password", required = false) String password, Model model) throws UserException {

		String message = null;
		log.info("Executing /register from com.garage.controller.UserController");
		String redirect = "registerUser";
		UserServiceImpl userOp = ctx.getBean(UserServiceImpl.class);
		if (username != null && username != "") {
			if (password != null && password != "") {
				if (first != null && first != "") {
					if (last != null && last != "") {
						try {
							User user = ctx.getBean(User.class);
							user.setFirstname(first);
							user.setLastname(last);
							user.setPassword(password);
							user.setUsername(username);
							message = userOp.registerUserService(user);
						} catch (UserException e) {
							message = e.getMessage();
							log.error(e);
						}
					}
				}
			}
			if (message != null) {
				model.addAttribute("message", message);
				if (message.equals("Registration Succesfully Done!")) {
					redirect = "index";
				}
			}
		}
		return redirect;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginUserController(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password, Model model, HttpServletRequest req) {

		String[] loginData = { null, null };
		log.info("Executing /login from com.garage.controller.UserController");
		String redirect = "index";
		UserServiceImpl userOp = ctx.getBean(UserServiceImpl.class);
		try {
			User user = ctx.getBean(User.class);
			user.setPassword(password);
			user.setUsername(username);
			loginData = userOp.loginUserService(user);

			if (loginData[0] != null) {
				if (loginData[0].equals("Login Succesfully Done!")) {
					redirect = "search";
				}
			}
		} catch (UserException e) {
			if (loginData != null) {
				loginData[0] = e.getMessage();
			}
			log.error(e);
		} finally {
			if (loginData != null) {
				model.addAttribute("message", loginData[0]);
				log.info("Setting username,password & iduser as HTTPSession's attributes...");
				req.getSession().setAttribute("username", username);
				req.getSession().setAttribute("password", password);
				req.getSession().setAttribute("iduser", loginData[1]);
				log.info("HTTPSession's attributes setted.");
			}
		}
		return redirect;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutUserController(Model model, HttpServletRequest req) {

		log.info("Executing /logout from com.garage.controller.UserController");
		req.getSession().invalidate();
		model.addAttribute("message", "Logout Succesfully Done!");
		return "index";
	}
}
