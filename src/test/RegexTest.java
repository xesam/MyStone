package test;

import dev.xesam.stone.Lexer;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xe on 2016/4/7.
 */
public class RegexTest {

    //[0-9]+
    public static final String PATTERN_NUMBER = Lexer.PATTERN_NUMBER;

    @Test
    public void test1() {
        Pattern pattern = Pattern.compile(PATTERN_NUMBER);
        Matcher matcher = pattern.matcher("0");
        Assert.assertTrue(matcher.find());
    }

    @Test
    public void test2() {
        Pattern pattern = Pattern.compile(PATTERN_NUMBER);
        Matcher matcher = pattern.matcher("0123");
        Assert.assertTrue(matcher.find());
    }

    @Test
    public void test3() {
        Pattern pattern = Pattern.compile(PATTERN_NUMBER);
        Matcher matcher = pattern.matcher("ab");
        Assert.assertFalse(matcher.find());
    }

    /**
     * abc -> "abc" -> "abc"
     * 'abc -> "'abc" -> "'abc"
     * "abc -> "\"abc" -> "\"abc"
     * "abc\ -> "\"abc\\" -> "\"abc\\\\"
     * "a[LF]bc\ -> "\"a\nbc\\" -> "\"a\\nbc\\\\"
     */
    public static final String PATTERN_STRING = Lexer.PATTERN_STRING;

    @Test
    public void test4() {
        Pattern pattern = Pattern.compile(PATTERN_STRING);
        Matcher matcher = pattern.matcher("\"abc\"");
        Assert.assertTrue(matcher.find());
    }

    @Test
    public void test5() {
        Pattern pattern = Pattern.compile(PATTERN_STRING);
        Matcher matcher = pattern.matcher("\"'abc\"");
        Assert.assertTrue(matcher.find());
    }

    @Test
    public void test6() {
        Pattern pattern = Pattern.compile(PATTERN_STRING);
        Matcher matcher = pattern.matcher("\"\\\"'abc\"");
        Assert.assertTrue(matcher.find());
    }

    @Test
    public void test7() {
        Pattern pattern = Pattern.compile(PATTERN_STRING);
        Matcher matcher = pattern.matcher("\"\\\\\\\"'abc\"");
        Assert.assertTrue(matcher.find());
    }

    @Test
    public void test8() {
        Pattern pattern = Pattern.compile(PATTERN_STRING);
        Matcher matcher = pattern.matcher("\"|\\\\\\\"'abc\"");
        Assert.assertTrue(matcher.find());
    }

    @Test
    public void test9() {
        Pattern pattern = Pattern.compile(PATTERN_STRING);
        Matcher matcher = pattern.matcher("\"\\n\"");
        Assert.assertTrue(matcher.find());
    }
}
