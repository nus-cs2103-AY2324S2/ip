package javassist.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javassist.task.Event;
import javassist.task.Task;
import javassist.task.Todo;

public class TaskListTest {

    @Test
    public void add_todo_listWithTodo() {
        TaskList list = new TaskList();
        Todo t = new Todo("read");
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(t);
        list.add(t);
        assertEquals(arr, list.getList());
    }

    @Test
    public void add_todoAndEvent_listWithTasks() {
        ArrayList<Task> arr = new ArrayList<>();
        TaskList list = new TaskList();
        Todo t = new Todo("read");
        Event e = new Event("meeting", "20-12-2023 13:00", "20-12-2023 20:00");
        arr.add(t);
        arr.add(e);
        list.add(t);
        list.add(e);
        assertEquals(arr, list.getList());
    }

    @Test
    public void getSize_emptyConstructor_zero() {
        TaskList list = new TaskList();
        assertEquals(0, list.getSize());
    }

    @Test
    public void getSize_newArrayList_zero() {
        ArrayList<Task> arr = new ArrayList<>();
        TaskList list = new TaskList(arr);
        assertEquals(0, list.getSize());
    }

    @Test
    public void delete_zeroIndexed_success() {
        Todo t = new Todo("read");
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(t);
        TaskList list = new TaskList(arr);
        assertEquals(t, list.delete(0));
    }

    @Test
    public void delete_oneIndexed_fail() {
        Todo t = new Todo("read");
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(t);
        TaskList list = new TaskList(arr);
        try {
            list.delete(1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }
    }

    @Test
    public void getTask_zeroIndexed_success() {
        Todo t = new Todo("read");
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(t);
        TaskList list = new TaskList(arr);
        assertEquals(t, list.getTask(0));
    }

    @Test
    public void getTask_oneIndexed_fail() {
        Todo t = new Todo("read");
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(t);
        TaskList list = new TaskList(arr);
        try {
            list.getTask(1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }
    }

    @Test
    public void print_todoInList_printHeadingAndTodo() {
        ArrayList<Task> arr = new ArrayList<>();
        Todo t = new Todo("read");
        arr.add(t);
        TaskList list = new TaskList(arr);
        assertEquals("Here are the tasks in your list:\n1." + t.printTask(), list.print());
    }

    @Test
    public void print_emptyList_printHeading() {
        TaskList list = new TaskList();
        assertEquals("No task in list.", list.print());
    }
}
