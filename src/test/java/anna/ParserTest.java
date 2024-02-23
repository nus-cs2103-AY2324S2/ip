package anna;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void test1() throws AnnaException {
        Command cmd = Parser.parseCommand("end");
        assertEquals(cmd.toString(), "CommandEnd:[]");
    }

    @Test
    public void test2() throws AnnaException {
        Command cmd = Parser.parseCommand("end uwu");
        assertEquals(cmd.toString(), "CommandEnd:[]");
    }

    @Test
    public void test3() {
        try {
            Parser.parseCommand("with the love of my life");
        } catch (AnnaException e) {
            assertEquals(e.getMessage(), "Unhandled command: with");
        }
    }

    @Test
    public void test4() throws AnnaException {
        Command cmd = Parser.parseCommand("mark 100");
        assertEquals(cmd.toString(), "CommandDelete:[99]");
    }

    @Test
    public void test5() throws AnnaException {
        Command cmd = Parser.parseCommand("todo 1 2 3 4 5 6");
        assertEquals(cmd.toString(), "CommandTodo:[1 2 3 4 5 6]");
    }

    @Test
    public void test6() throws AnnaException {
        Command cmd = Parser.parseCommand(
            "deadline return book /by 2/12/2019 18:00"
        );
        assertEquals(cmd.toString(), "CommandDeadline:[return book | 2/12/2019 18:00]");
    }

    @Test
    public void test7() {
        try {
            Parser.parseCommand("deadline return book 2/12/2019 18:00");
        } catch (AnnaException e) {
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
        } catch (AnnaException e) {
            assertEquals(
                e.getMessage(),
                "deadline command: task description cannot be empty."
            );
        }
    }

    @Test
    public void test9() throws AnnaException {
        Command cmd = Parser.parseCommand(
            "event project meeting /from 2/12/2020 12:00 /to 2/12/2020 14:00"
        );
        assertEquals(cmd.toString(), "CommandEvent:[project meeting | 2/12/2020 12:00 | 2/12/2020 14:00]");
    }
}
