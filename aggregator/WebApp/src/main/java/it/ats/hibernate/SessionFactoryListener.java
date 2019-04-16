package it.ats.hibernate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

public class SessionFactoryListener implements ServletContextListener {

	public final Logger logger = Logger.getLogger(SessionFactoryListener.class);

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		SessionFactory sessionFactoryObj = (SessionFactory) servletContextEvent.getServletContext()
				.getAttribute("SessionFactory");
		if (sessionFactoryObj != null && !sessionFactoryObj.isClosed()) {
			logger.info("Closing Session Factory Object!");
			sessionFactoryObj.close();
		}
		logger.info("Hibernate Session Factory Object Released!");
	}

//	public void contextInitialized(ServletContextEvent servletContextEvent) {
//		// Creating Configuration Instance & Passing Hibernate Configuration File
//		Configuration config = new Configuration();
//		String hibernateFilePath = "C:\\Users\\laura.santomarco\\eclipse-workspaceiee\\NuovoProgettoWeb\\src\\it\\ats\\confi\\hibernate.cfg.xml";
//		File file = new java.io.File(hibernateFilePath);
//		config.configure(file);
//		StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
//				.applySettings(config.getProperties());
//		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry.build());
//		servletContextEvent.getServletContext().setAttribute("SessionFactory", sessionFactory);
//		logger.info("Hibernate Session Factory Configured Successfully!");
//	}
 
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		try {
			Configuration config = new Configuration();
			URL url = getClass().getResource("../config/hibernate.cfg.xml");
			config.configure(url); 
			config.addResource("it/ats/config/Utenti.hbm.xml");
			config.addResource("it/ats/config/Contocorrente.hbm.xml");
			config.addResource("it/ats/config/UtentiAssociazioneConto.hbm.xml");
 
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(config.getProperties()).build();
			logger.info("ServiceRegistry created successfully");
			SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
			logger.info("SessionFactory created successfully");
	  
			servletContextEvent.getServletContext().setAttribute("SessionFactory", sessionFactory);
			logger.info("Hibernate SessionFactory Configured successfully");
		} catch (Exception e) {
			System.out.println("Non funziona!");
			e.printStackTrace();
		}
		
		
	}
}