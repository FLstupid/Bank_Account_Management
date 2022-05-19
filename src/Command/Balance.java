package Command;

import model.Account;

public class Balance {

	private Account account;
	private boolean on;
	
	public Balance() {
		
	}
	
	public Balance(Account account) {
		this.account=account;
	}
	
	public void switchOn() {
		on = true;
		System.out.println("So tai khoan: " + this.getAccount().getNumberAccount());
		System.out.println("Chu tai khoan: " + this.getAccount().getCustomer().getNameCustomer());
		System.out.println("So du: " + this.getAccount().BalanceFormatVND()+"\n");
	}
	
	public void switchOff() {
		on=false;
		System.out.println("So tai khoan: " + this.getAccount().getNumberAccount());
		System.out.println("Chu tai khoan: " + this.getAccount().getCustomer().getNameCustomer());
		System.out.println("So du: ********\n");
	}
	
	public Account getAccount() {
		return account;
	}
	
	public boolean getOn() {
		return on;
	}
}
