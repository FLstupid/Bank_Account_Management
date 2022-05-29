package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Account;
import model.TransactionDetail;
import util.HibernateUtil;

public class TransactionDetailDAO implements DAO<TransactionDetail> {

	@Override
	public void save(TransactionDetail t) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(TransactionDetail t) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public TransactionDetail get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionDetail> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<TransactionDetail> getAllByTransaction(int id){
//		List<TransactionDetail> list = new ArrayList<>();
//		Transaction transaction = null;
//		
//		try (Session session = HibernateUtil.getSessionFactory().openSession()){
//			transaction = session.beginTransaction();
//			list = session.createQuery("FROM TransactionDetail AS T WHERE T.transaction = ?1 ").setParameter(1, id).getResultList();
//			transaction.commit();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			if(transaction!=null)
//				transaction.rollback();
//			e.printStackTrace();
//		}
//		
//		return list;
//		
		
		try {
			return HibernateUtil.getSessionFactory().openSession().
					createQuery("FROM TransactionDetail AS T WHERE T.transaction = ?1")
					.setInteger(1, id).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<TransactionDetail> getAllByAccount(Account account){
		
		try {
			return HibernateUtil.getSessionFactory().openSession().
					createQuery("FROM TransactionDetail AS T WHERE T.account = ?1 ORDER BY T.id ASC")
					.setInteger(1, account.getIdAccount()).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
}
