package br.com.nils.restfulapi.app;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

public class QueryStringFilter {

	private Map<String, List<String>> filterFields = new HashMap<>();

	// price=gte:10&price=lte:100
	public QueryStringFilter(MultivaluedMap<String, String> queryParameters, Class<?> classz) {
		Map<String, List<String>> parseQueryString = parseQueryString(queryParameters);
		Field[] declaredFields = classz.getDeclaredFields();
		for (Field field : declaredFields) {
			String key = field.getName();
			if (parseQueryString.containsKey(key)) {
				filterFields.put(key, parseQueryString.get(key));
			}
		}
	}

	private Map<String, List<String>> parseQueryString(MultivaluedMap<String, String> queryParameters) {
		HashMap<String, List<String>> hashMap = new HashMap<>();
		Set<Entry<String, List<String>>> entrySet = queryParameters.entrySet();
		Iterator<Entry<String, List<String>>> iterator = entrySet.iterator();
		if (!iterator.hasNext()) {
			return hashMap;
		}
		do {
			Entry<String, List<String>> next = iterator.next();
			String key = next.getKey();
			List<String> value = next.getValue();
			hashMap.put(key, value);
		} while (iterator.hasNext());
		return hashMap;
	}

	public Map<String, List<String>> getFilterFields() {
		return filterFields;
	}

}
