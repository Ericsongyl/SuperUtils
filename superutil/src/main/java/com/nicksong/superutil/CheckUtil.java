package com.nicksong.superutil;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {

    public static boolean isEmpty(String str) {
        boolean isEmpty = false;
        if ((str != null) && (str.trim().length() > 0)) {
            isEmpty = false;
        } else {
            isEmpty = true;
        }
        return isEmpty;
    }

    public static boolean isEqual(String str, String des) {
        boolean isEqual = false;
        if (!isEmpty(str) && !isEmpty(des)) {
            isEqual = str.equals(des);
        }
        return isEqual;
    }

    /**
     * 检查银行卡是否合法
     * isLegalBankCardNum
     * @param cont
     * @param card
     * @return
     * boolean
     * 841306
     * @since  1.0.0
     */
    public static boolean isLegalBankCardNum(Context cont, String card) {
        boolean isCard = false;
        String regex = "^\\d{10,24}$";
        isCard = Pattern.matches(regex, card);
        if (!isCard) {
            Toast.makeText(cont, "银行卡号错误", Toast.LENGTH_SHORT).show();
        }
        return isCard;
    }

    /**
     * 检测是否输入名字
     * isLegalInputName
     *
     * @param cont
     * @param name
     * @return
     *
     * boolean
     *
     * 841306
     *
     * @since  1.0.0
     */
    public static boolean isLegalInputName(Context cont, String name) {
        boolean isCard = true;
        if (isEmpty(name) || name.length() < 2) {
            isCard = false;
            Toast.makeText(cont, "持卡人姓名不少于两个字", Toast.LENGTH_SHORT).show();
        }
        return isCard;
    }

    /**
     * 对身份证号进行验证
     * isLegalCardId
     *
     * @param cont
     * @param id
     * @return
     *
     * boolean
     *
     * 841306
     *
     * @since  1.0.0
     */
    public static boolean isLegalCardId(Context cont, String id) {
        String regex = "([0-9]{17}([0-9]|X))|([0-9]{15})";
        boolean isTrue = false;
        isTrue = Pattern.matches(regex, id);
        if (!isTrue) {
            Toast.makeText(cont, "请输入正确的身份证号码", Toast.LENGTH_SHORT).show();
        }
        return isTrue;
    }

    /**
     * 检查手机号
     * isLegalPhoneNum
     *
     * @param cont
     * @param mobile
     * @return
     *
     * boolean
     *
     * 841306
     *
     * @since  1.0.0
     */
    public static boolean isLegalPhoneNum(Context cont, String mobile) {
        boolean isTel = false;
        isTel = Pattern.matches("^(1)\\d{10}$", mobile);
        if (!isTel) {
            Toast.makeText(cont, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
        }
        return isTel;
    }

    /**
     * 检测银行卡密码、支付密码是否是6位数字的密码
     * isLegalSixIntePwd
     *
     * @param cont
     * @param pwd
     * @return
     *
     * boolean
     *
     * 841306
     *
     * @since  1.0.0
     */
    public static boolean isLegalSixIntePwd(Context cont, String pwd) {
        boolean isPwd = false;
        String regex = "^[0-9]{6}$";
        isPwd = Pattern.matches(regex, pwd);
        if (!isPwd) {
            Toast.makeText(cont, "您输入的密码不正确", Toast.LENGTH_SHORT).show();
        }
        return isPwd;
    }

    /**
     * 检测验证码
     * isLegalCaptcha
     *
     * @param cont
     * @param pwd
     * @return
     *
     * boolean
     *
     * 841306
     *
     * @since  1.0.0
     */
    public static boolean isLegalCaptcha(Context cont, String pwd) {
        boolean isPwd = false;
        String regex = "^[0-9]{6}$";
        isPwd = Pattern.matches(regex, pwd);
        if (!isPwd) {
            Toast.makeText(cont, "您输入的验证码不正确", Toast.LENGTH_SHORT).show();
        }
        return isPwd;
    }

    /**
     * 检测该字符是否是x
     * isStr
     *
     * @param text
     * @return
     *
     * boolean
     *
     * 841306
     *
     * @since  1.0.0
     */
    public static boolean isStr(CharSequence text) {
        try {
            if (text.toString().toUpperCase().contains("X")) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * 检测是否是纯数字
     * isNum
     *
     * @param text
     * @return
     *
     * boolean
     *
     * 841306
     *
     * @since  1.0.0
     */
    public static boolean isNum(CharSequence text) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(text);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    public static boolean validPayPwd(String password, String loginName) {
        // // 6-20 个字符
        // if (!password.matches("^\\d{6,20}$")) {
        // return false;
        // }
        // 不能包含有连续四位及以上顺序(或逆序)数字或字母；（如：1234、abcd等）
        int asc = 1;
        int desc = 1;
        int lastChar = password.charAt(0);
        for (int i = 1; i < password.length(); i++) {
            int currentChar = password.charAt(i);
            if (!(password.charAt(i) + "").matches("[0-9]")) {
                asc = 0;
                desc = 0;
            } else if (lastChar == currentChar - 1) {
                asc++;
                desc = 1;
            } else if (lastChar == currentChar + 1) {
                desc++;
                asc = 1;
            } else {
                asc = 1;
                desc = 1;
            }
            if (asc >= 4 || desc >= 4) {
                return false;
            }
            lastChar = currentChar;
        }

        // 不能将账号名作为密码的一部分存在于密码，账号密码也不能一样
        if (!isEmpty(loginName)
                && (loginName.equals(password) || password.contains(loginName))) {
            return false;
        }
        return true;
    }

    /**
     * 银行卡字符每四位分隔，并格式化成16位标准长度
     * @param number
     * @return
     */
    public static String strMaskFourForamt(String number){
        number = number.substring(number.lastIndexOf("*")+1);
        number = "************"+number;
        StringBuffer result = new StringBuffer();
        result.append(number);
        String formartStr = result.toString();
        String regex = "(.{4})";
        formartStr = (formartStr.replaceAll (regex, "$1 "));
        return formartStr;
    }

    /**
     * 银行卡字符每四位分隔，并格式化成16位标准长度
     * @param number
     * @return
     */
    public static String formatBankNo(String number){
        if (!StringUtils.isEmpty(number)) {
            if (number.length() > 4) {
                number = number.substring(number.length() - 4, number.length());
            }
            number = "************" + number;
            StringBuffer result = new StringBuffer();
            result.append(number);
            String formartStr = result.toString();
            String regex = "(.{4})";
            formartStr = (formartStr.replaceAll(regex, "$1 "));
            return formartStr;
        } else {
            return "**** **** **** ****";
        }
    }

	/**
     * 获取银行卡号后4位
     * @param number
     * @return
     */
    public static String getBankEnd4No(String number) {
        if (!StringUtils.isEmpty(number)) {
            if (number.length() > 4) {
                number = number.substring(number.length() - 4, number.length());
            }
            return number;
        } else {
            return "****";
        }
    }

    /**
     * 字符每四位分隔
     * @param number
     * @return
     */
    public static String strFourForamt(String number){
        StringBuffer result = new StringBuffer();
        result.append(number);
        String formartStr = result.toString();
        String regex = "(.{4})";
        formartStr = (formartStr.replaceAll (regex, "$1 "));
        return formartStr;
    }

	/**
     * 手机号中间4位用*显示
     * @param number
     * @return
     */
    public static String strPassForamt(String number){
        if (StringUtils.isEmpty(number)){
            return " ";
        }
        StringBuffer result = new StringBuffer();
        result.append(number);
        result.replace(3,7,"****");
        return result.toString();
    }

	/**
     * 格式化姓名显示，名字用*显示
     * @param name
     * @return
     */
    public static String formatTrueName(String name) {
        if (name == null || StringUtils.isEmpty(name)) {
            return "";
        }
        StringBuffer result = new StringBuffer();
        result.append(name);
        try {
            if (name.length() >= 2) {
                result.replace(0, 1, "*");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

	/**
     * 身份证格式化，中间出生年月日用*显示
     * @param cardCode
     * @return
     */
    public static String formatIdCardCode(String cardCode) {
        if (cardCode == null || StringUtils.isEmpty(cardCode)) {
            return "";
        }
        StringBuffer result = new StringBuffer();
        result.append(cardCode);
        try {
            String replaceStr = "";
            for (int i = 0; i < 14; i++) {
                replaceStr = replaceStr + "*";
            }
            result.replace(0, 14, replaceStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 手机号格式化为344样式 133 4567 8900
     * @param number
     * @return
     */
    public static String phoneFormat(String number){
        if (number.isEmpty()){
            return " ";
        }
        if(number.length()==11){
            StringBuffer result = new StringBuffer();
            result.append(number);
            result.replace(3,3," ");
            result.replace(8,8," ");
            return result.toString();
        }else{
            return  number;
        }
    }

    public static String numberFormat(String number) {
        StringBuffer result = new StringBuffer();
        result.append(number);
        if (number.length() >= 8) {
            result.replace(3,3, " ");
            result.replace(8,8," ");
        } else if (number.length() >= 4 && number.length() < 8) {
            result.replace(3,3, " ");
        }
        return result.toString();
    }

    /**
     * 手机号码输入时格式化成344格式
     */
    public class PhoneFormatTextWatcher implements TextWatcher {
        private int oldLength; // 用于对比，否则输入到3或者8位时无法删除
        private int curLength;

        public void afterTextChanged(Editable editable) {
            if (editable != null) {
                if (curLength > oldLength) {
                    if ((curLength == 4 || curLength == 9)) {
                        editable.insert(oldLength, " ");
                    }
                } else { // 删除了
                    if (oldLength == 5 || oldLength == 10) {
                        editable.delete(curLength - 1, curLength);
                    }
                }
            }
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            curLength = s.length();
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            oldLength = s.length();
        }
    }

}
