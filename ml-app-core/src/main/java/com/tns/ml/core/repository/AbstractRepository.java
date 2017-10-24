package com.tns.ml.core.repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractRepository<T, K> implements Repository<T, K> {

	protected static final int MAX_RESULTS = 500;

	private Class<T> type;

	@PersistenceContext(unitName = "ml")
	protected EntityManager em;

	protected AbstractRepository(Class<T> type) {
		this.type = type;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<T> findAll() {
		Query query = em.createQuery("SELECT T FROM " + type.getSimpleName() + " T");
		return (List<T>) query.setMaxResults(MAX_RESULTS).getResultList();
	}

	@Override
	public T save(T entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public T find(K key) {
		return em.find(type, key);
	}

	@Override
	public void delete(T entity) {
		em.remove(entity);
	}

	@Override
	public void merge(T entity) {
		this.em.merge(entity);
	}

	@Override
	public Collection<T> save(Collection<T> entities) {
		if (Objects.nonNull(entities) && entities.size() > 0) {
			for (T entity : entities) {
				save(entity);
			}
		}
		return entities;
	}

	public <E> E execute(String sql, Class<E> resultClass, Object... params) {
		Query query = em.createNativeQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		Object singleResult = query.getSingleResult();
		return resultClass.cast(singleResult);
	}
}
