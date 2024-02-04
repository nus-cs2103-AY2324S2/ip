package duke.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.CustomExceptions;

public class EventTest {

    @Test
    void constructor_validInput_returnsNewEvent() throws Exception {
        String s = "event A /from 10/10/10 1000 /to 10/10/10 1100";
        String[] info = s.split(" ");
        Event e = new Event(info);
        Assertions.assertEquals(e.toString(), "[E][ ] A (from: 10/10/10, 10:00 AM to: 10/10/10, 11:00 AM)");
    }

    @Test
    void constructor_missingName_throwsNamelessTaskException() {
        String s = "event /from 10/10/10 1000 /to 10/10/10 1100";
        String[] info = s.split(" ");
        Assertions.assertThrows(CustomExceptions.NamelessTaskException.class, () -> new Event(info));
    }

    @Test
    void constructor_missingFrom_throwsToBeforeFromException() {
        String s = "event A /to 10/10/10 1100";
        String[] info = s.split(" ");
        Assertions.assertThrows(CustomExceptions.ToBeforeFromException.class, () -> new Event(info));
    }

    @Test
    void constructor_missingTo_throwsEventExceptionForFromTo() {
        String s = "event A /from 10/10/10 1000";
        String[] info = s.split(" ");
        Assertions.assertThrows(CustomExceptions.EventExceptionForFromTo.class, () -> new Event(info));
    }

    @Test
    void constructor_unparseableDateTime_throwsUnrecognizableDateException() {
        Assertions.assertThrows(CustomExceptions.UnrecognizableDateException.class, (
            ) -> {
            throw new CustomExceptions.UnrecognizableDateException(""); });
    }

    @Test
    void constructor_swappedFromTo_throwsToBeforeFromException() {
        String s = "event A /to 10/10/10 1100 /from 10/10/10 1000";
        String[] info = s.split(" ");
        Assertions.assertThrows(CustomExceptions.ToBeforeFromException.class, () -> new Event(info));
    }

}
