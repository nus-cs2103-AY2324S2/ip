package Ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testGreet() {
        Ui ui = new Ui();
        ui.greet();
        assertEquals("    Hi! I am your favourite friend, Lelu :)\n    What can I do for you?\n\n",
                outContent.toString());
    }

    @Test
    public void testExit() {
        Ui ui = new Ui();
        ui.exit();
        assertEquals("    Ok bye, you shall be missed :(\n",
                outContent.toString());
    }

    @Test
    public void testDateFormatInstructions() {
        Ui ui = new Ui();
        ui.dateFormatInstructions();
        assertEquals("    Your date should be in this format:\n    <YYYY-MM-DD HH:mm> e.g. 2024-02-03 15:25\n\n",
                outContent.toString());
    }
}
