package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void dummyTest() {
        ToDo todo = new ToDo("hi", true, "T");
        assertEquals(todo.getIsDone(), true);
    }

}