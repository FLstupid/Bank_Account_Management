package model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.bytebuddy.utility.nullability.MaybeNull;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCustomer;
	private String nameCustomer,phoneNumber,CMND;
	
	//optinal
	@Column(nullable = true)
	private String emailCustomer,addressCustomer;
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private Gender genderCustomer;
	
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dobCustomer;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Account> listAccount;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "customer")
	private Login login;
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Customer() {

	}

	public String getEmailCustomer() {
		return emailCustomer;
	}

	public void setEmailCustomer(String emailCustomer) {
		this.emailCustomer = emailCustomer;
	}

	public Gender getGenderCustomer() {
		return genderCustomer;
	}

	public void setGenderCustomer(Gender genderCustomer) {
		this.genderCustomer = genderCustomer;
	}

	public String getAddressCustomer() {
		return addressCustomer;
	}

	public void setAddressCustomer(String addressCustomer) {
		this.addressCustomer = addressCustomer;
	}

	public Date getDobCustomer() {
		return dobCustomer;
	}

	public void setDobCustomer(Date dobCustomer) {
		this.dobCustomer = dobCustomer;
	}

	public List<Account> getListAccount() {
		return listAccount;
	}

	public void setListAccount(List<Account> listAccount) {
		this.listAccount = listAccount;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}
	
	public static class CustomerBuilder{
		//required
		private int idCustomer;
		private String nameCustomer,phoneNumber,CMND;
		
		//optinal
		private String emailCustomer,addressCustomer;
		private Gender genderCustomer;
		private Date dobCustomer;
		
		public CustomerBuilder( String nameCustomer, String phoneNumber, String CMND) {
			this.nameCustomer = nameCustomer;
			this.phoneNumber = phoneNumber;
			this.CMND = CMND;
		}
		
		public CustomerBuilder setEmail(String email) {
			emailCustomer = email;
			return this;
		}
		public CustomerBuilder setAddress(String address) {
			addressCustomer=address;
			return this;
		}
		public CustomerBuilder setGender(Gender gender) {
			genderCustomer = gender;
			return this;
		}
		public CustomerBuilder setDob(Date dob) {
			dobCustomer = dob;
			return this;
		}
		
		public Customer build() {
			return new Customer(this);
		}
	}
	
	public Customer(CustomerBuilder builder) {
		this.idCustomer = builder.idCustomer;
		this.nameCustomer = builder.nameCustomer;
		this.phoneNumber = builder.phoneNumber;
		this.CMND = builder.CMND;
		this.genderCustomer = builder.genderCustomer;
		this.addressCustomer = builder.addressCustomer;
		this.dobCustomer=builder.dobCustomer;
		this.emailCustomer = builder.emailCustomer;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Id: "+idCustomer+"\nName: "+nameCustomer+"\nPhoneNumber: "+phoneNumber
				+"\nCMNND: "+CMND+"\nEmail: "+emailCustomer+"\nGender: "+genderCustomer+"\nAddress: "+
		addressCustomer+"\nDOB: "+dobCustomer+"\n";
	}
}
