package ATM_Process;
/*
 * Project	:	ATM | Text Mode
 * Author	:	Sam An Mardy
 * 
 * */

public class Account {
	private int id;
	private String cardNumber;
	private String pinCode;
	private String type;
	private User user;
	private double balance;
	
	//open DB connection
	MySqlConn mySqlCon = new MySqlConn();
	
	public Account() {
		
	}
	
	public Account(int id, String cardNumber, String pinCode, String type, User user, double balance) {
		super();
		this.cardNumber = cardNumber;
		this.pinCode = pinCode;
		this.type = type;
		this.user = user;
		this.balance = balance;
	}

	public boolean checkBalance(double requestBalance) {
		if (getBalance() >= requestBalance && requestBalance <= 1000) {
			return true;
		}
		return false;
	}
	
	public boolean checkDeposit(double requestBalance) {
		if (requestBalance <= 1000) {
			return true;
		}
		return false;
	}
	
	public void changePinCode(String currentPinCode, String newPinCode, String confirmNewPinCode) {
		if (getPinCode().equals(currentPinCode)) {
			if (newPinCode.equals(confirmNewPinCode)) {
				setPinCode(confirmNewPinCode);
				System.out.println("Success: PIN Code changed.");
			}
			else {
				System.out.println("Failure: New PIN Code and Confirmed New PIN Code do not match.");
			}
		}
		else {
			System.out.println("Incorrect PIN code.");
		}
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getPinCode() {
		return pinCode;
	}
	
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
		mySqlCon.updateAccountBalance(getCardNumber(), balance);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
