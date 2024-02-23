package guanguan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void byeCommandTest() throws GgException {
        assertFalse(Parser.parse("bye", new TaskList(), new Ui()));
    }

    @Test
    public void markCommandTest() throws GgException {
        TaskList taskList = new TaskList(new ArrayList<>(List.of(new Todo("todo"),
                new Deadline("deadline", LocalDate.parse("2024-01-01")))));
        Parser.parse("mark 2", taskList, new Ui());

        assertEquals("X", taskList.get(1).getStatusIcon());
    }

    @Test
    public void unmarkCommandTest() throws GgException {
        TaskList taskList = new TaskList(new ArrayList<>(List.of(new Todo("todo"),
                new Deadline("deadline", LocalDate.parse("2024-01-01")))));
        Parser.parse("mark 2", taskList, new Ui());
        Parser.parse("unmark 2", taskList, new Ui());

        assertEquals(" ", taskList.get(1).getStatusIcon());
    }

    @Test
    public void todoCommandTest_success() throws GgException {
        TaskList taskList = new TaskList();
        Parser.parse("todo CS2103T tutorial!", taskList, new Ui());
        assertEquals("[T][ ] CS2103T tutorial!", taskList.get(0).toString());
    }

    @Test
    public void todoCommandTest_throwException() {
        try {
            Parser.parse("todo", new TaskList(), new Ui());
            fail();
        } catch (GgException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void deadlineCommandTest_success() throws GgException {
        TaskList taskList = new TaskList();
        Parser.parse("deadline CS2103T tutorial! /by 2024-05-12", taskList, new Ui());
        assertEquals("[D][ ] CS2103T tutorial! (by: May 12 2024)", taskList.get(0).toString());
    }

    @Test
    public void deadlineCommandTest_emptyDescription_throwException() {
        try {
            Parser.parse("deadline", new TaskList(), new Ui());
            fail();
        } catch (GgException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void deadlineCommandTest_noDate_throwException() {
        try {
            Parser.parse("deadline CS2103T tutorial!", new TaskList(), new Ui());
            fail();
        } catch (GgException e) {
            assertEquals("Use /by to specify deadline.", e.getMessage());
        }
    }

    @Test
    public void deadlineCommandTest_invalidDate_throwException() {
        try {
            Parser.parse("deadline CS2103T tutorial! /by tonight", new TaskList(), new Ui());
            fail();
        } catch (GgException e) {
            assertEquals("Invalid datetime format!", e.getMessage());
        }
    }

    @Test
    public void eventCommandTest_success() throws GgException {
        TaskList taskList = new TaskList();
        Parser.parse("event CS2103T tutorial! /from 2024-05-12 /to 2024-06-12", taskList, new Ui());
        assertEquals("[E][ ] CS2103T tutorial! (from: May 12 2024 to: Jun 12 2024)", taskList.get(0).toString());
    }

    @Test
    public void eventCommandTest_emptyDescription_throwException() {
        try {
            Parser.parse("event", new TaskList(), new Ui());
            fail();
        } catch (GgException e) {
            assertEquals("OOPS!!! The description of a event cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void eventCommandTest_noDate_throwException() {
        try {
            Parser.parse("event CS2103T tutorial!", new TaskList(), new Ui());
            fail();
        } catch (GgException e) {
            assertEquals("Invalid event date. Use /from and /to", e.getMessage());
        }
    }

    @Test
    public void eventCommandTest_invalidDate_throwException() {
        try {
            Parser.parse("deadline CS2103T tutorial! /by tonight", new TaskList(), new Ui());
            fail();
        } catch (GgException e) {
            assertEquals("Invalid datetime format!", e.getMessage());
        }
    }

    @Test
    public void deleteCommandTest_success() throws GgException {
        TaskList taskList = new TaskList(new ArrayList<>(List.of(new Todo("todo"),
                new Deadline("deadline", LocalDate.parse("2024-01-01")))));
        Parser.parse("delete 2", taskList, new Ui());
        assertEquals(1, taskList.size());
    }

    @Test
    public void deleteCommandTest_emptyDescription_throwException() {
        try {
            Parser.parse("delete", new TaskList(), new Ui());
            fail();
        } catch (GgException e) {
            assertEquals("OOPS!!! Task ID cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void deleteCommandTest_invalidDate_throwException() {
        try {
            Parser.parse("delete 1", new TaskList(), new Ui());
            fail();
        } catch (GgException e) {
            assertEquals("Invalid task ID", e.getMessage());
        }
    }

    @Test
    public void findCommandTest_validKeyword_success() throws GgException {
        TaskList taskList = new TaskList(new ArrayList<>(List.of(new Todo("todo"),
                new Deadline("deadline", LocalDate.parse("2024-01-01")))));
        Parser.parse("find todo", taskList, new Ui());
        assertEquals(2, taskList.size());
    }

    @Test
    public void findCommandTest_invalidKeyword_success() throws GgException {
        TaskList taskList = new TaskList(new ArrayList<>(List.of(new Todo("todo"),
                new Deadline("deadline", LocalDate.parse("2024-01-01")))));
        Parser.parse("find 1111", taskList, new Ui());
        assertEquals(2, taskList.size());
    }

    @Test
    public void findCommandTest_emptyDescription_throwException() {
        try {
            Parser.parse("find", new TaskList(), new Ui());
            fail();
        } catch (GgException e) {
            assertEquals("OOPS!!! Keyword cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void findCommandTest_throwException() {
        try {
            Parser.parse("random testttt", new TaskList(), new Ui());
            fail();
        } catch (GgException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
