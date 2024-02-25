package duke.utility;

import duke.task.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void checkDates_invalidDataTimeFormat_exceptionThrown() {
        try {
            String testInput = "Jan-20-2021 1800";
            Parser.checkDates(testInput);
            fail();
        } catch (DukeException e) {
            assertEquals("Pengu thinks that you need to put the date in this format: yyyy-MM-dd HHmm", e.getMessage());
        }
    }

    @Test
    public void parseInstructions_invalidCommandFormat_exceptionThrown() {
        try {
            String testInput = "Fake-input";
            Parser.parseInstructions(testInput);
            fail();
        } catch (DukeException e) {
            assertEquals("*HONK* Pengu has never seen such a command before, " +
                    "some commands Pengu can do are: list, todo, deadline", e.getMessage());
        }
    }

    @Test
    public void parseFileLine_correctFormat_success() {
        try {
            String testInput = "[E][ ] test event (from: Oct-20-1900 1800 to: Oct-20-1900 1800)";
            Task testOutput = Parser.parseFileLine(testInput);
            assertEquals(testInput, testOutput.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}
