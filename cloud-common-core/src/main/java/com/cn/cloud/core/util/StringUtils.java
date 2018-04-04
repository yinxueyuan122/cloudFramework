package com.cn.cloud.core.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.util.HtmlUtils;

public class StringUtils {
	
	private static final char[] ALPHAS = new char[] { 'A', 'B', 'C', 'D', 'E',
		'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
		'S', 'T', 'U', 'X', 'Y', 'V', 'W', 'Z', 'a', 'b', 'c', 'd', 'e',
		'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
		's', 't', 'u', 'x', 'y', 'v', 'w', 'z' };

	public static final String DEFAULT_EMPTY_STRING = "";
	
	private static final Random GENERATOR = new Random(System
			.currentTimeMillis());
	
	/** For UTF-8 character set, 1 byte code */
	private static final int ONE_BYTE = 0x00007F;
	
	/** For UTF-8 character set, 3 byte code */
	private static final int THREE_BYTE = 0x00FFFF;
	
	/** For UTF-8 character set, 2 byte code */
	private static final int TWO_BYTE = 0x0007FF;
	
	/**
	 * Appends spaces to Stringwidth the input length. <br>
	 * 
	 * ex) addSpace("12345", 5) => "12345     "
	 * 
	 * @param str
	 *            string to be modified
	 * @param size
	 *            length of spaces
	 * @return string that is appended with spaces
	 */
	public static String addSpace(String str, int size) {
		StringBuffer stringBuffer = new StringBuffer();
		if (str == null) {
			if (size == 0) {
				return null;
			}
		} else {
			stringBuffer.append(str);
		}
		for (int j = 0; j < size; j++) {
			stringBuffer.append(' ');
		}
		return stringBuffer.toString();
	}
	
	/**
	 * Appends a string to string array. <br>
	 * 
	 * ex) String[] test = {"aaa", "bbb", "ccc"}; addStringToArray(test, "ddd");
	 * 
	 * @param strings
	 *            string array to be modified
	 * @param str
	 *            string to be appended
	 * @return string array that is appended with string
	 */
	public static String[] addStringToArray(String[] strings, String str) {
		String newArray[] = new String[strings.length + 1];
		System.arraycopy(strings, 0, newArray, 0, strings.length);
		newArray[strings.length] = str;
		return newArray;
	}
	
	
	/**
	 * Joins the elements of the provided array into a single String containing
	 * the provided list of elements. ex) String[] test = {"aaa", "bbb", "ccc"};
	 * arrayToDelimitedString(test) => "aaa,bbb,ccc"
	 * 
	 * @param objects
	 *            the array of values to join together
	 * @return the joined String that is seperatd by comma
	 */
	public static String arrayToDelimitedString(Object[] objects) {
		return arrayToDelimitedString(objects, ",");
	}
	
	/**
	 * Joins the elements of the provided array into a single String containing
	 * the provided list of elements.
	 * 
	 * ex) String[] test = {"aaa", "bbb", "ccc"};
	 * arrayToDelimitedString(test,",") => "aaa,bbb,ccc"
	 * 
	 * @param objects
	 *            the array of values to join together
	 * @param delimiter
	 *            delimiter for conversioin
	 * @return the joined String
	 */
	public static String arrayToDelimitedString(Object[] objects,
			String delimiter) {
		if (objects == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < objects.length; i++) {
			if (i > 0 && delimiter != null) {
				sb.append(delimiter);
			}
			sb.append(objects[i]);
		}
		return sb.toString();
	}
	
	/**
	 * Joins the elements of the provided collection into a single String
	 * containing the provided list of elements with comma delimeter.
	 * 
	 * @param strings
	 *            the collection of values to join together
	 * @return the joined String that is seperatd by comma
	 */
	public static String collectionToDelimitedString(Collection<String> strings) {
		return collectionToDelimitedString(strings, ",");
	}
	
	/**
	 * Joins the elements of the provided collection into a single String
	 * containing the provided list of elements with delimeter.
	 * 
	 * @param strings
	 *            the collection of values to join together
	 * @param delimiter
	 *            delimiter for conversioin
	 * @return the joined String that is seperatd by delimeter
	 */
	public static String collectionToDelimitedString(
			Collection<String> strings, String delimiter) {
		if (strings == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = strings.iterator();
		int i = 0;
		for (; it.hasNext(); sb.append(it.next())) {
			if (i++ > 0 && delimiter != null) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}
	
	/**
	 * Converts a single String with comma delimiter to Set of String.
	 * 
	 * @param str
	 *            the single String to convert with comma delimiter
	 * @return Set of String values
	 */
	public static Set<String> commaDelimitedStringToSet(String str) {
		Set<String> set = new HashSet<String>();
		String tokens[] = tokenizeToStringArray(str);
		if (tokens == null) {
			return null;
		}
		for (int i = 0; i < tokens.length; i++) {
			set.add(tokens[i]);
		}
		return set;
	}
	
	/**
	 * Converts asterisk to space in a String.
	 * 
	 * ex) convertAsteriskToSpace("test**test") => "test  test"
	 * 
	 * @param str
	 *            string to be converted
	 * @return the converted string
	 */
	public static String asteriskToSpace(String str) {
		String target = "";
		target = str.replaceAll("\\*\\*", " ");
		target = target.replaceAll("\\*", " ");
		return target;
	}
	
	/**
	 * convert first letter to a big letter or a small letter.<br>
	 * 
	 * <pre>
	 * StringUtils.swapFirstLetterCase("Password") = "password'
	 * StringUtils.swapFirstLetterCase("password') = "Password"
	 * </pre>
	 * 
	 * @param str
	 *            String to be swapped
	 * @return String converting result
	 */
	public static String swapFirstLetterCase(String str) {
		if (Character.isLowerCase(str.substring(0, 1).toCharArray()[0])) {
			return changeFirstCharacterCase(true, str);
		} else {
			return changeFirstCharacterCase(false, str);
		}
	}
	
	/**
	 * Changes case of first character in a String.
	 * 
	 * ex) changeFirstCharacterCase(true, "abcd") => "Abcd"
	 * changeFirstCharacterCase(false, "ABCD") => "aBCD"
	 * 
	 * @param capitalize
	 *            flag for case (true : uppper case, false : lower case)
	 * @param str
	 *            string to be modified
	 * @return the modified string
	 */
	private static String changeFirstCharacterCase(boolean capitalize,
			String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		} else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}
	
	/**
	 * Compare two words in lexicographical order. if the input string or string
	 * to compare with is <code>null</code>, return -1.
	 * 
	 * @param sourceStr
	 *            input string
	 * @param targetStr
	 *            string to be compared with If return value is 0, the same
	 *            word, if it is under 0, the smaller one in lexicographical
	 *            order, if it is over 0, the bigger one in lexicographical
	 *            order.
	 * @see String#compareTo(String)
	 */
	public static int compareTo(String sourceStr, String targetStr) {
		if (sourceStr == null || targetStr == null) {
			return -1;
		}
		return sourceStr.compareTo(targetStr);
	}
	
	/**
	 * Compare two words in lexicographical order. if the input string or string
	 * to compare with is <code>null</code>, return -1.
	 * 
	 * @param sourceStr
	 *            input string
	 * @param targetStr
	 *            string to be compared with
	 * @return int If return value is 0, the same word, if it is under 0, the
	 *         smaller one in lexicographical order, if it is over 0, the bigger
	 *         one in lexicographical order.
	 * @see String#compareToIgnoreCase(String)
	 */
	public static int compareToIgnoreCase(String sourceStr, String targetStr) {
		if (sourceStr == null || targetStr == null) {
			return -1;
		}
		return sourceStr.compareToIgnoreCase(targetStr);
	}
	
	/**
	 * 
	 * Checks that the String contains certain characters.
	 * 
	 * 
	 * A <code>null</code> String will return <code>false</code>. A
	 * <code>null</code> invalid character array will return <code>false</code>.
	 * An empty String ("") always returns false.
	 * 
	 * <pre>
	 * StringUtils.containsAny(null, *)       = false
	 * StringUtils.containsAny(*, null)       = false
	 * StringUtils.containsAny("", *)         = false
	 * StringUtils.containsAny("ab", "".toCharArrray())      = false
	 * StringUtils.containsAny("abab", "xyz".toCharArray()) = false
	 * StringUtils.containsAny("ab1", 'xyz".toCharArray())  = false
	 * StringUtils.containsAny("xbz", "xyz".toCharArray())  = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param chars
	 *            an array of invalid chars, may be null
	 * @return false if it contains none of the invalid chars, or is null
	 */
	public static boolean containsAny(String str, char[] chars) {
		if (str == null || chars == null) {
			return false;
		}
	
		return containsAnyChar(str, chars);
	}
	
	private static boolean containsAnyChar(String str, char[] chars) {
		int strSize = str.length();
		int validSize = chars.length;
		for (int i = 0; i < strSize; i++) {
			char ch = str.charAt(i);
			for (int j = 0; j < validSize; j++) {
				if (chars[j] == ch) {
					return true;
				}
			}
		}
		return false;
	}
		
	/**
	 * Checks that the String contains certain characters.
	 * 
	 * A <code>null</code> String will return <code>false</code>. A
	 * <code>null</code> character array will return <code>false</code>. An
	 * empty String ("") always returns false.
	 * 
	 * <pre>
	 * StringUtils.containsAny(null, *)       = false
	 * StringUtils.containsAny(*, null)       = false
	 * StringUtils.containsAny("", *)         = false
	 * StringUtils.containsAny("ab", "")      = false
	 * StringUtils.containsAny("abab", "xyz") = false
	 * StringUtils.containsAny("ab1", "xyz")  = false
	 * StringUtils.containsAny("xbz", "xyz")  = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param chars
	 *            a String of invalid chars, may be null
	 * @return false if it contains none of the invalid chars, or is null
	 */
	public static boolean containsAny(String str, String chars) {
		if (str == null || chars == null) {
			return false;
		}
		return containsAnyChar(str, chars.toCharArray());
	}
	
	/**
	 * It returns true if string contains a sequence of the same character.
	 * 
	 * <pre>
	 * StringUtils.containsMaxOccurences("password", "2") = true
	 * StringUtils.containsMaxOccurences("my000", "3")    = true
	 * StringUtils.containsMaxOccurences("abbbbc", "5")   = false
	 * </pre>
	 * 
	 * @param str
	 *            original String
	 * @param maxSeqNumber
	 *            a sequence of the same character
	 * @return which contains a sequence of the same character
	 */
	public static boolean containsMaxOccurences(String str, String maxSeqNumber) {
		int occurence = 1;
		int max = NumberUtils.stringToInt(maxSeqNumber);
		if (str == null) {
			return false;
		}
	
		int sz = str.length();
		for (int i = 0; i < (sz - 1); i++) {
			if (str.charAt(i) == str.charAt(i + 1)) {
				occurence++;
	
				if (occurence == max)
					return true;
			} else {
				occurence = 1;
			}
		}
		return false;
	}
	
	/**
	 * Convert a string that may contain underscores to camel case.
	 * 
	 * @param str
	 *            the String to convert
	 * @return Camel case representation of the underscore string.
	 */
	public static String convertToCamelCase(String str) {
		return convertToCamelCase(str, '_');
	}
	
	/**
	 * Convert a camel case string to underscore representation.
	 * 
	 * @param str
	 *            Camel case name.
	 * @param delimiter
	 * 			  delimiter for conversioin
	 * @return Camel case representation of the inputString.
	 */
	public static String convertToCamelCase(String str, char delimiter) {
		StringBuilder result = new StringBuilder();
		boolean nextUpper = false;
		String allLower = str.toLowerCase();
	
		for (int i = 0; i < allLower.length(); i++) {
			char currentChar = allLower.charAt(i);
			if (currentChar == delimiter) {
				nextUpper = true;
			} else {
				if (nextUpper) {
					currentChar = Character.toUpperCase(currentChar);
					nextUpper = false;
				}
				result.append(currentChar);
			}
		}
		return result.toString();
	}
	
	/**
	 * Convert a camel case string to underscore representation.
	 * 
	 * @param str
	 *            the String to convert
	 * @return Underscore representation of the camel case string.
	 */
	public static String convertToUnderScore(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			char currentChar = str.charAt(i);
			// This is starting at 1 so the result does not end up with an
			// underscore at the begin of the value
			if (i > 0 && Character.isUpperCase(currentChar)) {
				result = result.concat("_");
			}
			result = result.concat(Character.toString(currentChar)
					.toLowerCase());
		}
		return result;
	}
	
	/**
	 * Compare the first and second string. If they are the same, return the
	 * third string, and if they are different, return the fourth string.
	 * 
	 * @param source
	 *            value to compare.
	 * @param target
	 *            value that is compared against source
	 * @param result
	 *            value returned
	 * @param base
	 *            optional string
	 * @return String to be decoded
	 */
	public static String decode(String source, String target, String result,
			String base) {
		if (source == null && target == null) {
			return result;
		} else if (source == null && target != null) {
			return base;
		} else if (source.trim().equals(target)) {
			return result;
		}
		return base;
	}
	
	/**
	 * Return byte length for each letter of related character
	 * 
	 * @param ch
	 *            one English letter
	 * @return byte length of one related English letter
	 */
	public static int getByteLength(char ch) {
		int charCode = ch;
	
		if (charCode <= ONE_BYTE) {
			return 1;
		} else if (charCode <= TWO_BYTE) {
			return 2;
		} else if (charCode <= THREE_BYTE) {
			return 3;
		} else {
			return 4;
		}
	}
	
	/**
	 * Return total length of related string calculated in byte unit. If string
	 * is null, return -1.
	 * 
	 * @param str
	 *            input string
	 * @return int length of string
	 */
	public static int getByteLength(String str) {
		if (str == null) {
			return -1;
		}
		int size = 0;
	
		for (int i = 0; i < str.length(); i++) {
			size += getByteLength(str.charAt(i));
		}
		return size;
	}
	
	/**
	 * For given string, return number that includes related characters. If
	 * given string or character to be searched for is <code>null</code>, return
	 * -1.
	 * 
	 * @param str
	 *            input string
	 * @param chars
	 *            character arrangement to be searched for
	 * @return int number of strings included
	 */
	public static int countMatches(String str, char[] chars) {
		return countMatches(str, new String(chars));
	}
	
	/**
	 * For the given string, return the number that includes the related string.
	 * 
	 * @param str
	 *            string to search in. Return 0 if this is null.
	 * @param sub
	 *            string to search for. Return 0 if this is null.
	 * @see org.springframework.util.StringUtils#countOccurrencesOf(String,
	 *      String)
	 */
	public static int countMatches(String str, String sub) {
		return org.springframework.util.StringUtils
				.countOccurrencesOf(str, sub);
	}
	
	/**
	 * 
	 * For given string regardless of upper case or lower case letters, return
	 * number that includes related characters. If given string or character to
	 * be searched for is <code>null</code>, return -1.
	 * 
	 * @param str
	 *            input string
	 * @param chars
	 *            string of characters to be searched for
	 * @return int number of strings included
	 */
	public static int countMatchesIgnoreCase(String str, char[] chars) {
		char[] lowerChar = new char[chars.length];
		for (int j = 0; j < chars.length; j++) {
			String res = String.valueOf(chars[j]).toLowerCase();
			lowerChar[j] = res.charAt(0);
		}
		return countMatches(str.toLowerCase(), lowerChar);
	}
	
	/**
	 * For the given string, return the number that includes related string,
	 * regardless of upper case or lower case letters.
	 * 
	 * @param str
	 *            string to search in. Return 0 if this is null.
	 * @param sub
	 *            string to search for. Return 0 if this is null.
	 * @see org.springframework.util.StringUtils#countOccurrencesOf(String,
	 *      String)
	 */
	public static int countMatchesIgnoreCase(String str, String sub) {
		return org.springframework.util.StringUtils.countOccurrencesOf(str
				.toLowerCase(), sub.toLowerCase());
	}
	
	/**
	 * Break a string into specific tokens and return a String of last location.<br>
	 * 
	 * <pre>
	 * StringUtils.getLastString(&quot;password*password*a*b*c&quot;, &quot;*&quot;) = &quot;c&quot;
	 * </pre>
	 * 
	 * @param str
	 *            original String
	 * @param token
	 *            specific tokens
	 * @return String of last location
	 */
	public static String getLastString(String str, String token) {
		StringTokenizer tokenizer = new StringTokenizer(str, token);
		String lastStr = "";
		while (tokenizer.hasMoreTokens()) {
			lastStr = tokenizer.nextToken();
		}
		return lastStr;
	}
	
	/**
	 * Return length of related string. If string is <code>null</code>, return
	 * -1.
	 * 
	 * @param str
	 *            input string
	 * @return int length of string
	 * @see String#length()
	 */
	public static int getLength(String str) {
		if (str == null) {
			return -1;
		}
		return str.length();
	}
	
	/**
	 * Return a specific length of random string.
	 * 
	 * @param size
	 *            the length of random string to be developed
	 * @return String random string
	 */
	public static String getRandomString(int size) {
		return randomAlphabetic(size);
	}
	
	/**
	 * Return random string between minimum and maximum digits.
	 * 
	 * @param minSize
	 *            minimum digits
	 * @param maxSize
	 *            maximum digits
	 * @return String random string
	 */
	public static String getRandomString(int minSize, int maxSize) {
		Random generator = new Random(System.currentTimeMillis());
		int randomLength = generator.nextInt(maxSize - minSize) + minSize;
	
		return randomAlphabetic(randomLength);
	}
	
	/**
	 * Return random string of a defined length between specific alphabet
	 * character.
	 * 
	 * @param size
	 *            the length of random string to be made
	 * @param startChar
	 *            the first letter of the random string being made
	 * @param endChar
	 *            the last letter of the random string being made
	 * @return String random string
	 */
	public static String getRandomString(int size, char startChar, char endChar) {
		int startInt = Integer.valueOf(startChar);
		int endInt = Integer.valueOf(endChar);
	
		int gap = endInt - startInt;
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int chInt;
			do {
				chInt = StringUtils.GENERATOR.nextInt(gap + 1) + startInt;
			} while (!Character.toString((char) chInt).matches("^[a-zA-Z]$"));
			buf.append((char) chInt);
		}
		return buf.toString();
	}
	
	/**
	 * Return a specific length of given character set string.
	 * 
	 * @param count
	 *            length of random string to be made
	 * @param charset
	 *            supported character set
	 * @return String random string
	 * @throws UnsupportedEncodingException
	 */
	public static String getRandomStringByCharset(int size, String charset)
			throws UnsupportedEncodingException {
		String randomStr = getRandomString(size);
		return convertStringCharset(randomStr, charset);
	}
	
	/**
	 * Return a specific length of random string in Korean characters (UTF-8
	 * only).
	 * 
	 * @param size
	 *            length of random string to be made
	 * @return String random string in Korean characters
	 * @throws UnsupportedEncodingException
	 */
	public static String getRandomStringByKorean(int size)
			throws UnsupportedEncodingException {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < size; i++) {
			buf.append((char) (StringUtils.GENERATOR.nextInt(11172) + 0xAC00));
		}
		return buf.toString();
	}
	
	/**
	 * Return token list which is separated by ","
	 * 
	 * @param str
	 *            the String to parse
	 * @return token list which is separated by ","
	 */
	public static List<String> getTokens(String str) {
		return getTokens(str, ",");
	}
	
	/**
	 * Return token list which is separated by input delimiter
	 * 
	 * @param str
	 *            the String to parse
	 * @param delimeter
	 *            the String used as the delimiter
	 * @return token list
	 */
	public static List<String> getTokens(String str, String delimeter) {
		List<String> tokens = new ArrayList<String>();
	
		if (str != null) {
			StringTokenizer st = new StringTokenizer(str, delimeter);
			while (st.hasMoreTokens()) {
				String en = st.nextToken().trim();
				tokens.add(en);
			}
		}
		return tokens;
	}
	
	/**
	 * Checks if String length is greater than zero. ex) "test" => true "" =>
	 * false
	 * 
	 * @param str
	 *            the String to check
	 * @return true if String length is greater than zero, false if not or null
	 *         string input
	 */
	public static boolean hasLength(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * Checks if String contains no whitespace. ex) hasText(" test ") => true
	 * hasText(" ") => false hasText("") => false
	 * 
	 * @param str
	 *            the String to check
	 * @return true if String contains no whitespace, false if not or null
	 *         string input
	 */
	public static boolean hasText(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Converts hex code to UniCode String
	 * 
	 * @param str
	 *            the String to convert
	 * @return UniCode String
	 */
	public static String hexToString(String str) {
		String inStr = str;
		char inChar[] = inStr.toCharArray();
		StringBuffer sb = new StringBuffer();
	
		for (int i = 0; i < inChar.length; i += 4) {
			String hex = str.substring(i, i + 4);
			sb.append((char) Integer.parseInt(hex, 16));
		}
		return sb.toString();
	}
	
	/**
	 * If the string that is searched for is included in the input string,
	 * return the index of the first string. If not found, return -1. If the
	 * input string or the string that is searched for is not null, return -1.
	 * 
	 * @param str
	 *            input string
	 * @param search
	 *            string that is searched for
	 * @return int index of the first string
	 * @see String#indexOf(String)
	 */
	public static int indexOf(String str, String search) {
		if (str == null || search == null) {
			return -1;
		}
		return str.indexOf(search);
	}
	
	/**
	 * If the string to be searched for is included in input string, regardless
	 * of upper case or lower case letters, return the first string. If not
	 * found, return -1. Even if the input string or string to be searched for
	 * is null, return -1.
	 * 
	 * @param str
	 *            input string
	 * @param search
	 *            string to be compared with
	 * @return index of the first string
	 * @see String#indexOf(String)
	 */
	public static int indexOfIgnoreCase(String str, String search) {
		if (str == null || search == null) {
			return -1;
		}
		return str.toLowerCase().indexOf(search.toLowerCase());
	}
	
	/**
	 * If string is null or empty string, return true. <br>
	 * If not, return false.
	 * 
	 * <pre>
	 * StringUtils.isEmpty("")    = true
	 * StringUtils.isEmpty(null)  = true
	 * StringUtils.isEmpty("abc") = false
	 * </pre>
	 * 
	 * @param str
	 *            original String
	 * @return which empty string or not.
	 */
	public static boolean isEmpty(String str) {
	    if (str != null) {
	        int len = str.length();
	        for (int i = 0; i < len; ++i) {
	            if (str.charAt(i) > ' ') {
	                return false;
	            }
	        }
	    }
	    return true;
	}
	
	/**
	 * Determine whether a (trimmed) string is empty
	 * 
	 * @param str
	 *            The text to check.
	 * @return Whether empty.
	 */
	public static boolean isEmptyTrimmed(String str) {
		if(str == null)
			return true;
		return isEmpty(str.trim());
	}
	
	/**
	 * Checks if the String contains only Korean characters. ex) isHangul('臧�)
	 * => true isHangul('T') => false
	 * 
	 * @param achar
	 *            the character to check, may be null
	 * @return true if the String contains only Korean Language, false if not
	 */
	public static boolean isHangul(char achar) {
		String unicodeBlock = Character.UnicodeBlock.of(achar).toString();
		return "HANGUL_JAMO".equals(unicodeBlock)
				|| "HANGUL_SYLLABLES".equals(unicodeBlock)
				|| "HANGUL_COMPATIBILITY_JAMO".equals(unicodeBlock);
	}
	
	/**
	 * Checks if the String contains only Korean characters or any Korean
	 * characters. ex) isHangul("臧�倶雼�, true) => true isHangul("臧�倶雼bc", true) =>
	 * false isHangul("臧�bc", false) => true isHangul("abcd", false) => false
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param checkForAll
	 *            flag for check only Korean characters(true) or any Korean
	 *            characters(false)
	 * @return true if the String contains only Korean Language(when checkForAll
	 *         is true) or any Korean characters(when checkForAll is false),
	 *         false if not
	 */
	public static boolean isHangul(String str, boolean checkForAll) {
		char chars[] = str.toCharArray();
		if (!checkForAll) {
			for (int i = 0; i < chars.length; i++) {
				if (isHangul(chars[i])) {
					return true;
				}
			}
			return false;
		} else {
			for (int i = 0; i < chars.length; i++) {
				if (!isHangul(chars[i])) {
					return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * Checks if the String contains only letters. ex) isLetter("test") => true
	 * isLetter("test#$%") => false isLetter("test123") => false isLetter("")=>
	 * false
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return true if String contains only letters, false if not or null or
	 *         empty string input
	 */
	public static boolean isLetter(String str) {
		if (isEmpty(str)) {
			return false;
		}
	
		char chars[] = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!Character.isLetter(chars[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if the String contains only letters or digits. ex)
	 * isLetterOrDigit("12abC") => true isLetterOrDigit("12@#%")=> false
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return true if String contains only letters or digits, false if not or
	 *         null or empty string input
	 */
	public static boolean isLetterOrDigit(String str) {
		if (isEmpty(str)) {
			return false;
		}
		char chars[] = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!Character.isLetterOrDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * If string is null or empty string, return false. <br>
	 * If not, return true.
	 * 
	 * <pre>
	 * StringUtils.isNotEmpty("")    = false
	 * StringUtils.isNotEmpty(null)  = false
	 * StringUtils.isNotEmpty("abc") = true
	 * </pre>
	 * 
	 * @param str
	 *            original String
	 * @return which empty string or not.
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * Checks if the CharSequence contains only whitespace. ex)
	 * isWhiteSpaceOnly("   ") => true isWhiteSpaceOnly("") => true
	 * isWhiteSpaceOnly("test") => false
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return true if String contains only whitespace, false if not or null
	 *         string input
	 */
	public static boolean isWhiteSpaceOnly(String str) {
		if (str == null) {
			return false;
		} else {
			return isEmptyTrimmed(str);
		}
	}
	
	/**
	 * Gets the leftmost len characters of a String. ex) left("1234567", 3) =>
	 * "123"
	 * 
	 * @param str
	 *            the String to get the leftmost characters from, may be null
	 * @param size
	 *            the length of the required String
	 * @return the leftmost characters, null if null String input
	 */
	public static String left(String str, int size) {
		if (str == null) {
			return null;
		} else if (size <= 0 || str.length() <= size) {
			return str;
		} else {
			return str.substring(0, size);
		}
	}
	
	/**
	 * For related string, fill the input length from the left with space.<br>
	 * 
	 * <pre>
	 * StringUtils.leftPad(null, *) = null
	 * StringUtils.leftPad("", 3) = "   "
	 * StringUtils.leftPad("bat", 3) = "bat"
	 * StringUtils.leftPad("bat", 5) = "  bat"
	 * StringUtils.leftPad("bat", 1) = "bat"
	 * StringUtils.leftPad("bat", -1) = "bat"
	 * </pre>
	 * 
	 * @param str
	 *            string to be modified
	 * @param size
	 *            size that includes letter for padding
	 * @return strings for padding <code>null</code> if null String input
	 */
	public static String leftPad(String str, int size) {
		return leftPad(str, size, ' ');
	}
	
	/**
	 * For related string, fill the input length from the left with defined
	 * letter.<br>
	 * 
	 * <pre>
	 * StringUtils.leftPad(null, *, *) = null
	 * StringUtils.leftPad("", 3, 'z') = "zzz"
	 * StringUtils.leftPad("bat", 3, 'z') = "bat"
	 * StringUtils.leftPad("bat", 5, 'z') = "zzbat"
	 * StringUtils.leftPad("bat", 1, 'z') = "bat"
	 * StringUtils.leftPad("bat", -1, 'z') = "bat"
	 * </pre>
	 * 
	 * @param str
	 *            string to be modified
	 * @param size
	 *            size that includes letter for padding
	 * @param padChar
	 *            letter to fill in
	 * @return string that is padded <code>null</code> if null String input
	 */
	public static String leftPad(String str, int size, char padChar) {
		return padString(str, size, String.valueOf(padChar), true);
	}
	
	/**
	 * For related string, fill the input length from the left with defined
	 * string.<br>
	 * 
	 * <pre>
	 * StringUtils.leftPad(null, *, *) = null
	 * StringUtils.leftPad("", 3, "z") = "zzz"
	 * StringUtils.leftPad("bat", 3, "yz") = "bat"
	 * StringUtils.leftPad("bat", 5, "yz") = "yzbat"
	 * StringUtils.leftPad("bat", 8, "yz") = "yzyzybat"
	 * StringUtils.leftPad("bat", 1, "yz") = "bat"
	 * StringUtils.leftPad("bat", -1, "yz") = "bat"
	 * StringUtils.leftPad("bat", 5, null) = "  bat"
	 * StringUtils.leftPad("bat", 5, "") = "  bat"
	 * </pre>
	 * 
	 * @param str
	 *            string to be modified
	 * @param size
	 *            size that includes letter for padding
	 * @param padStr
	 *            letter to fill in
	 * @return string that is padded <code>null</code> if null String input
	 */
	public static String leftPad(String str, int size, String padStr) {
		return padString(str, size, padStr, true);
	}
	
	/**
	 * Delete the space string on the left of the string.
	 * 
	 * @param str
	 *            input string
	 * @return string that deleted spaces
	 * @see org.springframework.util.StringUtils#trimLeadingWhitespace(String)
	 */
	public static String leftTrim(String str) {
		return org.springframework.util.StringUtils.trimLeadingWhitespace(str);
	}
	
	/**
	 * Converts CR/LF characters to a space in a String. ex)
	 * newLineToSpace("\r\ntest") => " test"
	 * 
	 * @param str
	 *            the String to convert
	 * @return the converted string
	 */
	public static String newLineToSpace(String str) {
		return str.replace("\r\n", " ");
	}
	
	/**
	 * Trim the original string. If the original string is null or string length
	 * size is zero, return the empty string.
	 * 
	 * @param org
	 *            original string
	 * @return trimmed string
	 */
	public static String nullToString(String org) {
		return nullToString(org, "");
	}
	
	/**
	 * Trim the original string. If the original string is null or string length
	 * size is zero, return the converted string.
	 * 
	 * @param str
	 *            original string
	 * @param defaultStr
	 *            converted string
	 * @return trimmed string
	 */
	public static String nullToString(String str, String defaultStr) {
		if (isEmptyTrimmed(str)) {
			return defaultStr;
		} else {
			return str.trim();
		}
	}
	
	/**
	 * Returns empty string if the given String is null, returns given String if
	 * not. ex) nullToEmpty("test") => "test" String test = null;
	 * nullToEmpty(test) => ""
	 * 
	 * @param str
	 *            the String to check
	 * @return empty string if the given String is null, given string if not
	 */
	public static String nullToEmpty(String str) {
		if (isEmpty(str)) {
			return DEFAULT_EMPTY_STRING;
		} else {
			return str;
		}
	}
	
	/**
	 * Returns the default Object if the given Object is null. ex) String test =
	 * null; System.out.println(nullToObject(test, "NULL TEST")) => "NULL TEST"
	 * 
	 * String test = "test"; System.out.println(nullToObject(test, "NULL TEST"))
	 * => "test"
	 * 
	 * @param obj
	 *            the Object to check
	 * @param defaultObj
	 *            the default Object
	 * @return Returns the default Object if the given Object is null, returns
	 *         the given Object if not
	 */
	public static Object nullToObject(Object obj, Object defaultObj) {
		return obj != null ? obj : defaultObj;
	}
	
	/**
	 * Gets the String with a specified character. Pad to a size of size ex)
	 * repeat(5, 'e') => "eeeee"
	 * 
	 * @param size
	 *            the number of repeat
	 * @param ch
	 *            the character to repeat
	 * @return the repeated String
	 */
	public static String repeat(int size, char ch) {
		return leftPad("", size, ch);
	}
	
	private static String padString(String str, int size, String padStr,
			boolean isLeft) {
		if (str == null) {
			return null;
		}
		int originalStrLength = str.length();
	
		if (size < originalStrLength)
			return str;
	
		int difference = size - originalStrLength;
	
		String tempPad = "";
		if (difference > 0) {
			if (padStr == null || "".equals(padStr)) {
				padStr = " ";
			}
			do {
				for (int j = 0; j < padStr.length(); j++) {
					tempPad += padStr.charAt(j);
					if (str.length() + tempPad.length() >= size) {
						break;
					}
				}
			} while (difference > tempPad.length());
			if (isLeft) {
				str = tempPad + str;
			} else {
				str = str + tempPad;
			}
		}
	
		return str;
	}
	
	private static String randomAlphabetic(int size) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < size; i++) {
			buf.append(ALPHAS[StringUtils.GENERATOR.nextInt(52)]);
		}
		return buf.toString();
	}
	
	/**
	 * For input strings, remove all strings to be deleted.
	 * 
	 * @param str
	 *            input string
	 * @param charsToDelete
	 *            string to be deleted
	 * @return String deleted string
	 * @see org.springframework.util.StringUtils#deleteAny(String, String)
	 */
	public static String deleteAny(String str, String charsToDelete) {
		return org.springframework.util.StringUtils.deleteAny(str,
				charsToDelete);
	}
	
	/**
	 * Removes all occurrences of given chars from within the source string.<br>
	 * ex) char[] ch = new char[2]; ch[0] = 'b'; ch[1] = 'z';
	 * deleteAny("AbbzzB", ch)) => "AB"
	 * 
	 * @param str
	 *            the source String to search
	 * @param charsToDelete
	 *            chars to search for (case insensitive) and remove
	 * @return the substring with given chars removed if found
	 */
	public static String deleteAny(String str, char[] charsToDelete) {
		return deleteAny(str, new String(charsToDelete));
	}
	
	/**
	 * Removes all occurrences of a character from within the source string. ex)
	 * deleteAny("ABBBBBC", 'B') => "AC"
	 * 
	 * @param str
	 *            the source String to search
	 * @param charToDelete
	 *            the char to search for and remove
	 * @return the substring with the char removed if found
	 */
	public static String deleteAny(String str, char charToDelete) {
		return deleteAny(str, String.valueOf(charToDelete));
	}
	
	/**
	 * For input strings, remove matched strings to be deleted.
	 * 
	 * @param str
	 *            input string
	 * @param subStr
	 *            string to be deleted
	 * @return String deleted string
	 * @see org.springframework.util.StringUtils#delete(String, String)
	 */
	public static String deleteMatches(String str, String subStr) {
		return org.springframework.util.StringUtils.delete(str, subStr);
	}
	
	/**
	 * For input strings, remove matched strings to be deleted.
	 * 
	 * <pre>
	 * StringUtils.deleteFirstMatches(&quot;pass*word&quot;, &quot;*&quot;) = &quot;password&quot;
	 * </pre>
	 * 
	 * @param str
	 *            original String
	 * @param deletedStr
	 *            String to be deleted
	 * @return converting result
	 */
	public static String deleteFirstMatches(String str, String deletedStr) {
		int startIndex = str.indexOf(deletedStr);
		if (startIndex != -1) {
			int endIndex = deletedStr.length() + startIndex;
			return str.substring(0, startIndex) + str.substring(endIndex);
		}
		return str;
	}
	
	/**
	 * Delete all space strings.
	 * 
	 * @param str
	 *            input string
	 * @return string that deleted space
	 * @see org.springframework.util.StringUtils#trimAllWhitespace(String)
	 */
	public static String removeWhitespace(String str) {
		return org.springframework.util.StringUtils.trimAllWhitespace(str);
	}
	
	/**
	 * Replaces each substring of this string that matches the given regular
	 * expression with the given replacement.
	 * 
	 * @param str
	 *            input string
	 * @param regex
	 *            regular expression
	 * @param replacement
	 *            given replacement
	 * @return changed string
	 * @see String#replaceAll(String, String)
	 */
	public static String replaceAll(String str, String regex, String replacement) {
		if (str == null) {
			return null;
		}
		return str.replaceAll(regex, replacement);
	}
	
	/**
	 * Replaces the first substring of this string that matches the given
	 * regular expression with the given replacement.
	 * 
	 * @param src
	 *            input string
	 * @param regex
	 *            regular expression
	 * @param replacement
	 *            given replacement
	 * @return changed string
	 * @see String#replaceFirst(String, String)
	 */
	public static String replaceFirst(String src, String regex,
			String replacement) {
		if (src == null) {
			return null;
		}
		return src.replaceFirst(regex, replacement);
	}
	
	/**
	 * Replaces the last substring of this string that matches the given regular
	 * expression with the given replacement.
	 * 
	 * @param str
	 *            input string
	 * @param regex
	 *            regular expression
	 * @param replacement
	 *            given replacement
	 * @return changed string
	 */
	public static String replaceLast(String str, String regex,
			String replacement) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find()) {
			return str;
		}
		int lastMatchStart = 0;
		do {
			lastMatchStart = matcher.start();
		} while (matcher.find());
		matcher.find(lastMatchStart);
		StringBuffer sb = new StringBuffer(str.length());
		matcher.appendReplacement(sb, replacement);
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	/**
	 * Unescape string that includes HTML escape characters.
	 * 
	 * @param input
	 *            the (unescaped) input string
	 * @return the escaped string
	 * @see HtmlUtils#htmlEscape(String)
	 */
	public static String htmlEscape(String input) {
		return HtmlUtils.htmlEscape(input);
	}
	
	/**
	 * Input string to HTML tag format.
	 * 
	 * @param input
	 *            the (escaped) input string
	 * @return the unescaped string
	 * @see HtmlUtils#htmlUnescape(String)
	 */
	public static String htmlUnescape(String input) {
		return HtmlUtils.htmlUnescape(input);
	}
	
	/**
	 * Reverses a String as per {@link StringBuffer#reverse()}.
	 * 
	 * <A code>null</code> String returns <code>null</code>.
	 * 
	 * @param str
	 *            the String to reverse, may be null
	 * @return the reversed String, <code>null</code> if null String input
	 */
	public static String reverse(String str) {
		if (str == null) {
			return null;
		}
		return new StringBuilder(str).reverse().toString();
	}
	
	/**
	 * Gets the rightmost len characters of a String. ex) right("1234567", 3) =>
	 * "567"
	 * 
	 * @param str
	 *            the String to get the rightmost characters from, may be null
	 * @param size
	 *            the length of the required String
	 * @return the rightmost characters, null if null String input
	 */
	public static String right(String str, int size) {
		if (str == null) {
			return null;
		} else if (size <= 0 || str.length() <= size) {
			return str;
		} else {
			return str.substring(str.length() - size);
		}
	}
	
	/**
	 * For related string, fill the input length from the right with space.<br>
	 * 
	 * <pre>
	 * StringUtils.rightPad(null, *) = null
	 * StringUtils.rightPad("", 3) = "   "
	 * StringUtils.rightPad("bat", 3) = "bat"
	 * StringUtils.rightPad("bat", 5) = "bat  "
	 * StringUtils.rightPad("bat", 1) = "bat"
	 * StringUtils.rightPad("bat", -1) = "bat"
	 * </pre>
	 * 
	 * @param str
	 *            string to be modified
	 * @param size
	 *            size that includes letter for padding
	 * @return string that is padded <code>null</code> if null String input
	 */
	public static String rightPad(String str, int size) {
		return rightPad(str, size, ' ');
	}
	
	/**
	 * For related string, fill the input length from the right with defined
	 * letter.<br>
	 * 
	 * <pre>
	 * StringUtils.rightPad(null, *, *) = null
	 * StringUtils.rightPad("", 3, 'z') = "zzz"
	 * StringUtils.rightPad("bat", 3, 'z') = "bat"
	 * StringUtils.rightPad("bat", 5, 'z') = "batzz"
	 * StringUtils.rightPad("bat", 1, 'z') = "bat"
	 * StringUtils.rightPad("bat", -1, 'z') = "bat"
	 * </pre>
	 * 
	 * @param str
	 *            string to be modified
	 * @param size
	 *            size that includes letter for padding
	 * @param padChar
	 *            letter for padding
	 * @return string that is padded <code>null</code> if null String input
	 */
	public static String rightPad(String str, int size, char padChar) {
		return padString(str, size, String.valueOf(padChar), false);
	}
	
	/**
	 * For related string, fill the input length from the right with defined
	 * string.<br>
	 * 
	 * <pre>
	 * StringUtils.rightPad(null, *, *) = null
	 * StringUtils.rightPad("", 3, "z") = "zzz"
	 * StringUtils.rightPad("bat", 3, "yz") = "bat"
	 * StringUtils.rightPad("bat", 5, "yz") = "batyz"
	 * StringUtils.rightPad("bat", 8, "yz") = "batyzyzy"
	 * StringUtils.rightPad("bat", 1, "yz") = "bat"
	 * StringUtils.rightPad("bat", -1, "yz") = "bat"
	 * StringUtils.rightPad("bat", 5, null) = "bat  "
	 * StringUtils.rightPad("bat", 5, "") = "bat  "
	 * </pre>
	 * 
	 * @param str
	 *            the String to pad out, may be null
	 * @param size
	 *            the size to pad to
	 * @param padStr
	 *            the String to pad with, null or empty treated as single space
	 * @return string that is padded <code>null</code> if null String input
	 */
	public static String rightPad(String str, int size, String padStr) {
		return padString(str, size, padStr, false);
	}
	
	/**
	 * 
	 * Delete the space string on the right of the string.
	 * 
	 * @param str
	 *            input string
	 * @return string that deleted spaces
	 * @see org.springframework.util.StringUtils#trimTrailingWhitespace(String)
	 */
	public static String rightTrim(String str) {
		return org.springframework.util.StringUtils.trimTrailingWhitespace(str);
	}
	
	/**
	 * substring the leftmost len characters of a String with ellipsis. ex)
	 * abbreviateFromLeft("12345678", 3) => "123..."
	 * 
	 * @param str
	 *            the String to get the leftmost characters from, may be null
	 * @param size
	 *            the length of the required String
	 * @return the leftmost characters with ellipsis, null if null String input
	 */
	public static String abbreviateFromLeft(String str, int size) {
		if (str == null) {
			return null;
		} else if (size <= 0 || str.length() <= size) {
			return str;
		} else {
			return str.substring(0, size) + "...";
		}
	}
	
	/**
	 * substring the rightmost len characters of a String with ellipsis. ex)
	 * abbreviateFromRight("12345678", 3) => "...678"
	 * 
	 * @param str
	 *            the String to get the rightmost characters from, may be null
	 * @param size
	 *            the length of the required String
	 * @return the rightmost characters with ellipsis, null if null String input
	 */
	public static String abbreviateFromRight(String str, int size) {
		if (str == null) {
			return null;
		} else if (size <= 0 || str.length() <= size) {
			return str;
		} else {
			return "..." + str.substring(str.length() - size);
		}
	}
	
	/**
	 * Converts UniCode String to hex code ex) stringToHex("123") =>
	 * "003100320033"
	 * 
	 * @param str
	 *            the String to convert
	 * @return the converted hex string
	 */
	public static String stringToHex(String str) {
	
		String inStr = str;
	
		char inChar[] = inStr.toCharArray();
		StringBuffer sb = new StringBuffer();
	
		for (int i = 0; i < inChar.length; i++) {
			String hex = Integer.toHexString((int) inChar[i]);
			if (hex.length() == 2) {
				hex = "00" + hex;
			}
			sb.append(hex);
		}
		return sb.toString();
	}
	
	/**
	 * Converts 10 digit String to business number format(Korean). ex)
	 * toBusinessNoPattern("1111111111") => "111-11-11111"
	 * 
	 * @param str
	 *            the String value to convert
	 * @return the String with business number format(Korean)
	 */
	public static String toBusinessNoPattern(String str) {
		if (str == null) {
			return "";
		}
		if (str.length() != 10 || !NumberUtils.isDigit(str)) {
			return "";
		} else {
			return String.format("%s-%s-%s", str.substring(0, 3), str
					.substring(3, 5), str.substring(5, 10));
		}
	}
	
	/**
	 * Splits the provided text into an array, separator specified. ex) String[]
	 * test; test = tokenizeToStringArray("aaa,bbb,ccc") => test[0]="aaa",
	 * test[1]="bbb"...
	 * 
	 * @param str
	 *            the String to parse
	 * @return an array of parsed Strings
	 */
	public static String[] tokenizeToStringArray(String str) {
		return tokenizeToStringArray(str, ",", false, false);
	}
	
	/**
	 * Splits the provided text into an array, separator specified. ex) String[]
	 * test; test = tokenizeToStringArray("aaa,bbb,ccc", ',') => test[0]="aaa",
	 * test[1]="bbb"...
	 * 
	 * @param str
	 *            the String to parse
	 * @param delimeter
	 *            the character used as the delimiter
	 * @return an array of parsed Strings
	 */
	public static String[] tokenizeToStringArray(String str, char delimeter) {
		return tokenizeToStringArray(str, String.valueOf(delimeter), false,
				false);
	}
	
	/**
	 * Splits the provided text into an array, separator specified. ex) String[]
	 * test; test = tokenizeToStringArray("aaa.bbb.ccc.ddd", "."); =>
	 * test[0]="aaa", test[1]="bbb"...
	 * 
	 * @param str
	 *            the String to parse
	 * @param delimiter
	 *            the String used as the delimiter
	 * @return an array of parsed Strings
	 */
	public static String[] tokenizeToStringArray(String str, String delimiter) {
		return tokenizeToStringArray(str, delimiter, false, false);
	}
	
	/**
	 * Splits the provided text into an array, separator specified. ex) String[]
	 * test; test = tokenizeToStringArray("aaa.bbb.ccc.ddd", ".", true, true) =>
	 * test[0]="aaa", test[1]="bbb"...
	 * 
	 * @param str
	 *            the String to parse
	 * @param delimeter
	 *            the character used as the delimiter
	 * @param trimTokens
	 *            trim every tokens of array
	 * @param ignoreEmptyTokens
	 *            ignore empty tokens
	 * @return an array of parsed Strings
	 */
	public static String[] tokenizeToStringArray(String str, String delimeter,
			boolean trimTokens, boolean ignoreEmptyTokens) {
		if (str == null) {
			return null;
		}
		if (delimeter == null) {
			return new String[] { str };
		}
		StringTokenizer st = new StringTokenizer(str, delimeter);
		List<String> tokens = new ArrayList<String>();
		do {
			if (!st.hasMoreTokens()) {
				break;
			}
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() != 0) {
				tokens.add(token);
			}
		} while (true);
		return tokens.toArray(new String[tokens.size()]);
	}
	
	/**
	 * Converts a first character to lower case. ex)
	 * toLowerCaseFirstLetter("ABCD") => "aBCD"
	 * 
	 * @param str
	 *            input string
	 * @return the converted string
	 * 
	 */
	public static String toLowerCaseFirstLetter(String str) {
		return changeFirstCharacterCase(false, str);
	}
	
	/**
	 * Converts 13 digit String to social security number format(Korean). ex)
	 * toSocialSecuNoPattern("1111111111111") => "111111=1111111"
	 * 
	 * @param str
	 *            the String value to convert
	 * @return the String with social security number format(Korean)
	 */
	public static String toSocialSecuNoPattern(String str) {
		if (str == null) {
			return "";
		}
		if (str.length() != 13 || !NumberUtils.isDigit(str)) {
			return "";
		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append(str.substring(0, 6));
			buffer.append('-');
			buffer.append(str.substring(6));
			return buffer.toString();
		}
	}
	
	/**
	 * Converts digit String to telephone number format(Korean).
	 * 
	 * <pre>
	 * String actual = StringUtils.toTelephoneNumberFormat(&quot;032-123-4567&quot;); // 032-123-4567
	 * String actual = StringUtils.toTelephoneNumberFormat(&quot;021234567&quot;); // 02-123-4567
	 * String actual = StringUtils.toTelephoneNumberFormat(&quot;0212345678&quot;); // 02-1234-5678
	 * String actual = StringUtils.toTelephoneNumberFormat(&quot;1234567&quot;); // 123-4567
	 * </pre>
	 * 
	 * @param str
	 *            the String value to convert
	 * @return the String with telephone number format(Korean), separated by '-'
	 */
	public static String toTelephoneNumberFormat(String str) {
	
		int endNumberDigit = 4;
		int minNumberDigit = 7;
	
		if (StringUtils.isEmpty(str)) {
			return null;
		}
	
		String origin = str.trim();
		String tempNumber;
	
		int originLength = origin.length();
	
		// extract numeric chars only
		if (NumberUtils.isNotDigit(origin)) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < originLength; i++) {
				if (Character.isDigit(origin.charAt(i))) {
					sb.append(origin.charAt(i));
				}
			}
			tempNumber = sb.toString();
		} else {
			tempNumber = origin;
		}
	
		int numberLength = tempNumber.length();
	
		if (numberLength < minNumberDigit) {
			return tempNumber;
		}
	
		String firstNumber = "";
		String secondNumber = "";
		String thirdNumber = "";
	
		if (tempNumber.charAt(0) == '0') { // local number or mobile number
			if (tempNumber.charAt(1) == '2') { // Seoul
				firstNumber = tempNumber.substring(0, 2);
				secondNumber = tempNumber.substring(2, numberLength
						- endNumberDigit);
				thirdNumber = tempNumber.substring(numberLength
						- endNumberDigit, numberLength); // split last 4 digits
			} else { // local number or mobile number
				firstNumber = tempNumber.substring(0, 3);
				secondNumber = tempNumber.substring(3, numberLength
						- endNumberDigit);
				thirdNumber = tempNumber.substring(numberLength
						- endNumberDigit, numberLength); // split last 4 digits
			}
			return firstNumber + "-" + secondNumber + "-" + thirdNumber;
		} else { // telephone number without local number
			firstNumber = tempNumber
					.substring(0, numberLength - endNumberDigit);
			secondNumber = tempNumber.substring(numberLength - endNumberDigit,
					numberLength);
	
			return firstNumber + "-" + secondNumber;
		}
	
	}
	
	/**
	 * Converts a first character to uppper case. ex)
	 * toUpperCaseFirstLetter("abcd") => "Abcd"
	 * 
	 * @param str
	 *            input string
	 * @return the converted string
	 */
	public static String toUpperCaseFirstLetter(String inputString) {
		return changeFirstCharacterCase(true, inputString);
	}
	
	/**
	 * Converts digit String to zip code format(Korean). ex)
	 * toZipCodePattern("111111") => "111-111"
	 * 
	 * @param str
	 *            the String value to convert
	 * @return the String with zip code format(Korean), separated by '-'
	 */
	public static String toZipCodePattern(String str) {
		if (str == null) {
			return "";
		}
		if (str.length() != 6 || !NumberUtils.isDigit(str)) {
			return "";
		} else {
			return String.format("%s-%s", str.substring(0, 3), str.substring(3,
					6));
		}
	}
	
	/**
	 * Compares two Strings with whitespace normalized by using trim ex)
	 * trimEquals("     test     ", "test") => true
	 * 
	 * @param str1
	 *            the first String, may be null
	 * @param str2
	 *            the second String, may be null
	 * @return if the CharSequences are equal, case sensitive, or both null
	 */
	public static boolean trimEquals(String str1, String str2) {
		if (str1 == null) {
			if (str2 == null) {
				return true;
			} else {
				return false;
			}
		} else if (str2 == null) {
			return false;
		} else {
			String trimBaseStr = str1.trim();
			String trimTargetStr = str2.trim();
			return trimBaseStr.equals(trimTargetStr);
		}
	}
	
	/**
	 * Converts qualified name String to unqualified name String using separator
	 * '.'.
	 * 
	 * @param str
	 *            input string
	 * @return the converted string
	 */
	public static String unqualify(String str) {
		return unqualify(str, '.');
	}
	
	/**
	 * Converts qualified name String to unqualified name String using
	 * separator.
	 * 
	 * @param str
	 *            input string
	 * @param delimiter
	 *            the seperator character
	 * @return the converted string
	 */
	public static String unqualify(String str, char delimiter) {
		return str.substring(str.lastIndexOf(delimiter) + 1);
	}
	
	/**
	 * Converts this String into a sequence of bytes using the named charset,
	 * storing the result into a new String
	 * 
	 * @param str
	 *            target string
	 * @param charset
	 *            the name of a supported charset
	 * @return The resultant string
	 * @throws UnsupportedEncodingException
	 */
	public static String convertStringCharset(String str, String charset)
			throws UnsupportedEncodingException {
		return new String(str.getBytes(charset));
	}
	
	/**
	 * Check if the entire pattern matches the formal input pattern.
	 * 
	 * <pre>
	 * StringUtils.isRegexPatternMatch(&quot;aaaaab&quot;, &quot;a*b&quot;) = true;
	 * StringUtils.isRegexPatternMatch(&quot;cabbbb&quot;, &quot;a*b&quot;) = false;
	 * </pre>
	 * 
	 * @param str
	 *            pattern to be checked
	 * @param pattern
	 *            regular expression pattern
	 * @return if the input string matches the formal pattern, <code>true</code>
	 */
	public static boolean isRegexPatternMatch(String str, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/**
	 * Check if the input string matches the formal pattern. Change * to ..
	 * 
	 * <pre>
	 * StringUtils.isPatternMatching("abc-def', "*-*") 	= true
	 * StringUtils.isPatternMatching("abc", "*-*") 	    = false
	 * </pre>
	 * 
	 * @param str
	 *            pattern to be checked
	 * @param pattern
	 *            pattern String
	 * @return if the entered string matches the formal pattern,
	 *         <code>true</code>
	 */
	public static boolean isPatternMatching(String str, String pattern) {
		// if url has wild key, i.e. "*", convert it to ".*" so that we can
		// perform regex matching
		if (pattern.indexOf('*') >= 0) {
			pattern = pattern.replaceAll("\\*", ".*");
		}
	
		pattern = "^" + pattern + "$";
	
		return Pattern.matches(pattern, str);
	}
	
	/**
	 * Perform escaping in advance so that metacharacters [\^$.|?*+() that are
	 * meaningfully used are not used in a way different from the user's
	 * intentions.
	 * 
	 * @param orgPattern
	 *            original string
	 * @return escaping string
	 */
	private static String regexMetaCharEscape(String orgPattern) {
		return orgPattern.replaceAll("([\\[\\\\\\^\\$\\.\\|\\?\\*\\+\\(\\)])",
				"\\\\$1");
	}
	
	/**
	 * Check if letter matching format defined by user has come in.
	 * 
	 * <pre>
	 * StringUtils.isUserFormat(&quot;123-456&quot;, &quot;###-###&quot;) = true;
	 * StringUtils.isUserFormat(&quot;123.456&quot;, &quot;###.###&quot;) = true;
	 * </pre>
	 * 
	 * @param str
	 *            string to be checked
	 * @param pattern
	 *            user defined pattern
	 * @return in case of string matching pattern that is defined by user,
	 *         <code>true</code>
	 */
	public static boolean isUserFormat(String str, String pattern) {
		String metaChange = regexMetaCharEscape(pattern);
		String regexChange = metaChange.replaceAll("#", "\\\\d").replaceAll(
				"S", "[a-zA-Z]");
		return str.matches(regexChange);
	}
	
	/**
	 * Check if input string matches the given filter pattern. s of sken is
	 * special character. k is korean. e is english. n is number.
	 * 
	 * <pre>
	 * StringUtils.isPatternInclude(&quot;asdf@5456&quot;, &quot;s&quot;) = true;
	 * StringUtils.isPatternInclude(&quot;-&quot;, &quot;s&quot;) = true;
	 * StringUtils.isPatternInclude(&quot;頃�quot;, &quot;k&quot;) = true;
	 * StringUtils.isPatternInclude(&quot;123臧�2&quot;, &quot;k&quot;) = true;
	 * StringUtils.isPatternInclude(&quot;asdfsdfsdf&quot;, &quot;e&quot;) = true;
	 * StringUtils.isPatternInclude(&quot;asdfs1dfsdf&quot;, &quot;e&quot;) = true;
	 * StringUtils.isPatternInclude(&quot;123123123&quot;, &quot;n&quot;) = true;
	 * StringUtils.isPatternInclude(&quot;asdfs1dfsdf&quot;, &quot;n&quot;) = true;
	 * </pre>
	 * 
	 * @param str
	 *            string to be checked
	 * @param param
	 *            filter pattern
	 * @return if input string pattern matches filter, <code>true</code>
	 */
	public static boolean isPatternInclude(String str, String param) {
	
		if (param.indexOf("s") >= 0) {
			return isRegexPatternMatch(str, ".*[~!@\\#$%<>^&*\\()\\-=+_\\'].*");
		}
		if (param.indexOf("k") >= 0) {
			return isRegexPatternMatch(str, ".*[銊�銋巪銋�銋臧�頌.*");
		}
		if (param.indexOf("e") >= 0) {
			return isRegexPatternMatch(str, ".*[a-zA-Z].*");
		}
		if (param.indexOf("n") >= 0) {
			return isRegexPatternMatch(str, ".*\\d.*");
		}
		return true;
	}
	
	/**
	 * Check if some strings match pattern.
	 * 
	 * <pre>
	 * StringUtils.isRegexPatternInclude("cabbbb", "a*b"))  = true
	 * </pre>
	 * 
	 * @param str
	 *            string to be checked
	 * @param pattern
	 *            regular expression pattern
	 * @return if input sting matches the formal pattern, <code>true</code>
	 */
	public static boolean isRegexPatternInclude(String str, String pattern) {
		return isRegexPatternMatch(str, ".*" + pattern + ".*");
	}
	
	/**
	 * 氍胳瀽鞐挫潣 觳竴鞛愲ゼ 雽�鞛愲 氤�步頃滊嫟.
	 * @param str 氤�步頃�氍胳瀽鞐�
	 * @return 氍胳瀽鞐挫潣 觳竴鞛愲ゼ 雽�鞛愲 氤�步頃橃棳 氚橅櫂頃滊嫟.
	 */
	public static String firstCharacterToLowerCase(String str) {
		if (Character.isLowerCase(str.substring(0, 1).toCharArray()[0])) {
			return changeFirstCharacterCase(true, str);
		}
		return str;
	}
}
