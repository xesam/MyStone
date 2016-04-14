package dev.xesam.stone;

import java.io.BufferedReader;
import java.io.Reader;

/**
 * Created by xe on 2016/4/14.
 */
public class LineNumberReader extends BufferedReader {
    public LineNumberReader(Reader in) {
        super(in, 1024);
    }

    public int getLineNumber(){
        return 0;
    }
}
