package panda.component;  //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import panda.task.Todo;

public class TaskListTest {
    @Test
    public void taskListToString_emptyList_success() {
        // valid command results in exit command
        assertEquals("Your list is empty.", new TaskList().toString());
    }

    @Test
    public void taskListToString_unemptyList_success() {
        // valid command results in exit command
        TaskList tlist = new TaskList();
        tlist.insert(new Todo("read book"));
        assertEquals("Here are the tasks in your list:\n1.[T][ ] read book", tlist.toString());
    }
}
