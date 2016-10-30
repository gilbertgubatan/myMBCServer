package com.mbc.server.database.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.mbc.server.util.ObjectUtil;

public class DBUtil {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private CriteriaBuilder cb;
	
	public DBUtil() {
		this.emf = Persistence.createEntityManagerFactory("MBCServer");
		this.em = this.emf.createEntityManager();
		this.cb = this.em.getCriteriaBuilder();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return this.emf;
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
	
	public CriteriaBuilder getCriteriaBuilder() {
		return this.cb;
	}
	
	public <T> EntityType<T> getEntityType(Class<T> clazz) {
		Metamodel metamodel = this.em.getMetamodel();
	    return metamodel.entity(clazz);
	}
	
	public <T> List<T> getAllEntityList(Class<T> clazz) {
		CriteriaQuery<T> cq = this.cb.createQuery(clazz);
		Root<T> rootUser = cq.from(clazz);
		cq.select(rootUser);
		
		TypedQuery<T> tqUser = this.em.createQuery(cq);
		
		if (ObjectUtil.isNotNull(tqUser)) {
			return tqUser.getResultList();
		} else {
			return null; 
		}
	}
	
	public <T> T getOneEntity(TypedQuery<T> typedQuery) {
        if (ObjectUtil.isNull(typedQuery)) {
        	return null;
        }
        
        List<T> resultList = typedQuery.getResultList();
        
        return ObjectUtil.isNull(resultList) || resultList.isEmpty() ? null : resultList.get(0);
    }
	
	public <T> void insertEntity(Class<T> clazz) {
		this.em.getTransaction().begin();
		this.em.persist(clazz);
		this.em.getTransaction().commit();
	}
	
}
