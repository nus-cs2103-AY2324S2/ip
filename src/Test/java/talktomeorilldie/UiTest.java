package talktomeorilldie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for Ui class.
 */
public class UiTest {

    /**
     * Tests the readInput method of Ui.
     */
    @Test
    public void testReadInput() {
        String input = "Test Input";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Ui ui = new Ui();
        assertEquals(input, ui.readInput());

        System.setIn(System.in);
    }

    /**
     * Tests the showTasks method of Ui.
     */
    @Test
    public void testShowTasks() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Task[] tasks = {new Task("Task 1"), new Task("Task 2")};
        int taskNum = 2;

        Ui ui = new Ui();
        String actualOutput = ui.showTasks(tasks, taskNum);

        String expectedOutput = "     1. [ ] Task 1\n"
                + "     2. [ ] Task 2\n";
        assertEquals(expectedOutput, actualOutput);

        System.setOut(System.out);
    }
}
