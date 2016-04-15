package dev.xesam.stone;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
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
//    public static final String PATTERN_STRING = "(\"(?:\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")";//个人感觉用这个更好，把内部的分组取消掉，因为并没有任何用处。

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
    private List<Token> tokens = new ArrayList<>();

    public Lexer(Reader reader) {
        this.reader = new LineNumberReader(reader);
    }

    public Token read() throws ParseException {
        if (fillQueue(0)) {
            return tokens.remove(0);
        } else {
            return Token.EOF;
        }
    }

    public Token peek(int i) throws ParseException {
        if (fillQueue(i)) {
            return tokens.get(i);
        } else {
            return Token.EOF;
        }
    }

    private boolean fillQueue(int size) throws ParseException {
        while (size >= tokens.size()) {
            if (hasMore) {
                readLine();
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean hasMore = true;//默认是有内容的

    private void addToken(int lineNumber, Matcher matcher) {
        String m = matcher.group(1);
        if (m != null) {
            if (matcher.group(2) == null) {//不是注释
                if (matcher.group(3) != null) {//number
                    tokens.add(new NumberToken(lineNumber, Integer.parseInt(matcher.group(3))));
                } else if (matcher.group(4) != null) {
                    tokens.add(new StringToken(lineNumber, matcher.group(4)));
                } else {
                    tokens.add(new IdToken(lineNumber, m));
                }
            }
        }
    }

    public void readLine() throws ParseException {
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (line == null) {
            hasMore = false;
            return;
        }
        int lineNumber = reader.getLineNumber();
        Matcher matcher = REGEX.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);
        int pos = 0;
        int endPos = line.length();
        while (pos < endPos) {
            matcher.region(pos, endPos);
            if (matcher.lookingAt()) {
                addToken(lineNumber, matcher);
                pos = matcher.end();
            } else {
                throw new ParseException("bad token at line" + lineNumber);
            }
        }
    }
}
