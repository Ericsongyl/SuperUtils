package com.nicksong.superutil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：songyuanlin
 * 创建时间：2017/2/17
 * 功能描述:
 */

public class FormatUtils {
    public FormatUtils() {
    }

    public static String formatF2Y(long amString) {
        return formatF2Y(String.valueOf(amString));
    }

    public static String formatF2Y(int amString) {
        return formatF2Y(String.valueOf(amString));
    }

    public static String formatF2Y(String amString) {
        StringBuffer result = new StringBuffer();
        if(isEmpty(amString)) {
            result.append("0.00");
        } else if(amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if(amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);

            for(int i = 1; i <= intString.length(); ++i) {
                if((i - 1) % 3 == 0 && i != 1) {
                    result.append(",");
                }

                result.append(intString.substring(intString.length() - i, intString.length() - i + 1));
            }

            result.reverse().append(".").append(amString.substring(amString.length() - 2));
        }

        return result.toString();
    }

    public static String formatY2F(long amount) {
        return formatY2F(String.valueOf(amount));
    }

    public static String formatY2F(int amount) {
        return formatY2F(String.valueOf(amount));
    }

    public static String formatY2F(String amount) {
        String currency = "";
        int index = 0;
        int length = 0;
        if(amount != null) {
            currency = amount.replaceAll("\\$|\\￥|\\,", "");
            index = currency.indexOf(".");
            length = currency.length();
        }

        Long amLong = Long.valueOf(0L);
        if(!isEmpty(amount) && index != -1) {
            if(length - index >= 3) {
                amLong = Long.valueOf(currency.substring(0, index + 3).replace(".", ""));
            } else if(length - index == 2) {
                amLong = Long.valueOf(currency.substring(0, index + 2).replace(".", "") + 0);
            } else {
                amLong = Long.valueOf(currency.substring(0, index + 1).replace(".", "") + "00");
            }
        } else {
            amLong = Long.valueOf(currency + "00");
        }

        return amLong.toString();
    }

    public static String getDecimalFormat(String str) {
        double initValue = 0.0D;
        String outStr = "";

        try {
            if(str != null && !"".equals(str.trim())) {
                initValue = Double.parseDouble(str);
                Double e = Double.valueOf(initValue / 100.0D);
                DecimalFormat fmt = new DecimalFormat("##,###,###,###,###.##");
                double d = Double.parseDouble(String.valueOf(e));
                outStr = fmt.format(d);
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return outStr;
    }

    public static String getDecimalFormat2(String str) {
        double initValue = 0.0D;
        String outStr = "";

        try {
            if(str != null && !"".equals(str.trim())) {
                initValue = Double.parseDouble(str);
                Double e = Double.valueOf(initValue / 100.0D);
                DecimalFormat fmt = new DecimalFormat("##,###,###,###,###.##");
                double d = Double.parseDouble(String.valueOf(e));
                outStr = fmt.format(d);
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        if(outStr.contains(".") && outStr.substring(outStr.lastIndexOf(".")).length() <= 2) {
            outStr = outStr + "0";
        }

        return outStr;
    }

    public static String insertComma(String s, int len) {
        if(s != null && s.length() >= 1) {
            try {
                DecimalFormat e = null;
                double num = Double.parseDouble(s);
                if(len == 0) {
                    e = new DecimalFormat("###,###");
                } else {
                    StringBuffer buff = new StringBuffer();
                    buff.append("###,###.00");

                    for(int i = 0; i < len; ++i) {
                        buff.append("#");
                    }

                    e = new DecimalFormat(buff.toString());
                }

                return e.format(num);
            } catch (Exception var7) {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String delComma(String s) {
        String formatString = "";
        if(s != null && s.length() >= 1) {
            formatString = s.replaceAll(",", "");
        }

        return formatString;
    }

    public static String dateFormat(String s) {
        try {
            long e = Long.parseLong(s);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = new Date(e);
            String t1 = format.format(d1);
            return t1;
        } catch (Exception var6) {
            return "";
        }
    }

    public static String dateTimeFormat(String s) {
        try {
            long e = Long.parseLong(s);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = new Date(e);
            String t1 = format.format(d1);
            return t1;
        } catch (Exception var6) {
            return "";
        }
    }

    public static String timeFormat(String s) {
        try {
            long e = Long.parseLong(s);
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date d1 = new Date(e);
            String t1 = format.format(d1);
            return t1;
        } catch (Exception var6) {
            return "";
        }
    }

    public static String presaleFormat(String s) {
        try {
            long e = Long.parseLong(s);
            SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm");
            Date d = new Date(e);
            String t = format.format(d);
            return t;
        } catch (Exception var6) {
            return "";
        }
    }

    public static String presaleFormat2(String s) {
        try {
            long e = Long.parseLong(s);
            SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
            Date d = new Date(e);
            String t = format.format(d);
            return t;
        } catch (Exception var6) {
            return "";
        }
    }

    public static String presaleFormatYMD(String s) {
        try {
            long e = Long.parseLong(s);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date(e);
            String t = format.format(d);
            return t;
        } catch (Exception var6) {
            return "";
        }
    }

    public static String customDataTime(String dateStyle, String s) {
        try {
            long e = Long.parseLong(s);
            SimpleDateFormat format = new SimpleDateFormat(dateStyle);
            Date d = new Date(e);
            String t = format.format(d);
            return t;
        } catch (Exception var7) {
            return "";
        }
    }

    public static boolean isEmpty(String str) {
        boolean isEmpty = false;
        if(str != null && str.trim().length() > 0) {
            isEmpty = false;
        } else {
            isEmpty = true;
        }

        return isEmpty;
    }
}
