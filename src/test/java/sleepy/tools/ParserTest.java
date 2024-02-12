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
    public void parse_commandWithoutTaskNumber_exceptionThrown() {
        try {
            Parser.parse("unmark");
        } catch (IllegalArgumentException i) {
            assertEquals("You need to choose a task number to mark/unmark/delete!", i.getMessage());
        }
    }
}
