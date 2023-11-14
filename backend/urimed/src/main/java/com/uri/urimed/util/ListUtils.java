package com.uri.urimed.util;

import java.util.List;

/**
 * Utility class for List operations.
 *
 * @author Luan Nadaletti
 */
public class ListUtils {

    /**
     * Checks if the provided list is null.
     *
     * @param list the list to check
     * @return true if the list is null, false otherwise
     */
    public static boolean isNull(List<?> list) {
        return list == null;
    }

    /**
     * Checks if the provided list is empty.
     * <p>
     * Note: This method does not check if the list is null.
     * If the list is null, this method will throw a NullPointerException.
     * </p>
     *
     * @param list the list to check
     * @return true if the list is empty, false otherwise
     * @throws NullPointerException if the list is null
     */
    public static boolean isEmpty(List<?> list) {
        return list.isEmpty();
    }

    /**
     * Checks if the provided list is null or empty.
     *
     * @param list the list to check
     * @return true if the list is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(List<?> list) {
        return isNull(list) || isEmpty(list);
    }
}
