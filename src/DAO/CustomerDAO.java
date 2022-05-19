package DAO;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Customer;
import util.HibernateUtil;

public class CustomerDAO implements DAO<Customer> {

	@Override
	public void save(Customer t) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void update(Customer t) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			String hqlString = "UPDATE Customer SET nameCustomer = :name, phoneNumber = :phone, CMND = :cmnd,"
					+ "emailCustomer = :email, addressCustomer = :address, genderCustomer = :gender, dobCustomer = :dob "
					+ "WHERE idCustomer = :id";
			Query query = session.createQuery(hqlString);
			query.setParameter("name", t.getNameCustomer()).setParameter("phone", t.getPhoneNumber())
					.setParameter("cmnd", t.getCMND()).setParameter("email", t.getEmailCustomer())
					.setParameter("address", t.getAddressCustomer()).setParameter("gender", t.getGenderCustomer())
					.setParameter("dob", t.getDobCustomer()).setParameter("id", t.getIdCustomer());

			query.executeUpdate();

			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Customer customer = session.get(Customer.class, id);
			if (customer != null) {
				session.createQuery("DELETE FROM Customer WHERE idCustomer = :id").setParameter("id", id)
						.executeUpdate();
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Customer get(int id) {
		// TODO Auto-generated method stub
		Customer customer = null;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			customer = session.get(Customer.class, id);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}

		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		try {
			return HibernateUtil.getSessionFactory().openSession().createQuery("FROM Customer ORDER BY idCustomer ASC")
					.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}


	public boolean checkCustomerExistByCMND(String cmnd) {

		List<Customer> list;
		boolean flag = false;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			list = session.createQuery("FROM Customer WHERE CMND = ?3").setParameter(3, cmnd).getResultList();
			if (list != null && !list.isEmpty()) {
				flag = true;
			}
			transaction.commit();

		} catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			e.printStackTrace();
		}

		return flag;

	}

	public boolean checkCustomerExistByPhoneNumber(String phoneNumber) {

		List<Customer> list;
		boolean flag = false;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			list = session.createQuery("FROM Customer WHERE phoneNumber = ?3").setParameter(3, phoneNumber)
					.getResultList();
			if (list != null && !list.isEmpty()) {
				flag = true;
			}
			transaction.commit();

		} catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			e.printStackTrace();
		}
		return flag;

	}

	public boolean checkCustomerExistByEmail(String email) {

		List<Customer> list;
		boolean flag = false;

		if(email.isEmpty() || email.equals("") || email==null)
			return flag;
		
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			list = session.createQuery("FROM Customer WHERE emailCustomer = ?3").setParameter(3, email).getResultList();
			if (list != null && !list.isEmpty()) {
				flag = true;
			}
			transaction.commit();

		} catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			e.printStackTrace();
		}

		return flag;

	}
}
