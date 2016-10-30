package com.mbc.server.util;

public class ObjectUtil {
	
	public static <T> T nullityCheckException(T object, String exceptionMessage) throws Exception {
        if (object == null) { throw new Exception(exceptionMessage); }
        return object;
    }

    public static boolean isNull(final Object object) {
        return object == null;
    }

    public static boolean isNotNull(final Object object) {
        return !isNull(object);
    }

    public static boolean isExistingInArray(final String object, final String[] array, final boolean ignoreCase) {
        for (final String element : array) {
            if (ignoreCase) {
                if (object.equalsIgnoreCase(element)) { return true; }
            } else {
                if (object.equals(element)) { return true; }
            }
        }
        return false;
    }

    public static boolean isInteger(final Object object) {
        if (isNull(object)) return false;

        try {
            Integer.valueOf(object.toString());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static Integer convertToInteger(final String object) {
        if (isInteger(object)) return Integer.valueOf(object);
        return null;
    }

    public static boolean equals(Object object0, Object object1) {
        if(object0 == null && object1 == null) {
            return true;
        } else if(object0 == null || object1 == null) {
            return false;
        } else {
            return object0.equals(object1);
        }
    }
	
}
