package com.ddlabs.core.util;

/**This is a test harness class to test
 * the wild card patter on a particular
 * String.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class WildCardTestharness
{
	public static void main(String[] args) 
	{
		String name = "John";
		String searchString = "John";
		
		WildCardStringFinder finder = new WildCardStringFinder();
		boolean flag = finder.isStringMatching(name, searchString);
		System.out.println(flag);
	}
}
