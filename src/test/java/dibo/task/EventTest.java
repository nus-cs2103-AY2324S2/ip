package dibo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {
    private Event event;
    private LocalDate startDate;
    private LocalDate endDate;

    @BeforeEach
    public void setUp() {
        this.startDate = LocalDate.parse("2019-02-27", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.endDate = LocalDate.parse("2019-02-28", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.event = new Event("Birthday", this.startDate, this.endDate);
    }
    @Test
    public void testMarkAsDone() {
        this.event.markAsDone();
        assertTrue(this.event.isDone());
    }

    @Test
    public void testMarkAsNotDone() {
        this.event.markAsDone();
        this.event.markAsNotDone();
        assertFalse(this.event.isDone());
    }

    @Test
    public void testToString_notDoneTask() {
        String expectedOutput = "[E][ ] Birthday (from: Feb 27 2019 to: Feb 28 2019)";
        assertEquals(expectedOutput,
                this.event.toString());
    }

    @Test
    public void testToString_doneTask() {
        this.event.markAsDone();
        String expectedOutput = "[E][X] Birthday (from: Feb 27 2019 to: Feb 28 2019)";
        assertEquals(expectedOutput,
                this.event.toString());
    }

    @Test
    public void testGetSaveFormat_notDoneTask() {
        String expectedOutput = "event | 0 | Birthday | Feb 27 2019 | Feb 28 2019";
        assertEquals(expectedOutput,
                this.event.getSaveFormat());
    }

    @Test
    public void testGetSaveFormat_doneTask() {
        this.event.markAsDone();
        String expectedOutput = "event | 1 | Birthday | Feb 27 2019 | Feb 28 2019";
        assertEquals(expectedOutput,
                this.event.getSaveFormat());
    }
}
