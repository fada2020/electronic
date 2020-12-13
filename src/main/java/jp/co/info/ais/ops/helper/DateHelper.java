package jp.co.info.ais.ops.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

    private DateHelper() {   }

    /**
     * 現在のタイムスタンプを返す
     */
    public static String getNowTimeStamp() {
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.JAPAN);
        return sdf.format(dt).toString();
    }

    public static Date convStrToDate(String tDate) {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
		try {
			date = sdFormat.parse(tDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return date;
    }

    public static String getTimeStampFormat(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd",Locale.JAPAN);
        return sdf.format(dt).toString();
    }

	public static String getCurrentTime(){
	 	java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.US );
	 	return  fmt.format(new java.util.Date() );
	}

	public static String getCurrentTime1(){
	 	String result = "";
		java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.US );
		try {
			result = getDateFormat(fmt.format(new java.util.Date()));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	 	return  result;
	}


    /**
     * 現在のタイムスタンプを返す
     */
    public static String getDateStr(String sDate) {
    	String result = "";
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        result = format.format(new Date());
        return result;
    }

    /**
     * 日付ﾌｫｰﾏｯﾄ
     *
     * @param String
     * @return フォーマット後の日付文字列
     */
    public static String getDateFormat(String sDate) throws Throwable {
    	System.out.println("sDate:"+sDate);
        StringBuffer strDate = new StringBuffer();
        if (!sDate.trim().equals("")) {
            strDate.append(sDate.substring(0, 4)).append("年");
            strDate.append(sDate.substring(4, 6)).append("月");
            strDate.append(sDate.substring(6, 8)).append("日");;
        }
    	System.out.println("sDate1:"+strDate.toString());
        return strDate.toString();
    }

    public static String getDateFormat1(String sDate) throws Throwable {
    	System.out.println("sDate:"+sDate);
        StringBuffer strDate = new StringBuffer();
        if (!sDate.trim().equals("")) {
            strDate.append(sDate.substring(0, 4)).append("/");
            strDate.append(sDate.substring(4, 6)).append("/");
            strDate.append(sDate.substring(6, 8));
        }
    	System.out.println("sDate1:"+strDate.toString());
        return strDate.toString();
    }

    public static String getWareiNowDate(){
    	String result = "";
    	Locale.setDefault(new Locale("ja", "JP", "JP"));
        Calendar cal = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("GGGGyy年 MM月 dd日");
        result = format.format(new Date());
        return result;
    }

    public static String getSerekiNowDate(){
    	String result = "";

    	Locale.setDefault(new Locale("ja", "JP", "JP"));
        Calendar cal = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("GGGGyy年 MM月 dd日");
        result = format.format(new Date());
        return result;
    }


}
