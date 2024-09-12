package pookie.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        assertEquals("[T][ ]read book", taskList.getTask(0).toString());
    }

    @Test
    public void markTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.markTask("1");
        assertEquals("[T][X]read book", taskList.getTask(0).toString());
    }

    @Test
    public void listTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new Event("tp project meeting", "2pm", "4pm"));

        String expected = "Here are the tasks in your list\n" + " 1. [T][ ]read book\n" + " 2. [E][ ]tp project meeting (from: 2pm to: 4pm)\n";

        assertEquals(expected, taskList.showList());
    }
}
