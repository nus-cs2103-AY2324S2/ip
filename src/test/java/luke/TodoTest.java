package luke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private final TaskList taskListTest = new TaskList();

    @Test
    public void testAddTodo() {
        assertEquals(0, taskListTest.getNoTasks());
        taskListTest.addTodo("return book");
        assertEquals(1, taskListTest.getNoTasks());
    }

    @Test
    public void testMarkTask() {
        taskListTest.addTodo("return book");

        // mark task that doesn't exist
        Ui ui = new Ui(taskListTest);
        String str = ui.handleInput("mark 5");
        assertEquals("Task does not exist. Please give a valid task number.", str);

        // mark task that exists
        str = ui.handleInput("mark 1");
        assertEquals("Nice! I've marked this task as done: \n" + taskListTest.getTask(0), str);
    }

    @Test
    public void testUnmarkTask() {
        taskListTest.addTodo("return book");

        // mark task that doesn't exist
        Ui ui = new Ui(taskListTest);
        String str = ui.handleInput("unmark 5");
        assertEquals("Task does not exist. Please give a valid task number.", str);

        // mark task that exists
        str = ui.handleInput("unmark 1");
        assertEquals("OK, I've marked this task as not done yet: \n" + taskListTest.getTask(0), str);
    }

    @Test
    public void testDeleteTask() {
        taskListTest.addTodo("return book");
        Todo d = new Todo("return book");

        // delete task that doesn't exist
        Ui ui = new Ui(taskListTest);
        String str = ui.handleInput("delete 5");
        assertEquals("Task does not exist. Please give a valid task number.", str);

        // delete task that exists
        str = ui.handleInput("delete 1");
        String expectedString = "Noted. I've removed this task:\n" + d
                + "\nNow you have " + taskListTest.getNoTasks() + " tasks in the list.";
        assertEquals(expectedString, str);
    }
}
