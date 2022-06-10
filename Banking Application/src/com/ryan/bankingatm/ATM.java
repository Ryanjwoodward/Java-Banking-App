package com.ryan.bankingatm;

import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		
		//intialize the Scanner
		Scanner scan = new Scanner(System.in);
		
		//initialize Bank
		Bank theBank = new Bank("Acerbus Bank");
		
		//add a user, which also creates a savings account
		User aUser = theBank.addUser("Ryan", "Woodawrd", "1234");
		
		//add a checking account for the created user
		Account newAccount = new Account("Checking", aUser, theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);
		
		User curUser;
		while(true) {
			
			//stay in the login prompt until successful login
			curUser = ATM.mainMenuPrompt(theBank, scan);
			
			//stay in the main menu until user quits
			ATM.printUserMenu(curUser, scan);
		}
		
	}//main method
	
	/**
	 * Print the ATM's login menu
	 * @param theBank		the Bank object whose accounts to use
	 * @param scan			the Scanner object to use for user input
	 * @return				the authenticated user object
	 */
	public static User mainMenuPrompt(Bank theBank, Scanner scan) {
		
		String userID;
		String pin;
		User authUser;
		
		//prompt the user for their id/pin combination
		do {
			System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
			System.out.print("Enter User ID: ");
			userID = scan.nextLine();
			System.out.print("Enter pin: ");
			pin = scan.nextLine();
			
			//try to get the user object that corresponds to the id/pin combo
			authUser = theBank.userLogin(userID, pin);
			
			if(authUser == null) {
				System.out.println("Incorrect user ID/Pin combination. " + "Please Try again.");
			}
			
		}while(authUser == null);//this will continue to loop until there is a succesful login
		
		return authUser;
	}//mainMenuPrompt
	
	
	/**
	 * 
	 * @param theUser
	 * @param scan
	 */
	public static void printUserMenu(User theUser, Scanner scan) {
		
		//print a summary of the user's account
		theUser.printAccountsSummary();
		
		int choice;
		
		//user menu
		do {
			System.out.printf("Welcome %s, what would you like to do?", theUser.getFirstName());
			System.out.println("  1) Display Transaction history");
			System.out.println("  2) Make a Withdrawal");
			System.out.println("  3) Make a Deposit");
			System.out.println("  4) Transfer Funds");
			System.out.println("  5) Quit the Program");
			System.out.println();
			System.out.println("Enter choice: ");
			
			choice = scan.nextInt();
			
			if(choice <1 || choice > 5) {
				System.out.println("Invalid choice. Please choose 1-5");
			}
			
		}while(choice < 1 || choice > 5);
		
		//process the user's choice
		switch(choice) {
		case 1://display the user's transaction history
			
			ATM.showTransactionHistory(theUser, scan);
			break;
		case 2: //guide the user to make a withdrawal
			
			ATM.withdrawFunds(theUser, scan);
			break;
			
		case 3: //guide the user to make a deposit
			
			ATM.depositFunds(theUser, scan);
			break;
			
		case 4: //guide the user to transfer funds between accounts
			
			ATM.transferFunds(theUser, scan);
			break;
			
		case 5: 
			
			break;
		}
		//redisplay this menu unless the user wants to quit
		if(choice != 5) {
			ATM.printUserMenu(theUser, scan);
		}
	}
	
	/**
	 * Show the transaction history for an account
	 * @param theUser		the logged-in user
	 * @param sc			the Scanner object used for user input
	 */
	public static void showTransactionHistory(User theUser, Scanner sc) {
		
		int theAcct;
		
		//get account whose transaction history to look at
		do {
			
			System.out.printf("Enter the number (1-%d) of the account whose transaction you want to see: ", 
					theUser.getNumAccounts());
			theAcct = sc.nextInt()-1;
			
			if(theAcct < 0 || theAcct >= theUser.getNumAccounts()) {
				
				System.out.println("Invalid account, please try again");
			}
		}while(theAcct < 0 || theAcct >= theUser.getNumAccounts());
		
		//print transaction history
		theUser.printAcctTransactionHistory(theAcct);
	}
	
	public static void transferFunds(User theUser, Scanner scan) {
		
		//inits
		int fromAcct;
		int toAcct;
		double amount;
		double acctBalance;
		
		//get the account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the account to transfer from: ", fromAcct = scan.nextInt()-1);
			
			if(fromAcct < 0 || fromAcct >= theUser.getNumAccounts()) {
				System.out.println("Invalid account, please try again.");
			}
		}while(fromAcct < 0 || fromAcct >= theUser.getNumAccounts());
		
		acctBalance = theUser.getAccountBalance(fromAcct);
	}
	
}//ATM Class
