package com.example.demo.controller;

import jakarta.servlet.RequestDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.demo.service.HomeService;

@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;
	
	/**
	 * @return
	 */
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public String loadLoginxPage() {
    	return "login";
    }
    
    /**
     * @param model
     * @return
     */
    @GetMapping("/login-fail")
    public String loadLoginPagefail(ModelMap model) {
    	model.addAttribute("error",true);
    	return "login";
    }
	
	/**
	 * @return
	 */
	@GetMapping("/home")
	public String loadHomePage() {
		return "index";
	}
		
	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/error")
	public String handleError(ModelMap model, HttpServletRequest request) {

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String errorNumber = status.toString();

		switch (errorNumber) {
		case "404":
			model.addAttribute("msg", "404");
			return "error/error";
		case "500":
			model.addAttribute("msg", "500");
			return "error/error";
		case "403":
			model.addAttribute("msg", "403");
			return "error/error";
		case "400":
			model.addAttribute("msg", "400");
			return "error/error";
		default:
			model.addAttribute("msg", "default");
			return "error/error";
		}
	}
	
	/**
	 * @param request
	 * @return
	 */
	@PostMapping("/valid-recaptcha")
    public @ResponseBody String validRecaptcha(HttpServletRequest request){
    	String result = null;
    	String response = request.getParameter("g-recaptcha-response");
    	boolean isRecaptcha = homeService.verifyRecaptcha(response);

    	if(isRecaptcha) {
    		result = "success";
    	}else {
    		result = "false";
    	}  	
    	return result;
    }
	
}
