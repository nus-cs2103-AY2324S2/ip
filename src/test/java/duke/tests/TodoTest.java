package duke.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.ToDo;


public class TodoTest {
    @Test
    public void todoToStringTest() {
        assertEquals("[T][ ] homework", new ToDo("homework").toString());
    }
}
