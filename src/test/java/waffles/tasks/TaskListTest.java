package waffles.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void add_taskList_success() {
        String root = System.getProperty("user.dir");
        String path = Path.of(root, "src/test/java/waffles/tasks/task_list_test.txt").toString();
        File f = new File(path);

        if (f.exists()) {
            f.delete();
        }

        TaskList taskList = new TaskList("src/test/java/waffles/tasks/task_list_test.txt");

        taskList.addToDoTask("go to the gym");
        String output = String.format("Here are the tasks in your list:%n%s", "1.[T][ ] go to the gym");
        assertEquals(output, taskList.toString());
    }

    @Test
    public void missing_argument_exceptionThrown() {
        String root = System.getProperty("user.dir");
        String path = Path.of(root, "src/test/java/waffles/tasks/task_list_test.txt").toString();
        File f = new File(path);

        if (f.exists()) {
            f.delete();
        }

        TaskList taskList = new TaskList("src/test/java/waffles/tasks/task_list_test.txt");

        try {
            taskList.markTask("");
        } catch (Exception e) {
            assertEquals("The command you entered has missing arguments. Please try again!", e.getMessage());
        }
    }

}
