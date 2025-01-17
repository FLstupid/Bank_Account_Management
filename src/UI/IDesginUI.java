package UI;

import model.Account;
import model.Customer;

public interface IDesginUI {
	
	public void initUI();
	
	public void initLoginUI();
	
	public void initSignupUI();
	
	public void initMainUI(Account account,Customer customer);
	
	public void initTransactionHistoryUI(Account account, Customer customer);
	
	public void initTransferUI(Account account, Customer customer);
	
	public void initCheckBalanceUI(Account account,Customer customer);
	
	public void initCheckInformationUI(Account account, Customer customer);
	
}
