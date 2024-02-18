package nicole.userrequests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import nicole.nicoleexceptions.NicoleException;
import nicole.task.Deadline;
import nicole.task.Event;
import nicole.task.Task;
import nicole.task.Todo;

public class ParserTest {

    @Test
    public void parser_todoCommandGiven_todoSuccessfullyCreated() {
        try {
            Task todo = Parser.parseRequest("todo borrow book");
            if (!(todo instanceof Todo)) {
                fail();
            }
        } catch (NicoleException e) {
            fail();
        }
    }

    @Test
    public void parser_deadlineCommandGiven_deadlineSuccessfullyCreated() {
        try {
            Task todo = Parser.parseRequest("deadline return book by 2024-12-31");
            if (!(todo instanceof Deadline)) {
                fail();
            }
        } catch (NicoleException e) {
            fail();
        }
    }

    @Test
    public void parser_eventCommandGiven_eventSuccessfullyCreated() {
        try {
            Task todo = Parser.parseRequest("event meeting from 2024-02-17 at 18:00:00 to 2024-02-17 at 21:00:00");
            if (!(todo instanceof Event)) {
                fail();
            }
        } catch (NicoleException e) {
            fail();
        }
    }

    @Test
    public void parser_gibberishCommandGiven_nicoleExceptionThrown() {
        try {
            Parser.parseRequest("blah");
            fail();
        } catch (NicoleException e) {
            assertEquals(e.toString(), "ERROR. What does this mean? "
                    + "Send \"help\" if you want to know what commands I can help you with");
        }
    }
}
