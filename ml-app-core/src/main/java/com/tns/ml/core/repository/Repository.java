package com.tns.ml.core.repository;

import java.util.Collection;

/**
 * The Interface GenericDAO.
 *
 * @param <T>
 *            the generic type
 * @param <K>
 *            the key type
 */
public interface Repository<T, K> {

	/**
	 * Save.
	 *
	 * @param entity
	 *            the entity
	 * @return the t
	 */
	T save(T entity);

	/**
	 * Find.
	 *
	 * @param key
	 *            the key
	 * @return the t
	 */
	T find(K key);

	/**
	 * Find all.
	 *
	 * @return the collection
	 */
	Collection<T> findAll();

	/**
	 * Delete.
	 *
	 * @param entity
	 *            the entity
	 */
	void delete(T entity);

	/**
	 * Save.
	 *
	 * @param entities
	 *            the entities
	 * @return the collection
	 */
	Collection<T> save(Collection<T> entities);

	/**
	 * Merge the state of the given entity into the current persistence context.
	 * 
	 * @param entity
	 */
	void merge(T entity);

}
