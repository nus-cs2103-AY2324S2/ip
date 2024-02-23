package ghbot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ghbot.executor.ByeExecutor;
import ghbot.executor.DeadlineExecutor;
import ghbot.executor.DeleteExecutor;
import ghbot.executor.EventExecutor;
import ghbot.executor.FindExecutor;
import ghbot.executor.ListExecutor;
import ghbot.executor.MarkExecutor;
import ghbot.executor.TodoExecutor;
import ghbot.executor.UnmarkExecutor;

/**
 * ParserTest Class.
 */
class ParserTest {

    @Test
    public void parse_byeInstruction_success() {
        String[] inputDetails = new String[1];
        inputDetails[0] = "bye";
        assertEquals(ByeExecutor.class, Parser.parse(inputDetails).getClass());
    }

    @Test
    public void parse_listInstruction_success() {
        String[] inputDetails = new String[1];
        inputDetails[0] = "list";
        assertEquals(ListExecutor.class, Parser.parse(inputDetails).getClass());
    }

    @Test
    public void parse_markInstruction_success() {
        String[] inputDetails = new String[2];
        inputDetails[0] = "mark";
        inputDetails[1] = "1";
        assertEquals(MarkExecutor.class, Parser.parse(inputDetails).getClass());
    }

    @Test
    public void parse_unmarkInstruction_success() {
        String[] inputDetails = new String[2];
        inputDetails[0] = "unmark";
        inputDetails[1] = "1";
        assertEquals(UnmarkExecutor.class, Parser.parse(inputDetails).getClass());
    }

    @Test
    public void parse_todoInstruction_success() {
        String[] inputDetails = new String[2];
        inputDetails[0] = "todo";
        inputDetails[1] = "gym";
        assertEquals(TodoExecutor.class, Parser.parse(inputDetails).getClass());
    }

    @Test
    public void parse_deadlineInstruction_success() {
        String[] inputDetails = new String[2];
        inputDetails[0] = "deadline";
        inputDetails[1] = "assignment /by 2030-12-23 0900";
        assertEquals(DeadlineExecutor.class, Parser.parse(inputDetails).getClass());
    }

    @Test
    public void parse_eventInstruction_success() {
        String[] inputDetails = new String[2];
        inputDetails[0] = "event";
        inputDetails[1] = "homework /from 2030-12-24 0700 /to 2030-12-24 0900";
        assertEquals(EventExecutor.class, Parser.parse(inputDetails).getClass());
    }

    @Test
    public void parse_deleteInstruction_success() {
        String[] inputDetails = new String[2];
        inputDetails[0] = "delete";
        inputDetails[1] = "1";
        assertEquals(DeleteExecutor.class, Parser.parse(inputDetails).getClass());
    }

    @Test
    public void parse_findInstruction_success() {
        String[] inputDetails = new String[2];
        inputDetails[0] = "find";
        inputDetails[1] = "gym";
        assertEquals(FindExecutor.class, Parser.parse(inputDetails).getClass());
    }

    @Test
    public void parse_wrongInstruction_assertionExceptionThrown() {
        String[] inputDetails = new String[2];
        inputDetails[0] = "hello";
        inputDetails[1] = "gym";
        AssertionError assertionError = assertThrows(AssertionError.class, () -> {
            Parser.parse(inputDetails);
        });
        assertEquals("hello is a wrong instruction!", assertionError.getMessage());
    }
}
