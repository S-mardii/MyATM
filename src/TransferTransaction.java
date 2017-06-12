
public class TransferTransaction extends Transaction {
	private Account reciever;
	
	public TransferTransaction(String type, Account currentAccount, Account reciever, double amount, double oldBalance, double newBalance) {
		super(type, currentAccount, amount, oldBalance, newBalance);
		setReciever(reciever);
	}
	
	public void viewTransaction() {
		System.out.println("==========================================================");
		System.out.println("Transaction Date: " + getDate());
		System.out.println("Transaction Type: " + getType());
		System.out.println("Sender Card Number: " + getCurrentAccount().getCardNumber());
		System.out.println("Reciever Card Number: " + getReciever().getCardNumber());
		System.out.println("Tranfer Amount: " + getAmount());
		System.out.println("Old Balance: " + getOldBalance());
		System.out.println("New Balance: " + getNewBalance());
		System.out.println("==========================================================");
	}
	
	public String printReciept() {
		String brand = "My ATM Reciept\n" + "================================================\n";
		String cardNumber = "Card Number: " + getCurrentAccount().getCardNumber() + "\n";
		String date = "Date: " + getDate() + "\n";
		String type = "Transaction Type: " + getType() + "\n\n";
		String destination = "Sent to: " + getReciever().getCardNumber();
		String amount = "Amount: " + getAmount() + "\n";
		String newBalance = "Available Balance: " + getNewBalance() + "\n" + "================================================\n";
		String footer = "Thank You";
		
		String reciept = brand + cardNumber + date + type + destination + amount + newBalance + footer;
		return reciept;
	}

	public Account getReciever() {
		return reciever;
	}

	public void setReciever(Account reciever) {
		this.reciever = reciever;
	}
}
