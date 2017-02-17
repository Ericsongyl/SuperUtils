package com.nicksong.superutil;

import java.util.regex.Pattern;

/**
 * 作者：NickSong
 * 创建时间：2017/2/17
 * 功能描述:
 */

public class EditInputFilter {

    final static String _LETTER = "[-`=///[/];',./~!@#$%^&*()_+|{}:\"<>?]";
    final static String _NUMBER = "\\d";
    final static String _MARK = "[a-zA-Z]";

    /**
     * 判断一个字符串是否含有数字
     *
     * @param content
     * @return
     */
    public static boolean hasDigit(String content) {
        return Pattern.compile(_NUMBER).matcher(content).find();
    }

    /**
     * 判断一个字符串是否含有字母
     *
     * @param content
     * @return
     */
    public static boolean hasMark(String content) {
        return Pattern.compile(_MARK).matcher(content).find();
    }

    /**
     * 判断一个字符串是否含有特殊字符
     *
     * @param content
     * @return
     */
    public static boolean hasLetter(String content) {
        return Pattern.compile(_LETTER).matcher(content).find();
    }
}
