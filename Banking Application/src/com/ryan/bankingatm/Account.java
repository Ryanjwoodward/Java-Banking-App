package com.ryan.bankingatm;

import java.util.ArrayList;

public class Account {

	private String name; //checking or savings etc.
	private String uuid; //different from a User's uuid
	private User acctHolder;
	
	/**
	 * 
	 */
	private ArrayList<Transactions> transactions;
	
	/**
	 * Create a new Account object
	 * @param name		the name of the account
	 * @param holder	the User object that holds this account
	 * @param theBank	the bank that issues the account
	 */
	public Account(String name, User holder, Bank theBank) {
		
		this.name = name;
		this.acctHolder = holder;
		
		//get new account UUID
		this.uuid = theBank.getNewAccountUUID();
		
		//init transactions
		this.transactions = new ArrayList<Transactions>();
		
		/*//add account to holder and bank lists
		//below objects will be the stored as address to same object in memory
		holder.addAccount(this);
		theBank.addAccount(this);
		*/
	}
	
	/**
	 * Get the summary line for the account (UUID, balance, account name)
	 * @return		the string summary of the account
	 */
	public String getSummaryLine() {
		
		//get the account balance
		double balance = this. getBalance();
		
		//format the summary line, depending on whether the balance is negative
		if(balance >= 0) {
			
			return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
		}else {
			
			return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
		}
	}
	
	/**
	 * Print the Transaction history of the account
	 */
	public void printTransactionHistory() {
		
		System.out.printf("\nTransaction history for account %s\n", this.uuid);
		
		for(int t = this.transactions.size()-1; t >= 0; t--) {
			System.out.printf(this.transactions.get(t).getSummaryLine());
		}
		
		System.out.println();
	}
	
	/**
	 * Add a new Transaction in this account 
	 * @param amount		the amount transacted
	 * @param memo			the transaction memo
	 */
	public void addTransaction(double amount, String memo) {
		
		//create a new Transaction obj and add to list
		Transactions newTransaction = new Transactions(amount, memo, this);
		
		this.transactions.add(newTransaction);
	}
	
	
	//***********************************
	//Getters and Setters
	//***********************************
	
	/**
	 * Get the accounts ID
	 * @return the accounts uuid
	 */
	public String getUUID() {
		return this.uuid;
	}
	
	/**
	 * Get the balance of an account by adding the amounts of all transactions
	 * NOTICE: there is no balance class attribute
	 * @return	the balance value
	 */
	public double getBalance() {
		
		double balance = 0;
		for(Transactions t: this.transactions) {
			
			balance += t.getAmount();
		}
		return balance;
	}
	
	
}//Account Class
