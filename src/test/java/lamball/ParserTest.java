package lamball;

import lamball.command.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {
    @Test
    public void byeParseTest() {
        TaskList tst = new TaskList();
        try {
            Command comd = Parser.parse("bye", tst, false);
            assertTrue(comd instanceof ByeCommand);
            assertTrue(!comd.run());
        } catch (LamballParseException e) {
            fail();
        }
    }

    @Test
    public void listParseTest() {
        TaskList tst = new TaskList();
        try {
            Command comd = Parser.parse("list", tst, false);
            assertTrue(comd instanceof PrintListCommand);
            assertTrue(comd.run());
        } catch (LamballParseException e) {
            fail();
        }
    }
    @Test
    public void byeParseFailTest() {
        TaskList tst = new TaskList();
        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("bye 123456", tst, false);
        });
        assertEquals("Do not type anything after \"bye\"", thrown.getMessage());
    }

    @Test
    public void listParseFailTest() {
        TaskList tst = new TaskList();
        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("list 123456", tst, false);
        });
        assertEquals("Do not type anything after \"list\"", thrown.getMessage());
    }

    @Test
    public void markParseTest() {
        try {
            TaskList tst = new TaskList();
            tst.toDo("TEST", false);
            Command comd = Parser.parse("mark 1", tst, false);
            assertTrue(comd instanceof MarkCommand);
            assertTrue(comd.run());
        } catch (LamballParseException e) {
            fail();
        }
    }

    @Test
    public void unmarkParseTest() {
        try {
            TaskList tst = new TaskList();
            tst.toDo("TEST", false);
            Command comd = Parser.parse("unmark 1", tst, false);
            assertTrue(comd instanceof UnMarkCommand);
            assertTrue(comd.run());
        } catch (LamballParseException e) {
            fail();
        }
    }

    @Test
    public void deleteParseTest() {
        try {
            TaskList tst = new TaskList();
            tst.toDo("TEST", false);
            Command comd = Parser.parse("delete 1", tst, false);
            assertTrue(comd instanceof DeleteCommand);
            assertTrue(comd.run());
            assertTrue(tst.size() == 0);
        } catch (LamballParseException e) {
            fail();
        }
    }

    @Test
    public void markParseFailTest() {
        TaskList tst = new TaskList();
        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("mark 1 1 1 1 1 1", tst, false);
        });
        assertEquals("Invalid number, baa.", thrown.getMessage());
    }

    @Test
    public void unmarkParseFailTest() {
        TaskList tst = new TaskList();
        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("unmark 1 1 1 1 1 1", tst, false);
        });
        assertEquals("Invalid number, baa.", thrown.getMessage());
    }

    @Test
    public void deleteParseFailTest() {
        TaskList tst = new TaskList();
        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("delete 1 1 1 1 1 1", tst, false);
        });
        assertEquals("Invalid number, baa.", thrown.getMessage());
    }

    @Test
    public void taskParseFailTest() {
        TaskList tst = new TaskList();
        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("todo                            ", tst, false);
        });
        assertEquals("Your todo field is empty, baaaaka.", thrown.getMessage());
    }

    @Test
    public void invalidCommandParseTest() {
        TaskList test = new TaskList();
        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("the quick brown fox jumps over the lazy dog", test, false);
        });
        assertEquals("Sorry, I don't understaaaaaand your commaaaaand, baa. :(", thrown.getMessage());
    }
}
