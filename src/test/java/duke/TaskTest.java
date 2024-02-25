package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import duke.tasks.Task;

public class TaskTest {

    @Test
    public void fromStorageString_noString_throws() {
        var thrown = assertThrows(DukeException.class, () -> Task.fromStorageString(""));
        assertEquals("expected a type identifier, but none was given", thrown.getMessage());
    }

    @Test
    public void fromStorageString_noDelimString_throws() {
        var thrown = assertThrows(DukeException.class, () -> Task.fromStorageString("Tno delimitersF"));
        assertEquals("expected a name, but none was given", thrown.getMessage());
    }

    @Test
    public void fromStorageString_oneDelim_throws() {
        var thrown = assertThrows(DukeException.class, () -> Task.fromStorageString("T,one delimF"));
        assertEquals("expected an done status, but none was given", thrown.getMessage());
    }

    @Test
    public void fromStorageString_invalidTypeId_throws() {
        var thrown = assertThrows(DukeException.class, () -> Task.fromStorageString("???,one delim,F,L"));
        assertEquals("unexpected type string ???", thrown.getMessage());
    }

    @Test
    public void fromStorageString_invalidDoneString_throws() {
        var thrown = assertThrows(DukeException.class, () -> Task.fromStorageString("T,one delim,???,L"));
        assertEquals("unexpected done string ???", thrown.getMessage());
    }

    @Nested
    class ToDo {

        @Test
        public void fromStorageString_todoStringHigh_success() throws DukeException {
            Task t = Task.fromStorageString("T,name,F,H");
            assertInstanceOf(duke.tasks.ToDo.class, t);
            assertEquals("[T][ ] \u2605 name", t.describe());
        }

        @Test
        public void fromStorageString_todoStringLow_success() throws DukeException {
            Task t = Task.fromStorageString("T,name,F,L");
            assertInstanceOf(duke.tasks.ToDo.class, t);
            assertEquals("[T][ ] name", t.describe());
        }

        @Test
        public void fromStorageString_markedTodoString_success() throws DukeException {
            Task t = Task.fromStorageString("T,name,T,L");
            assertInstanceOf(duke.tasks.ToDo.class, t);
            assertEquals("[T][X] name", t.describe());
        }

        @Test
        public void fromStorageString_namelessTodoString_success() throws DukeException {
            Task t = Task.fromStorageString("T,,F,L");
            assertInstanceOf(duke.tasks.ToDo.class, t);
            assertEquals("[T][ ] ", t.describe());
        }
    }

    @Nested
    class Deadline {

        @Test
        public void fromStorageString_deadlineString_success() throws DukeException {
            Task t = Task.fromStorageString("D,name,F,L,2024-06-06 0000");
            assertInstanceOf(duke.tasks.Deadline.class, t);
            assertEquals("[D][ ] name (by: 12:00 am, 6-06-2024)", t.describe());
        }

        @Test
        public void fromStorageString_markedDeadlineString_success() throws DukeException {
            Task t = Task.fromStorageString("D,name,T,L,2024-06-06 0000");
            assertInstanceOf(duke.tasks.Deadline.class, t);
            assertEquals("[D][X] name (by: 12:00 am, 6-06-2024)", t.describe());
        }

        @Test
        public void fromStorageString_noDate_throws() {
            var thrown = assertThrows(DukeException.class, () -> Task.fromStorageString("D,name,F,L"));
            assertEquals("expected a deadline date, but none was given", thrown.getMessage());
        }

        @Test
        public void fromStorageString_invalidDate_throws() {
            var thrown = assertThrows(DateTimeParseException.class, () -> Task.fromStorageString("D,name,F,L,2024-06-06 2500"));
            assertEquals("Text '2024-06-06 2500' could not be parsed: "
                    + "Invalid value for HourOfDay (valid values 0 - 23): 25", thrown.getMessage());
        }
    }

    @Nested
    class Event {

        @Test
        public void fromStorageString_eventString_success() throws DukeException {
            Task t = Task.fromStorageString("E,name,F,L,2024-06-06 0000,2024-06-06 0100");
            assertInstanceOf(duke.tasks.Event.class, t);
            assertEquals("[E][ ] name (from: 12:00 am, 6-06-2024 to: 01:00 am, 6-06-2024)", t.describe());
        }

        @Test
        public void fromStorageString_markedEventString_success() throws DukeException {
            Task t = Task.fromStorageString("E,name,T,L,2024-06-06 0000,2024-06-06 0100");
            assertInstanceOf(duke.tasks.Event.class, t);
            assertEquals("[E][X] name (from: 12:00 am, 6-06-2024 to: 01:00 am, 6-06-2024)", t.describe());
        }

        @Test
        public void fromStorageString_noFrom_throws() {
            var thrown = assertThrows(DukeException.class, () -> Task.fromStorageString("E,name,F,L"));
            assertEquals("expected a start date, but none was given", thrown.getMessage());
        }

        @Test
        public void fromStorageString_noTo_throws() {
            var thrown = assertThrows(DukeException.class, () -> Task.fromStorageString("E,name,F,L,2024-06-06 0000"));
            assertEquals("expected an end date, but none was given", thrown.getMessage());
        }

        @Test
        public void fromStorageString_invalidDate_throws() {
            var thrown = assertThrows(DateTimeParseException.class,
                    () -> Task.fromStorageString("E,name,F,L,2024-06-06 0000,2024-06-06 2500"));
            assertEquals("Text '2024-06-06 2500' could not be parsed: "
                    + "Invalid value for HourOfDay (valid values 0 - 23): 25", thrown.getMessage());
        }
    }
}
