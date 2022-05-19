package Test;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.AccountDAO;
import DAO.CustomerDAO;
import DAO.LoginDAO;
import UI.DesignUI;
import model.Account;
import model.Customer;
import model.Gender;
import model.Login;

public class Client {
	public static void main(String[] args) throws ParseException {
		CustomerDAO customerDAO = new CustomerDAO();
		LoginDAO loginDAO = new LoginDAO();
		AccountDAO accountDAO = new AccountDAO();
//		Customer customer = new Customer.CustomerBuilder("Danh", "0902891404", "079201014939").build();
//		
//		customerDAO.save(customer);
		
//		Customer danhCustomer  =customerDAO.get(1);
//		if(danhCustomer!=null)
//			System.out.println(danhCustomer.toString());
//		danhCustomer.setEmailCustomer("congdanh.01.01.2001@gmail.com");
//		danhCustomer.setAddressCustomer("");
//		danhCustomer.setGenderCustomer(Gender.FEMALE);
//		danhCustomer.setDobCustomer(new Date("12/01/2001"));
//		customerDAO.update(danhCustomer);
		
//		customerDAO.delete(danhCustomer.getIdCustomer());
		
//		List<Customer> listCustomers = new ArrayList<>();
//		listCustomers = customerDAO.getAll();
//		if(listCustomers!=null)
//			for (Customer customer : listCustomers) {
//				System.out.println(customer.toString());
//			}
		
//		Customer danh = new Customer.CustomerBuilder("Hello", "123456789", "0987654321").build();
//		Login danhLogin = new Login("danh", "danh", danh);
//		
//		customerDAO.save(danh);
//		loginDAO.save(danhLogin);
		
//		Customer abcCustomer = new Customer.CustomerBuilder("Danh", "0902891404", "079201014939").build();
//		Login login = new Login("admin", "admin", abcCustomer);
//		customerDAO.save(abcCustomer);	
//		loginDAO.save(login);
		
		
//		Account account1 = new Account(1000000, 123, danhCustomer);
//		Account account2 = new Account(500000, 4567, danhCustomer);
//		
//		accountDAO.save(account1);
//		accountDAO.save(account2);
//		
//		
//		Account a = new Account();
//		a = accountDAO.getAccountByNumberAccount(123);
//		
//		if(a!=null)
//			System.out.println(a.toString());
		
		DesignUI ui = new DesignUI();
		ui.initUI();

	}
	
	
}
