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
        this.event = new Event("My birthday", this.startDate, this.endDate);
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
        String expectedOutput = "[E][ ] My birthday (from: Feb 27 2019 to: Feb 28 2019)";
        assertEquals(expectedOutput,
                this.event.toString());
    }

    @Test
    public void testToString_doneTask() {
        this.event.markAsDone();
        String expectedOutput = "[E][X] My birthday (from: Feb 27 2019 to: Feb 28 2019)";
        assertEquals(expectedOutput,
                this.event.toString());
    }

    @Test
    public void testGetSaveFormat_notDoneTask() {
        String expectedOutput = "event | 0 | My birthday | Feb 27 2019 | Feb 28 2019";
        assertEquals(expectedOutput,
                this.event.getSaveFormat());
    }

    @Test
    public void testGetSaveFormat_doneTask() {
        this.event.markAsDone();
        String expectedOutput = "event | 1 | My birthday | Feb 27 2019 | Feb 28 2019";
        assertEquals(expectedOutput,
                this.event.getSaveFormat());
    }

    @Test
    public void testHasKeywords_containOneKeyword() {
        assertTrue(this.event.hasKeywords(new String[]{"birth"}));
    }

    @Test
    public void testHasKeywords_missingOneKeywordCaseSensitive() {
        assertFalse(this.event.hasKeywords(new String[]{"Birth"}));
    }

    @Test
    public void testHasKeywords_missingOneKeyword() {
        assertFalse(this.event.hasKeywords(new String[]{"concert"}));
    }

    @Test
    public void testHasKeywords_containTwoKeywords() {
        assertTrue(this.event.hasKeywords(new String[]{"birth", "day"}));
    }

    @Test
    public void testHasKeywords_missingOneOfTwoKeywords() {
        assertFalse(this.event.hasKeywords(new String[]{"birth", "concert"}));
    }

    @Test
    public void testHasKeywords_containThreeKeywords() {
        assertTrue(this.event.hasKeywords(new String[]{"birth", "day", "My"}));
    }

    @Test
    public void testHasKeywords_missingOneOfThreeKeywords() {
        assertFalse(this.event.hasKeywords(new String[]{"birth", "day", "concert"}));
    }
}
