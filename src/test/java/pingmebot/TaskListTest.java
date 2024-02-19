package pingmebot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import pingmebot.task.Task;
import pingmebot.task.ToDos;
public class TaskListTest {
    @Test
    public void getTaskSizeTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList tl = new TaskList(tasks);
        tasks.add(new ToDos("project 1"));
        tasks.add(new ToDos("project 2"));
        tasks.add(new ToDos("project 3"));
        assertEquals(3, tl.getTaskSize());
    }

    @Test
    public void updateTaskToStorageTest() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            tasks.add(new ToDos("project 1"));
            tasks.add(new ToDos("project 2"));
            TaskList tl = new TaskList(tasks);
            String filePath = "./data/dukeData.txt";
            // File will always exist since it will be created in the file storage constructor if not found
            tl.updateTaskToStorage(new Storage(filePath));

            String line;
            String totalLine = "";
            try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(filePath).toFile()))) {
                while ((line = reader.readLine()) != null) {
                    totalLine += line + "\n";
                }
            } catch (IOException e) {
                fail("Test Failed.");
            }

            assertEquals("todo | 0 | project 1" + "\n" + "todo | 0 | project 2" + "\n", totalLine);
        } catch (PingMeException e) {
            fail("Test Failed.");
        }

    }

    @Test
    public void addTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList tl = new TaskList(tasks);
        tl.addTask(new ToDos("project"));
        assertEquals(1, tl.tasks.size());
    }

    @Test
    public void removeTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("project"));
        TaskList tl = new TaskList(tasks);
        // Task number is 0-based
        tl.removeTask(0);
        assertEquals(0, tl.tasks.size());
    }

    @Test
    public void taskToStringTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("project"));
        TaskList tl = new TaskList(tasks);
        assertEquals("[T][ ] project", tl.taskToString(0));
    }

    @Test
    public void taskStatusIconTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("project"));
        TaskList tl = new TaskList(tasks);
        assertEquals(" ", tl.taskStatusIcon(0));
    }

    @Test
    public void taskMarkAsDoneTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("project"));
        TaskList tl = new TaskList(tasks);
        tl.taskMarkAsDone(0);
        assertEquals("[T][X] project", tl.tasks.get(0).toString());
    }

    @Test
    public void taskUncheckTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("project"));
        TaskList tl = new TaskList(tasks);
        tl.taskMarkAsDone(0);
        tl.taskUncheckTask(0);
        assertEquals("[T][ ] project", tl.tasks.get(0).toString());
    }

    @Test
    public void findMatchingTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("read book"));
        tasks.add(new ToDos("borrow book"));
        tasks.add(new ToDos("project"));
        TaskList tl = new TaskList(tasks);

        ArrayList<Task> expectedOutput = new ArrayList<>();
        expectedOutput.add(new ToDos("read book"));
        expectedOutput.add(new ToDos("borrow book"));
        assertEquals(expectedOutput, tl.findMatchingTask("book"));
    }

}
