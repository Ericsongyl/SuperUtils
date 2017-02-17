/**
 * =================================================================
 * 版权所有 2011-2020 泰海网络支付服务有限公司，并保留所有权利
 * -----------------------------------------------------------------
 * 这不是一个自由软件！您不能在任何未经允许的前提下对程序代码进行修改和使用；
 * 不允许对程序代码以任何形式任何目的的再发布
 * =================================================================
 */
package com.nicksong.superutil;

import android.widget.EditText;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {
    private static final String HEX_CHARS = "0123456789abcdef";
    public static final String PATTERN_SSS = "yyyyMMddhhmmssSSS";
    public static final String PATTERN = "yyyyMMddhhmmss";
    private final static Pattern emailer = Pattern
			.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

	/**
	 * 判断字符串中是否包含中文
	 * @param str
	 * @return
     */
	public static boolean isContainChinese(String str) {

		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

    public static void getDoubleStrAt2DecimalByEditText(EditText editText, String str){
    	/**
    	 * 验证参数
    	 */
    	if(str==null||"".equals(str)){
    		return;
    	}
    	//验证是否 0 .开始
		if(str.indexOf("0")==0||str.indexOf(".")==0){
			editText.setText(str.substring(1,str.length()));
		}
		//验证是否小数点后两位
		if(str.indexOf(".")!=-1&&str.substring(str.indexOf(".")+1).length()>2){
			String values=str.substring(0,str.indexOf(".")+3);
			editText.setText(values);
			editText.setSelection(values.length());
		}
		
    }

	public static String MD5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

    public static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_CHARS.charAt(b[i] >>> 4 & 0x0F));
            sb.append(HEX_CHARS.charAt(b[i] & 0x0F));
        }
        return sb.toString();
    }

    public static byte[] toByteArray(String s) {
        byte[] buf = new byte[s.length() / 2];
        int j = 0;
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) ((Character.digit(s.charAt(j++), 16) << 4) | Character
                    .digit(s.charAt(j++), 16));
        }
        return buf;
    }

    public static String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

	
	/**
	 * 方法说明：获得指定格式当前系统时间字符串，yyyyMMddhhmmssSSS
	 *
	 * @return
	 * @throws Exception
	 */
	public static String getDateMSString(){
		SimpleDateFormat sf = new SimpleDateFormat(PATTERN_SSS);
		return sf.format(new Date());
	}
	/**
	 * 方法说明：获得指定格式当前系统时间字符串，yyyyMMddhhmmss
	 *
	 * @return
	 * @throws Exception
	 */
	public static String getDateString(){
		SimpleDateFormat sf = new SimpleDateFormat(PATTERN);
		return sf.format(new Date());
	}

	/**
	 * 方法说明：获得指定格式当前系统时间字符串
	 * 
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static String getDateString(String pattern){
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(new Date());
	}

	/**
	 * 方法说明：获得指定格式指定时间字符串
	 * 
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static String getDateString(String pattern, long time){
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(time);
	}
	
	/**
	 * 判断是否为手机号
	 */
	public static boolean isMobile(String str){
		return str.matches("^1\\d{10}$");
	}
	
	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.get().parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 将20141111转2014-11-11类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static String dateString(String sdate) {
		if (!StringUtils.isEmpty(sdate) && sdate.length()>7) {
			sdate = sdate.substring(0, 4)+"-"+sdate.substring(4, 6)+"-"+sdate.substring(6, 8);
			return sdate;
		}
		return "";
	}
	/**
	 * 将20141111转2014/11/11类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static String dateString1(String sdate) {
		if (!StringUtils.isEmpty(sdate) && sdate.length()>7) {
			sdate = sdate.substring(0, 4)+"/"+sdate.substring(4, 6)+"/"+sdate.substring(6, 8);
			return sdate;
		}
		return "";
	}
	/**
	 * 将20141111122334转2014-11-11 12:23:34类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static String dateTimeString(String sdate) {
		if (!StringUtils.isEmpty(sdate) && sdate.length()>7) {
			sdate = sdate.substring(0, 4)+"-"+sdate.substring(4, 6)+"-"+sdate.substring(6, 8)+" "+sdate.subSequence(8, 17);
			return sdate;
		}
		return "";
	}

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0) {
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			} else {
				ftime = hour + "小时前";
			}
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0) {
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			} else {
				ftime = hour + "小时前";
			}
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormater2.get().format(time);
		}
		return ftime;
	}

	/**
	 * 以友好的方式显示时间
	 *
	 * @param sdate
	 * @return
	 */
	public static String friendlyTimeShow(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0) {
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			} else {
				ftime = hour + "小时前";
			}
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0) {
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			} else {
				ftime = hour + "小时前";
			}
		} else if (days > 0 && days <= 3) {
			ftime = days + "天前";
		} else if (days > 3) {
			ftime = getFormatDateByPattern(sdate, "MM-dd");
		}
		return ftime;
	}

	/**
	 * 判断给定字符串时间是否为今日
	 * 
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate) {
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if (time != null) {
			String nowDate = dateFormater2.get().format(today);
			String timeDate = dateFormater2.get().format(time);
			if (nowDate.equals(timeDate)) {
				b = true;
			}
		}
		return b;
	}
	
	/**
	 * 返回long类型的今天的日期
	 * @return
	 */
	public static long getToday() {
		Calendar cal = Calendar.getInstance();
		String curDate = dateFormater2.get().format(cal.getTime());
		curDate = curDate.replace("-", "");
		return Long.parseLong(curDate);
	}

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}
	
	/**
	 * 判断是不是一个合法的手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,1-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	/**
	 * 判断是不是一个点
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPoint(String str){
		if(str.equals(".")){
			return true;
		}
		return false;
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * String转double
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static double toDouble(String obj) {
		try {
			return Double.parseDouble(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 将一个InputStream流转换成字符串
	 * @param is
	 * @return
	 */
	public static String toConvertString(InputStream is) {
		StringBuffer res = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader read = new BufferedReader(isr);
		try {
			String line;
			line = read.readLine();
			while (line != null) {
				res.append(line);
				line = read.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr) {
					isr.close();
					isr.close();
				}
				if (null != read) {
					read.close();
					read = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res.toString();
	}

	public static String formatDouble(double d){
		return new DecimalFormat("######0.00").format(d);
	}

	public static String formatDouble(String dStr){
		try{
			return new DecimalFormat("######0.00").format(Double.valueOf(dStr));
		}catch(Exception e){
			return "";
		}
		
	}

	public static boolean  isPhone(String phone){
		if(isEmpty(phone)){
			return false;
		}
		String telRegex = "^[1]\\d{10}$";
		return phone.matches(telRegex);
	}

	public static boolean isNumber(String number){
		if(isEmpty(number)){
			return false;
		}
		if (number.endsWith(".")) {
			number += "00";
		}
		String telRegex = "^[0-9]{0,}\\.{0,1}[0-9]{1,}$";
		return number.matches(telRegex);
	}

	public static boolean isDoubleOrFloat(String number, int w){
		if(isEmpty(number)){
			return false;
		}
		String telRegex = new StringBuffer("^[0-9]+\\.{0,1}[0-9]{1,").append(w).append("}$").toString();
		return number.matches(telRegex);
	}

	/*
	 *分转元，保留两位有限数字
	 */
	public static long moneyLong(String money) {
		if (!StringUtils.isNumber(money)) {
			return 0;
		}
		BigDecimal b = new BigDecimal(money);
		BigDecimal c = new BigDecimal(100);
		BigDecimal d = b.multiply(c).setScale(-2, BigDecimal.ROUND_DOWN);
		return d.longValue();
	}

	/**
	 * 将异常堆栈转换为字符串
	 */
	public static String getStackTrace(Throwable ex){
		final Writer result = new StringWriter();
		final PrintWriter pw = new PrintWriter(result);
		ex.printStackTrace(pw);
		return result.toString();
	}

	/**
	 * JSONObject中字段是否存在
	 */
	public static String isNull(JSONObject object, String str){
		try {
			if (!object.isNull(str)) {
				str = object.getString(str);
			}else{
				str = "";
			}
		}catch (Exception e){
			e.printStackTrace();
			str = "";
		}
		return str;
	}

	/**
	 * JSONObject中字段是否存在
	 */
	public static boolean jsonHasObject(JSONObject object, String str){
		try {
			object.getString(str);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public static long getTimeStamp(String timeStr) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = simpleDateFormat.parse(timeStr);
			return date.getTime();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 将字符串类型的时间戳(如149785460000)转为年.月.日
	 * @param str
	 * @return
     */
	public static String getFormatedDateTime(String str) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy.MM.dd");
		try {
			long dateTime = Long.valueOf(str);
			return sDateFormat.format(new Date(dateTime + 0));
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 根据所需日期格式返回当前日期
	 * @param str
	 * @param pattern
     * @return
     */
	public static String getFormatedDateTime(String str, String pattern) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
		try {
			long dateTime = Long.valueOf(str);
			return sDateFormat.format(new Date(dateTime + 0));
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 只取年月日时分秒字符串中的年月日
	 * @param str
	 * @return
     */
	public static String getFormatedDate(String str) {
		String[] date = str.split(" ");
		if (date[0] == null) {
			date[0] = "";
		}
		return date[0];
	}

	/**
	 * 获取指定格式的日期
	 * @param str
	 * @param pattern
     * @return
     */
	public static String getFormatDateByPattern(String str, String pattern) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
		try {
			long dateTime = getTimeStamp(str);
			return sDateFormat.format(new Date(dateTime + 0));
		} catch (Exception e) {
			return str;
		}
	}

	/**
	 * 将产品倒计时时间戳转化为时分秒显示
	 * @param mss
	 * @return
	 */
	public static String formatDuring(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		String formatStr = "时间错误,请重新加载界面数据";
		if (days > 0) {
			formatStr = "还有" + days + "天" + hours + "小时" + minutes + "分" + seconds + "秒";
		} else {
			formatStr = "还有" + hours + "小时" + minutes + "分" + seconds + "秒";
		}
		return formatStr;
	}

	public static String getStringOutE(String str){
		BigDecimal bd = new BigDecimal(str);
		return bd.toPlainString();
	}

	public static String getDecimalFormat(String str) {
		double initValue = 0.0D;
		String outStr = "";

		try {
			if(str != null && !"".equals(str.trim())) {
				initValue = Double.parseDouble(getStringOutE(str));
				DecimalFormat fmt = new DecimalFormat("##,###,###,###,###.##");
				double d = Double.parseDouble(String.valueOf(initValue));
				outStr = fmt.format(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int index = outStr.indexOf(".");
		if (index < 0) {
			outStr = outStr + ".00";
		}
		return outStr;
	}

	public static String getDecimalFormatTwo(String str) {
		double initValue = 0.0D;
		String outStr = "";

		try {
			if(str != null && !"".equals(str.trim())) {
				initValue = Double.parseDouble(getStringOutE(str));
				DecimalFormat fmt = new DecimalFormat(".##");
				double d = Double.parseDouble(String.valueOf(initValue));
				outStr = fmt.format(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int index = outStr.indexOf(".");
		if (index < 0) {
			outStr = outStr + ".00";
		}
		return outStr;
	}

	public static float computePercent(String current, String total) {
		float yt = Long.parseLong(current);
		float progress = (yt / (float) Float.parseFloat(total) * 100);
		DecimalFormat df = new DecimalFormat("#.##");
		return Float.parseFloat(df.format(progress));
	}

}
