package me.ruibin.leto.tasklist;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    /** Test creating Deadline from valid command by date 1 day ago. */
    @Test
    public void deadlineFromCommand_validCommand_success() {
        LocalDate deadline = LocalDate.now().minusDays(1);
        String command = "DEadLine SUBMIT.Paper #CS320  /by " + deadline.toString();
        String correctOutput = "[D][ ] SUBMIT.Paper #CS320 (by: "
                + deadline.toString() + ") 1 days past deadline";
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
            Deadline produced = Deadline.deadlineFromCommand(command);
        } catch (Exception e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }

    /** Test creating Deadline from valid CSV. */
    @Test
    public void deadlineFromCSV_validEntry_success() {
        LocalDate deadline = LocalDate.now().minusDays(1);
        String entry = "D,N,SUBMIT.Paper #CS320," + deadline.toString() + ",,";
        String correctOutput = "[D][ ] SUBMIT.Paper #CS320 (by: "
                + deadline.toString() + ") 1 days past deadline";
        try {
            Deadline produced = Deadline.deadlineFromCsv(entry);
            assertEquals(produced.toString(), correctOutput);
        } catch (Exception e) {
            fail(e);
        }
    }

    /** Test creating Deadline from invalid CSV with empty by date. */
    @Test
    public void deadlineFromCSV_invalidEmptyDatesEntry_failure() {
        String entry = "D,N,SUBMIT.Paper #CS320,XXX,,";
        String errorMessage = "By field, [XXX] is invalid!";
        try {
            Deadline produced = Deadline.deadlineFromCsv(entry);
        } catch (Exception e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
