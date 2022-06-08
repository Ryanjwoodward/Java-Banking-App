package com.ryan.bankingapplication;

import java.util.Scanner;

public class BankingApplication {

	public static void main(String[] args) {
		
		BankAccount acct = new BankAccount("Ryan", "11711");
		
		acct.showMenu();
		
	}// main

}//BankingApplication Class


class BankAccount{
	
	int balance;
	int previousTransaction;
	String customerName;
	String customerId;
	
	public BankAccount(String custName, String custId) {
		
		this.customerName = custName;
		this.customerId = custId;
	}
	
	void deposit(int amount) {
		
		if(amount > 0) {
			
			this.balance += amount;
			this.previousTransaction = amount;
			System.out.println(" The amount added to your bank account was: " + amount +"\n\n");
		}
	}
	
	void withdraw(int amount) {
		
		if(amount > balance) {
			
			System.out.println("ERROR: The amount you are attempting to withdraw is greater than your current balance of: " + this.balance + "\n");
		}else if(amount <= 0) {
			
			System.out.println("ERROR: an amount of 0 cannot be withdrawn\n");
		}else {
			
			this.balance -= amount;
			this.previousTransaction = -amount;
			System.out.println(" The amount removed from your bank account was: " + amount +"\n\n");
		}
	}
	
	void getPreviousTransaction() {
		
		if(this.previousTransaction > 0) {
			
			System.out.println("Deposited: " + this.previousTransaction + "\n");
		}else if(this.previousTransaction < 0) {
			
			System.out.println("Withdrew: " + Math.abs(this.previousTransaction)+ "\n");
		}else {
			
			System.out.println("No Transaction Occured \n");
		}
	}
	
	void showMenu() {
		
		char option = '\0';
		int userValue = 0;
		Scanner scan = new Scanner(System.in);
		
		do {
			
			System.out.println("\t   Welcome to Acerbus Bank\n\t--------------------------------");
			System.out.print("\t Please select an option: \n\t\t A: Make a withdrawal \n\t\t B: Make a deposit \n\t\t C: Check Balance \n\t\t D: Previous Transaction"
					+ "\n\t\t E: Exit Program \n\t\t>>");
			
			option = scan.next().charAt(0);
			
			switch(option) {
			
			case 'A'://withdraw
				
				System.out.print("\nEnter the amount you would like to withdraw: ");
				userValue = scan.nextInt();
				
				this.withdraw(userValue);
				break;
				
			case 'B'://deposit
				
				System.out.print("\nEnter the amount you will be depositing: ");
				userValue = scan.nextInt();
				
				this.deposit(userValue);
				break;
				
			case 'C'://balance
				
				System.out.print("\nYour current balance: " + balance +"\n\n");
				break;
				
			case 'D'://previous transaction
				
				this.getPreviousTransaction();
				break;
				
			case 'E'://exit program
				System.out.print("\nExiting Program: Auf Wiedersehen! \n\n");
				break;
			}
			
		}while(option != 'E' || option != 'e');
	}
	
	
	
}//BankAccount Class