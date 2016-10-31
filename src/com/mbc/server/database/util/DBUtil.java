package com.mbc.server.database.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

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
	
	public <T> Object getEntityByPrimaryKey(Class<T> entity, Object primaryKey) {
		return em.find(entity, primaryKey);
	}
	
	public <T> T getEntityByColumnValue(Class<T> clazz, String columnName, Object columnValue) {
		CriteriaQuery<T> cq = this.cb.createQuery(clazz);
		Root<T> rootEntity = cq.from(clazz);
		cq.select(rootEntity);
		
		Path<Object> pathEntity = rootEntity.get(columnName);
		Predicate prColumnValue = this.cb.equal(pathEntity, columnValue);
		
		cq.where(prColumnValue);
		
		TypedQuery<T> tqEntity = this.em.createQuery(cq);
		
		return getOneEntity(tqEntity);
	}
	
	@SuppressWarnings("unchecked")
	public <T> Object getMaxEntityByColumnValue(Class<T> clazz, String columnName) {
		CriteriaQuery<T> cq = this.cb.createQuery(clazz);
		Root<T> rootEntity = cq.from(clazz);
		
		cq.select((Selection<? extends T>) this.cb.max(rootEntity.get(columnName)));
		
		TypedQuery<T> tqEntity = this.em.createQuery(cq);
		
		return tqEntity.getSingleResult();
	}
	
	public <T> List<T> getAllEntityList(Class<T> clazz) {
		CriteriaQuery<T> cq = this.cb.createQuery(clazz);
		Root<T> rootEntity = cq.from(clazz);
		cq.select(rootEntity);
		
		TypedQuery<T> tqEntity = this.em.createQuery(cq);
		
		if (ObjectUtil.isNotNull(tqEntity)) {
			return tqEntity.getResultList();
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
	
	public <T> T insertEntity(T entity) {
		this.em.getTransaction().begin();
		this.em.persist(entity);
		this.em.flush();
		this.em.getTransaction().commit();
		
		return entity;
	}
	
	public <T> Object getEntityToUpdateByPrimaryKey(Class<T> entity, Object primaryKey) {
		Object objectReturn = em.find(entity, primaryKey);
		
		em.getTransaction().begin();
		
		return objectReturn;
	}
	
	public Object endUpdateEntity(Object updatedObject) {
		em.flush();
		em.getTransaction().commit();
		
		return updatedObject;
	}
	
	public <T> void deleteEntity(Class<T> entity, Object primaryKey) {
		Object objectToDelete = em.find(entity, primaryKey);
		
		em.getTransaction().begin();
		em.remove(objectToDelete);
		em.flush();
		em.getTransaction().commit();
	}
	
}
