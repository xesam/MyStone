package dev.xesam.stone;

import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xe on 2016/4/7.
 */
public class Lexer {

    // 注释
    // //.*
    public static final String PATTERN_COMMENT = "(//.*)";

    // 整型字面量
    // [0-9]+
    public static final String PATTERN_NUMBER = "([0-9]+)";

    // 标识符
    // [a-z_A-Z][a-z_A-Z0-9]*  ==   <=   >=  ||  &&   \p{Punct}
    public static final String PATTERN_IDENTIFIER = "[a-z_A-Z][a-z_A-Z0-9]*|==|<=|>=|\\|\\||\\p{Punct}?";

    // 字符串
    // 原始文本，不是 java 字符串： "a\\b\"c\nd"
    // java 字符串表示： "\"a\\\\b\\\"c\\nd\""
    // \ 才需要转义
    // 正则字符串："\"\\\\\\\\b\\\\\"c\\\\nd\""
    public static final String PATTERN_STRING = "(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")";

    /**
     * 注意先后顺序 \p{Punct} 是会匹配双引号的。
     */
    public static final String REGEX_PATTERN = "\\s*("
            + PATTERN_COMMENT
            + "|" + PATTERN_NUMBER
            + "|" + PATTERN_STRING
            + "|" + PATTERN_IDENTIFIER
            + ")?";

    public static Pattern REGEX = Pattern.compile(REGEX_PATTERN);

    private LineNumberReader reader;

    public Lexer(Reader reader) {
        this.reader = new LineNumberReader(reader);
    }

    public Token read() {
        return null;
    }

    public Token peek(int i) {
        return null;
    }

    private boolean hasMore = false;

    private void addToken(int lineNo, Matcher matcher) {
        String m = matcher.group(1);
        System.out.println(m);
    }

    public void readLine() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            hasMore = false;
            return;
        }
        int lineNo = reader.getLineNumber();
        Matcher matcher = REGEX.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);
        int pos = 0;
        int endPos = line.length();
        while (pos < endPos) {
            matcher.region(pos, endPos);
            if (matcher.lookingAt()) {
                addToken(lineNo, matcher);
                pos = matcher.end();
            } else {
                throw new IOException("");
            }
        }
    }
}
