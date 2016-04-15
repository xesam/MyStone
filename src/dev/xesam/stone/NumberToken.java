package dev.xesam.stone;

/**
 * Created by xe on 2016/4/14.
 */
public class NumberToken extends Token {
    private int value;

    public NumberToken(int lineNumber, int value) {
        super(lineNumber);
        this.value = value;
    }

    public boolean isNumber() {
        return true;
    }

    @Override
    public String getText() {
        return value + "";
    }
}
