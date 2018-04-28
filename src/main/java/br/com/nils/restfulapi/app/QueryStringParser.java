package br.com.nils.restfulapi.app;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

public class QueryStringParser {

	private QueryStringFilter qsFilter;
	private QueryStringSort qsSort;
	private QueryStringPagination qsPag;

	public QueryStringParser(UriInfo uriInfo, Class<?> classz) {
		MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
		qsFilter = new QueryStringFilter(queryParameters, classz);
		qsSort = new QueryStringSort(queryParameters, classz);
		qsPag = new QueryStringPagination(queryParameters);
	}

	public QueryStringFilter getQsFilter() {
		return qsFilter;
	}

	public QueryStringSort getQsSort() {
		return qsSort;
	}

	public QueryStringPagination getQsPag() {
		return qsPag;
	}

}
