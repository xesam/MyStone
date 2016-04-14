package test;

import dev.xesam.stone.Lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Created by xe on 2016/4/14.
 */
public class Test1 {
    public static void main(String[] args) {
        test1();
//        test2();
//        test3();
    }

    public static void test1() {
        String content = "a = \"this is xesar\"";
        System.out.println(content.length());
        Matcher matcher = Lexer.REGEX.matcher(content);
        int start = 0;
        int end = content.length();
        while (start < end) {
            final int tStart = start;
            matcher.region(start, end);
            if (matcher.lookingAt()) {
                System.out.println("-------------------------------------");
                IntStream.range(0, matcher.groupCount() + 1).forEach(i -> {
                    System.out.println(i + ",start=" + tStart + ",end=" + end + ":" + matcher.group(i));
                });
                start = matcher.end();
            } else {
                throw new RuntimeException("error");
            }
        }
    }

    public static void test2() {
        Matcher matcher = Pattern.compile("a(b)(c)").matcher("abcdefg");
        matcher.lookingAt();
        System.out.println(matcher.groupCount());
        System.out.println(matcher.group(0));
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
    }

    public static void test3() {
        Matcher matcher = Pattern.compile("\\p{Punct}").matcher("\"");
        System.out.print(matcher.find());
    }
}
