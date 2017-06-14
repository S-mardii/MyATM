package ATM_Process;
/*
 * Project	:	ATM | Text Mode
 * Author	:	Sam An Mardy
 * 
 * */

import java.sql.*;
import java.util.Scanner;

public class ATM_Machine {
	public static void main(String[] args) {
		ATM atm = new ATM();
		Scanner sc = new Scanner(System.in);
		
		short choice = 0;
		String getReciept;
		
		//Account Attribute
		int accountId;
		String cardNumber, pinCode, type;
		double accountBalance;
		
		//User Attribute
		String firstName, lastName, gender;
		int userId, age;
		
		//Open DB connection to the project
		MySqlConn mySqlCon = new MySqlConn();
		ResultSet accountDB = (ResultSet) mySqlCon.fetchAccountData();
		
		//retrieve Account Value from DB
		try {
			while (accountDB.next()) {
				accountId = accountDB.getInt("id");
				cardNumber = accountDB.getString("cardNumber");
				pinCode = accountDB.getString("pinCode");
				type = accountDB.getString("type");
				accountBalance = accountDB.getDouble("balance");
				
				userId = accountDB.getInt("userId");
				firstName = accountDB.getString("firstName");
				lastName = accountDB.getString("lastName");
				gender = accountDB.getString("gender");
				age = accountDB.getInt("age");
				
				User user = new User(userId, firstName, lastName, gender, age);
				Account account = new Account(accountId, cardNumber, pinCode, type, user, accountBalance);
				atm.accountList.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Account currentAccount = new Account();
		
		do {
			//ATM Login
			
			/* Accounts for Testing: 
			 * CardNumber = aaa
			 * PIN = 123
			 * 
			 * CardNumber = bbb
			 * PIN = 123
			 * */
			
			System.out.println("*** Log In ***");
			System.out.println("-----------------------------------------------------------------------------------");
		
			System.out.print("Enter your Card Number: ");
			cardNumber = sc.next();
			System.out.print("Enter your PIN Code: ");
			pinCode = sc.next();
		
			System.out.println();
			currentAccount = atm.login(cardNumber, pinCode);
			
			//Check if authentication is correct
			while (currentAccount != null) {
				//ATM Menu
				System.out.println();
				System.out.println("----------------------------------------------------------------------------------");
				System.out.println("Menu: ");
				System.out.println("----------------------------------------------------------------------------------");
				System.out.println("1. Check Current Balance.");
				System.out.println("2. Withdraw Money");
				System.out.println("3. Deposit Money");
				System.out.println("4. Transfer Money");
				System.out.println("5. Change Password");
				System.out.println("6. Logout");
				System.out.println("7. Close Program");
				System.out.println("----------------------------------------------------------------------------------");
				System.out.println();
				System.out.println("What do you want to do?");
				System.out.print("Enter a Choice: ");
				choice = sc.nextShort();
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println();
				
				switch (choice) {
				
				case 1: //check balance
					System.out.println("*** Check Balance ***");
					System.out.println("============================================================================");
					System.out.println("Your Balance: " + currentAccount.getBalance());
					pressAnyKeyToContinue();
					break;
				
				case 2: //withdraw money
					System.out.println("*** Withdraw ***");
					System.out.println("============================================================================");
					System.out.print("Insert Amount to Withdraw: ");
					double cashWithdraw = sc.nextDouble();
					System.out.println("Do you want to print the reciept? (Y/N): ");
					getReciept = sc.next();
					
					atm.withdraw(currentAccount, cashWithdraw, getReciept);
					pressAnyKeyToContinue();
					break;
				case 3: //deposit money
					System.out.println("*** Deposit ***");
					System.out.println("============================================================================");
					System.out.print("Enter Amount to Deposit: ");
					double cashDeposit = sc.nextDouble();
					System.out.println("Do you want to print the reciept? (Y/N): ");
					getReciept = sc.next();
					
					atm.deposit(currentAccount, cashDeposit, getReciept);
					pressAnyKeyToContinue();
					break;
				case 4:
					System.out.println("*** Transfer ***");
					System.out.println("============================================================================");
					System.out.print("Enter the Card Number of the Reciever: ");
					String recieverCardNumber = sc.next();
					System.out.print("Enter the Amount to Transfer: ");
					double amount = sc.nextDouble();
					System.out.println("Do you want to print the reciept? (Y/N): ");
					getReciept = sc.next();
					
					atm.transfer(currentAccount, recieverCardNumber, amount, getReciept);
					pressAnyKeyToContinue();
					break;
					
				case 5: //Change PIN code
					System.out.println("*** Change PIN Code ***");
					System.out.print("Enter Current PIN Code: ");
					String currentPinCode = sc.next();
					System.out.print("Enter New PIN Code: ");
					String newPinCode = sc.next();
					System.out.print("Re-enter New PIN Code: ");
					String confirmNewPinCode = sc.next();
					
					currentAccount.changePinCode(currentPinCode, newPinCode, confirmNewPinCode);
					pressAnyKeyToContinue();
					break;
				case 6: //logout
					System.out.println("Are you sure? (Y/N)");
					String option = sc.next();
					if (option.toUpperCase().equals("Y")) {
						System.out.println("Good Bye! See You Next Time!");
						pressAnyKeyToContinue();
						//ATM Login
						System.out.println("*** Login ***");
						System.out.println("-----------------------------------------------------------------------------------");
						
						System.out.print("Enter your Card Number: ");
						cardNumber = sc.next();
						System.out.print("Enter your PIN Code: ");
						pinCode = sc.next();
						
						currentAccount = atm.login(cardNumber, pinCode);
						System.out.println("----------------------------------------------------------------------------------");
					} 
					break;
					
				case 7: //close program
					System.out.println("Are you sure? (Y/N)");
					option = sc.next();
					if (option.toUpperCase().equals("Y")) {
						System.out.println("Thank you for choosing our service.");
						pressAnyKeyToContinue();
						System.exit(0);
					}
				}
			}
		} while(currentAccount == null);
		sc.close();
	}
	
	//Press Enter Key to Continue
	public static void pressAnyKeyToContinue() { 
		System.out.println();
		System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        }  
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
