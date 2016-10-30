package com.mbc.server.util;

import java.util.Arrays;

public class StringUtil {
	
	public static boolean isEmptyOrNull(final String object) {
        return ObjectUtil.isNull(object) || object.trim().isEmpty();
    }

    public static boolean isNotEmptyOrNull(final String object) {
        return !isEmptyOrNull(object);
    }

    public static String trimToNull(final String object) {
        return isEmptyOrNull(object) ? "" : object.trim();
    }

    public static String trimToEmpty(final String object) {
        return isEmptyOrNull(object) ? "" : object.trim();
    }
    
    public static boolean containsInStringArray(final String stringList, final String delimiter, final String query) {
        if (isEmptyOrNull(stringList)) return false;
        if (isEmptyOrNull(delimiter)) return false;
        if (isEmptyOrNull(query)) return false;

        String[] array = stringList.trim().split(delimiter);
        return Arrays.asList(array).contains(query);
    }
	
}
