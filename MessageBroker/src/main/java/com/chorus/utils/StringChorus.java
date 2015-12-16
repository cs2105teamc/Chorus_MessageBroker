package com.chorus.utils;

/**
 * <h1> Static String Methods </h1>
 * 
 * Useful methods for string manipulation
 * 
 * @author garfijam
 * @version 1.0
 * @since 2015-12-07
 */
public final class StringChorus {

	/**
	 * <h2>IsNullorEmpty</h2>
	 * 
	 * Returns true if string parameter is null or empty
	 * 
	 * @param param String
	 * @return true if null or empty; false is there is an object or non-zero length
	 */
	public static boolean IsNullorEmpty(String param) {
		
		if ((param == null) 
				|| (param.isEmpty())) {
			return true;
		}
		
		return false;
	}
}
