package com.cn.cloud.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

public class NumberUtils {
	/** #,##0.0 */
	public static final String CURRENCY_BELOWTHEDECIMAL1 = "#,##0.0";
	/** #,##0.00 */
	public static final String CURRENCY_BELOWTHEDECIMAL2 = "#,##0.00";
	/** #,##0.000 */
	public static final String CURRENCY_BELOWTHEDECIMAL3 = "#,##0.000";
	/** #,##0.0000 */
	public static final String CURRENCY_BELOWTHEDECIMAL4 = "#,##0.0000";
	/** #,##0.00000 */
	public static final String CURRENCY_BELOWTHEDECIMAL5 = "#,##0.00000";
	/** #,##0 */
	public static final String CURRENCY_NO_DECIMALPOINT = "#,##0";

	private static Random generator = new Random(System.currentTimeMillis());

	/** # */
	public static final String NO_EFFCT_FORMAT = "#";

	/**
	 * get input string and check whether if positive/negative/whole/real number
	 * is true or not.
	 * 
	 * <pre>
	 * NumberUtils.checkNumberType(&quot;1234&quot;, &quot;positive&quot;) = true;
	 * NumberUtils.checkNumberType(&quot;0.1234&quot;, &quot;positive&quot;) = true;
	 * NumberUtils.checkNumberType(&quot;-1234.12&quot;, &quot;negative&quot;) = true;
	 * NumberUtils.checkNumberType(&quot;-0.1234&quot;, &quot;negative&quot;) = true;
	 * NumberUtils.checkNumberType(&quot;1234&quot;, &quot;whole&quot;) = true;
	 * NumberUtils.checkNumberType(&quot;-1234&quot;, &quot;whole&quot;) = true;
	 * NumberUtils.checkNumberType(&quot;-1234.123&quot;, &quot;real&quot;) = true;
	 * NumberUtils.checkNumberType(&quot;1.34&quot;, &quot;real&quot;) = true;
	 * </pre>
	 * 
	 * @param number
	 *            number to check
	 * @param type
	 *            check number type- positive, negative, whole, real number
	 * @return boolean if the input number type is true, return
	 *         <code>true</code>.
	 */
	public static boolean checkNumberType(String number, String type) {
		String positivePattern = "^[+]?([1-9]\\d*|[1-9]\\d*\\.\\d*|0?\\.\\d*[1-9]\\d*)$";
		String negativePattern = "^-([1-9]\\d*|[1-9]\\d*\\.\\d*|0?\\.\\d*[1-9]\\d*)$";
		String wholePattern = "^[+-]?[1-9]\\d*$";
		String realPattern = "^[+-]?([1-9]\\d*\\.\\d*|0?\\.\\d*[1-9]\\d*)$";

		if ("positive".equals(type)) {
			return Pattern.matches(positivePattern, number);
		} else if ("negative".equals(type)) {
			return Pattern.matches(negativePattern, number);
		} else if ("whole".equals(type)) {
			return Pattern.matches(wholePattern, number);
		} else if ("real".equals(type)) {
			return Pattern.matches(realPattern, number);
		}
		return false;
	}

	/**
	 * return number matching user-input format<br>
	 * 
	 * <pre>
	 * NumberUtils.formatNumber(1023412, &quot;###,###,###&quot;) = &quot;1,023,412&quot;;
	 * NumberUtils.formatNumber(1023412123, &quot;###,###&quot;) = &quot;1,023,412,123&quot;;
	 * NumberUtils.formatNumber(1023412123, &quot;##,##&quot;) = &quot;10,23,41,21,23&quot;;
	 * NumberUtils.formatNumber(1023412123, &quot;##.##&quot;) = &quot;1023412123&quot;;
	 * </pre>
	 * 
	 * @param number
	 *            number to convert
	 * @param format
	 *            A non-localized pattern string.
	 * @return String converted number
	 */
	public static String formatNumber(int number, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}

	/**
	 * Converts short to String with the given format. ex)
	 * NumberUtils.formatNumber(12345, "###,###") => "12,345"
	 * 
	 * @param number
	 *            the short value to convert
	 * @param format
	 *            decimal format for conversion
	 * @return the converted string
	 */
	public static String formatNumber(short number, String format) {
		DecimalFormat decimalformat = new DecimalFormat(format);
		return decimalformat.format(number);
	}

	/**
	 * Convert a double value to string with given decimal format.<br>
	 * 
	 * <pre>
	 * NumberUtils.formatNumber(12345.67d, &quot;###,###.#&quot;) = &quot;12,345.7&quot;
	 * </pre>
	 * 
	 * @param number
	 *            the double value to convert
	 * @param format
	 *            decimal format for conversion
	 * @return the converted string
	 */
	public static String formatNumber(double number, String format) {
		DecimalFormat decimalformat = new DecimalFormat(format);
		return decimalformat.format(number);
	}

	/**
	 * Convert a long value to string with given decimal format.<br>
	 * 
	 * <pre>
	 * NumberUtils.formatNumber(12345.67L, "###,###.#") = "12,345.7"
	 * </pre>
	 * 
	 * @param number
	 *            the long value to convert
	 * @param format
	 *            decimal format for conversion
	 * @return the converted string
	 */
	public static String formatNumber(long number, String format) {
		DecimalFormat decimalformat = new DecimalFormat(format);
		return decimalformat.format(number);
	}

	/**
	 * Convert a String to string with given decimal format.<br>
	 * str may allow ',' and '.' chars.<br>
	 * 
	 * <pre>
	 * NumberUtils.formatNumber(&quot;1234567&quot;, &quot;#,##0.000&quot;) = &quot;1,234,567.000&quot;
	 * </pre>
	 * 
	 * @param number
	 *            the String to convert
	 * @param format
	 *            decimal format for conversion
	 * @return the converted string
	 * @throws ParseException
	 */
	public static String formatNumber(String number, String format)
			throws ParseException {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern(format);
		Number sourceNumber = df.parse(StringUtils.deleteAny(number, ','));
		return df.format(sourceNumber);
	}

	/**
	 * bring currency mark matching specific locale<br>
	 * 
	 * <pre>
	 * NumberUtils.formatNumberByLocale(3527900, Locale.KOREA) = &quot;锟�,527,900&quot;;
	 * NumberUtils.formatNumberByLocale(3527900, Locale.US) = &quot;$3,527,900.00&quot;;
	 * </pre>
	 * 
	 * @param number
	 *            number to convert
	 * @param locale
	 *            the locale for which a number format is needed
	 * @return String converted number
	 */
	public static String formatNumberByLocale(int number, Locale locale) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		return nf.format(number);
	}

	/**
	 * support up to five converted decimal points only for the decimal points
	 * requested for the input number, default is three decimal points.
	 * 
	 * <pre>
	 * NumberUtils.formatNumberByPoint(10231023123.1213, 2) = &quot;10,231,023,123.12&quot;;
	 * NumberUtils.formatNumberByPoint(10231023123.1213, 6) = &quot;10,231,023,123.121&quot;;
	 * </pre>
	 * 
	 * @param number
	 *            number to convert
	 * @param point
	 *            decimal points
	 * @return String converted number
	 */
	public static String formatNumberByPoint(double number, int point) {
		String format = "";

		switch (point) {
		case 0:
			format = "###,###,###.###";
			break;
		case 1:
			format = "###,###,###,##0.0";
			break;
		case 2:
			format = "###,###,###,##0.00";
			break;
		case 3:
			format = "###,###,###,##0.000";
			break;
		case 4:
			format = "###,###,###,##0.0000";
			break;
		case 5:
			format = "###,###,###,##0.00000";
			break;
		default:
			format = "###,###,###.###";
			break;
		}
		DecimalFormat df = new DecimalFormat(format);
		return String.valueOf(df.format(number));
	}

	/**
	 * return random number matching the given targetClass
	 * 
	 * @param targetClass
	 *            number type to define <code>Integer</code>, <code>Long</code>,
	 *            <code>Float</code>, <code>Double</code>
	 * @return random number
	 */
	public static <T extends Number> T getRandomNumber(Class<T> targetClass) {
		return getRandomNumber(targetClass, true);
	}

	/**
	 * return random number matching targetClass. if sign is <code>true</code>,
	 * return positive number.
	 * 
	 * @param targetClass
	 *            number type to define <code>Integer</code>, <code>Long</code>,
	 *            <code>Float</code>, <code>Double</code>
	 * @param sign
	 *            positive number if<code>true</code>
	 * @return random number
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T getRandomNumber(Class<T> targetClass,
			boolean sign) {
		if (sign) {
			if (targetClass.equals(Integer.class)) {
				return (T) (Integer) generator.nextInt();
			} else if (targetClass.equals(Long.class)) {
				return (T) (Long) generator.nextLong();
			} else if (targetClass.equals(Float.class)) {
				return (T) (Float) generator.nextFloat();
			} else if (targetClass.equals(Double.class)) {
				return (T) (Double) generator.nextDouble();
			}
		} else {
			if (targetClass.equals(Integer.class)) {
				return (T) (Integer) (-generator.nextInt());
			} else if (targetClass.equals(Long.class)) {
				return (T) (Long) (-generator.nextLong());
			} else if (targetClass.equals(Float.class)) {
				return (T) (Float) (-generator.nextFloat());
			} else if (targetClass.equals(Double.class)) {
				return (T) (Double) (-generator.nextDouble());
			}
		}
		return (T) null;
	}

	/**
	 * return random number in fixed length matching targetClass
	 * 
	 * @param targetClass
	 *            number type to define <code>Integer</code>, <code>Long</code>,
	 *            <code>Float</code>, <code>Double</code>
	 * @param fixedLength
	 *            number type to define
	 * @return random number
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T getRandomNumber(Class<T> targetClass,
			int fixedLength) {
		if (fixedLength < 0)
			return (T) null;

		double randomNumber = 0.0;

		if (targetClass.equals(Integer.class)) {
			randomNumber = getRandomNumber(fixedLength, 10, Integer.MAX_VALUE);
			return (T) (Integer) (int) randomNumber;
		} else if (targetClass.equals(Long.class)) {
			randomNumber = getRandomNumber(fixedLength, 19, Long.MAX_VALUE);
			return (T) (Long) (long) randomNumber;
		} else if (targetClass.equals(Float.class)) {
			int digit = generator.nextInt(fixedLength);
			randomNumber = getRandomNumber(digit, 39, Float.MAX_VALUE);
			return (T) (Float) (float) randomNumber;
		} else if (targetClass.equals(Double.class)) {
			int digit = generator.nextInt(fixedLength);
			randomNumber = getRandomNumber(digit, 309, Double.MAX_VALUE);
			return (T) (Double) randomNumber;
		}
		return (T) null;
	}

	/**
	 * return random number within the maximum digits
	 * 
	 * @param fixedLength
	 *            number length
	 * @param maxLength
	 *            maximum digits of each number type
	 * @param maxValue
	 *            maximum value of each number type
	 * @return random number
	 */
	private static double getRandomNumber(int fixedLength, int maxLength,
			double maxValue) {
		double max = 0;
		double base = Math.pow(10, fixedLength - 1);

		if (fixedLength < maxLength) {
			max = Math.pow(10, fixedLength) - 1;
		} else if (fixedLength == maxLength) {
			max = maxValue;
		} else {
			return -1;
		}
		return (Math.random() * (max - base + 1)) + base;
	}

	/**
	 * return random number in maximum minimum value matching given targetClass
	 * 
	 * @param targetClass
	 *            targetClass number type to define <code>Integer</code> ,
	 *            <code>Long</code>, <code>Float</code>, <code>Double</code>
	 * @param min
	 *            minimum value
	 * @param max
	 *            maximum value
	 * @return random number
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T getRandomNumber(Class<T> targetClass,
			T min, T max) {
		double minDouble = org.springframework.util.NumberUtils
				.convertNumberToTargetClass(min, Double.class);
		double maxDouble = org.springframework.util.NumberUtils
				.convertNumberToTargetClass(max, Double.class);
		double randomNumber = (Math.random() * (maxDouble - minDouble + 1))
				+ minDouble;

		if (targetClass.equals(Integer.class)) {
			return (T) (Integer) (int) randomNumber;
		} else if (targetClass.equals(Long.class)) {
			return (T) (Long) (long) randomNumber;
		} else if (targetClass.equals(Float.class)) {
			return (T) (Float) (float) randomNumber;
		} else if (targetClass.equals(Double.class)) {
			return (T) (Double) randomNumber;
		}
		return (T) null;
	}

	/**
	 * check whether input string has number
	 * 
	 * @param str
	 *            input string
	 * @return if number exists, <code>true</code>
	 */
	public static boolean hasNumber(String str) {
		boolean result = false;

		if (StringUtils.isEmpty(str)) {
			return false;
		}
		for (int index = 0; index < str.length(); index++) {
			if (str.charAt(index) > 47 && str.charAt(index) < 58)
				result = true;
			else
				continue;
		}
		return result;
	}


	/**
	 * It converts BigDecimal type to String
	 * 
	 * @param number
	 *            the bigDecimal converted to String
	 * @return the converted String, if the input value is null or equal to Zero
	 *         then return ""
	 */
	public static String bigDecimalToString(BigDecimal number) {
		BigDecimal zero = BigDecimal.ZERO;

		if (number == null || zero.equals(number))
			return "";
		else
			return number.toString();
	}

	/**
	 * It converts integer type to String ( 27 -> '27')
	 * 
	 * <pre>
	 * NumberUtils.intToString(14) = '14'
	 * </pre>
	 * 
	 * @param number
	 *            integer type
	 * @return String string representation of a number
	 */
	public static String intToString(int number) {
		return String.valueOf(number);
	}

	/**
	 * check whether input string is number<br>
	 * check not only <code>int</code>, <code>double</code> but also
	 * <code>long</code>, <code>float</code>
	 * 
	 * <pre>
	 * NumberUtils.isNumber(&quot;12312312.2f&quot;) = true;
	 * NumberUtils.isNumber(&quot;1.7976931348623157E308&quot;) = true;
	 * </pre>
	 * 
	 * @param str
	 *            the <code>String</code> to check
	 * @return <code>true</code> if the string is a correctly formatted number
	 */
	public static boolean isNumber(String str) {
		if (StringUtils.isEmpty(str))
			return false;

		// cf.) apache commons lang NumberUtils.isNumber - ex.) 875634512312312l
		// - true
		if (str.matches("^[-+]?\\d+(\\.\\d+)?$")) {
			return true;
		} else {
			// try parse double
			try {
				@SuppressWarnings("unused")
				double doubleVal = Double.parseDouble(str);
				return true;
			} catch (NumberFormatException de) {
				// try BigDecimal
				try {
					@SuppressWarnings("unused")
					BigDecimal bigDecimalVal = new BigDecimal(str);
					return true;
				} catch (NumberFormatException be) {
					return false;
				}
			}
		}
	}

	/**
	 * search target number for the input number and convert it to replacement
	 * number
	 * 
	 * @param source
	 *            number to convert
	 * @param target
	 *            number to search
	 * @param replacement
	 *            number to convert
	 * @return double converted number
	 */
	public static double replaceNumber(double source, int target,
			int replacement) {
		String sourceStr = String.valueOf(source);
		String targetStr = String.valueOf(target);
		String replacementStr = String.valueOf(replacement);

		return Double.parseDouble(sourceStr.replaceAll(targetStr,
				replacementStr));
	}

	

	/**
	 * Converts String to BigDecimal
	 * 
	 * @param number
	 *            the String value to convert
	 * @return the converted BigDecimal
	 */
	public static BigDecimal stringToBigDecimal(String number) {
		if (StringUtils.isEmptyTrimmed(number))
			return BigDecimal.ZERO;
		else
			return new BigDecimal(number);
	}

	/**
	 * Converts String to int if the String is empty or null returns default
	 * value ex) NumberUtils.stringToInt("", -1) => -1
	 * 
	 * @param number
	 *            the String to convert
	 * @param defaultValue
	 * @return the converted int value
	 */
	public static int stringToInt(String number, int defaultValue) {
		if (StringUtils.isEmptyTrimmed(number)) {
			return defaultValue;
		}
		return Integer.parseInt(number.trim());
	}

	/**
	 * Converts String to int ex) NumberUtils.stringToInt("123") => 123
	 * 
	 * @param number
	 *            the String to convert
	 * @return the converted int value
	 */
	public static int stringToInt(String number) {
		return stringToInt(number, 0);
	}

	/**
	 * Converts String to BigDecimal from the specified position
	 * 
	 * @param number
	 *            the String value to convert
	 * @param beginIndex
	 *            the start position
	 * @param size
	 *            the length of str from pos
	 * @return the converted BigDecimal
	 */
	public static BigDecimal substringToBigDecimal(String number,
			int beginIndex, int size) {
		if (StringUtils.isEmptyTrimmed(number))
			return BigDecimal.ZERO;
		else if (number.length() < beginIndex + size)
			return new BigDecimal(StringUtils.leftPad(number, beginIndex + size,
					"0"));
		else
			return new BigDecimal(number.substring(beginIndex, beginIndex
					+ size));
	}

	/**
	 * Converts String to int from a position ex)
	 * NumberUtils.substringToInt("123456789", 5, 3) => 678
	 * 
	 * @param number
	 *            the String value to convert
	 * @param beginIndex
	 *            the start position
	 * @param size
	 *            the length of str from pos
	 * @return the converted int value
	 */
	public static int substringToInt(String number, int beginIndex, int size) {
		if (StringUtils.isEmptyTrimmed(number))
			return 0;
		else if (number.length() < beginIndex + size)
			return Integer.parseInt(StringUtils.leftPad(number, beginIndex
					+ size, "0"));
		else
			return Integer.parseInt(number.substring(beginIndex, beginIndex
					+ size));
	}
	

	/**
	 * For given int value, fill the input length from the right with zero.<br>
	 * 
	 * @param number
	 *            the int value to pad
	 * @param size
	 *            the length for padding
	 * @return the string padded with zero
	 */
	public static String intToZeroPadString(int number, int size) {
		return StringUtils.leftPad(String.valueOf(number), size, '0');
	}

	/**
	 * Checks if the String contains only digits. ex) isDigit("1234") => true
	 * isDigit("1234A")=> false
	 * 
	 * @param number
	 *            the String to check, may be null
	 * @return true if String contains only digits, false if not or null string
	 *         input
	 */
	public static boolean isDigit(String number) {
		if (StringUtils.isEmptyTrimmed(number)) {
			return false;
		}
		char chars[] = number.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the String contains any letters except digits. ex)
	 * isNotDigit("12345") => false isNotDigit("12345ABC") => true
	 * 
	 * @param number
	 *            the String to check, may be null
	 * @return true if String contains any letters, false if not or null string
	 *         input
	 */
	public static boolean isNotDigit(String number) {
		if (number == null) {
			return false;
		}
		for (int i = 0; i < number.length(); i++) {
			if (!Character.isDigit(number.charAt(i))) {
				return true;
			}
		}
		return false;
	}
}
