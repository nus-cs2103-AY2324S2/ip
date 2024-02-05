package chatbot;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void testGreet() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the greet method
        Ui ui = new Ui();
        ui.greet();

        // Check if the output matches the expected output
        assertEquals("________________________________________________________________________________________\n" +
                        "Hello! I'm Alfred\nWhat can I do for you?\n" +
                        "________________________________________________________________________________________\n",
                outputStreamCaptor.toString());
    }

    @Test
    public void testBye() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the bye method
        Ui ui = new Ui();
        ui.bye();

        // Check if the output matches the expected output
        assertEquals("________________________________________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "________________________________________________________________________________________\n",
                outputStreamCaptor.toString());
    }

    @Test
    public void testEcho() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the echo method
        Ui ui = new Ui();
        ui.echo("Test message");

        // Check if the output matches the expected output
        assertEquals("________________________________________________________________________________________\n" +
                        "Test message\n" +
                        "________________________________________________________________________________________\n",
                outputStreamCaptor.toString());
    }

    @Test
    public void testPrintListWithEmptyList() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the printList method with an empty list
        Ui ui = new Ui();
        ui.printList(new ArrayList<>());

        // Check if the output matches the expected output
        assertEquals("________________________________________________________________________________________\n" +
                        "Sorry Master Bruce. There are no tasks in the list.\n" +
                        "________________________________________________________________________________________\n",
                outputStreamCaptor.toString());
    }

    @Test
    public void testPrintList() {
        Ui ui = new Ui();

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Task("Task 1"));
        taskList.add(new Task("Task 2"));
        taskList.add(new Task("Task 3"));

        // Create a ByteArrayOutputStream to capture the printed output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ui.printList(taskList);

        String printedOutput = outputStreamCaptor.toString().trim();

        String expectedOutput = "________________________________________________________________________________________\n" +
                "1. [ ] Task 1\n" +
                "2. [ ] Task 2\n" +
                "3. [ ] Task 3\n" +
                "________________________________________________________________________________________";

        assertEquals(expectedOutput, printedOutput);
    }
}
