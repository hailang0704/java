package demo;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Strings工具类的使用
 */
public class StringUseDemo {
    public static void main(String[] args) {
        notNullOrEmpty();
        System.out.println("----------------------------");
        commonPrefix();
        System.out.println("----------------------------");
        padStartEnd();
        System.out.println("----------------------------");
        splitter();
        System.out.println("----------------------------");
        splitterToMap();
        System.out.println("----------------------------");
        joiner();
        System.out.println("----------------------------");
        joinerTwice();
        System.out.println("----------------------------");

    }

    private static void joinerTwice() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "b");
        map.put("c", "d");
        String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(mapJoinResult);
    }

    private static void joiner() {
        String joinResult = Joiner.on(" ").join(new String[]{"hello", "world"});
        System.out.println(joinResult);
    }

    private static void splitterToMap() {
        String toSplitString = "a=b;c=d,e=f";
        Map<String, String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=').split(toSplitString);
        for (Map.Entry<String, String> entry : kvs.entrySet()) {
            System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
    }

    private static void splitter() {
        Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
                .trimResults()
                .omitEmptyStrings()
                .split("hello,word,,世界，水平");

        for (String item : splitResults) {
            System.out.println(item);
        }
    }

    private static void padStartEnd() {
        int minLength = 5;
        String padEndResult = Strings.padEnd("123", minLength, '0');
        System.out.println("padEndResult is " + padEndResult);

        String padStartResult = Strings.padStart("1", minLength, '0');
        System.out.println("padStartResult is " + padStartResult);
    }

    private static void notNullOrEmpty() {
        String input = "";
        boolean isNullOrEmpty = Strings.isNullOrEmpty(input);
        System.out.println("input " + (isNullOrEmpty ? "is" : "is not") + " null or empty.");
    }

    private static void commonPrefix() {
        //Strings.commonPrefix(a,b) demo
        String a = "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";
        String ourCommonPrefix = Strings.commonPrefix(a, b);
        System.out.println("a,b common prefix is " + ourCommonPrefix);

        //Strings.commonSuffix(a,b) demo
        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        String ourSuffix = Strings.commonSuffix(c, d);
        System.out.println("c,d common suffix is " + ourSuffix);
    }


}
