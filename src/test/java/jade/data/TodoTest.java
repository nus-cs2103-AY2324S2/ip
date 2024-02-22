package jade.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The <code>TodoTest</code> class contains unit tests for the <code>Todo</code> class,
 * which represents a todo task within the task management system.
 * These tests verify the functionality of converting a todo task to a string representation.
 * The above comment is generated using ChatGPT 3.5 using the prompt:
 * "generate a block comment for the TodoTest class:{code}".
 * Modified by author for higher quality.
 */
public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] read a book", new Todo("read a book").toString());
        assertEquals("[T][X] read two books", new Todo("read two books", true).toString());
    }
}
