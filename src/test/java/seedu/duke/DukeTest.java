package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.core.Chatbot;

public class DukeTest {
    @Test
    void testFind() {
        Chatbot tribara = new Chatbot("Tribara");
        String actual = tribara.getResponse("find project");
        String expected = "Here are the matching tasks in your list:\n"
                + "1. [T][ ] project proposal\n"
                + "2. [E][ ] project meeting (from: Jan 01 2020, 00:00 to: Jan 02 2020, 00:00)\n";

        assertEquals(expected, actual);
    }

    @Test
    void testBye() {
        Chatbot tribara = new Chatbot("Tribara");
        String actual = tribara.getResponse("bye");
        assertEquals("Bye lol see you again!", actual);

    }
}
