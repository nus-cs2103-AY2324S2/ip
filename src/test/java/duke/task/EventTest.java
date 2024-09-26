package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * The `EventTest` class represents a test class for the functionality of `Event` class.
 */
public class EventTest {
    private static final String SAMPLE_DESCRIPTION = "Project";
    private static final String SAMPLE_START_DATE_TIME = "2022-12-31 12:31";
    private static final String SAMPLE_END_DATE_TIME = "2023-01-02 03:04";

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private LocalDateTime parsedFromDateTime = LocalDateTime.parse(SAMPLE_START_DATE_TIME, DATE_TIME_FORMATTER);
    private LocalDateTime parsedToDateTime = LocalDateTime.parse(SAMPLE_END_DATE_TIME, DATE_TIME_FORMATTER);
    private Event newTask = new Event(SAMPLE_DESCRIPTION, parsedFromDateTime, parsedToDateTime);


    /**
     * Ensure the "padZero" function outputs the padded integer as string correctly.
     */
    @Test
    public void padZero_validFormat_returnsFormattedIntegerToHaveAtLeastTwoDigits() {
        assertEquals("00", newTask.padZero(0));
        assertEquals("05", newTask.padZero(5));
        assertEquals("10", newTask.padZero(10));
    }

    /**
     * Ensure the "getEventFromForSave" function outputs the formatted string correctly.
     */
    @Test
    public void getEventFromForSave_validFormat_returnsFormattedDateTime() {
        assertEquals("2022-12-31 12:31", newTask.getEventFromForSave());
    }

    /**
     * Ensure the "getEventToForSave" function outputs the formatted string correctly.
     */
    @Test
    public void getEventToForSave_validFormat_returnsFormattedDateTime() {
        assertEquals("2023-01-02 03:04", newTask.getEventToForSave());
    }

    /**
     * Ensure the "getEventFromForDisplay" function outputs the formatted string correctly.
     */
    @Test
    public void getEventFromForDisplay_validFormat_returnsFormattedDateTime() {
        assertEquals("DECEMBER 31 2022 12:31PM", newTask.getEventFromForDisplay());
    }

    /**
     * Ensure the "getEventToForDisplay" function outputs the formatted string correctly.
     */
    @Test
    public void getEventToForDisplay_validFormat_returnsFormattedDateTime() {
        assertEquals("JANUARY 02 2023 03:04AM", newTask.getEventToForDisplay());
    }

    /**
     * Ensure the "getDescriptionStatus" function outputs the formatted string correctly.
     */
    @Test
    public void getDescriptionStatus_validFormat_returnsFormattedDescriptionStatus() {
        assertEquals("[E][ ] Project (from: DECEMBER 31 2022 12:31PM to: JANUARY 02 2023 03:04AM)",
                newTask.getDescriptionStatus());
    }

    /**
     * Ensure the "getFields" function outputs the string array containing each field correctly.
     */
    @Test
    public void getFields_correctOutput_returnsStringArrayContainingFields() {
        String[] fields = newTask.getFields();
        assertEquals("Project", fields[0]);
        assertEquals("N", fields[1]);
        assertEquals("2022-12-31 12:31", fields[2]);
        assertEquals("2023-01-02 03:04", fields[3]);
    }
}
