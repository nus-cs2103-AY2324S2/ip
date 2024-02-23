package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public void setup() {
        tasks = new TaskList("testFilePath");
        ui = new Ui();
        parser = new Parser("");
    }
    @Test
    public void testParser() {
        TaskList tasks = new TaskList("testFilePath");
        tasks.addTask(new ToDo("Sample Task"));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        parser = new Parser("list");
        parser.execute(tasks, ui);

        String expectedOutput = "Here are the tasks in your list!\n1.[ ] Sample Task.";
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());

        System.setOut(System.out);
    }

}

