package dev.xesam.stone;

/**
 * Created by xe on 16-2-28.
 */
public class Token {
    public static final Token EOF = new Token(-1);//�ļ���β
    public static final String EOL = "\\n";//�н�β

    private int lineNumber;

    public Token(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public boolean isIdentifier() {
        return false;
    }


    public boolean isNumber() {
        return false;
    }

    public boolean isString() {
        return false;
    }


}
