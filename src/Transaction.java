import java.util.Date;

public class Transaction {
	private String type;
	private String date;
	private Account currentAccount;
	private double amount;
	private double oldBalance;
	private double newBalance;
	
	public Transaction(String type, Account currentAccount, double amount, double oldBalance, double newBalance) {
		setType(type);
		setDate();
		setCurrentAccount(currentAccount); 
		setAmount(amount);
		setOldBalance(oldBalance);
		setNewBalance(newBalance);
	}
	
	public void viewTransaction() {
		System.out.println("==========================================================");
		System.out.println("Confirmation");
		System.out.println("|  Transaction Date: " + getDate());
		System.out.println("|  Transaction Type: " + getType());
		System.out.println("|  " + getType() + " Amount: " + getAmount());
		System.out.println("|  Old Balance: " + getOldBalance());
		System.out.println("|  New Balance: " + getNewBalance());
		System.out.println("==========================================================");
	}
	
	public String printReciept() {
		String brand = "My ATM Reciept\n" + "================================================\n";
		String cardNumber = "Card Number: " + getCurrentAccount().getCardNumber() + "\n";
		String date = "Date: " + getDate() + "\n";
		String type = "Transaction Type: " + getType() + "\n\n";
		String amount = "Amount: " + getAmount() + "\n";
		String newBalance = "Available Balance: " + getNewBalance() + "\n" + "================================================\n";
		String footer = "Thank You";
		
		String reciept = brand + cardNumber + date + type + amount + newBalance + footer;
		return reciept;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate() {
		this.date = new Date().toString();
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(double oldBalance) {
		this.oldBalance = oldBalance;
	}

	public double getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}

	public Account getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(Account currentAccount) {
		this.currentAccount = currentAccount;
	}
	
	public Date getFullDate() {
		return new Date();
	}
}
