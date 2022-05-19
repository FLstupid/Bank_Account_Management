package model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTransactionPK;
	
	private String idTransactionReal ;
	private String content;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Temporal(TemporalType.TIME)
	private Date time;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "transaction")
	private List<TransactionDetail> listTransactionDetails;
	


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<TransactionDetail> getListTransactionDetails() {
		return listTransactionDetails;
	}

	public void setListTransactionDetails(List<TransactionDetail> listTransactionDetails) {
		this.listTransactionDetails = listTransactionDetails;
	}

	public int getIdTransactionPK() {
		return idTransactionPK;
	}

	public void setIdTransactionPK(int idTransactionPK) {
		this.idTransactionPK = idTransactionPK;
	}

	public String getIdTransactionReal() {
		return idTransactionReal;
	}

	public void setIdTransactionReal(String idTransactionReal) {
		this.idTransactionReal = idTransactionReal;
	}

	public Transaction(String idTransactionReal, String content, Date date, Date time) {
		super();
		this.idTransactionReal = idTransactionReal;
		this.content = content;
		this.date = date;
		this.time = time;
	}


	
	public Transaction() {
		
	}
	
	
}
