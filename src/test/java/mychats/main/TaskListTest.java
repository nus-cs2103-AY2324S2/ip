package mychats.main;
import java.util.ArrayList;

import mychats.exception.MyChatsException;
import mychats.task.Task;
import mychats.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {

    @Test
    void add() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList list = new TaskList(tasks);
        assertEquals(0, list.getSize(), "getSize() passed");
        Todo firstTask = new Todo("go to school");
        list.add(firstTask);
        assertEquals(1, list.getSize(), "add() passed");
        assertEquals(firstTask, list.get(0), "add() passed");
        Todo secondTask = new Todo("go home");
        list.add(secondTask);
        assertEquals(2, list.getSize(), "add() passed");
        assertEquals(secondTask, list.get(1), "add() passed");
    }
    @Test
    void getSize() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList list = new TaskList(tasks);
        assertEquals(0, list.getSize(), "getSize() passed");
        list.add(new Todo("go to school"));
        assertEquals(1, list.getSize(), "getSize() passed");
        list.add(new Todo("go home"));
        assertEquals(2, list.getSize(), "getSize() passed");
    }

    @Test
    void get() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList list = new TaskList(tasks);
        Todo firstTask = new Todo("go to school");
        list.add(firstTask);
        assertEquals(firstTask, list.get(0), "get() passed");
        Todo secondTask = new Todo("go home");
        list.add(secondTask);
        assertEquals(secondTask, list.get(1), "get() passed");
        Todo thirdTask = new Todo("eat dinner");
        list.add(thirdTask);
        assertEquals(thirdTask, list.get(2), "get() passed");
    }

    @Test
    void delete() throws MyChatsException {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList list = new TaskList(tasks);
        assertEquals(0, list.getSize(), "getSize() passed");
        Todo firstTask = new Todo("go to school");
        list.add(firstTask);
        assertEquals(1, list.getSize(), "add() passed");
        list.deleteTask(0);
        assertEquals(0, list.getSize(), "deleteTask() passed");
        Todo secondTask = new Todo("go home");
        list.add(secondTask);
        Todo thirdTask = new Todo("eat dinner");
        list.add(thirdTask);
        assertEquals(2, list.getSize(), "add() passed");
        list.deleteTask(0);
        assertEquals(1, list.getSize(), "deleteTask() passed");
        list.deleteTask(0);
        assertEquals(0, list.getSize(), "deleteTask() passed");
    }
}
