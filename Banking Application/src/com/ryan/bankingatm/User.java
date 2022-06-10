package com.ryan.bankingatm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

	private String firstName;
	private String lastName;
	private String uuid; //Universally Unique Identifier
	
	/**
	 * the MD5 hash of the user's pin number
	 */
	private byte pinHash[]; //use MD5 hash algorithm
	
	/**
	 * the list of accounts for this user
	 */
	private ArrayList<Account> accounts;
	
	/**
	 * Non-default constructor to create a new user
	 * @param fName		the user's first name
	 * @param lName		the user's last name
	 * @param pin		the user's account pin
	 * @param theBank	the bank object that the user is a customer of
	 */
	public User(String fName, String lName, String pin, Bank theBank) {
		
		this.firstName = fName;
		this.lastName = lName;
		
		//store the pin;s MD5 hash, rather than original value, security purposes
		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
			
		} catch (NoSuchAlgorithmException e) {
			
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		//get a new UUID for the user
		this.uuid = theBank.getNewUserUUID();
		
		//create empty list of accounts
		this.accounts = new ArrayList<Account>();
		
		//print log message
		System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);
		
	}
	
	/**
	 * Add an account to the list of accounts corresponding to a specific user
	 * @param acct		the account that is added
	 */
	public void addAccount(Account acct) {
		
		this.accounts.add(acct);
	}
	
	/**
	 * Print summaries for the accounts of a user
	 */
	public void printAccountsSummary() {
		
		System.out.printf("\n\n%s's accounts summary:\n", this.firstName);
		for(int idx = 0; idx < this.accounts.size(); idx++) {
			
			System.out.printf("%d) %s\n", idx+1, this.accounts.get(idx).getSummaryLine());
		}
		System.out.println();
	}
	
	/**
	 * Check whether a given pin matches the true user pin 
	 * @param aPin		the pin to check
	 * @return			whether the pin is valid or not
	 */
	public boolean validatePin(String aPin) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
			
		} catch (NoSuchAlgorithmException e) {
			
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		return false; //this is the merely appease java as it will work or throw exception
	}
	
	/**
	 * Print Transaction history for a particular account
	 * @param acctIdx		the index of the account to use
	 */
	public void printAcctTransactionHistory(int acctIdx) {
		
		this.accounts.get(acctIdx).printTransactionHistory();
	}
	
	public void addAccountTransaction(int acctIdx, double amount, String memo) {
		
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}
	
	//*******************************************
	//Getters and Setters
	//*******************************************
	
	/**
	 * Return the user's UUID
	 * @return		the uuid
	 */
	public String getUUID() {
		return this.uuid;
	}
	
	/**
	 * Return the user's first name
	 * @return		the first name
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Get the number of accounts of the user
	 * @return		the number of accounts
	 */
	public int getNumAccounts() {
		
		return this.accounts.size();
	}
	
	/**
	 * Get the balance of a particular account
	 * @param acctIdx		the index of the account to use
	 * @return				the balance of the account
	 */
	public double getAccountBalance(int acctIdx) {
		
		return this.accounts.get(acctIdx).getBalance();
	}
	
	/**
	 * Get the UUID of a particular account with index from arraylist of accounts
	 * @param acctIdx		the index of the account to use
	 * @return				the UUID of the account
	 */
	public String getAccountUUID(int acctIdx) {
		
		return this.accounts.get(acctIdx).getUUID();
	}
	
	
}//User Class
