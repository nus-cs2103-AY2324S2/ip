package nicole.task;

import nicole.nicoleexceptions.NicoleException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void event_noDescriptionGiven_nicoleExceptionThrown() {
        try {
            new Event("from 2024-01-29 at 18:00:00 to 2024-01-29 at 21:00:00 from null at null to null at null");
            fail();
        } catch (NicoleException e) {
            assertEquals(
                    "ERROR. Describe your event like this: event [name] from YYYY-MM-DD at HH-MM-SS "
                              + "to YYYY-MM-DD at HH-MM-SS",
                    e.toString());
        }
    }
    @Test
    public void event_noDeadlineGiven_nicoleExceptionThrown() {
        try {
            new Event("meeting with kay from null at null to null at null");
            fail();
        } catch (NicoleException e) {
            assertEquals(
                    "ERROR. Describe your event like this: event [name] from YYYY-MM-DD at HH-MM-SS "
                              + "to YYYY-MM-DD at HH-MM-SS",
                    e.toString());
        }
    }

    @Test
    public void parseDate_invalidLocalDate_nicoleExceptionThrown() {
        try {
            new Event("meeting with kay from 2024-30-89 at 18:00:00 to 2024-30-89 at 21:00:00");
            fail();
        } catch (NicoleException e) {
            assertEquals(
                    "ERROR. Are you sure your date and time are in the proper [YYYY-MM-DD] and "
                              + "[HH-MM-SS] format...?",
                    e.toString());
        }
    }

    @Test
    public void parseDate_impossibleDate_nicoleExceptionThrown() {
        try {
            new Event("meeting with kay from 2024-01-30 at 18:00:00 to 2024-01-29 at 21:00:00");
        } catch (NicoleException e) {
            assertEquals(
                    "ERROR. Erm, the 'to' datetime can't be before 'from' right...",
                    e.toString());
        }
    }

    @Test
    void eventCreation_perfectConditions_toStringCorrect() {
        try {
            Event testEvent = new Event("meeting with kay from 2024-12-30 at 18:00:00 to 2024-12-30 at 21:00:00");
            assertEquals("[E][I] meeting with kay from 2024-12-30 at 18:00:00 to 2024-12-30 at 21:00:00",
                         testEvent.toString());
        } catch (NicoleException e) {
            fail();
        }
    }
}
