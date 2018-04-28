package br.com.nils.restfulapi.crud.util;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.beanutils.BeanUtils;

import br.com.nils.restfulapi.app.QueryStringParser;

public class BaseRest<T, X> {

	protected BaseBO<X> baseBO;

	protected Class<T> classT;
	protected Class<X> classX;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> getAll(@Context UriInfo uriInfo) {
		QueryStringParser queryStringParser = new QueryStringParser(uriInfo, classT);

		List<X> findAll = baseBO.findAll(classX, queryStringParser);
		List<T> list = new ArrayList<>();
		for (X genericEntity : findAll) {
			try {
				T genericRestObj = classT.newInstance();
				BeanUtils.copyProperties(genericRestObj, genericEntity);
				list.add(genericRestObj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public T getById(@PathParam(value = "id") Long id) {
		T genericRestObj = null;
		try {
			X genericEntity = baseBO.findById(classX, id);
			if (genericEntity != null) {
				genericRestObj = classT.newInstance();
				BeanUtils.copyProperties(genericRestObj, genericEntity);
				return genericRestObj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genericRestObj;
	}

	@DELETE
	@Path("/{id}")
	public void deleteById(@PathParam(value = "id") Long id) {
		X genericEntity = baseBO.findById(classX, id);
		if (genericEntity != null) {
			baseBO.delete(genericEntity);
		}
	}

	@PUT
	@Path("/{id}")
	public void put(@PathParam(value = "id") Long id, T genericRestObj) {
		X genericEntity = baseBO.findById(classX, id);
		try {
			BeanUtils.copyProperties(genericEntity, genericRestObj);
			baseBO.update(genericEntity);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public T post(T genericRestObj) {
		try {
			X genericEntity = classX.newInstance();
			BeanUtils.copyProperties(genericEntity, genericRestObj);
			baseBO.insert(genericEntity);
			BeanUtils.copyProperties(genericRestObj, genericEntity);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return genericRestObj;
	}

	@PUT
	public void putAll(List<T> genericRestObjList) {
		System.out.println(genericRestObjList.size());
	}

	@DELETE
	public void deleteAll() {

	}
}
