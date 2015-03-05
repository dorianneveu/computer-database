package com.excilys.computerdatabase.service;

import java.util.List;

/**
 * @author excilys
 *
 * @param <T>
 */
public interface Service<T> {
	/**
	 * @param object
	 */
	void insert(final T object);

	/**
	 * @param object
	 */
	void delete(final T object);

	/**
	 * @param id
	 * @return
	 */
	T get(final int id);

	/**
	 * @return
	 */
	List<T> getAll();

	/**
	 * @param object
	 */
	void update(final T object);

}
