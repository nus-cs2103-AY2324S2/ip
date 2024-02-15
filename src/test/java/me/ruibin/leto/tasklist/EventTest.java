package me.ruibin.leto.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    /** Test creating Event from valid command with 10 days long 10 days from now. */
    @Test
    public void eventFromCommand_validCommand_success() {
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(20);
        StringBuilder correctOuputBuilder = new StringBuilder("[E][ ] H4(K1nG  cOnv@nt!0n (from: ");
        correctOuputBuilder.append(startDate)
                .append(" to: ").append(endDate).append(") 10 days to the event");
        String correctOutput = correctOuputBuilder.toString();
        String command = "event  H4(K1nG  cOnv@nt!0n   /from " + startDate + " /to " + endDate + "  ";
        try {
            Event produced = Event.eventFromCommand(command);
            assertEquals(produced.toString(), correctOutput);
        } catch (Exception e) {
            fail(e);
        }
    }

    /** Test creating Event from invalid command with bad date formats. */
    @Test
    public void eventFromCommand_commandWithBadFormat_failure() {
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(20);
        String command = MessageFormat.format("event  H4(K1nG  cOnv@nt!0n   /from {0} /to {1}",
                startDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy")),
                endDate);
        try {
            Event produced = Event.eventFromCommand(command);
        } catch (Exception e) {
            assertEquals((new EventInvalidCmdException()).getMessage(), e.getMessage());
        }
    }

    /** Test creating Event from valid csv. */
    @Test
    public void eventFromCsv_validCsvEntry_success() {
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(20);
        String entry = MessageFormat.format("E,Y,  H4(K1nG  cOnv@nt!0n,,{0},{1}",
                startDate,
                endDate);
        String pattern = "[E][X]   H4(K1nG  cOnv@nt!0n (from: {0} to: {1}) 10 days to the event";
        String correctOutput = MessageFormat.format(pattern, startDate, endDate);
        try {
            Event produced = Event.eventFromCsv(entry);
            assertEquals(produced.toString(), correctOutput);
        } catch (Exception e) {
            fail(e);
        }
    }

    /** Test creating Event from invalid csv with empty dates. */
    @Test
    public void eventFromCsv_badCsvEntry_failure() {
        String entry = "E,N,event  H4(K1nG  cOnv@nt!0n,,,";
        try {
            Event produced = Event.eventFromCsv(entry);
        } catch (Exception e) {
            String expectedErrorMessage = "Cannot match " + entry + " with regex";
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }
}
