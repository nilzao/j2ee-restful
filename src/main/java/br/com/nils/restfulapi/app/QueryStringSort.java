package br.com.nils.restfulapi.app;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

public class QueryStringSort {

	private List<String> sortByFields = new ArrayList<>();

	// sort_by=-last_modified,email
	public QueryStringSort(MultivaluedMap<String, String> queryParameters, Class<?> classz) {
		String sortBy = queryParameters.getFirst("sort_by");
		if (sortBy == null || sortBy.isEmpty()) {
			return;
		}
		List<String> fields = new ArrayList<>();
		Field[] declaredFields = classz.getDeclaredFields();
		for (Field field : declaredFields) {
			fields.add(field.getName());
		}
		String[] sortByFieldsArr = sortBy.split(",");
		for (String sortByStr : sortByFieldsArr) {
			String cleanField = sortByStr.replace("-", "");
			if (fields.contains(cleanField)) {
				sortByFields.add(sortByStr);
			}
		}
	}

	public List<String> getSortByFieldsList() {
		return sortByFields;
	}

}
