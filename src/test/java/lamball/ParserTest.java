package lamball;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void byeAndListParseTest() {
        try {
            assertEquals(Parser.parse("bye")[0], "bye");
        } catch (LamballParseException e) {
            fail();
        }
    }

    @Test
    public void byeAndListParseFailTest() {
        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("bye 123456");
        });
        assertEquals("Do not type anything after \"bye\"", thrown.getMessage());
    }

    @Test
    public void markAndUnMarkAndDeleteParseTest() {
        try {
            String[] parsed = Parser.parse("mark 1");
            assertEquals("mark", parsed[0]);
            assertEquals("1", parsed[1]);
        } catch (LamballParseException e) {
            fail();
        }
    }

    @Test
    public void markAndUnMarkAndDeleteParseFailTest() {
        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("mark 1 1 1 1 1 1");
        });
        assertEquals("Invalid number, baa.", thrown.getMessage());
    }

    @Test
    public void taskParseTest() {
        try {
            assertEquals(Parser.parse("todo TEST")[0], "todo");
            assertEquals(Parser.parse("todo TEST")[1], "TEST");
        } catch (LamballParseException e) {
            fail();
        }
    }

    @Test
    public void taskParseFailTesT() {
        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("todo                            ");
        });
        assertEquals("Your todo field is empty, baaaaka.", thrown.getMessage());
    }

    @Test
    public void invalidCommandParseTest() {
        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("the quick brown fox jumps over the lazy dog");
        });
        assertEquals("Sorry, I don't understaaaaaand your commaaaaand, baa. :(",thrown.getMessage());
    }
}
