package charlie.commands;  //same package as the class being tested

import charlie.models.*;
import charlie.exceptions.CharlieException;
import charlie.storage.Storage;
import charlie.storage.TaskList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Storage storage;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testAddTodoTask() throws CharlieException {

        Command command = new AddCommand("todo borrow book");
        Task todoTask = new Todo("todo borrow book");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(todoTask);
        TaskList tasklist = new TaskList(tasks);
        storage = new Storage("./data/charlie.txt");
        String response = command.execute(tasklist, storage);

        String expectedResponse = "Got it. I've added this task:\n" +
                "  [T][ ] borrow book\n" +
                "Now you have 1 tasks in the list.";

            // Check if the actual response contains the expected text
        assertTrue(response.contains("Got it. I've added this task:"));
        assertTrue(response.contains("[T][ ] borrow book"));
        assertTrue(response.contains("tasks in the list."));
    }

    @Test
    public void testAddDeadlineTask() throws CharlieException {

        Command command = new AddCommand("deadline return book /by 2019-10-15");
        Task deadlineTask = new Deadline("return book", "2019-10-15");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(deadlineTask);
        TaskList tasklist = new TaskList(tasks);
        storage = new Storage("./data/charlie.txt");
        String response = command.execute(tasklist, storage);

        String expectedResponse = "Got it. I've added this task:\n" +
                "  [D][ ] return book (by: Oct 15 2019)\n" +
                "Now you have 2 tasks in the list.";

        // Check if the actual response contains the expected text
        assertTrue(response.contains("Got it. I've added this task:"));
        assertTrue(response.contains("[D][ ] return book (by: Oct 15 2019)"));
        assertTrue(response.contains("tasks in the list."));
    }

}
