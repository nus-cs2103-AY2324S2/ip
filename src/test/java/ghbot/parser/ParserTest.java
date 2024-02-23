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
    void parse_byeInstruction_success() {
        String[] input = new String[1];
        input[0] = "bye";
        assertEquals(ByeExecutor.class, Parser.parse(input).getClass());
    }

    @Test
    void parse_listInstruction_success() {
        String[] input = new String[1];
        input[0] = "list";
        assertEquals(ListExecutor.class, Parser.parse(input).getClass());
    }

    @Test
    void parse_markInstruction_success() {
        String[] input = new String[2];
        input[0] = "mark";
        input[1] = "1";
        assertEquals(MarkExecutor.class, Parser.parse(input).getClass());
    }

    @Test
    void parse_unmarkInstruction_success() {
        String[] input = new String[2];
        input[0] = "unmark";
        input[1] = "1";
        assertEquals(UnmarkExecutor.class, Parser.parse(input).getClass());
    }

    @Test
    void parse_todoInstruction_success() {
        String[] input = new String[2];
        input[0] = "todo";
        input[1] = "gym";
        assertEquals(TodoExecutor.class, Parser.parse(input).getClass());
    }

    @Test
    void parse_deadlineInstruction_success() {
        String[] input = new String[2];
        input[0] = "deadline";
        input[1] = "assignment /by 2030-12-23 0900";
        assertEquals(DeadlineExecutor.class, Parser.parse(input).getClass());
    }

    @Test
    void parse_eventInstruction_success() {
        String[] input = new String[2];
        input[0] = "event";
        input[1] = "homework /from 2030-12-24 0700 /to 2030-12-24 0900";
        assertEquals(EventExecutor.class, Parser.parse(input).getClass());
    }

    @Test
    void parse_deleteInstruction_success() {
        String[] input = new String[2];
        input[0] = "delete";
        input[1] = "1";
        assertEquals(DeleteExecutor.class, Parser.parse(input).getClass());
    }

    @Test
    void parse_findInstruction_success() {
        String[] input = new String[2];
        input[0] = "find";
        input[1] = "gym";
        assertEquals(FindExecutor.class, Parser.parse(input).getClass());
    }

    @Test
    void parse_wrongInstruction_assertionExceptionThrown() {
        String[] input = new String[2];
        input[0] = "hello";
        input[1] = "gym";
        AssertionError assertionError = assertThrows(AssertionError.class, () -> {
            Parser.parse(input);
        });
        assertEquals("hello is a wrong instruction!", assertionError.getMessage());
    }
}
