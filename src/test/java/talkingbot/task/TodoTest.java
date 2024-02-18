package talkingbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class for testing the Todo class.
 */
public class TodoTest {
    /**
     * Tests the Todo class' getSaveFileString() method.
     */
    @Test
    public void todoGetSaveFileStringTest() {
        assertEquals("T | 0 | read book",
                new Todo("read book").getSaveFileString());
    }

    /**
     * Tests the Todo class' toString() method.
     */
    @Test
    public void todoToStringTest() {
        assertEquals("[T] [X] study for exam",
                new Todo("study for exam", true).toString());
    }
}
