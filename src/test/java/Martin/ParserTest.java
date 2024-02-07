package Martin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testGetRemainingWords() {
        Parser parser = new Parser();

        // Test case 1: input with single word
        String input1 = "Hello";
        String expectedOutput1 = "";
        assertEquals(expectedOutput1, parser.getRemainingWords(input1));

        // Test case 2: input with multiple words
        String input2 = "Hello world, how are you?";
        String expectedOutput2 = "world, how are you?";
        assertEquals(expectedOutput2, parser.getRemainingWords(input2));

        // Test case 3: input with leading spaces
        String input3 = "   Hello   world";
        String expectedOutput3 = "world";
        assertEquals(expectedOutput3, parser.getRemainingWords(input3));

        // Test case 4: input with trailing spaces
        String input4 = "Hello world   ";
        String expectedOutput4 = "world";
        assertEquals(expectedOutput4, parser.getRemainingWords(input4));

        // Test case 5: input with leading and trailing spaces
        String input5 = "   Hello   world   ";
        String expectedOutput5 = "world";
        assertEquals(expectedOutput5, parser.getRemainingWords(input5));
    }
}