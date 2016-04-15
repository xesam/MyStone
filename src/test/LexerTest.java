package test;

import dev.xesam.stone.Lexer;
import dev.xesam.stone.ParseException;
import dev.xesam.stone.Token;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by xe on 2016/4/16.
 */
public class LexerTest {
    public static void main(String[] args) {
        try {
            Files.lines(Paths.get("1.txt")).forEach(System.out::println);

            FileReader fileReader = new FileReader(new File("1.txt"));
            Lexer lexer = new Lexer(fileReader);
            Token token;
            while ((token = lexer.read()) != Token.EOF) {
                System.out.println(token.getLineNumber() + ":" + token.getText());
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
