package charlie.commands;

import charlie.exceptions.CharlieException;
import charlie.storage.TaskList;
import charlie.ui.Ui;
import charlie.storage.Storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarkCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Ui ui;
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
    public void testMarkTask() throws CharlieException {

        Command command = new MarkCommand(1);
        storage = new Storage("./data/charlie.txt");
        TaskList tasks = new TaskList(storage.loadTasks());
        ui = new Ui();
        command.execute(tasks, ui, storage);
        String response = outContent.toString();

        String expectedResponse = "Marked task: 'borrow book";

        // Check if the actual response contains the expected text
        assertTrue(response.contains("Marked task: "));
    }

}