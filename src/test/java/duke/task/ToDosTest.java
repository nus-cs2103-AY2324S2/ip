package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    ToDos t = new ToDos("return book");



    /**
     * Tests whether the string given is
     * same as the expected value.
     *
     */
    @Test
    public void stringTest(){
        assertEquals("[T][ ] return book",t.toString());
    }

    /**
     * Tests whether the string given is
     * same as the expected value.
     *
     */
    @Test
    public void writeTest(){
        assertEquals("T/false/return book",t.toWrite());
    }
}
