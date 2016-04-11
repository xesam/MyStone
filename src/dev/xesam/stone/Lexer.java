package dev.xesam.stone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Created by xe on 2016/4/7.
 */
public class Lexer {

    // 整型字面量
    // //.*
    public static final String PATTERN_COMMENT = "(//.*)";

    // 整型字面量
    // [0-9]+
    public static final String PATTERN_NUMBER = "([0-9]+)";

    // 标识符
    // [a-z_A-Z][a-z_A-Z0-9]*  ==   <=   >=  ||  &&   \p{Punct}
    public static final String PATTERN_IDENTIFIER = "([a-z_A-Z][a-z_A-Z0-9]*|==|<=|>=|\\|\\||\\p{Punct})?";

    // 字符串
    // 标准形式： "(\"|\\\\|\\n|[^\"])*"
    // 使用 java 表达，需要转义：\"(\\\"|\\\\\\\\|\\\\n|[^\"])*\"
    public static final String PATTERN_STRING = "(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")";

    public static final String regexPat = "\\s*(" + PATTERN_COMMENT + "|" + PATTERN_NUMBER + "|" + PATTERN_IDENTIFIER + "|" + PATTERN_STRING + ")?";

    public Token read() {
        return null;
    }

    public Token peek(int i) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(regexPat);
        Pattern pattern = Pattern.compile(regexPat);
        Matcher matcher = pattern.matcher("a = \"abdc\"");
        System.out.println(matcher.find());
        IntStream.range(0, matcher.groupCount()).forEach((i) -> {
            String group = matcher.group(i);
            System.out.println(group);
        });
    }
}
