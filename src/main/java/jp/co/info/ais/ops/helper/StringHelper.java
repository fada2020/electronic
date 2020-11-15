package jp.co.info.ais.ops.helper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
	private StringHelper() {
	}

	public static String nullTrim(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	public static boolean isNull(String str) {

		return (str == null || (str.trim().length()) == 0);
	}

	public boolean isNull(Object obj) {
		String str = null;
		if (obj instanceof String) {
			str = (String) obj;
		} else {
			return true;
		}

		return isNull(str);
	}

	public boolean isNotNull(Object obj) {
		String str = null;
		if (obj instanceof String) {
			str = (String) obj;
		} else {
			return false;
		}
		return isNotNull(str);
	}

	public static String replaceNull(String value, String defaultValue) {
		if (isNull(value)) {
			return defaultValue;
		}
		return value.trim();
	}

	public static boolean isNullOrEmpty(String s) {
		return ((s == null) || s.isEmpty());
	}

	public static String valueOf(String s) {
		return isNullOrEmpty(s) ? "" : s;
	}

	public static String[] splitArr(String text, String rex, boolean keepDelim) {
		if (text == null) {
			return null;
		}
		int lastInx = 0;
		LinkedList splitted = new LinkedList();

		Pattern pattern = Pattern.compile(rex);
		Matcher m = pattern.matcher(text);

		while (m.find()) {
			splitted.add(text.substring(lastInx, m.start()));
			if (keepDelim) {
				splitted.add(m.group());
			}
			lastInx = m.end();
		}
		splitted.add(text.substring(lastInx));
		return (String[]) splitted.toArray(new String[splitted.size()]);
	}

	public static String replaceStr(String src, String oldstr, String newstr) {

		if (src == null)
			return null;

		StringBuffer dest = new StringBuffer("");
		int len = oldstr.length();
		int srclen = src.length();
		int pos = 0;
		int oldpos = 0;

		while ((pos = src.indexOf(oldstr, oldpos)) >= 0) {
			dest.append(src.substring(oldpos, pos));
			dest.append(newstr);
			oldpos = pos + len;
		}

		if (oldpos < srclen)
			dest.append(src.substring(oldpos, srclen));

		return dest.toString();

	}

	public static String setContentNoList(String arrStr, String tStr, String dStr) {
		String result = null;

		ArrayList<String> CHK_STR = arrayNoList(arrStr);
		ArrayList<String> DEL_STR = arrayNoList(dStr);
		ArrayList<String> TGT_STR = arrayNoList(tStr);

		if (StringHelper.isNullOrEmpty(arrStr)) {
			// check状態保持用
			result = arrayJoin(",", TGT_STR);
		} else {
			result = arrayJoin(",", arrayNoListResult(CHK_STR, TGT_STR, DEL_STR));
		}
		return result;
	}

	/**
	 * カンマ文字列を配列化して返す
	 */
	public static String[] splitString(String str) {
		return StringHelper.splitArr(str, ",", true);
	}

	public static ArrayList<String> arrayNoList(String str) {

		ArrayList<String> array = new ArrayList<String>();
		if (!StringHelper.isNullOrEmpty(str)) {
			StringTokenizer st = new StringTokenizer(str, ",");
			while (st.hasMoreTokens()) {
				String temp = st.nextToken();
				array.add(temp);
			}
		}
		return array;
	}

	public static ArrayList<String> arrayNoListResult(ArrayList<String> arrayC, ArrayList<String> arrayT,
			ArrayList<String> arrayD) {

		ArrayList<String> array = new ArrayList<String>();

		for (int i = 0; i < arrayC.size(); i++) {
			array.add(arrayC.get(i));
		}

		for (int i = 0; i < arrayT.size(); i++) {
			if (!checkArray(arrayT.get(i), arrayC)) {
				array.add(arrayT.get(i));
			}
		}

		for (int j = 0; j < arrayC.size(); j++) {
			if (checkArray(arrayC.get(j), arrayD)) {
				array.remove(arrayC.get(j));
			}
		}

		return array;
	}

	public static boolean checkArray(String str, ArrayList<String> arrayT) {
		boolean result = false;
		for (int i = 0; i < arrayT.size(); i++) {
			if (str.equals(arrayT.get(i))) {
				result = true;
				break;
			}
		}
		return result;
	}

	public static String arrayJoin(String glue, ArrayList<String> array) {
		String result = "";
		for (int i = 0; i < array.size(); i++) {
			result += array.get(i);
			if (i < array.size() - 1)
				result += glue;
		}
		return result;
	}

	public static boolean isEmpty(Object o) throws IllegalArgumentException {
		if(o == null) return true;

		if(o instanceof String) {
			if(((String)o).length() == 0){
				return true;
			}
		} else if(o instanceof Collection) {
			if(((Collection)o).isEmpty()){
				return true;
			}
		} else if(o.getClass().isArray()) {
			if(Array.getLength(o) == 0){
				return true;
			}
		} else if(o instanceof Map) {
			if(((Map)o).isEmpty()){
				return true;
			}
		}else {
			return false;
		}

		return false;
	}

	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}
}
