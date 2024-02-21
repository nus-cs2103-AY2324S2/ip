package waffles.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void add_taskList_success() {
        TaskList taskList = new TaskList("src/test/java/waffles/tasks/task_list_test.txt");

        taskList.addToDoTask("go to the gym");
        String output = String.format("Here are the tasks in your list:%n%s", "1.[T][ ] go to the gym");
        assertEquals(output, taskList.toString());
    }

    @Test
    public void missing_argument_exceptionThrown() {
        TaskList taskList = new TaskList("src/test/java/waffles/tasks/task_list_test.txt");

        try {
            taskList.markTask("");
        } catch (Exception e) {
            assertEquals("The command you entered has missing arguments. Please try again!", e.getMessage());
        }
    }

}
