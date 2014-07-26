package com.mercury.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PaymentInfo {
	private List<Payment> payments;
	private double save=0;
	public PaymentInfo(){
		this.payments=new ArrayList<Payment>();
	}
    
	@XmlElement(name="payment")
	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
    
	@XmlElement(name="save")
	public double getSave() {
		return save;
	}

	public void setSave(double save) {
		this.save = save;
	}
	
	

}
