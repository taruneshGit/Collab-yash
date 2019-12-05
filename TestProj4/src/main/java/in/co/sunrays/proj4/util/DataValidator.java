package in.co.sunrays.proj4.util;

import java.util.Date;

public class DataValidator {
	public static boolean isNull(String val) {

		if (val == null || val.trim().length() == 0) {

			return true;

		} else {
			return false;
		}
	}

	public static boolean isNotNull(String val) {
		return !isNull(val);

	}

	public static boolean isInteger(String val) {

		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;

			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;

		}
	}

	public static boolean isLong(String val) {

		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;

			} catch (NumberFormatException e) {

				return false;

			}
		} else {

			return false;
		}

	}

	public static boolean isDate(String val) {

		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}

	public static void main(String[] args) {
		System.out.println("Not Null 2" + isNotNull("ABC"));
		System.out.println("Not Null 3" + isNotNull(null));
		System.out.println("Not Null 4" + isNull("123"));

		System.out.println("Is Int " + isInteger(null));
		System.out.println("Is Int " + isInteger("ABC1"));
		System.out.println("Is Int " + isInteger("123"));
		System.out.println("Is Int " + isNotNull("123"));
		System.out.println("Is long " + isLong("123"));

	}
}
