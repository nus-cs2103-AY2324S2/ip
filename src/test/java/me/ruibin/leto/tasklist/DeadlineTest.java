package me.ruibin.leto.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    /** Test creating Deadline from valid command by date 1 day ago. */
    @Test
    public void deadlineFromCommand_validCommand_success() {
        LocalDate deadline = LocalDate.now().minusDays(1);
        String command = "DEadLine SUBMIT.Paper #CS320  /by " + deadline;
        String correctOutput = "[D][ ] SUBMIT.Paper #CS320 (by: "
                + deadline + ") 1 days past deadline";
        try {
            Deadline produced = Deadline.deadlineFromCommand(command);
            assertEquals(produced.toString(), correctOutput);
        } catch (Exception e) {
            fail(e);
        }
    }

    /** Test creating Deadline from invalid command with bad by date. */
    @Test
    public void deadlineFromCommand_commandWithBadFormat_failure() {
        LocalDate deadline = LocalDate.now().minusDays(1);
        String command = "DEadLine SUBMIT.Paper #CS320  /by "
                + deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        String errorMessage = "deadline <description> /by <date>, <date> should be in the format YYYY-MM-DD.";
        try {
            Deadline.deadlineFromCommand(command);
        } catch (Exception e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    /** Test creating Deadline from valid CSV. */
    @Test
    public void deadlineFromCsv_validEntry_success() {
        LocalDate deadline = LocalDate.now().minusDays(1);
        String entry = "D,N,SUBMIT.Paper #CS320," + deadline + ",,";
        String correctOutput = "[D][ ] SUBMIT.Paper #CS320 (by: "
                + deadline + ") 1 days past deadline";
        try {
            Deadline produced = Deadline.deadlineFromCsv(entry);
            assertEquals(produced.toString(), correctOutput);
        } catch (Exception e) {
            fail(e);
        }
    }

    /** Test creating Deadline from invalid CSV with empty by date. */
    @Test
    public void deadlineFromCsv_invalidEmptyDatesEntry_failure() {
        String entry = "D,N,SUBMIT.Paper #CS320,XXX,,";
        String errorMessage = "By field, [XXX] is invalid!";
        try {
            Deadline.deadlineFromCsv(entry);
        } catch (Exception e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    /** Test snoozing Deadline to one day after today. */
    @Test
    public void deadlineFromCsvSnoozeToTomorrow_validDays_success() {
        LocalDate deadline = LocalDate.now().minusDays(1);
        String entry = "D,N,SUBMIT.Paper #CS320," + deadline + ",,";
        String correctOutput = "[D][ ] SUBMIT.Paper #CS320 (by: "
                + deadline.plusDays(1) + ") due today";
        try {
            Deadline produced = Deadline.deadlineFromCsv(entry);
            produced.extendDeadline(1);
            assertEquals(produced.toString(), correctOutput);
        } catch (Exception e) {
            fail(e);
        }
    }

    /** Test snoozing Deadline to one day after today. */
    @Test
    public void deadlineFromCsvSnoozeToTomorrow_invalidDays_fail() {
        LocalDate deadline = LocalDate.now().minusDays(1);
        String entry = "D,N,SUBMIT.Paper #CS320," + deadline + ",,";
        try {
            Deadline produced = Deadline.deadlineFromCsv(entry);
            produced.extendDeadline(0);
        } catch (Exception e) {
            assertEquals("Cannot extend deadline by a negative number of or 0 days.", e.getMessage());
        }
    }
}
