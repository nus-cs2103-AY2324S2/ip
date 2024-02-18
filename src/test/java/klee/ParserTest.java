package klee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import klee.command.Bye;
import klee.command.Command;
import klee.command.Deadline;
import klee.command.Delete;
import klee.command.Event;
import klee.command.List;
import klee.command.Mark;
import klee.command.ToDo;
import klee.command.Unmark;

public class ParserTest {
    private Parser parserTest = new Parser();

    @Test
    public void testGeneralInputs() throws Exception {
        try {
            Command actual = parserTest.parseInput("");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("Klee doesn't understand, what are you talking about?");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("blah");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("Klee doesn't understand, what are you talking about?");
            assertEquals(expected, actual);
        }

        try {
            Command expected = new Bye();
            Command actual = parserTest.parseInput("bye");
            assertEquals(expected, actual);
        } catch (KleeException actual) {
            throw new Exception("Test Failed");
        }

        try {
            Command expected = new List();
            Command actual = parserTest.parseInput("list");
            assertEquals(expected, actual);
        } catch (KleeException actual) {
            throw new Exception("Test Failed");
        }
    }

    @Test
    public void testToDoInputs() throws Exception {
        try {
            Command expected = new ToDo("test");
            Command actual = parserTest.parseInput("todo test");
            assertEquals(expected, actual);
        } catch (KleeException e) {
            throw new Exception("Test Failed");
        }

        try {
            Command actual = parserTest.parseInput("todo ");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a todo is `todo [description]`");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("todo");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a todo is `todo [description]`");
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testDeadlineInputs() throws Exception {
        try {
            Command expected = new Deadline("test",
                    LocalDateTime.of(2024, 1, 1, 18, 30));
            Command actual = parserTest.parseInput("deadline test /by 2024-1-1 1830");
            assertEquals(expected, actual);
        } catch (KleeException e) {
            throw new Exception("Test Failed");
        }

        try {
            Command expected = new Deadline("test",
                    LocalDateTime.of(2024, 1, 1, 18, 30));
            Command actual = parserTest.parseInput("deadline test /by 1/1/2024 1830");
            assertEquals(expected, actual);
        } catch (KleeException e) {
            throw new Exception("Test Failed");
        }

        try {
            Command actual = parserTest.parseInput("deadline ");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a deadline is "
                    + "`deadline [description] /by [date and time]`");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("deadline");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a deadline is "
                    + "`deadline [description] /by [date and time]`");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("deadline test");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a deadline is "
                    + "`deadline [description] /by [date and time]`");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("deadline test /by");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a deadline is "
                    + "`deadline [description] /by [date and time]`");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("deadline test/by 2024-1-1 1830");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a deadline is "
                    + "`deadline [description] /by [date and time]`");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("deadline test /by ");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a deadline is "
                    + "`deadline [description] /by [date and time]`");
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testEventInputs() throws Exception {
        try {
            Command expected = new Event("test",
                    LocalDateTime.of(2024, 1, 1, 18, 30),
                    LocalDateTime.of(2024, 1, 31, 18, 30));
            Command actual = parserTest.parseInput("event test /from 2024-1-1 1830 /to 2024-1-31 1830");
            assertEquals(expected, actual);
        } catch (KleeException e) {
            throw new Exception("Test Failed");
        }

        try {
            Command expected = new Event("test",
                    LocalDateTime.of(2024, 1, 1, 18, 30),
                    LocalDateTime.of(2024, 1, 31, 18, 30));
            Command actual = parserTest.parseInput("event test /from 2024-1-1 1830 /to 31/1/2024 1830");
            assertEquals(expected, actual);
        } catch (KleeException e) {
            throw new Exception("Test Failed");
        }

        try {
            Command actual = parserTest.parseInput("event ");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a event is "
                    + "`event [description] /from [date and time] /to [date and time]`");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("event");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a event is "
                    + "`event [description] /from [date and time] /to [date and time]`");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("event test");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a event is "
                    + "`event [description] /from [date and time] /to [date and time]`");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("event test /from");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a event is "
                    + "`event [description] /from [date and time] /to [date and time]`");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("event test /from 1/1/2024");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a event is "
                    + "`event [description] /from [date and time] /to [date and time]`");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("event test /from 1/1/2024 /to");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("The correct way to indicate a event is "
                    + "`event [description] /from [date and time] /to [date and time]`");
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testMarkInputs() throws Exception {
        try {
            Command expected = new Mark(0);
            Command actual = parserTest.parseInput("mark 1");
            assertEquals(expected, actual);
        } catch (KleeException e) {
            throw new Exception("Test Failed");
        }

        try {
            Command actual = parserTest.parseInput("mark");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("There should be an integer after mark to indicate "
                    + "which task to mark!");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("mark tasks");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("There should be an integer after mark to indicate "
                    + "which task to mark!");
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testUnMarkInputs() throws Exception {
        try {
            Command expected = new Unmark(0);
            Command actual = parserTest.parseInput("unmark 1");
            assertEquals(expected, actual);
        } catch (KleeException e) {
            throw new Exception("Test Failed");
        }

        try {
            Command actual = parserTest.parseInput("unmark");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("There should be an integer after unmark to indicate "
                    + "which task to unmark!");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("unmark tasks");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("There should be an integer after unmark to indicate "
                    + "which task to unmark!");
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testDeleteInputs() throws Exception {
        try {
            Command expected = new Delete(0);
            Command actual = parserTest.parseInput("delete 1");
            assertEquals(expected, actual);
        } catch (KleeException e) {
            throw new Exception("Test Failed");
        }

        try {
            Command actual = parserTest.parseInput("delete");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("There should be an integer after delete to indicate "
                    + "which task to delete!");
            assertEquals(expected, actual);
        }

        try {
            Command actual = parserTest.parseInput("delete tasks");
        } catch (KleeException actual) {
            KleeException expected = new KleeException("There should be an integer after delete to indicate "
                    + "which task to delete!");
            assertEquals(expected, actual);
        }
    }
}
