package dev.xesam.stone;

/**
 * Created by xe on 2016/4/14.
 */
public class StringToken extends Token {
    private String value;

    public StringToken(int lineNumber, String value) {
        super(lineNumber);
        this.value = value;
    }

    public boolean isString() {
        return true;
    }

    @Override
    public String getText() {
        return value;
    }
}