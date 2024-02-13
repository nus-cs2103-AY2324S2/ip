package duke;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void test1() throws DukeException {
        String[] cmds = Parser.parseCommand("end");
        assertArrayEquals(cmds, new String[] { "end" });
    }

    @Test
    public void test2() throws DukeException {
        String[] cmds = Parser.parseCommand("end uwu");
        assertArrayEquals(cmds, new String[] { "end" });
    }

    @Test
    public void test3() {
        try {
            Parser.parseCommand("with the love of my life");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Unhandled command: with");
        }
    }

    @Test
    public void test4() throws DukeException {
        String[] cmds = Parser.parseCommand("mark 100");
        assertArrayEquals(cmds, new String[] { "mark", "100" });
    }

    @Test
    public void test5() throws DukeException {
        String[] cmds = Parser.parseCommand("todo 1 2 3 4 5 6");
        assertArrayEquals(cmds, new String[] { "todo", "1 2 3 4 5 6" });
    }

    @Test
    public void test6() throws DukeException {
        String[] cmds = Parser.parseCommand(
        "deadline return book /by 2/12/2019 18:00"
        );
        assertArrayEquals(
            cmds,
            new String[] { "deadline", "return book", "2/12/2019 18:00" }
        );
    }

    @Test
    public void test7() {
        try {
            Parser.parseCommand("deadline return book 2/12/2019 18:00");
        } catch (DukeException e) {
            assertEquals(
                e.getMessage(),
                "deadline command: expected `/by` argument."
            );
        }
    }

    @Test
    public void test8() {
        try {
            Parser.parseCommand("deadline /by 2/12/2019 18:00");
        } catch (DukeException e) {
            assertEquals(
                e.getMessage(),
                "deadline command: task description cannot be empty."
            );
        }
    }

    @Test
    public void test9() throws DukeException {
        String[] cmds = Parser.parseCommand(
        "event project meeting /from 2/12/2020 12:00 /to 2/12/2020 14:00"
        );
        assertArrayEquals(
            cmds,
            new String[] {
                "event",
                "project meeting",
                "2/12/2020 12:00",
                "2/12/2020 14:00",
            }
        );
    }
}
