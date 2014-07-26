package com.mercury.service;

import javax.ws.rs.FormParam;

import org.springframework.cache.annotation.Cacheable;

import com.mercury.beans.Payment;
import com.mercury.beans.PaymentInfo;

public class FixedrateCal {
	@Cacheable(value="andCache")
	public PaymentInfo cal(double principal,double start_rate,int year,double additional_principal,int start_month){
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double rate = start_rate/1200;
		int totalMonth = year * 12;
		int month = 1;
		double remainingBalance = principal;
		double monthPayment=principal*(rate*(Math.pow(1+rate,totalMonth)))/
				(Math.pow(1+rate, totalMonth)-1);
		PaymentInfo paymentInfo = new PaymentInfo();
		if(additional_principal==0){
	        while(remainingBalance>0&&month<=totalMonth){
	        	double beginningBalance = remainingBalance;
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
		}else{
			while(remainingBalance>0&&month<=totalMonth){
				double beginningBalance = remainingBalance;
	            double interestMonth = beginningBalance*rate;
	            if(month==start_month) monthPayment = monthPayment+additional_principal;
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
		}
		if(additional_principal!=0){
			double save = 0;
			PaymentInfo paymentInfo_new=new PaymentInfo();
			paymentInfo_new = new FixedrateCal().cal(principal, start_rate, year, 0, 0);
			double sum=0;
			double sum_new=0;
			for(Payment p:paymentInfo.getPayments()){
				sum = sum+p.getInterest();
			}
			for(Payment p:paymentInfo_new.getPayments()){
				sum_new=sum_new+p.getInterest();
			}
			save = sum_new-sum;
			paymentInfo.setSave(save);
		}
		return paymentInfo;
	}
}
