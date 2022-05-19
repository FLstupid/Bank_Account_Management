package model;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAccount;
	private long balanceAccount;
	@Column(unique = true)
	private long numberAccount;
	
	@ManyToOne
	private Customer customer;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "account")
	private List<TransactionDetail> listTransactions;
	
	public Account(long balanceAccount,long numberAccount, Customer customer) {
		this.balanceAccount = balanceAccount;
		this.customer = customer;
		this.numberAccount= numberAccount;
	}
	

//	public long getLastBalance() {
//		return lastBalance;
//	}
//
//
//	public void setLastBalance(long lastBalance) {
//		this.lastBalance = lastBalance;
//	}


	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public long getBalanceAccount() {
		return balanceAccount;
	}

	public void setBalanceAccount(long balanceAccount) {
		this.balanceAccount = balanceAccount;
	}

	public long getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(long numberAccount) {
		this.numberAccount = numberAccount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<TransactionDetail> getListTransactions() {
		return listTransactions;
	}

	public void setListTransactions(List<TransactionDetail> listTransactions) {
		this.listTransactions = listTransactions;
	}

	public Account() {
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "NumberAccount: " +numberAccount;
	}
	
	public String BalanceFormatVND() {
		DecimalFormat balance = new DecimalFormat("###,###,###,###,###");
		return balance.format(this.getBalanceAccount())+" VND"; 
	}
}
