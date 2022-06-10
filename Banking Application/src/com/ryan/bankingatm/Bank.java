package com.ryan.bankingatm;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

	private String name;
	private ArrayList<User> customers;
	private ArrayList<Account> accounts;
	
	/**
	 * Create a new Bank Object with empty list of users and accounts
	 * @param name, the name of the bank
	 */
	public Bank(String name) {
		
		this.name = name;
		this.customers = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}
	
	/**
	 * Creates a new UUID for a user
	 * @return uuid (String)
	 */
	public String getNewUserUUID() {
		
		String uuid;
		Random rng = new Random();
		int length = 6;
		boolean nonUnique = false;
		
		//the loop will proceed until a unique UUID is generated
		do {
			
			//generate number
			uuid = "";
			
			for(int c = 0; c < length; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString();
			}
			
			//check the list of user to see if the generated UUID is unique
			for(User u: this.customers) {
				
				if(uuid.compareTo(u.getUUID()) == 0) {
					
					nonUnique = true;
					break;
				}
			}
			
		}while(nonUnique);
		
		return uuid;
	}
	
	/**
	 * Creates a new UUID for accounts
	 * @return uuid (String)
	 */
	public String getNewAccountUUID() {
		
		String uuid;
		Random rng = new Random();
		int length = 10;
		boolean nonUnique = false;
		
		//the loop will proceed until a unique UUID is generated
		do {
			
			//generate number
			uuid = "";
			
			for(int c = 0; c < length; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString();
			}
			
			//check the list of user to see if the generated UUID is unique
			for(Account a: this.accounts) {
				
				if(uuid.compareTo(a.getUUID()) == 0) {
					
					nonUnique = true;
					break;
				}
			}
			
		}while(nonUnique);
		
		return uuid;
	}
	
	/**
	 * Add an account
	 * @param acct   the account to add
	 */
	public void addAccount(Account acct) {
		
		this.accounts.add(acct);
	}
	
	/**
	 * Create a new user of the bank
	 * @param firstName  user's first name
	 * @param lastName  user's last name
	 * @param pin  t	user's pin
	 * @return			the new user object
	 */
	public User addUser(String firstName, String lastName, String pin) {
		
		//create a new user object and add it to our list
		User newUser = new User(firstName, lastName, pin, this);
		this.customers.add(newUser);
		
		//create a savings account
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		
		return newUser;
	}
	
	/**
	 * Get the user object associated with a particular userID and pin if they are valid
	 * @param userID   the UUID of the user to login
	 * @param pin		the pin of the user
	 * @return			the user object if the login is successful, of null if it not
	 */
	public User userLogin(String userID, String pin) {
		
		//search through the list of users
		for(User u: this.customers) {
			
			//check if user Id is correct
			if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		//if we did not find the user of the pin was incorrect
		return null;
	}
	
	//***********************************
	//Getters and Setters
	//***********************************
	
	/**
	 * Get the name of the bank
	 * @return		the name of the bank
	 */
	public String getName() {
		return this.name;
	}
	
	
}//Bank Class
