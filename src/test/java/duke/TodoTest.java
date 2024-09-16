package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toStringTest() {
        assertEquals(new Todo("return book").toString(),
            "[T][ ] return book");
    }
}

