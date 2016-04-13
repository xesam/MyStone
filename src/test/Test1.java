package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xe on 2016/4/14.
 */
public class Test1 {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Pattern pattern = Pattern.compile("([a-z])");
        Matcher matcher = pattern.matcher("abcdefg");
        int start = 0;
        while (matcher.find(start)) {
            String group = matcher.group(0);
            System.out.println(group);
            start = matcher.end();
        }
    }
}
