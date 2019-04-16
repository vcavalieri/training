package it.ats.hibernate;
// Generated Apr 5, 2019 12:53:12 PM by Hibernate Tools 5.1.10.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Utenti.
 * @see it.ats.hibernate.Utenti
 * @author Hibernate Tools
 */
public class UtentiHome {

	private static final Log log = LogFactory.getLog(UtentiHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Utenti transientInstance) {
		log.debug("persisting Utenti instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Utenti instance) {
		log.debug("attaching dirty Utenti instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Utenti instance) {
		log.debug("attaching clean Utenti instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Utenti persistentInstance) {
		log.debug("deleting Utenti instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Utenti merge(Utenti detachedInstance) {
		log.debug("merging Utenti instance");
		try {
			Utenti result = (Utenti) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Utenti findById(int id) {
		log.debug("getting Utenti instance with id: " + id);
		try {
			Utenti instance = (Utenti) sessionFactory.getCurrentSession().get( "it.ats.hibernate.Utenti", new Integer(id));
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Utenti instance) {
		log.debug("finding Utenti instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("it.ats.hibernate.Utenti")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
