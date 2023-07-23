package com.venturi.technology.librarysystem.helper;

import java.util.HashMap;
import java.util.Map;

public class AliasObjectMapper {
	
	private final Map<String, Object> aliasToIndexMap = new HashMap<>();

	public AliasObjectMapper(Object[] objects, String[] aliases) {
		for (int i = 0; i < objects.length; i++) {
			aliasToIndexMap.put(aliases[i].toLowerCase(), objects[i]);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String alias) {
		return (T) aliasToIndexMap.get(alias.toLowerCase());
	}
}
