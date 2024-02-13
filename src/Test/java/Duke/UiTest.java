package Duke;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void testReadInput() {
        String input = "Test Input";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Ui ui = new Ui();
        assertEquals(input, ui.readInput());

        System.setIn(System.in);
    }

    @Test
    public void testShowTasks() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Task[] tasks = {new Task("Task 1"), new Task("Task 2")};
        int taskNum = 2;

        Ui ui = new Ui();
        ui.showTasks(tasks, taskNum);

        String expectedOutput = "    _______________________________________________________\n" +
                "     1. [ ] Task 1\n" +
                "     2. [ ] Task 2\n" +
                "    _______________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());

        System.setOut(System.out);
    }
}
