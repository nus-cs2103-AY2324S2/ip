package util;

import exception.NarutoException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseDescription() {
        try {
            assertEquals(Parser.parseDescription(" Homework"), "Homework" );
        } catch (NarutoException n) {
            System.out.println(n.getMessage());
            assertEquals(false, true);
        }
    }

}
