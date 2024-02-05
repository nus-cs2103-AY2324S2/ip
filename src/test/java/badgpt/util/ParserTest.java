package badgpt.util;

import badgpt.BadGpt;
import badgpt.exceptions.BadException;
import badgpt.tasks.Task;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_validCommandFormat_success() throws Exception {
        BadGpt bot = new BadGpt();
        TaskList taskList = new TaskList();
        taskList.store(new Task("return book"));

        // @@author ronnnnnnnnn-reused
        // Reused from https://stackoverflow.com/a/32241300 with minor modifications
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // @@author

        Parser.parse("mark 1", bot, taskList);
        assertEquals("____________________________________________________________\r\n" +
                "Nice! I've marked this task as done:\n" +
                "[X] return book\r\n" +
                "____________________________________________________________\r\n", out.toString());

        out.reset();

        Parser.parse("unmark 1", bot, taskList);
        assertEquals("____________________________________________________________\r\n" +
                "wyd bro why undo\n" +
                "[ ] return book\r\n" +
                "____________________________________________________________\r\n", out.toString());
    }

    @Test
    public void parse_invalidCommandFormat_notSuccess() {
        BadGpt bot = new BadGpt();
        TaskList taskList = new TaskList();

        // Try mark command without a number
        try {
            Parser.parse("mark", bot, taskList);
            fail();
        } catch (BadException e) {
            assertEquals("Please type in the command as follows: mark taskNum\n" +
                    "Example: mark 2", e.toString());
        }

        // Try unmark command without a number
        try {
            Parser.parse("unmark", bot, taskList);
        } catch (BadException e) {
            assertEquals("Please type in the command as follows: unmark taskNum\n" +
                    "Example: unmark 2", e.toString());
        }
    }
}
