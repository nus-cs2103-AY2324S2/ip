package duke;

import duke.tasks.Deadline;
import duke.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    public static final String NAME_1 = "deadline 1";
    public static final String DATE_1 = "2024-06-06 0000";
    public static final String EXPECTED_DESCRIBE_1 = "[D][ ] deadline 1 (by: 12:00 am, 6-06-2024)";
    public static final String EXPECTED_STORED_1 = "D,deadline 1,F,2024-06-06 0000";


    @Nested
    class TaskTest {
        private static Task t;
        @BeforeEach
        public void createT() {
            t = new Deadline(NAME_1, DATE_1);
        }

        @Test
        public void Deadline_normalDeadline_success() {
            assertEquals(EXPECTED_DESCRIBE_1, t.describe());
        }

        @Test
        public void describe_markedDeadline_success() {
            t.mark();
            assertEquals("[D][X] deadline 1 (by: 12:00 am, 6-06-2024)", t.describe());
        }

        @Test
        public void describe_unmarkedDeadline_success() {
            t.mark();
            assertEquals("[D][X] deadline 1 (by: 12:00 am, 6-06-2024)", t.describe());
            t.unmark();
            assertEquals(EXPECTED_DESCRIBE_1, t.describe());
        }

        @Test
        public void describe_noNameDeadline_success() {
            t = new Deadline("", DATE_1);
            assertEquals("[D][ ]  (by: 12:00 am, 6-06-2024)", t.describe());
        }

        @Test
        public void toStorageString_normalDeadline_success() {
            assertEquals(t.toStorageString(), EXPECTED_STORED_1);
        }

        @Test
        public void toStorageString_markedDeadline_success() {
            t.mark();
            assertEquals(t.toStorageString(), "D,deadline 1,T,2024-06-06 0000");
        }

        @Test
        public void toStorageString_unmarkedDeadline_success() {
            t.mark();
            assertEquals(t.toStorageString(), "D,deadline 1,T,2024-06-06 0000");
            t.unmark();
            assertEquals(t.toStorageString(), EXPECTED_STORED_1);
        }

        @Test
        public void toStorageString_noNameDeadline_success() {
            t = new Deadline("", DATE_1);
            assertEquals(t.toStorageString(), "D,,F,2024-06-06 0000");
        }

        // by right this should fail as it will mess up parsing
        // will fail when fixed in the future
        @Test
        public void toStorageString_commaInName_success() {
            Task t = new Deadline("mwa, ha, ha", DATE_1);
            assertEquals(t.toStorageString(), "D,mwa, ha, ha,F,2024-06-06 0000");
        }
    }

    @Test
    public void Deadline_invalidDateString_exceptionThrown() {
        var thrown = assertThrows
                (DateTimeParseException.class, () -> new Deadline(NAME_1, "2024-06-06 0000 nonsense"));
        assertEquals
                ("Text '2024-06-06 0000 nonsense' could not be parsed, unparsed text found at index 15",
                        thrown.getMessage());
    }

    @Test
    public void Deadline_outOfBoundsDateString_exceptionThrown() {
        var thrown = assertThrows
                (DateTimeParseException.class, () -> new Deadline(NAME_1, "2024-06-06 2500"));
        assertEquals
                ("Text '2024-06-06 2500' could not be parsed: "
                        + "Invalid value for HourOfDay (valid values 0 - 23): 25",
                        thrown.getMessage());
    }
}
