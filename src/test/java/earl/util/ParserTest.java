package earl.util;

import earl.tasks.Deadline;
import earl.tasks.Event;
import earl.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    void parseStorageEntry_normalInputs_correctType() throws Exception {
        assertInstanceOf(Todo.class, Parser.parseStorageEntry("T, ,t"));
        assertInstanceOf(Deadline.class, Parser.parseStorageEntry(
                "D, ,d,01/01/1970 0000"));
        assertInstanceOf(Event.class, Parser.parseStorageEntry(
                "E, ,e,01/01/1970 0000,01/01/1970 0001"));
    }

    @Test
    void parseStorageEntry_normalInputs_correctFields() throws Exception {
        assertEquals("[T][X] t",
                Parser.parseStorageEntry("T,X,t").toString());
        assertEquals("[D][ ] d (by: 01/01/1970 0000)",
                Parser.parseStorageEntry("D, ,d,01/01/1970 0000").toString());
        assertEquals("[E][ ] e (from: 01/01/1970 0000 to: 01/01/1970 0001)",
                Parser.parseStorageEntry(
                        "E, ,e,01/01/1970 0000,01/01/1970 0001").toString());
    }

    @Test
    void parseStorageEntry_invalidStorageEntry_exceptionThrown() {
        try {
            Parser.parseStorageEntry("abc");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown arguments for task creation.",
                    e.getMessage());
        }
    }
}