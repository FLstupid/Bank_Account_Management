package DAO;

import java.util.List;

import org.hibernate.Session;

import model.Transaction;
import util.HibernateUtil;

public class TransactionDAO implements DAO<Transaction> {

	@Override
	public void save(Transaction t) {
		// TODO Auto-generated method stub
		org.hibernate.Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();
		}catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void update(Transaction t) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public Transaction get(int id) {
		// TODO Auto-generated method stub
		
		Transaction transaction = null;
		org.hibernate.Transaction transaction2 = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction2 = session.beginTransaction();
			transaction = session.get(Transaction.class, id);
			transaction2.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			if(transaction2!=null)
				transaction2.rollback();
			e.printStackTrace();
		}
		
		return transaction;
	}

	@Override
	public List<Transaction> getAll() {
		// TODO Auto-generated method stub
		try {
			return HibernateUtil.getSessionFactory().openSession().createQuery("FROM Transaction ORDER BY idTransaction ASC").getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Transaction getTransactionByRealID(String id) {
		Transaction transaction = null;
		org.hibernate.Transaction transaction2 = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction2=session.beginTransaction();
			
			List<Transaction> list = session.createQuery("FROM Transaction WHERE idTransactionReal = ?1")
					.setParameter(1, id).getResultList();
			if(list!=null && !list.isEmpty())
				transaction = list.get(0);
			
			
			transaction2.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(transaction2!=null)
				transaction2.rollback();
			e.printStackTrace();
		}
		return transaction;
	}

}
