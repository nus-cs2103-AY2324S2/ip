package tes.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the testing class for Ui class.
 */
public class UiTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Tests the greet function in Ui class.
     */
    @Test
    public void testGreet() {
        Ui ui = new Ui();

        ui.greet();

        String printedOutput = outputStreamCaptor.toString().replaceAll("\\r\\n", "\n");

        String line = "    _______________________________________________________________\n";
        String expected = line + "    Tes here.\n"
                + "    huh? What you want from me?\n" + line;
        assertEquals(expected, printedOutput);
    }
}
