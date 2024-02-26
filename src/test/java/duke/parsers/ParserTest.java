package duke.parsers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class ParserTest {
    @Test
    public void testEmptyMarkTaskNumber() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        String output = Parser.parse("mark").execute(ui, tasks);
        assertEquals("Please state which task you want to mark.", output);
    }
    @Test
    public void testInvalidMarkTaskNumber() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        String output = Parser.parse("mark a").execute(ui, tasks);
        assertEquals("Unable to parse the input as an integer. Please put a number after mark.", output);
    }

    @Test
    public void testOutOfBoundMarkTaskNumber() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        String output = Parser.parse("mark 1").execute(ui, tasks);
        assertEquals("Please look carefully. This task is not inside the task list.", output);
    }

    @Test
    public void testMissingDescriptionForDeadline() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        String output = Parser.parse("deadline").execute(ui, tasks);
        assertEquals("OOPS! The description of a deadline cannot be empty.", output);
    }

    @Test
    public void testMissingDescription2ForDeadline() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        String output = Parser.parse("deadline by").execute(ui, tasks);
        assertEquals("OOPS! You forget to write the task description. Please follow this format: "
                + "'<task_description> by <deadline>' in yyyy-mm-dd HHmm 24-hr format", output);
    }

    @Test
    public void testMissingKeywordForDeadline() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        String output = Parser.parse("deadline return book").execute(ui, tasks);
        assertEquals("OOPS! 'by' keyword is missing. You are required to state the deadline "
                + "using the 'by' keyword.", output);
    }
}
