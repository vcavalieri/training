package it.ats.hibernate;


import java.net.URL;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

public class SessionFactoryListener implements ServletContextListener {

	public final Logger logger = Logger.getLogger(SessionFactoryListener.class);
    /**
     * Il metodo contextDestroyed è stato definito per chiudere la sessione 
     * @param servletContextEvent
     */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		SessionFactory sessionFactoryObj = (SessionFactory) servletContextEvent.getServletContext()
				.getAttribute("SessionFactory");
		if (sessionFactoryObj != null && !sessionFactoryObj.isClosed()) {
			logger.info("Closing Session Factory Object!");
			sessionFactoryObj.close();
		}
		logger.info("Hibernate Session Factory Object Released!");
	}		
	/**
	 * Il metodo contextInitilized definisce le configurazioni e i mapping
	 * tra le risorse 
	 * Alla configurazione vengono passati i path delle risorse,
	 * come: "it/ats/config/Utenti.hbm.xml", "it/ats/config/Contocorrente.hbm.xml",
	 * "it/ats/config/UtentiAssociazioneConto.hbm.xml"
	 * @param servletContextEvent 
	 */
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