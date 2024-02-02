package toothless.tasks;

import org.junit.jupiter.api.Test;
import toothless.ToothlessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void createEvent() {
        try{
            Event event = new Event("Sleep", "2024-01-14T23:00", "2024-01-15T08:00");
            assertEquals("Sleep (from: Jan 14 2024 23:00 to: Jan 15 2024 08:00)", event.toString());
        } catch (ToothlessException e){
            fail();
        }
    }

    @Test
    public void createIncorrectEvent() {
        try{
            Event event = new Event("Sleep", "2024-01-16T23:00", "2024-01-15T08:00");
            fail();
        } catch (ToothlessException e){
            assertEquals("End date is earlier :/", e.getMessage());
        }
    }

    @Test
    public void markAndUnmarkEvent() {
        try{
            Event event = new Event("Sleep", "2024-01-14T23:00", "2024-01-15T08:00");
            event.markAsDone();
            assertEquals("X", event.getStatusIcon());
            event.markAsNotDone();
            assertEquals(" ", event.getStatusIcon());
        } catch (ToothlessException e){
            fail();
        }
    }

    @Test
    public void saveEvent() {
        try{
            Event event = new Event("Sleep", "2024-01-14T23:00", "2024-01-15T08:00");
            assertEquals("E | 0 | Sleep | 2024-01-14T23:00 | 2024-01-15T08:00", event.toWrite());
        } catch (ToothlessException e){
            fail();
        }
    }
}
