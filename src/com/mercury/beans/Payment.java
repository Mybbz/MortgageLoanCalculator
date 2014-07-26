package com.mercury.beans;

public class Payment {
	private int month;
	private double beginningBalance;
	double monthPayment;
	double interest;
	double principal;
	double endingBalance;
	public Payment(){}
	public Payment(int month,double beginningBalance,double monthPayment,double interest,double principal,double endingBalance){
		this.month=month;
		this.beginningBalance=beginningBalance;
		this.monthPayment=monthPayment;
		this.interest=interest;
		this.principal=principal;
		this.endingBalance=endingBalance;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getBeginningBalance() {
		return beginningBalance;
	}
	public void setBeginningBalance(double beginningBalance) {
		this.beginningBalance = beginningBalance;
	}
	public double getMonthPayment() {
		return monthPayment;
	}
	public void setMonthPayment(double monthPayment) {
		this.monthPayment = monthPayment;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public double getEndingBalance() {
		return endingBalance;
	}
	public void setEndingBalance(double endingBalance) {
		this.endingBalance = endingBalance;
	}
	

}
