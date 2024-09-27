package lamball;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import lamball.command.ByeCommand;
import lamball.command.Command;
import lamball.command.PrintListCommand;
import lamball.exception.LamballParseException;
import lamball.memo.MemoList;

public class ParserTest {
    @Test
    public void byeParseTest() {
        Lamball testLamb = new Lamball();
        TaskList tst = new TaskList(testLamb);
        MemoList memTst = new MemoList(testLamb);
        try {
            Command comd = Parser.parse("bye", tst, memTst, false);
            assertTrue(comd instanceof ByeCommand);
        } catch (LamballParseException e) {
            fail();
        }
    }

    @Test
    public void listParseTest() {
        Lamball testLamb = new Lamball();
        TaskList tst = new TaskList(testLamb);
        MemoList memTst = new MemoList(testLamb);
        try {
            Command comd = Parser.parse("list", tst, memTst, false);
            assertTrue(comd instanceof PrintListCommand);
        } catch (LamballParseException e) {
            fail();
        }
    }
    @Test
    public void byeParseFailTest() {
        Lamball testLamb = new Lamball();
        TaskList tst = new TaskList(testLamb);
        MemoList memTst = new MemoList(testLamb);

        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("bye 123456", tst, memTst, false);
        });
        assertEquals("Do not type anything after \"bye\"", thrown.getMessage());
    }

    @Test
    public void listParseFailTest() {
        Lamball testLamb = new Lamball();
        TaskList tst = new TaskList(testLamb);
        MemoList memTst = new MemoList(testLamb);

        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("list 123456", tst, memTst, false);
        });
        assertEquals("Do not type anything after \"list\"", thrown.getMessage());
    }

    @Test
    public void markParseFailTest() {
        Lamball testLamb = new Lamball();
        TaskList tst = new TaskList(testLamb);
        MemoList memTst = new MemoList(testLamb);

        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("mark 1 1 1 1 1 1", tst, memTst, false);
        });
        assertEquals("Invalid number, baa.", thrown.getMessage());
    }

    @Test
    public void unmarkParseFailTest() {
        Lamball testLamb = new Lamball();
        TaskList tst = new TaskList(testLamb);
        MemoList memTst = new MemoList(testLamb);

        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("unmark 1 1 1 1 1 1", tst, memTst, false);
        });
        assertEquals("Invalid number, baa.", thrown.getMessage());
    }

    @Test
    public void deleteParseFailTest() {
        Lamball testLamb = new Lamball();
        TaskList tst = new TaskList(testLamb);
        MemoList memTst = new MemoList(testLamb);

        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("delete 1 1 1 1 1 1", tst, memTst, false);
        });
        assertEquals("Invalid number, baa.", thrown.getMessage());
    }

    @Test
    public void taskParseFailTest() {
        Lamball testLamb = new Lamball();
        TaskList tst = new TaskList(testLamb);
        MemoList memTst = new MemoList(testLamb);

        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("todo                            ", tst, memTst, false);
        });
        assertEquals("Your todo field is empty, baaaaka.", thrown.getMessage());
    }

    @Test
    public void invalidCommandParseTest() {
        Lamball testLamb = new Lamball();
        TaskList tst = new TaskList(testLamb);
        MemoList memTst = new MemoList(testLamb);

        LamballParseException thrown = Assertions.assertThrows(LamballParseException.class, () -> {
            Parser.parse("the quick brown fox jumps over the lazy dog", tst, memTst, false);
        });
        assertEquals("Sorry, I don't understaaaaaand your commaaaaand, baa. :(", thrown.getMessage());
    }
}
