package seedu.ui;

import gui.GuiUi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.InvalidInputException;
import task.Task;
import task.ToDo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuiUiTest {
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
        GuiUi guiUi = new GuiUi();
        assertEquals("I'm Balkan Bot\n" + "Jebem ti mat",
                guiUi.printWelcome());
    }

    @Test
    public void testBye() {
        GuiUi guiUi = new GuiUi();
        assertEquals("Јебаћу ти бабицу",
                guiUi.printByeMessage());
    }

    @Test
    public void testDeletion() throws InvalidInputException {
        GuiUi guiUi = new GuiUi();
        Task t = new ToDo("test");
        int current = 1;
        assertEquals("Noted. I've removed this task:\n" +
                        t + "\n" +
                        "Now you have " + current + " task(s) in the list.",
                guiUi.printDeletion(t, current));
    }
}