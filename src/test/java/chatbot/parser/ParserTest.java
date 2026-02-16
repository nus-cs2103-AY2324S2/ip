package chatbot.parser;

import chatbot.exception.DukeException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private static final String TEST_PATH_FILE = "./out/test.txt";

    @BeforeEach
    public void setUp() throws DukeException {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser(tasks, ui);
        this.storage = new Storage(TEST_PATH_FILE);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_PATH_FILE);
        file.delete();
    }

    @Test
    public void testParseUserInput_byeCommand() throws DukeException {
        assertTrue(parser.parseUserInput("bye"));
    }

    @Test
    public void testParseUserInput_listCommand() throws DukeException {
        assertFalse(parser.parseUserInput("list"));
    }

    @Test
    public void testParseUserInput_todoCommand_success() throws DukeException {
        assertFalse(parser.parseUserInput("todo test"));
        assertEquals(1, tasks.getNumOfTasks());
    }

    @Test
    public void testParseUserInput_todoCommand_exceptionThrown() {
        try {
            parser.parseUserInput("todo");
            fail();
        } catch (DukeException e) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "you didn't specify what you want to do! use this format instead:\ntodo [task description]\n"
                    + Ui.createLine();
            assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void testParseUserInput_deadlineCommand_success() throws DukeException {
        assertFalse(parser.parseUserInput("deadline test /by 2023-12-31 23:59"));
        assertEquals(1, tasks.getNumOfTasks());
    }

    @Test
    public void testParseUserInput_deadlineCommand_exceptionThrown() {
        try {
            parser.parseUserInput("deadline test");
            fail();
        } catch (DukeException e) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "error! please specify the deadline task in this format:\n"
                    + "deadline [task description] /by [YYYY-MM-DD HH:MM]\n"
                    + Ui.createLine();
            assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void testParseUserInput_eventCommand_success() throws DukeException {
        assertFalse(parser.parseUserInput("event test /from 2023-12-31 12:00 /to 2023-12-31 14:00"));
        assertEquals(1, tasks.getNumOfTasks());
    }

    @Test
    public void testParseUserInput_eventCommand_exceptionThrown() {
        try {
            parser.parseUserInput("event test /from sat");
            fail();
        } catch (DukeException e) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "error! please specify the event task in this format:\n"
                    + "event [task description] /from [YYYY-MM-DD HH:MM] /to [YYYY-MM-DD HH:MM]\n"
                    + Ui.createLine();
            assertEquals(exceptionMessage, e.getMessage());
        }
    }
}
