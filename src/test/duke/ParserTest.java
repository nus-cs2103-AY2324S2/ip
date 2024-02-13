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
    @Test
    void byeShouldReturnNull() {
        TaskList tl = new TaskList();
        String in = "bye";
        Storage store = new Storage();
        assertNull(p.parse(in, tl, store));
    }

    @Test
    void listShouldCallPrintList() {
        TaskList tl = mock(TaskList.class);
        String in = "list";
        Storage store = new Storage();
        p.parse(in, tl, store);
        verify(tl).printList();
    }

    @Test
    void saveShouldCallStorageSaveSuccess() {
        TaskList tl = new TaskList();
        String in = "save";
        Storage store = mock(Storage.class);
        boolean thrown = false;
        try {
            p.parse(in, tl, store);
            verify(store).save(tl);
        } catch (IOException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void markShouldReturnMarkedTasks() {
        List<Task> lstBefore = new ArrayList<>(Arrays.asList(new Todo("task1"), new Todo("task2")));
        List<Task> lstAfter = new ArrayList<>(Arrays.asList(new Todo("task1"),
                new Todo(true, "task2")));
        TaskList tl1 = new TaskList(lstBefore);
        TaskList tl2 = new TaskList(lstAfter);
        String in = "mark 2";
        Storage store = new Storage();
        tl1 = p.parse(in, tl1, store);
        assertEquals(tl2, tl1);
    }

    @Test
    void unmarkShouldReturnUnmarkedTasks() {
        List<Task> lstBefore = new ArrayList<>(Arrays.asList(new Todo("task1"),
                new Todo(true, "task2")));
        List<Task> lstAfter = new ArrayList<>(Arrays.asList(new Todo("task1"), new Todo("task2")));
        TaskList tl1 = new TaskList(lstBefore);
        TaskList tl2 = new TaskList(lstAfter);
        String in = "unmark 2";
        Storage store = new Storage();
        tl1 = p.parse(in, tl1, store);
        assertEquals(tl2, tl1);
    }

    @Test
    void deleteShouldReturnDeletedTaskList() {
        List<Task> lstBefore = new ArrayList<>(Arrays.asList(new Todo("task1"), new Todo("task2")));
        List<Task> lstAfter = new ArrayList<>(Arrays.asList(new Todo("task1")));
        TaskList tl1 = new TaskList(lstBefore);
        TaskList tl2 = new TaskList(lstAfter);
        String in = "delete 2";
        Storage store = new Storage();
        tl1 = p.parse(in, tl1, store);
        assertEquals(tl2, tl1);
    }

    @Test
    void todoShouldAddTodoTask() {
        List<Task> lstBefore = new ArrayList<>(Arrays.asList(new Todo("task1")));
        List<Task> lstAfter = new ArrayList<>(Arrays.asList(new Todo("task1"), new Todo("task2")));
        TaskList tl1 = new TaskList(lstBefore);
        TaskList tl2 = new TaskList(lstAfter);
        String in = "todo task2";
        tl1 = p.addTask(in, tl1);
        assertEquals(tl2, tl1);
    }

    @Test
    void deadlineShouldAddDeadlineTask() {
        List<Task> lstBefore = new ArrayList<>(Arrays.asList(new Todo("task1")));
        List<Task> lstAfter = new ArrayList<>(Arrays.asList(
                new Todo("task1"), new Deadline("task2", "2024-01-31")));
        TaskList tl1 = new TaskList(lstBefore);
        TaskList tl2 = new TaskList(lstAfter);
        String in = "deadline task2 /by 2024-01-31";
        tl1 = p.addTask(in, tl1);
        assertEquals(tl2, tl1);
    }

    @Test
    void eventShouldAddEventTask() {
        List<Task> lstBefore = new ArrayList<>(Arrays.asList(new Todo("task1")));
        List<Task> lstAfter = new ArrayList<>(Arrays.asList(
                new Todo("task1"), new Event("task2", "2024-01-31", "2024-02-01")));
        TaskList tl1 = new TaskList(lstBefore);
        TaskList tl2 = new TaskList(lstAfter);
        String in = "event task2 /from 2024-01-31 /to 2024-02-01";
        tl1 = p.addTask(in, tl1);
        assertEquals(tl2, tl1);
    }

    @Test
    void emptyTodoShouldThrowEmptyArgumentException() {
        boolean thrown = false;
        try {
            TaskList tl = new TaskList();
            String in = "todo ";
            p.addTask(in, tl);
        } catch (DukeEmptyArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void emptyDeadlineShouldThrowEmptyArgumentException() {
        boolean thrown = false;
        try {
            TaskList tl = new TaskList();
            String in = "deadline /by 2024-01-01";
            p.addTask(in, tl);
        } catch (DukeEmptyArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void emptyDeadlineDateTimeShouldThrowEmptyArgumentException() {
        boolean thrown = false;
        try {
            TaskList tl = new TaskList();
            String in = "deadline test /by ";
            p.addTask(in, tl);
        } catch (DukeEmptyArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void erroneousDeadlineShouldThrowErroneousArgumentException() {
        boolean thrown = false;
        try {
            TaskList tl = new TaskList();
            String in = "deadline test /by2024-01-01";
            p.addTask(in, tl);
        } catch (DukeErroneousArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void erroneousDeadLineFormatShouldThrowDateTimeException() {
        boolean thrown = false;
        try {
            TaskList tl = new TaskList();
            String in = "deadline test /by 2024-0-01";
            p.addTask(in, tl);
        } catch (DateTimeException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void emptyEventShouldThrowEmptyArgumentException() {
        boolean thrown = false;
        try {
            TaskList tl = new TaskList();
            String in = "event /from 2024-01-01 /to 2024-01-02";
            p.addTask(in, tl);
        } catch (DukeEmptyArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void emptyEventDateTimeShouldThrowEmptyArgumentException1() {
        boolean thrown = false;
        try {
            TaskList tl = new TaskList();
            String in = "event test /from  /to 2024-01-02";
            p.addTask(in, tl);
        } catch (DukeEmptyArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void emptyEventDateTimeShouldThrowEmptyArgumentException2() {
        boolean thrown = false;
        try {
            TaskList tl = new TaskList();
            String in = "event test /from 2024-01-02 /to ";
            p.addTask(in, tl);
        } catch (DukeEmptyArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void erroneousEventShouldThrowErroneousArgumentException1() {
        boolean thrown = false;
        try {
            TaskList tl = new TaskList();
            String in = "event test /from2024-01-01 /to 2024-01-02";
            p.addTask(in, tl);
        } catch (DukeErroneousArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void erroneousEventShouldThrowErroneousArgumentException2() {
        boolean thrown = false;
        try {
            TaskList tl = new TaskList();
            String in = "event test /from 2024-01-01 /to2024-01-02";
            p.addTask(in, tl);
        } catch (DukeErroneousArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void erroneousEventFormatShouldThrowDateTimeException1() {
        boolean thrown = false;
        try {
            TaskList tl = new TaskList();
            String in = "event test /from 2024-00-01 /to 2024-01-02";
            p.addTask(in, tl);
        } catch (DateTimeException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void erroneousEventFormatShouldThrowDateTimeException2() {
        boolean thrown = false;
        try {
            TaskList tl = new TaskList();
            String in = "event test /from 2024-01-01 /to 2024-00-02";
            p.addTask(in, tl);
        } catch (DateTimeException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
