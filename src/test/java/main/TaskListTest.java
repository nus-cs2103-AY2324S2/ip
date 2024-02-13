package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TaskListTest {

    @Test
    public void mark_exceptionThrown(){
        try {
            TaskList taskList = new TaskList();
            taskList.mark(-1);
        } catch (Exception e) {
            assertEquals("Index -1 out of bounds for length 0", e.getMessage());
        }
    }
    @Test
    public void unmark_exceptionThrown(){
        try {
            TaskList taskList = new TaskList();
            taskList.unmark(-1);
        } catch (Exception e) {
            assertEquals("Index -1 out of bounds for length 0", e.getMessage());
        }
    }

    @Test
    public void remove_exceptionThrown(){
        try {
            TaskList taskList = new TaskList();
            taskList.remove(-1);
        } catch (Exception e) {
            assertEquals("Index -1 out of bounds for length 0", e.getMessage());
        }
    }
}
