/**
 * The TestParser class contains JUnit tests for the Parser class.
 */
package App;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import Commands.Command;
import TaskLists.TaskList;
import UiRelated.Parser;
import UiRelated.Ui;

/**
 * Contains JUnit tests for the Parser class.
 */
public class TestParser {

    /**
     * Tests the parseInput method for a complicated event task.
     */
    @Test
    public void testParseComplicatedEvent1() {
        Command c = Parser.parseInput("event return to fddfdf 01-01 10:00 am to 12:00 AM");
        TaskList t = new TaskList();
        c.execute(t, new Ui());
        String expected = "1. [E][ ] return to fddfdf  (from Jan-01 10:00 AM to 12:00 AM)";
        String actual = t.showLists();
        assertEquals(expected.replaceAll("\\s+", ""), actual.replaceAll("\\s+", ""));
    }

    /**
     * Tests the parseInput method for another complicated event task.
     */
    @Test
    public void testParseComplicatedEvent2() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Command c = Parser.parseInput("event return to fddfdf Jan-0110:00 am 12:00 am");
            TaskList t = new TaskList();
            c.execute(t, new Ui());
            System.out.println(t.showLists());
        });
        assertEquals(exception.getMessage(), "Invalid event format. "
                                                    + "Please specify the event using event followed by task name "
                                                    + "and its time in the following format "
                                                    + "<hh:mm am/pm to hh:mm am/pm "
                                                    + "or MM-dd hh:mm am/pm to hh:mm am/pm> "
                                                    + "eg1. event task1 10:00 am to 12:00 am  "
                                                    + "eg.2 event task1 01-01 10:00 am to 12:00 pm");
    }

    /**
     * Tests the parseInput method for a complicated deadline task.
     */
    @Test
    public void testParseComplicatedDeadline1() {
        TaskList t = new TaskList();
        Command c = Parser.parseInput("deadline return to fddfdf Jan-01 10:00 am "
                                    + "to 12:00 am by him adsd asda "
                                    + "by 01-01 10:00 am");
        c.execute(t, new Ui());
        String actual = "1.  [D][ ] return to fddfdf Jan-01 10:00 am "
                      + "to 12:00 am by him adsd asda  (by Jan-01 10:00 AM)\n";
        assertEquals(t.showLists().replaceAll("\\s+", ""), actual.replaceAll("\\s+", ""));
    }

    /**
     * Tests the parseInput method for another complicated deadline task.
     */
    @Test
    public void testParseComplicatedDeadline2() {
        DateTimeParseException exception = assertThrows(DateTimeParseException.class, () -> {
            Command c = Parser.parseInput("event return to fddfdf Jan-01 20:00 am to 12:00 am by 20:00 am");
            c.execute(new TaskList(), new Ui());
        });
        assertEquals(exception.getMessage(), "Please input a valid time format in am or pm.");
    }
}
