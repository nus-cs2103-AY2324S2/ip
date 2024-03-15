package sleepy.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseTask_normalEvent_success() throws IllegalArgumentException {
        String testEvent = "event 2103t lecture /from fri 1600 /to fri 1800";
        for (int i = 0; i < 4; i++) {
            assertEquals(new String[]{ "event", "2103t lecture", "fri 1600", "fri 1800" }[i],
                    Parser.parse(testEvent)[i]);
        }
    }
    @Test
    public void parseTask_normalToDo_success() throws IllegalArgumentException {
        String testTodo = "todo sleep 8 hours";
        for (int i = 0; i < 2; i++) {
            assertEquals(new String[]{ "todo", "sleep 8 hours" }[i], Parser.parse(testTodo)[i]);
        }
    }
    @Test
    public void parseTask_normalDeadline_success() throws IllegalArgumentException {
        String testDeadline = "deadline test /by today";
        for (int i = 0; i < 3; i++) {
            assertEquals(new String[]{ "deadline", "test", "today" }[i], Parser.parse(testDeadline)[i]);
        }
    }
    @Test
    public void parseTask_normalPlan_success() throws IllegalArgumentException {
        String testPlan = "plan cry /after finals";
        for (int i = 0; i < 3; i++) {
            assertEquals(new String[]{ "plan", "cry", "finals" }[i], Parser.parse(testPlan)[i]);
        }
    }
    @Test
    public void parse_invalidCommand_exceptionThrown() {
        String expectedMessage = "Invalid command type!";
        try {
            Parser.parse("hello world");
        } catch (IllegalArgumentException i) {
            assertEquals(expectedMessage, i.getMessage());
        }
    }
    @Test
    public void parse_commandWithoutTaskNumber_exceptionThrown() {
        String expectedMessage = "You need to choose a task number to mark/unmark/delete!";
        try {
            Parser.parse("unmark");
        } catch (IllegalArgumentException i) {
            assertEquals(expectedMessage, i.getMessage());
        }
        try {
            Parser.parse("mark");
        } catch (IllegalArgumentException i) {
            assertEquals(expectedMessage, i.getMessage());
        }
        try {
            Parser.parse("delete");
        } catch (IllegalArgumentException i) {
            assertEquals(expectedMessage, i.getMessage());
        }
    }
}
