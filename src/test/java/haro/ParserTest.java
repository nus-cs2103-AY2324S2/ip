package haro;

import haro.command.ListCommand;
import haro.command.MarkCommand;
import haro.exception.InvalidArgsException;
import haro.exception.MissingDuedateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class ParserTest {
    @Test
    public void parser_listInput_listCommand() throws Exception {
        assertEquals(new ListCommand(), Parser.parseCommand("list"));
    }

    @Test
    public void parser_markInput_markCommand() throws Exception {
        assertEquals(new MarkCommand(1), Parser.parseCommand("mark 2"));
    }

    @Test
    public void parser_markNonNumericalIndex_invalidArgsExceptionThrown() {
        try {
            Parser.parseCommand("mark adam");
            fail(); // the test should not reach this line
        } catch (InvalidArgsException e) {
            assertEquals("Please input a number for the task you want to mark!\n", e.getMessage());
        } catch (Exception e) {
            // the test shouldn't reach here as other exceptions should not be thrown
            fail();
        }
    }

    @Test
    public void parser_addDeadlineMissingBy_missingDueDateExceptionThrown() {
        try {
            Parser.parseCommand("deadline read book today");
            fail(); // the test should not reach this line
        } catch (MissingDuedateException e) {
            assertEquals("Please specify a due date using '/by'!\n", e.getMessage());
        } catch (Exception e) {
            fail(); // the test should not reach this line
        }
    }

}
