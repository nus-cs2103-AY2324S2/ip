package duke;

import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UiTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream("test command".getBytes());
    private final InputStream originalInputStream = System.in;
    private final PrintStream originalOutputStream = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setIn(inputStreamCaptor);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOutputStream);
        System.setIn(originalInputStream);
    }

    @Test
    void testPrintLogo() {
        Ui.printLogo();
        assertEquals("Hello from\n" + Ui.logo + "\n", outputStreamCaptor.toString());
    }

    @Test
    void testPrintWelcomeMessage() {
        Ui.printWelcomeMessage();
        assertEquals("____________________________________________________________\n" +
                "Hello! I'm CharmBot \n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n", outputStreamCaptor.toString());
    }

    @Test
    void testPrintGoodbyeMessage() {
        Ui.printGoodbyeMessage();
        assertEquals("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n", outputStreamCaptor.toString());
    }

    @Test
    void testGetUserCommand() {
        assertEquals("test command", Ui.getUserCommand(new Scanner(System.in)));
    }

    @Test
    void testPrintMessage() {
        String testMessage = "This is a test message";
        Ui.printMessage(testMessage);
        assertEquals("____________________________________________________________\n" +
                "This is a test message\n" +
                "____________________________________________________________\n", outputStreamCaptor.toString());
    }
}
