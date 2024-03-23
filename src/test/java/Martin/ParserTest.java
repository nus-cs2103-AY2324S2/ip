package martin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void testParse() {
        Parser parser = new Parser();

        // Test case 1: input is "list"
        String input1 = "list";
        ChatbotKeyword expectedOutput1 = ChatbotKeyword.LIST;
        assertEquals(expectedOutput1, parser.parse(input1));

        // Test case 2: input is "deadline"
        String input2 = "deadline";
        ChatbotKeyword expectedOutput2 = ChatbotKeyword.DEADLINE;
        assertEquals(expectedOutput2, parser.parse(input2));

        // Test case 3: input is "hello"
        String input3 = "hello";
        assertThrows(IllegalArgumentException.class, () -> parser.parse(input3));
    }

    @Test
    public void testRemoveCommandWord() {
        Parser parser = new Parser();

        // Test case 1: input with single word
        String input1 = "Hello";
        String expectedOutput1 = "";
        assertEquals(expectedOutput1, parser.removeCommandWord(input1));

        // Test case 2: input with multiple words
        String input2 = "Hello world, how are you?";
        String expectedOutput2 = "world, how are you?";
        assertEquals(expectedOutput2, parser.removeCommandWord(input2));

        // Test case 3: input with trailing spaces
        String input3 = "Hello world   ";
        String expectedOutput3 = "world";
        assertEquals(expectedOutput3, parser.removeCommandWord(input3));
    }
}