package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exception.NarutoException;

public class ParserTest {

    @Test
    public void testParseDescription() {
        try {
            assertEquals(Parser.parseDescription(" Homework"), "Homework");
        } catch (NarutoException n) {
            System.out.println(n.getMessage());
            assertEquals(false, true);
        }
    }

}
