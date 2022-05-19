package model;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class TransactionDetail {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private long amount,lastBalance;
	
	@ManyToOne
	private Account account;
	
	@ManyToOne
	private Transaction transaction;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public TransactionDetail(long amount, Account account, Transaction transaction) {
		super();
		this.amount = amount;
		this.account = account;
		this.transaction = transaction;
	}
	
	
	
	public long getLastBalance() {
		return lastBalance;
	}

	public void setLastBalance(long lastBalance) {
		this.lastBalance = lastBalance;
	}

	public TransactionDetail() {
		
	}
	
	public String AmountFormatVND() {
		DecimalFormat dm = new DecimalFormat("###,###,###,###,###");
		return dm.format(this.getAmount())+" VND"; 
	}
	
	public String FormatLastBalanceVND() {
		DecimalFormat dm = new DecimalFormat("###,###,###,###,###");
		return dm.format(Math.abs(this.getAmount() + this.getLastBalance()))+" VND"; 
	}
}
