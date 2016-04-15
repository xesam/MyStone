package dev.xesam.stone;

/**
 * Created by xe on 2016/4/14.
 */
public class IdToken extends Token {
    String id;

    public IdToken(int lineNumber, String id) {
        super(lineNumber);
        this.id = id;
    }

    public boolean isIdentifier() {
        return true;
    }

    @Override
    public String getText() {
        return id;
    }

}
