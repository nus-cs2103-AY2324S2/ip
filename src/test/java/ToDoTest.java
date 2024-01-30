import duke.Tasks.TaskList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Tasks.TaskList;
import duke.Tasks.ToDo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest {
    /*
    public TaskList taskList;

    public TaskListTest() {
        this.taskList = new TaskList();
    }
    */

    public void dummyTest() {
        ToDo todo = new ToDo("hi", true, "T");
        assertEquals(todo.getIsDone(), true);
    }

}