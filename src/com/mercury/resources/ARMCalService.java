package com.mercury.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.mercury.beans.Payment;
import com.mercury.beans.PaymentInfo;
import com.mercury.context.SpringContext;
import com.mercury.service.ARMCal;
import com.mercury.service.FixedrateCal;
import com.mercury.service.HelloService;

@Path("/armcal")
public class ARMCalService{
	
	
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public PaymentInfo execute(
			@FormParam("armType") int armType,  
			@FormParam("ARM_principal") double principal,
			@FormParam("ARM_year") int year,
			@FormParam("ARM_adjust_rate") double ARM_adjust_rate,
			@FormParam("ARM_rate_cap") double ARM_rate_cap
			){
	    System.out.println("this is working Arm Cal");
		PaymentInfo paymentInfo = new PaymentInfo();
		ARMCal armcal= (ARMCal) SpringContext.getBean("armcal");
		HelloService hs =(HelloService) SpringContext.getBean("helloService1");
		double start_rate = hs.findByYear(armType);
		System.out.println("rate is " + start_rate);
		int start_month = armType*12+1;
		paymentInfo = armcal.cal(principal,start_rate,year,start_month, ARM_adjust_rate, ARM_rate_cap);
		return paymentInfo;
	}
}
