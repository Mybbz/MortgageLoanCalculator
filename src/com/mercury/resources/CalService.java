package com.mercury.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.mercury.beans.Payment;
import com.mercury.beans.PaymentInfo;
import com.mercury.context.SpringContext;
import com.mercury.service.FixedrateCal;
import com.mercury.service.HelloService;

@Path("/cal")
public class CalService{
	
	
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public PaymentInfo execute(
			@FormParam("principal") double principal,
			@FormParam("fixedyear") int year,
			@FormParam("additional_principal") double additional_principal,
			@FormParam("start_month") int start_month){
	    System.out.println("this is the result "+year);
	    FixedrateCal frc = (FixedrateCal) SpringContext.getBean("fixedrateCal");
	    HelloService hs =(HelloService) SpringContext.getBean("helloService1");
		double start_rate = hs.findByYear(year);
		
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo = frc.cal(principal, start_rate, year, additional_principal, start_month);
		return paymentInfo;
	}
}
