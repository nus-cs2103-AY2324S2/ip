package commands;


import org.junit.jupiter.api.Test;
import tasks.Event;
import tasks.ToDo;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AddEventCommandTest {
    @Test
    public void toString_savedInTaskList_correct() {
        Event event = new Event("Have Dinner", LocalDateTime.of(2024, 1, 25, 8, 0), LocalDateTime.of(2024, 1, 25, 9, 30));
        assertEquals("[E][ ] Have Dinner (from: Jan 25 2024 08:00 AM to: Jan 25 2024 09:30 AM)", event.toString());
    }

    @Test
    public void fileString_savedInHardDisk_correct() {
        Event event = new Event("Borrow books", LocalDateTime.of(1999, 1, 25, 8, 0), LocalDateTime.of(2099, 1, 25, 9, 30));
        assertEquals("E | 0 | Borrow books | 1999-01-25T08:00 to 2099-01-25T09:30", event.fileString());
    }

    @Test
    public void markAsDone_toString_savedInTaskList_correct() {
        Event event  = new Event("Play Bridge", LocalDateTime.of(2024, 1, 10, 8, 0), LocalDateTime.of(2024, 1, 12, 10, 45));
        event.markAsDone();
        assertEquals("[E][X] Play Bridge (from: Jan 10 2024 08:00 AM to: Jan 12 2024 10:45 AM)", event.toString());
    }

    @Test
    public void markAsDone_fileString_savedInHardDisk_correct() {
        Event event  = new Event("Play Baccarat", LocalDateTime.of(2024, 1, 22, 23, 0), LocalDateTime.of(2024, 1, 23, 7, 00));
        event.markAsDone();
        assertEquals("E | 1 | Play Baccarat | 2024-01-22T23:00 to 2024-01-23T07:00", event.fileString());
    }
}
