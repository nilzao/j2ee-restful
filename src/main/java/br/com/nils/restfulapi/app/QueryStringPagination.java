package br.com.nils.restfulapi.app;

import java.util.Date;

import javax.ws.rs.core.MultivaluedMap;

public class QueryStringPagination {

	private int limit = 20;
	private int offset = 0;
	private Date created;

	// limit=20&offset=100
	// limit=20&created:lte:2018-01-20T00:00:00
	public QueryStringPagination(MultivaluedMap<String, String> queryParameters) {
		String limitStr = queryParameters.getFirst("limit");
		if (limitStr != null) {
			this.limit = Integer.valueOf(limitStr);
		}
		String offsetStr = queryParameters.getFirst("offset");
		if (offsetStr != null) {
			this.offset = Integer.valueOf(offsetStr);
		}
		String createdStr = queryParameters.getFirst("created");
		if (createdStr != null) {
			this.created = new Date();
		}
	}

	public int getLimit() {
		return limit;
	}

	public int getOffset() {
		return offset;
	}

	public Date getCreated() {
		return created;
	}

}
