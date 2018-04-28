package br.com.nils.restfulapi.crud.util;

import java.util.List;

import br.com.nils.restfulapi.app.QueryStringParser;

public class BaseBO<T> {

	protected BaseDAO<T> baseDAO;

	public T findById(Class<T> type, Long id) {
		return baseDAO.findById(type, id);
	}

	public List<T> findAll(Class<T> type, QueryStringParser queryStringParser) {
		return baseDAO.findAll(type, queryStringParser);
	}

	public void insert(T entity) {
		baseDAO.insert(entity);
	}

	public void update(T entity) {
		baseDAO.update(entity);
	}

	public void delete(T entity) {
		baseDAO.delete(entity);
	}
}
