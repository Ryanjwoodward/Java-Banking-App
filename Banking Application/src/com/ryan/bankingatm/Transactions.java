package com.ryan.bankingatm;

import java.util.Date;

public class Transactions {

	private double amount;
	private Date timestamp;//time and date of the transaction
	private String memo; //(optional) could be a statement
	private Account inAccount; //the account the transactions occur in
	
	/**
	 * Create a new transaction
	 * @param amount		the amount transacted
	 * @param inAccount		the account the transaction belongs to
	 */
	public Transactions(double amount, Account inAccount) {
		this.amount = amount;
		this.inAccount = inAccount;
		this.timestamp = new Date();
	}
	
	/**
	 * Create a new Transaction
	 * @param amount		the amount transacted
	 * @param memo			the memo for the transaction
	 * @param inAccount		the account the transaction belongs to
	 */
	public Transactions(double amount, String memo, Account inAccount) {
		//call two-arg constrc
		this(amount, inAccount);
		
		this.memo = memo;
	}
	
	/**
	 * NOTICE: not a getter because summary line is not a class attribute
	 * Get a string summarizing the transaction
	 * @return		the summary String
	 */
	public String getSummaryLine() {
		
		if(this.amount >= 0) {
			
			return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
		}else {
			
			return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), this.amount, this.memo);

		}
	}
	
	//*********************************
	//Getters and Setters
	//*********************************
	
	/**
	 * Get the amount of the transaction
	 * @return	the amount
	 */
	public double getAmount() {
		return this.amount;
	}
	
	
}//Transaction Class
