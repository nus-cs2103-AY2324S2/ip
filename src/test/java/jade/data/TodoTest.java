package jade.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] read a book", new Todo("read a book").toString());
        assertEquals("[T][X] read two books", new Todo("read two books", true).toString());
    }
}
