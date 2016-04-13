package dev.xesam.stone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Created by xe on 2016/4/7.
 */
public class Lexer {

    // ע��
    // //.*
    public static final String PATTERN_COMMENT = "(//.*)";

    // ����������
    // [0-9]+
    public static final String PATTERN_NUMBER = "([0-9]+)";

    // ��ʶ��
    // [a-z_A-Z][a-z_A-Z0-9]*  ==   <=   >=  ||  &&   \p{Punct}
    public static final String PATTERN_IDENTIFIER = "([a-z_A-Z][a-z_A-Z0-9]*|==|<=|>=|\\|\\||\\p{Punct})?";

    // �ַ���
    // ԭʼ�ı������� java �ַ����� "a\\b\"c\nd"
    // java �ַ�����ʾ�� "\"a\\\\b\\\"c\\nd\""
    // ����ת���ַ��Ķ�����ֱ���ù�����
    // �����ַ�����"\"\\\\\\\\b\\\\\"c\\\\nd\""
    public static final String PATTERN_STRING = "(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")";

    public static final String REGEX_PATTERN = "\\s*(" + PATTERN_COMMENT + "|" + PATTERN_NUMBER + "|" + PATTERN_IDENTIFIER + "|" + PATTERN_STRING + ")?";

    public Token read() {
        return null;
    }

    public Token peek(int i) {
        return null;
    }

    public static void main(String[] args) {
//        System.out.println(PATTERN_COMMENT);
//        System.out.println(PATTERN_NUMBER);
//        System.out.println(PATTERN_IDENTIFIER);
//        System.out.println(PATTERN_STRING);
//        System.out.println(REGEX_PATTERN);

        Pattern pattern = Pattern.compile(REGEX_PATTERN);
        String content = "a = \"abdc\"";
        Matcher matcher = pattern.matcher(content);

        int start = 0;
        while (matcher.find(start)) {
            System.out.println("groupCount():" + matcher.groupCount());
            IntStream.range(0, matcher.groupCount()).forEach(i -> {
                System.out.println("group(" + i + "):" + matcher.group(i));
            });

            start = matcher.end();
            if (start >= content.length()) {
                break;
            }
        }
    }
}
