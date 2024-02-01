package duke.test;

import duke.task.TaskException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

public class ParserTest {
    @Test
    void Event_wrongTimeFormat_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskList(), new Ui());
            parser.identify("event read book /by today");
        } catch (TaskException e) {
            assertEquals("Please specify the event timeframe.", e.getMessage());
        }
    }

    @Test
    void Event_noEndTime_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskList(), new Ui());
            parser.identify("event read book /from today");
        } catch (TaskException e) {
            assertEquals("Please specify when the event ends.", e.getMessage());
        }
    }

    @Test
    void Parser_invalidInput_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskList(), new Ui());
            parser.identify("hello");
        } catch (TaskException e) {
            assertEquals("Apologies, I don't understand you. Please try again", e.getMessage());
        }
    }
}
