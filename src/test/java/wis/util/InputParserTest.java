package wis.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InputParserTest {

    @Test
    public void parseDeadline_success() {
        // multiple word task name
        String[] actualOutput = InputParser.parseDeadline(
                "deadline some task /by time");
        assertEquals("some task", actualOutput[0]);
        assertEquals("time", actualOutput[1]);

        // multiple word time
        actualOutput = InputParser.parseDeadline(
                "deadline task /by 2023-12-03 10:15");
        assertEquals("task", actualOutput[0]);
        assertEquals("2023-12-03 10:15", actualOutput[1]);

        // multiple word task name and time
        actualOutput = InputParser.parseDeadline(
                "deadline some task /by 2023-12-03 10:15");
        assertEquals("some task", actualOutput[0]);
        assertEquals("2023-12-03 10:15", actualOutput[1]);

        // spaces and special characters in task name
        actualOutput = InputParser.parseDeadline(
                "deadline TAS_K    *))?2e   ƒ∞†¥£ß    /by 2023-12-03 10:15");
        assertEquals("TAS_K    *))?2e   ƒ∞†¥£ß   ", actualOutput[0]);
        assertEquals("2023-12-03 10:15", actualOutput[1]);
    }

    @Test
    public void parseDeadline_fail() {
        // only deadline keyword
        try {
            InputParser.parseDeadline(
                    "deadline");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown input format", e.getMessage());
        }

        // only deadline keyword followed by spaces
        try {
            InputParser.parseDeadline(
                    "deadline    ");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown input format", e.getMessage());
        }

        // no <task_name>
        try {
            InputParser.parseDeadline(
                    "deadline /by 2023-12-03 10:15");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown input format", e.getMessage());
        }

        // >1 space at the beginning of <task_name> section
        try {
            InputParser.parseDeadline(
                    "deadline   task /by 2023-12-03 10:15");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown input format", e.getMessage());
        }

        // no space after deadline
        try {
            InputParser.parseDeadline(
                    "deadlinetask /by 2023-12-03 10:15");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown input format", e.getMessage());
        }

        // no space before '/by'
        try {
            InputParser.parseDeadline(
                    "deadline assignment/by 2023-12-03 10:15");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown input format", e.getMessage());
        }

        // no space after '/by'
        try {
            InputParser.parseDeadline(
                    "deadline assignment /by2023-12-03 10:15");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown input format", e.getMessage());
        }

        // multiple '/by'
        try {
            InputParser.parseDeadline(
                    "deadline assignment /by 2023-12-03 10:15 /by 2024-01-01 17:00");
            fail();
        } catch (Exception e) {
            assertEquals("Unknown input format", e.getMessage());
        }
    }
}