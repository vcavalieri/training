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
 * Home object for domain model class Contocorrente.
 * @see it.ats.hibernate.Contocorrente
 * @author Hibernate Tools
 */
public class ContocorrenteHome {

	private static final Log log = LogFactory.getLog(ContocorrenteHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Contocorrente transientInstance) {
		log.debug("persisting Contocorrente instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Contocorrente instance) {
		log.debug("attaching dirty Contocorrente instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Contocorrente instance) {
		log.debug("attaching clean Contocorrente instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Contocorrente persistentInstance) {
		log.debug("deleting Contocorrente instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Contocorrente merge(Contocorrente detachedInstance) {
		log.debug("merging Contocorrente instance");
		try {
			Contocorrente result = (Contocorrente) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Contocorrente findById(int id) {
		log.debug("getting Contocorrente instance with id: " + id);
		try {
			Contocorrente instance = (Contocorrente) sessionFactory.getCurrentSession()
					.get("it.ats.hibernate.Contocorrente", new Integer(id));
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

	public List findByExample(Contocorrente instance) {
		log.debug("finding Contocorrente instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("it.ats.hibernate.Contocorrente")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
