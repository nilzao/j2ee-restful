package br.com.nils.restfulapi.crud.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.nils.restfulapi.app.QueryStringPagination;
import br.com.nils.restfulapi.app.QueryStringParser;

public abstract class BaseDAO<T> {

	protected EntityManager entityManager;

	protected abstract void setEntityManager(EntityManager entityManager);

	public T findById(Class<T> type, Long id) {
		return entityManager.find(type, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> type, QueryStringParser queryStringParser) {
		QueryStringPagination qsPag = queryStringParser.getQsPag();
		Query createQuery = entityManager.createQuery("from " + type.getName());
		createQuery.setFirstResult(qsPag.getOffset());
		createQuery.setMaxResults(qsPag.getLimit());
		return createQuery.getResultList();
	}

	public void insert(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entityManager.merge(entity));
	}

}
