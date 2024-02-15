package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.duke.Duke;
import duke.ui.Skibidi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import duke.duke.Parser;
import duke.duke.Storage;
import duke.exceptions.DukeEmptyArgumentException;
import duke.exceptions.DukeErroneousArgumentException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

@ExtendWith(MockitoExtension.class)
class ParserTest {
    private Parser p = new Parser();
    private Duke d = new Duke("/data/", "duke.txt");
    @Test
    void byeShouldReturnNull() {
        String input = "bye";
        assertEquals(Skibidi.BYE, p.parse(input));
    }

    @Test
    void listShouldCallPrintList() {
        TaskList tl = mock(TaskList.class);
        Duke.tasks = tl;
        String input = "list";
        p.parse(input);
        verify(tl).printList();
    }

    @Test
    void saveShouldCallStorageSaveSuccess() {
        TaskList tl = new TaskList();
        String input = "save";
        Storage store = mock(Storage.class);

        Duke.storage = store;
        Duke.tasks = tl;
        boolean thrown = false;
        try {
            p.parse(input);
            verify(store).save(tl);
        } catch (IOException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void markShouldReturnMarkedTasks() {
        List<Task> lstBefore = new ArrayList<>(Arrays.asList(new Todo("task1"), new Todo("task2")));
        List<Task> lstAfter = new ArrayList<>(Arrays.asList(
                new Todo("task1"),
                new Todo(true, "task2")));
        TaskList tl1 = new TaskList(lstBefore);
        TaskList tl2 = new TaskList(lstAfter);

        String input = "mark 2";
        Duke.tasks = tl1;

        p.parse(input);
        assertEquals(tl2, tl1);
    }

    @Test
    void unmarkShouldReturnUnmarkedTasks() {
        List<Task> lstBefore = new ArrayList<>(Arrays.asList(new Todo("task1"),
                new Todo(true, "task2")));
        List<Task> lstAfter = new ArrayList<>(Arrays.asList(new Todo("task1"), new Todo("task2")));
        TaskList tl1 = new TaskList(lstBefore);
        TaskList tl2 = new TaskList(lstAfter);

        String input = "unmark 2";
        Duke.tasks = tl1;

        p.parse(input);
        assertEquals(tl2, tl1);
    }

    @Test
    void deleteShouldReturnDeletedTaskList() {
        List<Task> lstBefore = new ArrayList<>(Arrays.asList(new Todo("task1"), new Todo("task2")));
        List<Task> lstAfter = new ArrayList<>(Arrays.asList(new Todo("task1")));
        TaskList tl1 = new TaskList(lstBefore);
        TaskList tl2 = new TaskList(lstAfter);

        String input = "delete 2";
        Duke.tasks = tl1;

        p.parse(input);
        assertEquals(tl2, tl1);
    }

    @Test
    void todoShouldAddTodoTask() {
        List<Task> lstBefore = new ArrayList<>(Arrays.asList(new Todo("task1")));
        List<Task> lstAfter = new ArrayList<>(Arrays.asList(new Todo("task1"), new Todo("task2")));
        TaskList tl1 = new TaskList(lstBefore);
        TaskList tl2 = new TaskList(lstAfter);

        String input = "todo task2";
        Duke.tasks = tl1;

        p.parse(input);
        assertEquals(tl2, tl1);
    }

    @Test
    void deadlineShouldAddDeadlineTask() {
        List<Task> lstBefore = new ArrayList<>(Arrays.asList(new Todo("task1")));
        List<Task> lstAfter = new ArrayList<>(Arrays.asList(
                new Todo("task1"), new Deadline("task2", "2024-01-31")));
        TaskList tl1 = new TaskList(lstBefore);
        TaskList tl2 = new TaskList(lstAfter);

        String input = "deadline task2 /by 2024-01-31";
        Duke.tasks = tl1;

        p.parse(input);
        assertEquals(tl2, tl1);
    }

    @Test
    void eventShouldAddEventTask() {
        List<Task> lstBefore = new ArrayList<>(Arrays.asList(new Todo("task1")));
        List<Task> lstAfter = new ArrayList<>(Arrays.asList(
                new Todo("task1"), new Event("task2", "2024-01-31", "2024-02-01")));
        TaskList tl1 = new TaskList(lstBefore);
        TaskList tl2 = new TaskList(lstAfter);
        String input = "event task2 /from 2024-01-31 /to 2024-02-01";
        Duke.tasks = tl1;

        p.parse(input);
        assertEquals(tl2, tl1);
    }

    @Test
    void emptyTodoShouldThrowEmptyArgumentException() {
        String input = "todo ";

        String reply = p.parse(input);

        assertEquals("There is an argument that is empty!!!", reply);
    }

    @Test
    void emptyDeadlineShouldThrowEmptyArgumentException() {
        String input = "deadline /by 2024-01-01";

        String reply = p.parse(input);

        assertEquals("There is an argument that is empty!!!", reply);
    }

    @Test
    void emptyDeadlineDateTimeShouldThrowEmptyArgumentException() {
        String input = "deadline test /by ";

        String reply = p.parse(input);

        assertEquals("There is an argument that is empty!!!", reply);
    }

    @Test
    void erroneousDeadlineShouldThrowErroneousArgumentException() {
        String input = "deadline test /b 2024-01-01";

        String reply = p.parse(input);

        assertEquals("There is an argument in the wrong format!!!", reply);
    }

    @Test
    void erroneousDeadLineFormatShouldThrowDateTimeException() {
        String input = "deadline test /by 2024-0-01";

        String reply = p.parse(input);

        String expected = "The format of your date is wrong! Make sure it is of the form 'yyyy-MM-dd'.\n"
                + "More specifically: \n"
                + "Text '2024-0-01' could not be parsed at index 5";

        assertEquals(expected, reply);
    }

    @Test
    void emptyEventShouldThrowEmptyArgumentException() {
        String input = "event /from 2024-01-01 /to 2024-01-02";

        String reply = p.parse(input);

        assertEquals("There is an argument that is empty!!!", reply);
    }

    @Test
    void emptyEventDateTimeShouldThrowEmptyArgumentException1() {
        String input = "event test /from /to 2024-01-02";

        String reply = p.parse(input);

        assertEquals("There is an argument that is empty!!!", reply);
    }

    @Test
    void emptyEventDateTimeShouldThrowEmptyArgumentException2() {
        String input = "event test /from 2024-01-02 /to ";

        String reply = p.parse(input);

        assertEquals("There is an argument that is empty!!!", reply);
    }

    @Test
    void erroneousEventShouldThrowErroneousArgumentException1() {
        String input = "event test /fro 2024-01-01 /to 2024-01-02";

        String reply = p.parse(input);

        assertEquals("There is an argument in the wrong format!!!", reply);
    }

    @Test
    void erroneousEventShouldThrowErroneousArgumentException2() {
        String input = "event test /from 2024-01-01 /t 2024-01-02";

        String reply = p.parse(input);

        assertEquals("There is an argument in the wrong format!!!", reply);
    }

    @Test
    void erroneousEventFormatShouldThrowDateTimeException1() {
        String input = "event test /from 2024-00-01 /to 2024-01-02";

        String reply = p.parse(input);
        String expected = "The format of your date is wrong! Make sure it is of the form 'yyyy-MM-dd'.\n"
                + "More specifically: \n"
                + "Text '2024-00-01' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 0";

        assertEquals(expected, reply);
    }

    @Test
    void erroneousEventFormatShouldThrowDateTimeException2() {
        String input = "event test /from 2024-01-01 /to 2024-00-02";

        String reply = p.parse(input);
        String expected = "The format of your date is wrong! Make sure it is of the form 'yyyy-MM-dd'.\n"
                + "More specifically: \n"
                + "Text '2024-00-02' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 0";

        assertEquals(expected, reply);
    }
}
