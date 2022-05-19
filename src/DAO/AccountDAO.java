package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Account;
import model.Customer;
import util.HibernateUtil;

public class AccountDAO implements DAO<Account> {

	@Override
	public void save(Account t) {
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
	public void update(Account t) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.createQuery("UPDATE Account SET balanceAccount =: balance WHERE idAccount = :id")
					.setParameter("balance", t.getBalanceAccount()).setParameter("id", t.getIdAccount())
					.executeUpdate();
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
			Account account = session.get(Account.class, id);
			if (account != null)
				session.createQuery("DELETE FROM Account WHERE idAccount = :id").setParameter("id", id).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Account get(int id) {
		// TODO Auto-generated method stub

		Account account = null;
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			account = session.get(Account.class, id);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}

		return account;
	}

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		try {
			return HibernateUtil.getSessionFactory().openSession().createQuery("FROM Account ORDER BY idAccount ASC")
					.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public List<Account> getAllByCustomer(Customer customer) {

		try {
			return HibernateUtil.getSessionFactory().openSession()
					.createQuery("FROM Account AS A WHERE A.customer = ?1 ").setInteger(1, customer.getIdCustomer())
					.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	public Account getAccountByNumberAccount(long id) {
		List<Account> list = new ArrayList<>();
		Account account = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			list = session.createQuery("FROM Account WHERE numberAccount = ?1").setParameter(1, id).getResultList();
			if (list != null && !list.isEmpty())
				account = list.get(0);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}

		return account;
	}

	public boolean checkAccountExist(long number) {
		boolean flag = false;
		List<Account> list;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			list = session.createQuery("FROM Account WHERE numberAccount = ?1").setParameter(1, number).getResultList();
			if (list != null && !list.isEmpty())
				flag = true;
			transaction.commit();

		} catch (Exception e) {
			// TODO: handle exception
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}

		return flag;
	}
}
