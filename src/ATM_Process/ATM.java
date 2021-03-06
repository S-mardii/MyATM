package ATM_Process;
/*
 * Project	:	ATM | Text Mode
 * Author	:	Sam An Mardy
 * 
 * */

import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ATM {
	ArrayList<Account> accountList = new ArrayList<Account>();
	ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	
	Account account;
	Date date;
	
	//login
	public Account login(String cardNumber, String pinCode) {
		for (int i=0; i<accountList.size(); i++) {
			account = accountList.get(i);
			if (account.getCardNumber().equals(cardNumber) && account.getPinCode().equals(pinCode)) {
				System.out.println("Welcome to ATM");
				return account;
			}
			else {
				account = null;
			}
		}
		return account;
	}
	
	//Withdraw Money
	public void withdraw(Account account, double cashWithdraw, String getReciept) {
		if ( cashWithdraw > 0) {
			if (account.checkBalance(cashWithdraw)) {
				double oldBalance = account.getBalance();
				double newBalance = oldBalance - cashWithdraw;
				account.setBalance(newBalance);
				Transaction transaction = new Transaction("Withdraw", account, cashWithdraw, oldBalance, newBalance);
				transaction.viewTransaction();
				transactionList.add(transaction);
				
				if (getReciept.toUpperCase().equals("Y"))
					printReciept(transaction);
			}
			else {
				System.out.println("Sorry! Insufficent Balance for this Transaction.");
			}
		}
		else {
			System.out.println("Invalid Input Data");
		}
	}
	
	//Deposit Money
	public void deposit(Account account, double cashDeposit, String getReciept) {
		if (cashDeposit > 0) {
			if (account.checkDeposit(cashDeposit)) {
				double oldBalance = account.getBalance();
				double newBalance = oldBalance + cashDeposit;
				account.setBalance(newBalance);
				Transaction transaction = new Transaction("Deposit", account, cashDeposit, oldBalance, newBalance);
				transaction.viewTransaction();
				transactionList.add(transaction);
				printReciept(transaction);
				
				if (getReciept.toUpperCase().equals("Y"))
					printReciept(transaction);
			}
			else {
				System.out.println("Sorry! Insufficent Balance for this Transaction.");
			}
		}
		else {
			System.out.println("Invalid Input Data");
		}
	}
	
	//Transfer Money to another Account
	public void transfer(Account sender, String recieverCardNumber, double amount, String getReciept) {
		if (amount > 0) {
			if (sender.checkBalance(amount)) {
				double senderOldBalance = sender.getBalance();
				double senderNewBalance = senderOldBalance - amount;
				sender.setBalance(senderNewBalance);
				
				Account reciever = findAccount(recieverCardNumber);
				if (reciever != null) {
					double recieverBalance = reciever.getBalance() + amount;
					reciever.setBalance(recieverBalance);
					Transaction transaction = new TransferTransaction("Transfer", sender, reciever, amount, senderOldBalance, senderNewBalance);
					transaction.viewTransaction();
					transactionList.add(transaction);
					printReciept(transaction);
					
					if (getReciept.toUpperCase().equals("Y"))
						printReciept(transaction);
				}
				else {
					System.out.println("Wrong Card Number: Destination is not Found!");
				}
			}
			else {
				System.out.println("Sorry! Insufficent Balance for this Transaction.");
			}
		}
		else {
			System.out.println("Invalid Input Data");
		}
	}
	
	
	//Find if an account exist in the System
	public Account findAccount(String cardNumber) {
		for (int i=0; i<accountList.size(); i++) {
			Account account = accountList.get(i);
			if (account.getCardNumber().equals(cardNumber)) {
				return account;
			}
		}
		return null;
	}
	
	//Print receipt of each transaction to file
	public void printReciept(Transaction transaction) {
		String pattern = "yyyyMMddHHmmss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String date = dateFormat.format(transaction.getFullDate());
		File file = new File("./reciept/" + date + transaction.getType() + ".txt");
		String reciept = transaction.printReciept();
		
		try {
			FileWriter fileWriter = new FileWriter(file, false);
			fileWriter.write(reciept);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
