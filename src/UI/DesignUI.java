package UI;

import java.io.Console;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

import Command.Balance;
import Command.Command;
import Command.RemoteControl;
import Command.ShowBalanceOff;
import Command.ShowBalanceOn;
import DAO.AccountDAO;
import DAO.CustomerDAO;
import DAO.LoginDAO;
import DAO.TransactionDAO;
import DAO.TransactionDetailDAO;
import Generate.RandomGenerate;
import Strategy.Calculator;
import Strategy.Router;
import Strategy.SubNumber;
import Strategy.SumNumber;
import model.Account;
import model.Customer;
import model.Gender;
import model.Login;
import model.Transaction;
import model.TransactionDetail;
import validate.CheckValidate;

public class DesignUI implements IDesginUI {

	@Override
	public void initUI() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Vui long lua chon");
		System.out.println("1. Dang nhap");
		System.out.println("2. Dang ky");
		System.out.println("3. Thoat");
		int options = scanner.nextInt();

		while (options < 0 || options > 3) {

			System.out.println("Vui long chon lai!");
			options = scanner.nextInt();

		}
		if (options == 1)
			initLoginUI();
		if (options == 2)
			initSignupUI();
		if (options == 3)
			System.exit(0);

	}

	@Override
	public void initLoginUI() {
		// TODO Auto-generated method stub
//		System.out.println("\n");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Username: ");
		String username = scanner.nextLine();
//		System.out.println("\n");
		System.out.print("Password: ");
		String password = scanner.nextLine();
//		System.out.println("\n");

		LoginDAO dao = new LoginDAO();

//		while(dao.checkLoginSuccess(new Login(username,password)) == false) {
//			System.out.println("Username hoac Password khong dung, vui long nhap lai\n");
//			System.out.print("Username: ");
//			username = scanner.nextLine();
//			System.out.println("\n");
//			System.out.print("Password: ");
//			password = scanner.nextLine();
//			System.out.println("\n");
//		}

		if (dao.checkLoginSuccess(new Login(username, password)) == null) {
			System.out.println("Username hoac Password khong dung, vui long nhap lai");
			initLoginUI();
		} else {
			CustomerDAO customerDAO = new CustomerDAO();
			int cid = dao.checkLoginSuccess(new Login(username, password)).getCustomer().getIdCustomer();
			Customer customer = customerDAO.get(cid);
			initListAccountUI(customer);
		}

	}

	@Override
	public void initSignupUI() {
		// TODO Auto-generated method stub
//		System.out.println("Sign up");
		CustomerDAO customerDAO = new CustomerDAO();
		LoginDAO loginDAO = new LoginDAO();
		AccountDAO accountDAO = new AccountDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ho va ten: ");
		String name = scanner.nextLine();

		System.out.println("So dien thoai: ");
		String phone = scanner.nextLine();

		while (customerDAO.checkCustomerExistByPhoneNumber(phone) || new CheckValidate().CheckNumber(phone) == false) {
			System.out.print("So dien thoai khong hop le, vui long nhap lai!");
			System.out.print("So dien thoai: ");
			phone = scanner.nextLine();
		}

		System.out.print("CMND: ");
		String cmnd = scanner.nextLine();

		while (customerDAO.checkCustomerExistByCMND(cmnd) || new CheckValidate().CheckNumber(cmnd) == false) {
			System.out.println("CMND khong hop le, vui long nhap lai!");
			System.out.print("CMND: ");
			cmnd = scanner.nextLine();
		}

		System.out.print("Dia chi (khong bat buoc): ");
		String address = scanner.nextLine();

		System.out.print("Gioi tinh (khong bat buoc): ");
		String gioitinh = scanner.nextLine();
		Gender gender = new CheckValidate().CheckGender(gioitinh);

		System.out.print("Ngay sinh (khong bat buoc) (Vi du: 01/01/2001, mm/dd/yyyy):  ");
		String ngaysinh = scanner.nextLine();
		Date dob = new Date();
		if (!ngaysinh.isEmpty())
			dob = new Date(ngaysinh);

		System.out.print("Email (khong bat buoc): ");
		String email = scanner.nextLine();
		if (!email.isEmpty()) {
			while (customerDAO.checkCustomerExistByEmail(email) || new CheckValidate().CheckEmail(email) == false) {
				System.out.println("Email khong hop le, vui long nhap lai");

				System.out.println("Ban co muon tiep tuc nhap email khong?");
				System.out.println("1. Tiep tuc");
				System.out.println("2. Dung lai");
				int options = scanner.nextInt();
				if (options == 1) {
					System.out.print("Email (khong bat buoc): ");
					email = scanner.nextLine();
				}
				if (options == 2) {
					email = "";
					break;
				}

			}
		}

		Customer customer = new Customer.CustomerBuilder(name, phone, cmnd).setAddress(address).setDob(dob)
				.setEmail(email).setGender(gender).build();
		Login login = new Login(phone, new RandomGenerate().RandomGeneratePassword(), customer);
		long number = new RandomGenerate().RandomGenerateLongNumber();
		while (accountDAO.checkAccountExist(number))
			number = new RandomGenerate().RandomGenerateLongNumber();
		Account account = new Account(0, number, customer);

		customerDAO.save(customer);
		loginDAO.save(login);
		accountDAO.save(account);
		initUI();

	}

	public void initListAccountUI(Customer customer) {
		Scanner scanner = new Scanner(System.in);
		LoginDAO loginDAO = new LoginDAO();
		CustomerDAO customerDAO = new CustomerDAO();
		AccountDAO accountDAO = new AccountDAO();
		Account account = new Account();

		List<Account> listAccounts = new ArrayList<>();
		listAccounts = accountDAO.getAllByCustomer(customer);

		System.out.println("Vui long chon tai khoan");
		int i = 1;
		for (Account account1 : listAccounts) {
			System.out.println(i + ". " + account1.toString());
			i++;
		}
		int options = scanner.nextInt();
		while (options < 0 || options > listAccounts.size()) {
			System.out.println("Lua chon khong phu hop, vui long chon lai");
			options = scanner.nextInt();
		}

		for (int x = 0; x < listAccounts.size(); x++) {
			if (x == options - 1) {
				account = listAccounts.get(x);
				break;
			}
		}

		initMainUI(account, customer);
	}

	@Override
	public void initMainUI(Account account, Customer customer) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. Xem so du tai khoan");
		System.out.println("2. Xem lich su giao dich");
		System.out.println("3. Chuyen tien");
		System.out.println("4. Xem thong tin ca nhan");
		System.out.println("5. Them moi tai khoan");
		System.out.println("6. Nap tien vao tai khoan");
		System.out.println("7. Rut tien khoi tai khoan");
		System.out.println("8. Chon tai khoan khac");
		System.out.println("9. Dang xuat");
		System.out.println("10. Thoat");

		int options = scanner.nextInt();

		while (options < 0 || options > 10) {
			System.out.println("Lua chon khong phu hop, vui long chon lai");
			options = scanner.nextInt();
		}

		switch (options) {
		case 1: {
			initCheckBalanceUI(account, customer);
			break;
		}
		case 2: {
			initTransactionHistoryUI(account, customer);
			break;
		}
		case 3: {
			initTransferUI(account, customer);
			break;
		}
		case 4: {
			initCheckInformationUI(account, customer);
			break;
		}
		case 5: {
			ThemTaiKhoan(customer);
			initListAccountUI(customer);
			break;
		}
		case 6: {
			NapTien(account);
			initMainUI(account, customer);
			break;
		}
		case 7: {
			RutTien(account);
			initMainUI(account, customer);
		}
		case 8: {
			initListAccountUI(customer);
			break;
		}
		case 9: {
			initUI();
			break;
		}
		case 10: {
			System.exit(0);
		}
		default:
//			throw new IllegalArgumentException("Unexpected value: " + options);
			break;
		}

	}

	private void RutTien(Account account) {
		// TODO Auto-generated method stub

		System.out.print("Vui long nhap so tien muon rut: ");
		Scanner scanner = new Scanner(System.in);
		String money = scanner.nextLine();

		if (!money.isEmpty()) {
			while (new CheckValidate().CheckNumber(money) == false) {
				System.out.println("So tien khong hop le, vui long nhap lai");
				money = scanner.nextLine();
			}
		} else
			return;

		long delta = Long.parseLong(money);

		Router router = new Router();
		router.setCalculator(new SubNumber());
		if (router.Calculate(account.getBalanceAccount(), delta) < 0) {
			System.out.println("So du khong du de thuc hien giao dich, vui long thu lai sau");
		} else {
//			router.setCalculator(new SumNumber());
//			account.setLastBalance(account.getBalanceAccount());
			long lastBalance = account.getBalanceAccount();
			account.setBalanceAccount(router.Calculate(account.getBalanceAccount(), delta));
			
			AccountDAO accountDAO = new AccountDAO();
			TransactionDAO transactionDAO = new TransactionDAO();
			TransactionDetailDAO transactionDetailDAO = new TransactionDetailDAO();

			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			Timestamp sqlTimestamp = new Timestamp(date.getTime());
			String idRealTransaction = new RandomGenerate().RandomGenerateIdTransaction();
			while (transactionDAO.getTransactionByRealID(idRealTransaction) != null)
				idRealTransaction = new RandomGenerate().RandomGenerateIdTransaction();
			Transaction transaction = new Transaction(idRealTransaction, "Rut tien khoi tai khoan", sqlDate,
					sqlTimestamp);

			TransactionDetail transactionDetail = new TransactionDetail(-delta, account, transaction);
			transactionDetail.setLastBalance(lastBalance);
			accountDAO.update(account);
			transactionDAO.save(transaction);
			transactionDetailDAO.save(transactionDetail);
		}

	}

	@Override
	public void initTransactionHistoryUI(Account account, Customer customer) {
		// TODO Auto-generated method stub
		TransactionDetailDAO transactionDetailDAO = new TransactionDetailDAO();
		TransactionDAO transactionDAO = new TransactionDAO();
		List<TransactionDetail> listByAccount = new ArrayList<>();
		listByAccount = transactionDetailDAO.getAllByAccount(account);
		List<TransactionDetail> listByTransaction = new ArrayList<>();

		for (TransactionDetail x : listByAccount) {
			int transID = x.getTransaction().getIdTransactionPK();
			listByTransaction = transactionDetailDAO.getAllByTransaction(transID);

			// Nap tien, rut tien
			if (listByTransaction.size() == 1) {
				// Nap tien
				if (x.getAmount() > 0) {
					System.out.println("Tu tai khoan: " + x.getAccount().getNumberAccount() + "\t\t\t\tChu tai khoan: "
							+ x.getAccount().getCustomer().getNameCustomer());
					System.out.println("Den tai khoan: " + x.getAccount().getNumberAccount() + "\t\t\t\tChu tai khoan: "
							+ x.getAccount().getCustomer().getNameCustomer());
					System.out.println("Ma giao dich: \t\t\t\t" + x.getTransaction().getIdTransactionReal());
					System.out.println("Noi dung: " + x.getTransaction().getContent());
					System.out.println(
							"Thoi gian: \t\t\t\t" + x.getTransaction().getDate() + "\t" + x.getTransaction().getTime());
					System.out.println("So tien: \t\t\t\t+" + x.AmountFormatVND());
					System.out.println("So du tai khoan: \t\t\t\t" + x.FormatLastBalanceVND());
				} else {
					System.out.println("Tu tai khoan: " + x.getAccount().getNumberAccount() + "\t\t\t\tChu tai khoan: "
							+ x.getAccount().getCustomer().getNameCustomer());
					System.out.println("Den tai khoan: " + x.getAccount().getNumberAccount() + "\t\t\t\tChu tai khoan: "
							+ x.getAccount().getCustomer().getNameCustomer());
					System.out.println("Ma giao dich: \t\t\t\t" + x.getTransaction().getIdTransactionReal());
					System.out.println("Noi dung: " + x.getTransaction().getContent());
					System.out.println(
							"Thoi gian: \t\t\t\t" + x.getTransaction().getDate() + "\t" + x.getTransaction().getTime());
					System.out.println("So tien: \t\t\t\t" + x.AmountFormatVND());
					System.out.println("So du tai khoan: \t\t\t\t" + x.FormatLastBalanceVND());
				}
			} else {
				TransactionDetail transactionDetail1 = listByTransaction.get(0);
				TransactionDetail transactionDetail2 = listByTransaction.get(1);
				System.out.println(transactionDetail1.getId());
				System.out.println(transactionDetail2.getId());

				if (transactionDetail1 != x) {
					//x is source, trans1 is destination
					if (transactionDetail1.getAmount() > 0) {
						System.out.println("Tu tai khoan: " + x.getAccount().getNumberAccount()
								+ "\t\t\t\tChu tai khoan: " + x.getAccount().getCustomer().getNameCustomer());
						System.out.println("Den tai khoan: " + transactionDetail1.getAccount().getNumberAccount()
								+ "\t\t\t\tChu tai khoan: "
								+ transactionDetail1.getAccount().getCustomer().getNameCustomer());
						System.out.println("Ma giao dich: \t\t\t\t" + x.getTransaction().getIdTransactionReal());
						System.out.println("Noi dung: " + x.getTransaction().getContent());
						System.out.println("Thoi gian: \t\t\t\t" + x.getTransaction().getDate() + "\t"
								+ x.getTransaction().getTime());
						System.out.println("So tien: \t\t\t\t" + x.AmountFormatVND());
						System.out.println("So du tai khoan: \t\t\t\t" + x.FormatLastBalanceVND());
					} if (transactionDetail1.getAmount() < 0) {
						System.out.println("Tu tai khoan: " + transactionDetail1.getAccount().getNumberAccount()
								+ "\t\t\t\tChu tai khoan: "
								+ transactionDetail1.getAccount().getCustomer().getNameCustomer());
						System.out.println("Den tai khoan: " + x.getAccount().getNumberAccount()
								+ "\t\t\t\tChu tai khoan: " + x.getAccount().getCustomer().getNameCustomer());
						System.out.println("Ma giao dich: \t\t\t\t" + x.getTransaction().getIdTransactionReal());
						System.out.println("Noi dung: " + x.getTransaction().getContent());
						System.out.println("Thoi gian: \t\t\t\t" + x.getTransaction().getDate() + "\t"
								+ x.getTransaction().getTime());
						System.out.println("So tien: \t\t\t\t+" + x.AmountFormatVND());
						System.out.println("So du tai khoan: \t\t\t\t" + x.FormatLastBalanceVND());
					}
				} else {
					//x is source, trans2 is destination
					if (x.getAmount() < 0) {
						System.out.println("Tu tai khoan: " + x.getAccount().getNumberAccount()
								+ "\t\t\t\tChu tai khoan: " + x.getAccount().getCustomer().getNameCustomer());
						System.out.println("Den tai khoan: " + transactionDetail2.getAccount().getNumberAccount()
								+ "\t\t\t\tChu tai khoan: "
								+ transactionDetail2.getAccount().getCustomer().getNameCustomer());
						System.out.println("Ma giao dich: \t\t\t\t" + x.getTransaction().getIdTransactionReal());
						System.out.println("Noi dung: " + x.getTransaction().getContent());
						System.out.println("Thoi gian: \t\t\t\t" + x.getTransaction().getDate() + "\t"
								+ x.getTransaction().getTime());
						System.out.println("So tien: \t\t\t\t+" + x.AmountFormatVND());
						System.out.println("So du tai khoan: \t\t\t\t" + x.FormatLastBalanceVND());
					} if(x.getAmount() >0) {
						System.out.println("Tu tai khoan: " + transactionDetail2.getAccount().getNumberAccount()
								+ "\t\t\t\tChu tai khoan: "
								+ transactionDetail2.getAccount().getCustomer().getNameCustomer());
						System.out.println("Den tai khoan: " + x.getAccount().getNumberAccount()
								+ "\t\t\t\tChu tai khoan: " + x.getAccount().getCustomer().getNameCustomer());
						System.out.println("Ma giao dich: \t\t\t\t" + x.getTransaction().getIdTransactionReal());
						System.out.println("Noi dung: " + x.getTransaction().getContent());
						;
						System.out.println("Thoi gian: \t\t\t\t" + x.getTransaction().getDate() + "\t"
								+ x.getTransaction().getTime());
						System.out.println("So tien: \t\t\t\t" + x.AmountFormatVND());
						System.out.println("So du tai khoan: \t\t\t\t" + x.FormatLastBalanceVND());
					}

					listByTransaction.clear();
				}
			}

		}
		System.out.println("\nQuay lai (Y/N)");
		Scanner scanner = new Scanner(System.in);
		String quaylai = scanner.nextLine();
		quaylai = quaylai.toUpperCase().trim();
		if (quaylai.equals("Y"))
			initMainUI(account, customer);
		else if (quaylai.equals("N"))
			initTransactionHistoryUI(account, customer);
		else
			System.exit(0);
	}

	@Override
	public void initTransferUI(Account account, Customer customer) {
		// TODO Auto-generated method stub
		AccountDAO accountDAO = new AccountDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.print("So tai khoan thu huong: ");
		String number = scanner.nextLine();
		Long numberLong = Long.parseLong(number);
		while (new CheckValidate().CheckNumber(number) == false || accountDAO.checkAccountExist(numberLong) == false
				|| account.getNumberAccount() == accountDAO.getAccountByNumberAccount(numberLong).getNumberAccount()) {
			System.out.println("So tai khoan khong hop le hoac khong ton tai, vui long nhap lai");
			System.out.print("So tai khoan thu huong: ");
			number = scanner.nextLine();
			numberLong = Long.parseLong(number);
		}
		Account destAccount = new Account();
		destAccount = accountDAO.getAccountByNumberAccount(numberLong);
		System.out.println("Chu tai khoan: " + destAccount.getCustomer().getNameCustomer());
		System.out.print("So tien: ");
		long amount = scanner.nextLong();

		Router router = new Router();
		router.setCalculator(new SubNumber());
		if (router.Calculate(account.getBalanceAccount(), amount) < 0) {
			System.out.println("So tien khong du de thuc hien giao dich, vui long thu lai sau");
			initMainUI(account, customer);
		} else {
//			account.setLastBalance(account.getBalanceAccount());
			long srcLastBalace= account.getBalanceAccount();
			account.setBalanceAccount(router.Calculate(account.getBalanceAccount(), amount));
			router.setCalculator(new SumNumber());
//			destAccount.setLastBalance(destAccount.getBalanceAccount());
			long destLastBalance = destAccount.getBalanceAccount();
			destAccount.setBalanceAccount(router.Calculate(destAccount.getBalanceAccount(), amount));

			System.out.println(account.getNumberAccount());
			System.out.println(destAccount.getNumberAccount());

			accountDAO.update(destAccount);
			accountDAO.update(account);

			System.out.print("Noi dung chuyen khoan: ");
			scanner.nextLine();
			String content = scanner.nextLine();

			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			Timestamp sqlTimestamp = new Timestamp(date.getTime());

			TransactionDAO transactionDAO = new TransactionDAO();
			TransactionDetailDAO transactionDetailDAO = new TransactionDetailDAO();
			String idRealTransaction = new RandomGenerate().RandomGenerateIdTransaction();
			while (transactionDAO.getTransactionByRealID(idRealTransaction) != null)
				idRealTransaction = new RandomGenerate().RandomGenerateIdTransaction();

			Transaction transaction = new Transaction(idRealTransaction, content, sqlDate, sqlTimestamp);
			transactionDAO.save(transaction);

			TransactionDetail srcTransactionDetail = new TransactionDetail(-amount, account, transaction);
			srcTransactionDetail.setLastBalance(srcLastBalace);
			TransactionDetail destTransactionDetail = new TransactionDetail(amount, destAccount, transaction);
			destTransactionDetail.setLastBalance(destLastBalance);
			transactionDetailDAO.save(destTransactionDetail);
			transactionDetailDAO.save(srcTransactionDetail);
			initMainUI(account, customer);
		}
	}

	@Override
	public void initCheckBalanceUI(Account account, Customer customer) {
		// TODO Auto-generated method stub

		RemoteControl control = new RemoteControl();
		Balance balance = new Balance(account);
		Command showOn = new ShowBalanceOn(balance);
		Command showOff = new ShowBalanceOff(balance);

		control.setCommand(showOff);
		control.pressButton();

		while (true) {
			if (balance.getOn() == false) {
				System.out.println("Vui long lua chon");
				System.out.println("1. Hien so du");
				System.out.println("2. Quay lai");
				Scanner scanner = new Scanner(System.in);
				int options = scanner.nextInt();
				while (options < 0 || options > 2) {
					System.out.println("Vui long nhap lai");
					options = scanner.nextInt();
				}
				if (options == 1) {
					System.out.println("Y/N");
					scanner.nextLine();
					String show = scanner.nextLine();
					show = show.toUpperCase().trim();
					if (show.contains("Y")) {
						control.setCommand(showOn);
						control.pressButton();
					} else if (show.contains("N")) {
						control.setCommand(showOff);
						control.pressButton();
					} else
						System.out.println("Vui long nhap lai");
				} else {
//					initMainUI(account, customer);
					break;
				}

			} else {
				System.out.println("Vui long lua chon");
				System.out.println("1. An so du");
				System.out.println("2. Quay lai");
				Scanner scanner = new Scanner(System.in);
				int options = scanner.nextInt();
				while (options < 0 || options > 2) {
					System.out.println("Vui long nhap lai");
					options = scanner.nextInt();
				}
				if (options == 1) {
					System.out.println("Y/N");
					scanner.nextLine();
					String show = scanner.nextLine();
					show = show.toUpperCase().trim();
					if (show.contains("Y")) {
						control.setCommand(showOff);
						control.pressButton();
					} else if (show.contains("N")) {
						control.setCommand(showOn);
						control.pressButton();
					} else
						System.out.println("Vui long nhap lai");
				} else {
//					initMainUI(account, customer);
					break;
				}

			}
		}
		initMainUI(account, customer);

	}

	@Override
	public void initCheckInformationUI(Account account, Customer customer) {
		// TODO Auto-generated method stub
		CustomerDAO customerDAO = new CustomerDAO();
		System.out.println(customer.toString() + "\n");
		System.out.println("Vui long lua chon");
		System.out.println("1. Cap nhat thong tin");
		System.out.println("2. Quay lai");
		Scanner scanner = new Scanner(System.in);
		int options = scanner.nextInt();

		while (options < 0 || options > 2) {
			System.out.println("Vui long nhap lai");
			options = scanner.nextInt();
		}
		if (options == 1) {
			System.out.print("Email: ");
			scanner.nextLine();
			String email = scanner.nextLine();
			if (email.isEmpty())
				email = "";
			else {
				while (customerDAO.checkCustomerExistByEmail(email) || new CheckValidate().CheckEmail(email) == false) {
					System.out.println("Email khong hop le, vui long nhap lai");
					System.out.print("Email: ");
					email = scanner.nextLine();
				}
			}

			System.out.print("Gioi tinh (Nam/Nu): ");
			String gioitinh = scanner.nextLine();
			Gender gender = new CheckValidate().CheckGender(gioitinh);

			System.out.print("Dia chi: ");
			String address = scanner.nextLine();

			System.out.print("Ngay sinh (mm/dd/yyyy): ");
			String ngaysinh = scanner.nextLine();
			Date dob = new Date();
			if (!ngaysinh.isEmpty())
				dob = new Date(ngaysinh);

			customer.setAddressCustomer(address);
			customer.setDobCustomer(dob);
			customer.setGenderCustomer(gender);
			customer.setEmailCustomer(email);

			customerDAO.update(customer);
			initCheckInformationUI(account, customer);

		} else {
			initMainUI(account, customer);
		}
	}

	public void NapTien(Account account) {
		System.out.println(account.getNumberAccount());

		System.out.print("Vui long nhap so tien muon nap: ");
		Scanner scanner = new Scanner(System.in);
		String money = scanner.nextLine();

		if (!money.isEmpty()) {
			while (new CheckValidate().CheckNumber(money) == false) {
				System.out.println("So tien khong hop le, vui long nhap lai");
				money = scanner.nextLine();
			}
		} else
			return;

		long delta = Long.parseLong(money);

		Router router = new Router();
		router.setCalculator(new SumNumber());
//		account.setLastBalance(account.getBalanceAccount());
		long lastBalance =account.getBalanceAccount();
		account.setBalanceAccount(router.Calculate(account.getBalanceAccount(), delta));
		AccountDAO accountDAO = new AccountDAO();
		TransactionDAO transactionDAO = new TransactionDAO();
		TransactionDetailDAO transactionDetailDAO = new TransactionDetailDAO();

		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		Timestamp sqlTimestamp = new Timestamp(date.getTime());
		String idRealTransaction = new RandomGenerate().RandomGenerateIdTransaction();
		while (transactionDAO.getTransactionByRealID(idRealTransaction) != null)
			idRealTransaction = new RandomGenerate().RandomGenerateIdTransaction();
		Transaction transaction = new Transaction(idRealTransaction, "Nap tien vao tai khoan", sqlDate, sqlTimestamp);

		TransactionDetail transactionDetail = new TransactionDetail(delta, account, transaction);
//		transactionDetail.setLastBalance(account.getLastBalance());
		transactionDetail.setLastBalance(lastBalance);
		accountDAO.update(account);
		transactionDAO.save(transaction);
		transactionDetailDAO.save(transactionDetail);

//		initMainUI(account, customer);

	}

	public void ThemTaiKhoan(Customer customer) {
		AccountDAO accountDAO = new AccountDAO();

		Account account = new Account(0, new RandomGenerate().RandomGenerateLongNumber(), customer);
		accountDAO.save(account);
	}
}
