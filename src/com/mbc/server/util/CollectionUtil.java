package com.mbc.server.util;

import java.util.List;

public class CollectionUtil {
	
	public static boolean isNullOrEmpty(final List<?> objects) {
        return objects == null || objects.isEmpty();
    }

    public static boolean isNotNullOrEmpty(final List<?> objects) {
        return !isNullOrEmpty(objects);
    }

}
