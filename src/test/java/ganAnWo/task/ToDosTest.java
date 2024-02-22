package ganAnWo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDosTest {
    private ToDos t = new ToDos("return book");



    /**
     * Tests whether the string given is
     * same as the expected value.
     *
     */
    @Test
    public void stringTest() {
        assertEquals("[T][ ] return book", t.toString());
    }

    /**
     * Tests whether the string given is
     * same as the expected value.
     *
     */
    @Test
    public void writeTest() {
        assertEquals("T/false/return book", t.toWrite());
    }
}
