package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * The `DeadlineTest` class represents a test class for the functionality of `Deadline` class.
 */
public class DeadlineTest {
    private static final String SAMPLE_DESCRIPTION = "Project";
    private static final String SAMPLE_DATE_TIME = "2022-12-31 12:31";

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    /**
     * Ensure the "padZero" function outputs the padded integer as string correctly.
     */
    @Test
    public void padZero_validFormat_returnsFormattedIntegerToHaveAtLeastTwoDigits() {
        LocalDateTime parsedDateTime = LocalDateTime.parse(SAMPLE_DATE_TIME, DATE_TIME_FORMATTER);
        Deadline newTask = new Deadline(SAMPLE_DESCRIPTION, parsedDateTime);
        assertEquals("00", newTask.padZero(0));
        assertEquals("05", newTask.padZero(5));
        assertEquals("10", newTask.padZero(10));
    }

    /**
     * Ensure the "getDeadlineForSave" function outputs the formatted string correctly.
     */
    @Test
    public void getDeadlineForSave_validFormat_returnsFormattedDateTime() {
        LocalDateTime parsedDateTime = LocalDateTime.parse(SAMPLE_DATE_TIME, DATE_TIME_FORMATTER);
        Deadline newTask = new Deadline(SAMPLE_DESCRIPTION, parsedDateTime);
        assertEquals("2022-12-31 12:31", newTask.getDeadlineForSave());
    }

    /**
     * Ensure the "getDeadlineForDisplay" function outputs the formatted string correctly.
     */
    @Test
    public void getDeadlineForDisplay_validFormat_returnsFormattedDateTime() {
        LocalDateTime parsedDateTime = LocalDateTime.parse(SAMPLE_DATE_TIME, DATE_TIME_FORMATTER);
        Deadline newTask = new Deadline(SAMPLE_DESCRIPTION, parsedDateTime);
        assertEquals("DECEMBER 31 2022 12:31PM", newTask.getDeadlineForDisplay());
    }

    /**
     * Ensure the "getDescriptionStatus" function outputs the formatted string correctly.
     */
    @Test
    public void getDescriptionStatus_validFormat_returnsFormattedDescriptionStatus() {
        LocalDateTime parsedDateTime = LocalDateTime.parse(SAMPLE_DATE_TIME, DATE_TIME_FORMATTER);
        Deadline newTask = new Deadline(SAMPLE_DESCRIPTION, parsedDateTime);
        assertEquals("[D][ ] Project (by: DECEMBER 31 2022 12:31PM)", newTask.getDescriptionStatus());
    }

    /**
     * Ensure the "getFields" function outputs the string array containing each field correctly.
     */
    @Test
    public void getFields_correctOutput_returnsStringArrayContainingFields() {
        LocalDateTime parsedDateTime = LocalDateTime.parse(SAMPLE_DATE_TIME, DATE_TIME_FORMATTER);
        Deadline newTask = new Deadline(SAMPLE_DESCRIPTION, parsedDateTime);
        String[] fields = newTask.getFields();
        assertEquals("Project", fields[0]);
        assertEquals("N", fields[1]);
        assertEquals("2022-12-31 12:31", fields[2]);
    }
}
