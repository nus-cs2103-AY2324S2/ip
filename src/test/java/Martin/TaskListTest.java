package Martin;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testPrintList() {
        // Create a new TaskList object
        TaskList taskList = new TaskList(new ArrayList<Task>());

        // Add some tasks to the task list
        taskList.add(new Todo("Task 1"));
        taskList.add(new Todo("Task 2"));
        taskList.add(new Todo("Task 3"));

        // Redirect the standard output to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the printList method
        taskList.printList();

        // Verify the output
        String expectedOutput = "1.[T][ ] Task 1\n2.[T][ ] Task 2\n3.[T][ ] Task 3\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}