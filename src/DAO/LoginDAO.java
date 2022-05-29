package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Login;
import util.HibernateUtil;

public class LoginDAO implements DAO<Login> {

	@Override
	public void save(Login t) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
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
	public void update(Login t) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.createQuery("UPDATE Login SET password = :passwrod").setParameter("password", t.getPassword()).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction=session.beginTransaction();
			Login login = session.get(Login.class, id);
			if(login!=null) {
				session.createQuery("DELETE FROM Login WHERE idLogin = :id").setParameter("idLogin", id).executeUpdate();
			}
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Login get(int id) {
		// TODO Auto-generated method stub
		
		Transaction transaction=null;
		Login login = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction=session.beginTransaction();
			login = session.get(Login.class, id);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			e.printStackTrace();
		}
		
		return login;
	}

	@Override
	public List<Login> getAll() {
		// TODO Auto-generated method stub
		
		try {
			return HibernateUtil.getSessionFactory().openSession().createQuery("FROM Login ORDER BY idLogin ASC").getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public Login checkLoginSuccess(Login login) {
		Transaction transaction = null;
		Login login2 = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			List<Login> list= session.createQuery("FROM Login WHERE username = :username AND password = :password")
					.setParameter("username", login.getUsername()).setParameter("password", login.getPassword()).getResultList();
			if(list != null && !list.isEmpty()) {
				login2 = list.get(0);
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			e.printStackTrace();
		}
		
		return login2;
	}
	
}
