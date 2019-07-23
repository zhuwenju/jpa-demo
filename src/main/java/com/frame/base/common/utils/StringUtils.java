package com.frame.base.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final String DEFAULT_CHAR_SET = "UTF-8";

    public static String safeString(String input) {
        if (isEmpty(input)) {
            return "";
        } else {
            return input;
        }
    }

    public static String zeroString(String input) {
        if (isEmpty(input)) {
            return "0";
        } else {
            return input;
        }
    }

    public static String listToString(List stringList) {
        return listToString(stringList, ",");
    }

    /**
     * 将字符串数组以指定字符分隔的形式拼接起来
     *
     * @param stringList
     * @return
     */
    public static String listToString(List stringList, String splitChar) {
        if (CollectionUtils.isEmpty(stringList)) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            builder.append(stringList.get(i));
            if (i < stringList.size() - 1) {
                builder.append(splitChar);
            }
        }
        return builder.toString();
    }

    /**
     * 将字符串从逗号分隔的地方分隔为String数组
     *
     * @param string
     * @return
     */
    public static List<String> stringToList(String string) {
        if (isEmpty(string)) {
            return null;
        }

        return stringToList(string, ",");
    }

    public static List<String> stringToList(String string, String splitChar) {
        if (isEmpty(string)) {
            return null;
        }

        return Arrays.asList(string.split(splitChar));
    }

    /**
     * 将字符串从逗号分隔的地方分隔为int数组
     *
     * @param string
     * @return
     */
    public static List<Integer> stringToIntList(String string) {
        if (isEmpty(string)) {
            return null;
        }

        List<Integer> integerList = new ArrayList<>();
        String[] stringArray = string.split(",");
        for (int i = 0; i < stringArray.length; i++) {
            String str = stringArray[i];
            if (isNotEmpty(str)) {
                integerList.add(Integer.valueOf(str));
            }
        }
        return integerList;
    }

    /**
     * 对指定数字补0
     *
     * @param input 待补0数据
     * @param num   位数
     * @return
     */
    public static String fillZero(int input, int num) {
        return String.format("%0" + num + "d", input);
    }


    /**
     * 对Email进行模糊化处理
     *
     * @param email
     * @return
     */
    public static String fuzzyEmail(String email) {
        int index = email.indexOf("@");
        if (index < 0) {
            return email;
        }
        String prefix = email.substring(0, index);
        String suffix = email.substring(index, email.length());
        if (prefix.length() < 3) {
            return "***" + suffix;
        } else {
            return prefix.substring(0, 2) + "***" + suffix;
        }
    }

    /**
     * 限制字符串长度
     *
     * @param input  字符串
     * @param length 长度
     * @return
     */
    public static String limitLength(String input, int length) {
        if (StringUtils.isEmpty(input)) {
            return "";
        }

        if (input.length() > length) {
            return input.substring(0, length);
        }
        return input;
    }

    public static String valueOf(byte[] bytes) {
        try {
            return new String(bytes, DEFAULT_CHAR_SET);
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转为查询字符串
     *
     * @param params
     * @return
     */
    public static String toQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (CollectionUtils.isNotEmpty(params)) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (i == 0) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(entry.getKey()).append("=").append(entry.getValue());
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * 转为查询字符串
     *
     * @param params
     * @return
     */
    public static String toQueryString(Map<String, String> params, String encoding) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        if (CollectionUtils.isNotEmpty(params)) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String paramName = entry.getKey();
                String paramValue = entry.getValue();
                if (i > 0) {
                    sb.append("&");
                }
                paramValue = encode(encoding, paramValue);
                sb.append(paramName).append("=").append(paramValue);
                i++;
            }
        }
        return sb.toString();
    }

    public static String encode(String value, String encoding) {
        if (isNotEmpty(value)) {
            try {
                value = URLEncoder.encode(value, encoding);
            } catch (UnsupportedEncodingException e) {
                // ignore
            }
        }
        return value;
    }

    public static String html_br(String input) {
        return input.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }

    /**
     * 计算文件比特数
     *
     * @param maxFileSizeStr
     * @return
     */
    public static long calculateFileBites(String maxFileSizeStr) {
        long maxFileSize = 0;
        if (maxFileSizeStr.toUpperCase().endsWith("MB")) {
            int mb = Integer.parseInt(maxFileSizeStr.substring(0, maxFileSizeStr.length() - 2));
            maxFileSize = mb * 1024 * 1024L;
        } else if (maxFileSizeStr.toUpperCase().endsWith("KB")) {
            int kb = Integer.parseInt(maxFileSizeStr.substring(0, maxFileSizeStr.length() - 2));
            maxFileSize = kb * 1024;
        }
        return maxFileSize;
    }

    /**
     * 去空格
     *
     * @param input
     * @return
     */
    public static String trim(String input) {
        if (StringUtils.isEmpty(input)) {
            return "";
        } else {
            input = input.trim();
            input = input.replaceAll("[\\u00A0]+", ""); // 去除Ascii160的空格
            return input;
        }
    }


    /**
     * 获得一个带有随机数字的字符串
     *
     * @param preffix 前缀
     * @param len     随机数字的长度
     * @return String 结果字符串
     */
    public static String randomDateStr(String preffix, Integer len) {
        String timePoint = DateUtils.dateToString(new Date(), DateUtils.yyyyMMddHHmmss);
        String randomNum = RandomStringUtils.randomNumeric(len);
        if (StringUtils.isNotEmpty(preffix)) {
            return preffix + timePoint + randomNum;
        }
        return timePoint + randomNum;
    }

    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static StringBuffer camel(StringBuffer str) {
        //利用正则删除下划线，把下划线后一位改成大写
        Pattern pattern = Pattern.compile("_(\\w)");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if(matcher.find()) {
            sb = new StringBuffer();
            //将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
            //正则之前的字符和被替换的字符
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            //把之后的也添加到StringBuffer对象里
            matcher.appendTail(sb);
        }else {
            return sb;
        }
        return camel(sb);
    }


    /**
     * 驼峰转下划线
     * @param str
     * @return
     */
    public static StringBuffer underline(StringBuffer str) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if(matcher.find()) {
            sb = new StringBuffer();
            //将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
            //正则之前的字符和被替换的字符
            matcher.appendReplacement(sb,"_"+matcher.group(0).toLowerCase());
            //把之后的也添加到StringBuffer对象里
            matcher.appendTail(sb);
        }else {
            return sb;
        }
        return underline(sb);
    }
}
