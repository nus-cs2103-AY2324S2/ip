package anita;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoString_Success() throws Exception{
        assertEquals("[T][ ] max win", new Todo("max win", "False").toString());
    }
}
