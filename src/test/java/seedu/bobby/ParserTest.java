package seedu.bobby;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseNum_success() throws Exception {
        assertEquals(2, Parser.parseNum("delete 2"));
        assertEquals(5, Parser.parseNum("mark 5"));
    }

    @Test
    public void parseNum_emptyDesc() {
        try {
            assertEquals(0, Parser.parseNum("mark"));
            fail();
        } catch (BobbyException e) {
            assertEquals("Oops! Please state the task number.", e.getMessage());
        }
    }

    @Test
    public void parseTodo_success() throws Exception{
        assertEquals("eat apples", Parser.parseTodo("todo eat apples"));
        assertEquals("complete 5 assignments", Parser.parseTodo("todo complete 5 assignments"));
    }

    @Test
    public void parseTodo_emptyDesc() {
        try {
            assertEquals("", Parser.parseTodo("todo"));
            fail();
        } catch (BobbyException e) {
            assertEquals("Oops! Please state the description of your todo task.", e.getMessage());
        }
    }

    @Test
    public void parseDeadlineTask_success() throws Exception{
        assertEquals("cs2109s ps2", Parser.parseDeadlineTask("deadline cs2109s ps2 /by 10-02-2024 2359"));
    }

    @Test
    public void parseDeadlineTask_emptyDesc() {
        try {
            assertEquals("deadline cs2109s ps2 /by 10-02-2024 2359",
                    Parser.parseDeadlineTask("deadline"));
            fail();
        } catch (BobbyException e) {
            assertEquals("Oops! Please state the description of your task and the deadline.", e.getMessage());
        }
    }

    @Test
    public void parseEvent_success() throws Exception{
        String[] expected = new String[3];
        expected[0] = "project meeting ";
        expected[1] = "Mon 2pm ";
        expected[2] = "4pm";
        assertArrayEquals(expected, Parser.parseEvent("event project meeting /from Mon 2pm /to 4pm"));
    }

    @Test
    public void parseEvent_emptyDesc() {
        try {
            String[] expected = new String[3];
            assertEquals(expected, Parser.parseEvent("event"));
            fail();
        } catch (BobbyException e) {
            assertEquals("Oops! Please state the description of your event and provide the start " +
                    "and end timing.", e.getMessage());
        }
    }
    @Test
    public void parseEvent_exceptionThrown() {
        try {
            String[] expected = new String[2];
            assertEquals(expected, Parser.parseEvent("event career fest /from 2pm"));
            fail();
        } catch (BobbyException e) {
            assertEquals("Please state the details like this: event event_name /from timing /to timing.",
                    e.getMessage());
        }
    }

}