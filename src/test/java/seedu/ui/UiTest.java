package seedu.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.InvalidInputException;
import task.Task;
import task.ToDo;
import ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void redirect() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restore() {
        System.setOut(System.out);
    }

    @Test
    public void testWelcome() {
        Ui ui = new Ui();
        ui.printWelcome();
        assertEquals("------------------------------------------\n" +
                        "I'm Balkan Bot\n" + "Jebem ti mat\n" +
                        "------------------------------------------\n",
                outContent.toString());
    }

    @Test
    public void testBye() {
        Ui ui = new Ui();
        ui.printByeMessage();
        assertEquals("------------------------------------------\n" +
                        "Јебаћу ти бабицу\n" +
                        "------------------------------------------\n",
                outContent.toString());
    }

    @Test
    public void testDeletion() throws InvalidInputException {
        Ui ui = new Ui();
        Task t = new ToDo("test");
        int current = 1;
        ui.printDeletion(t, current);
        assertEquals("------------------------------------------\n" +
                        "Noted. I've removed this task:\n" +
                        t + "\n" +
                        "Now you have " + current + " task(s) in the list.\n" +
                        "------------------------------------------\n",
                outContent.toString());
    }
}