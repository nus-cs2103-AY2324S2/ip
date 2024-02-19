package Kokbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toString_normalInput_success() {
        assertEquals("[T][ ] read book", new Todo("read book").toString());
    }

    @Test
    public void toFileString_normalInput_success() {
        assertEquals("T, ,read book", new Todo ("read book").toFileString());
    }
}
