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
 * Home object for domain model class UtentiAssociazioneConto.
 * @see it.ats.hibernate.UtentiAssociazioneConto
 * @author Hibernate Tools
 */
public class UtentiAssociazioneContoHome {

	private static final Log log = LogFactory.getLog(UtentiAssociazioneContoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(UtentiAssociazioneConto transientInstance) {
		log.debug("persisting UtentiAssociazioneConto instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UtentiAssociazioneConto instance) {
		log.debug("attaching dirty UtentiAssociazioneConto instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UtentiAssociazioneConto instance) {
		log.debug("attaching clean UtentiAssociazioneConto instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UtentiAssociazioneConto persistentInstance) {
		log.debug("deleting UtentiAssociazioneConto instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UtentiAssociazioneConto merge(UtentiAssociazioneConto detachedInstance) {
		log.debug("merging UtentiAssociazioneConto instance");
		try {
			UtentiAssociazioneConto result = (UtentiAssociazioneConto) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UtentiAssociazioneConto findById(it.ats.hibernate.UtentiAssociazioneContoId id) {
		log.debug("getting UtentiAssociazioneConto instance with id: " + id);
		try {
			UtentiAssociazioneConto instance = (UtentiAssociazioneConto) sessionFactory.getCurrentSession()
					.get("it.ats.hibernate.UtentiAssociazioneConto", id);
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

	public List findByExample(UtentiAssociazioneConto instance) {
		log.debug("finding UtentiAssociazioneConto instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("it.ats.hibernate.UtentiAssociazioneConto")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
