package com.mercury.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.beans.User;
import com.mercury.beans.UserInfo;
import com.mercury.context.SpringContext;
import com.mercury.service.HelloService;
import com.mercury.service.SendEmail;

@Controller
@SessionAttributes
public class HelloController {
	private HelloService hs;
	private String viewPage;
	
	public HelloService getHs() {
		return hs;
	}
	public void setHs(HelloService hs) {
		this.hs = hs;
	}
	public String getViewPage() {
		return viewPage;
	}
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	
	@RequestMapping(value="/hello", method=RequestMethod.POST)
	public ModelAndView process(@ModelAttribute("user") 
			User user, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewPage);
		
		return mav;
	}
	
	@RequestMapping(value="/doRegister", method=RequestMethod.POST)
	public ModelAndView processSave(@ModelAttribute("user") 
			User user,BindingResult result) {
		System.out.println("email is ");
		HelloService hs = (HelloService) SpringContext.getBean("helloService1");
		SendEmail se =(SendEmail) SpringContext.getBean("sendEmail");
		hs.saveUser(user);
		se.sendEmail(user.getEmail());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("successful");
		return mav;
	}
	
	@RequestMapping(value="/validation", method=RequestMethod.POST)
	@ResponseBody
	public String processValidate(@RequestParam("name") String name) {
		    return hs.validate(name);
    }
	
	
}
