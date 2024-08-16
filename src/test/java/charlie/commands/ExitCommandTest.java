package charlie.commands;

import charlie.exceptions.CharlieException;
import charlie.storage.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {
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
    public void testDeleteTask() throws CharlieException {

        Command command = new ExitCommand();
        storage = new Storage("./data/charlie.txt");
        TaskList tasks = new TaskList();
        String response = command.execute(tasks, storage);

        String expectedResponse = "See you later!";

        // Check if the actual response contains the expected text
        assertTrue(response.equals("See you later!"));
    }

}