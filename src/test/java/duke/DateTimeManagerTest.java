package duke;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import andelu.AndeluException;
import andelu.DateTimeManager;

public class DateTimeManagerTest {

    @Test
    public void convertStringToLocalDateTime_matchedStringInput_success() throws AndeluException {
        assertEquals(LocalDateTime.of(2024, 01, 30, 23, 59),
                DateTimeManager.convertStringToLocalDateTime("2024-01-30 23:59"));

        assertEquals(LocalDateTime.of(2024, 01, 30, 23, 59),
                DateTimeManager.convertStringToLocalDateTime("2024-01-30 23:59"));

        assertEquals(LocalDateTime.of(2024, 03, 30, 10, 30),
                DateTimeManager.convertStringToLocalDateTime("2024-03-30 10:30"));
    }

    @Test
    public void convertStringToLocalDateTime_unmatchedStringInput_fail() throws AndeluException {
        assertNotEquals(LocalDateTime.of(2025, 03, 30, 10, 30),
                DateTimeManager.convertStringToLocalDateTime("2024-03-30 10:30"));

        assertNotEquals(LocalDateTime.of(2024, 03, 30, 12, 30),
                DateTimeManager.convertStringToLocalDateTime("2024-03-30 10:30"));

    }


    @Test
    public void convertLocalDateTimeToString_matchedDateTimeInput_success() throws AndeluException {
        assertEquals("Mar 30 2024 12:30",
                DateTimeManager.convertLocalDateTimeToString(LocalDateTime.of(2024, 03, 30, 12, 30)));

        assertEquals("Jan 30 2024 15:30",
                DateTimeManager.convertLocalDateTimeToString(LocalDateTime.of(2024, 01, 30, 15, 30)));
    }


    @Test
    public void convertLocalDateTimeToString_unmatchedDateTimeInput_fail() {
        try {
            assertNotEquals("Dec 30 2024 12:30",
                    DateTimeManager.convertLocalDateTimeToString(LocalDateTime.of(2024, 03, 30, 12, 30)));

            assertNotEquals("Jan 30 2024 08:30",
                    DateTimeManager.convertLocalDateTimeToString(LocalDateTime.of(2024, 01, 30, 15, 30)));
        } catch (AndeluException e) {
            assertEquals("There is an error of converting LocalDateTime to String.", e.getMessage());
        }
    }


    @Test
    public void convertStringToLocalDateTime_invalidInputNoTime_exceptionThrown() {
        try {
            assertNotEquals(LocalDateTime.of(2024, 01, 30, 23, 59),
                    DateTimeManager.convertStringToLocalDateTime("2024-01-30"));
            printSomething(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Invalid format for Date-Time. The format is \"yyyy-MM-dd HH:mm\".", e.getMessage());
        }
    }


    @Test
    public void convertStringToLocalDateTime_invalidInputTimeWrong_exceptionThrown() {
        try {
            assertNotEquals(LocalDateTime.of(2024, 01, 30, 23, 59),
                    DateTimeManager.convertStringToLocalDateTime("2024-01-30 25:59"));
            printSomething(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Invalid format for Date-Time. The format is \"yyyy-MM-dd HH:mm\".", e.getMessage());
        }
    }




    private static void printSomething() {
        System.out.println("Hello!");
    }

}
