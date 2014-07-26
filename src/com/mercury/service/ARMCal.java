package com.mercury.service;

import javax.ws.rs.FormParam;

import org.springframework.cache.annotation.Cacheable;

import com.mercury.beans.Payment;
import com.mercury.beans.PaymentInfo;

public class ARMCal {
	@Cacheable(value="andCache")
	public PaymentInfo cal(
			double principal,
			double start_rate,
			int year,
			int arm_start_month,
	        double ARM_adjust_rate,
			double ARM_rate_cap){
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("this is Arm working");
		double rate = start_rate/1200;
		int totalMonth = year * 12;
		int month = 1;
		double remainingBalance = principal;
		double monthPayment=principal*(rate*(Math.pow(1+rate,totalMonth)))/
				(Math.pow(1+rate, totalMonth)-1);
		PaymentInfo paymentInfo = new PaymentInfo();
		while((remainingBalance>0)&&(month<=totalMonth)){
			double beginningBalance = remainingBalance;
			if((month>=arm_start_month)&&((month-1)%12==0)){
				rate = (rate*1200+ARM_adjust_rate)/1200;
				if(rate>=(ARM_rate_cap/1200)){
					rate=ARM_rate_cap/1200;
				}
				monthPayment=remainingBalance*(rate*(Math.pow(1+rate,totalMonth-month+1)))/
						(Math.pow(1+rate, totalMonth-month+1)-1);
			}
	        double interestMonth = beginningBalance*rate;
	        double principalMonth = monthPayment - interestMonth;
	        double endingBalance = remainingBalance - principalMonth;
	        if(endingBalance<0){
	        	double monthlyPayment = interestMonth+remainingBalance;
            	remainingBalance = 0;
            	double bB=Math.round(beginningBalance*100);
            	bB = bB/100;
            	double mP = Math.round(monthlyPayment*100);
            	mP = mP/100;
            	double iM=Math.round(interestMonth*100);
            	iM = iM/100;
            	Payment payment = new Payment(month,bB,mP,iM,bB,0);
            	paymentInfo.getPayments().add(payment);
            	break;
            }else {
            	double bB=Math.round(beginningBalance*100);
            	bB = bB/100;
            	double mP = Math.round(monthPayment*100);
            	mP = mP/100;
            	double iM=Math.round(interestMonth*100);
            	iM = iM/100;
            	double pM=Math.round(principalMonth*100);
            	pM=pM/100;
            	double eB=Math.round(endingBalance*100);
            	eB=eB/100;
            	
            	Payment payment = new Payment(month,bB,mP,iM,pM,eB);
            	paymentInfo.getPayments().add(payment);
            	remainingBalance = endingBalance;
            }
	        month++;
		}
		return paymentInfo;
	}
	

}
