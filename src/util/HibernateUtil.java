package util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import model.Account;
import model.Customer;
import model.Login;
import model.Transaction;
import model.TransactionDetail;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();

				settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/bank?useSSL=false");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "Minhnhut75!");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Customer.class);
				configuration.addAnnotatedClass(Account.class);
				configuration.addAnnotatedClass(Login.class);
				configuration.addAnnotatedClass(Transaction.class);
				configuration.addAnnotatedClass(TransactionDetail.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				
//				System.out.println("Hibernate Java Config serviceRegistry created");
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//				System.out.println(sessionFactory);
				return sessionFactory;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(sessionFactory);
		return sessionFactory;
	}
}
