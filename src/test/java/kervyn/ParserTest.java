package kervyn;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import kervyn.Tasks.Task;
import kervyn.Tasks.TaskList;
import kervyn.Tasks.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ParserTest {
    ArrayList<Task> userTasks = new ArrayList<Task>();
    ToDo todo = new ToDo("This is for a test case.", false);
    ToDo todo2 = new ToDo("This is for a test case again.", false);

    String[] markTaskUserInput = "mark 1".split(" ");
    String[] unMarkTaskUserInput = "unmark 1".split(" ");
    TaskList tasklist = new TaskList();
    @Test
    public void parseCommand_caseList() {
        tasklist.addTask(todo);
        tasklist.addTask(todo2);
        assertEquals(1, tasklist.listTasks(tasklist.getTaskList()));
    }

    @Test
    public void parseCommand_markTask() {
        tasklist.addTask(todo);
        tasklist.addTask(todo2);
        assertEquals(1, tasklist.markTask(tasklist.getTaskList(), markTaskUserInput));
    }

    @Test
    public void parseCommand_unMarkTask() {
        tasklist.addTask(todo);
        tasklist.addTask(todo2);
        assertEquals(1, tasklist.unMarkTask(tasklist.getTaskList(), unMarkTaskUserInput));
    }

}
