package toothless.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import toothless.task.TaskList;
import toothless.ui.Ui;

public class ParserTest {
    @Test
    public void validateEventInput_normalInput_success() throws Exception {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        assertEquals("Got it. I've added this task:\n\t"
                        + "[E][ ] dinner (from: Feb 2 2024, 18:00 to: Feb 2 2024, 20:30)\n\tTags: NIL\n"
                        + "Nya-ow you have 1 tasks in the list.",
                new Parser().parseEventInput(tasks, ui, "event dinner /from 2024-02-02 18:00 /to 2024-02-02 20:30"));
    }

    @Test
    public void validateEventInput_emptyInput_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        try {
            assertEquals("", new Parser().parseEventInput(tasks, ui, "event"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, purr-lease use the format: "
                    + "event <description> /from <yyyy-mm-dd hh:mm> /to <yyyy-mm-dd hh:mm>", e.getMessage());
        }
    }

    @Test
    public void validateEventInput_emptyDescription_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        try {
            assertEquals("",
                    new Parser().parseEventInput(tasks, ui, "event    /from 2024-02-02 18:00 /to 2024-02-02 20:30"));
            fail();
        } catch (Exception e) {
            assertEquals("Apurrlogies, the task description cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void validateEventInput_emptyFrom_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        try {
            assertEquals("",
                    new Parser().parseEventInput(tasks, ui, "event dinner /from    /to 2024-02-02 20:30"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, purr-lease use the format: "
                    + "event <description> /from <yyyy-mm-dd hh:mm> /to <yyyy-mm-dd hh:mm>", e.getMessage());
        }
    }

    @Test
    public void validateEventInput_emptyTo_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        try {
            assertEquals("",
                    new Parser().parseEventInput(tasks, ui, "event dinner /from 2024-02-02 18:00 /to    "));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, purr-lease use the format: "
                    + "event <description> /from <yyyy-mm-dd hh:mm> /to <yyyy-mm-dd hh:mm>", e.getMessage());
        }
    }

    @Test
    public void validateEventInput_doubleFrom_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        try {
            assertEquals("",
                    new Parser().parseEventInput(tasks, ui,
                            "event dinner /from 2024-02-02 18:00 /from 2024-02-02 20:30"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, purr-lease use the format: "
                    + "event <description> /from <yyyy-mm-dd hh:mm> /to <yyyy-mm-dd hh:mm>", e.getMessage());
        }
    }

    @Test
    public void validateEventInput_extraFields_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        try {
            assertEquals("",
                    new Parser().parseEventInput(tasks, ui, "event dinner /from 2024-02-02 18:00"
                            + " /from 2024-02-02 20:30 /to 2024-02-02 20:30"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, purr-lease use the format: "
                    + "event <description> /from <yyyy-mm-dd hh:mm> /to <yyyy-mm-dd hh:mm>", e.getMessage());
        }
    }

    @Test
    public void validateEventInput_invalidDateTime_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        try {
            assertEquals("",
                    new Parser().parseEventInput(tasks, ui, "event dinner /from 2024-13-02 25:00"
                            + " /to 2024-02-90 30:30"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, /from and /to field datetime should be in the following format: "
                    + "yyyy-mm-dd hh:mm", e.getMessage());
        }
    }
}
