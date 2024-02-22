package henry.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import henry.HenryException;

public class EventTest {
    @Test
    public void constructor_validInput1_success() {
        try {
            Event event = new Event("test1", "24/2/2024 1200", "24/2/2024 1500");
            assertEquals("[E][ ] test1 (from: Feb 24 2024 12:00 to: Feb 24 2024 15:00)", event.toString());
        } catch (HenryException e) {
            fail();
        }
    }

    @Test
    public void constructor_validInput2_success() {
        try {
            Event event = new Event("test1", "24/2/2024 1200", "24/2/2024 1500");
            assertEquals("E | 0 | test1 | 24/2/2024 1200 | 24/2/2024 1500", event.toFileString());
        } catch (HenryException e) {
            fail();
        }
    }

    @Test
    public void constructor_markAsDone_success() {
        try {
            Event event = new Event("test1", "24/2/2024 1200", "24/2/2024 1500");
            event.markAsDone();
            assertEquals("[E][X] test1 (from: Feb 24 2024 12:00 to: Feb 24 2024 15:00)", event.toString());
        } catch (HenryException e) {
            fail();
        }
    }

    @Test
    public void constructor_invalidInput1_exceptionThrown() {
        try {
            Event event = new Event("", "24/2/2024 1200", "24/2/2024 1500");
            fail();
        } catch (HenryException e) {
            assertEquals("No description of task!", e.getMessage());
        }
    }

    @Test
    public void constructor_invalidInput2_exceptionThrown() {
        try {
            Event event = new Event("test1", "", "24/2/2024 1500");
            fail();
        } catch (HenryException e) {
            assertEquals("Missing time !!!\n", e.getMessage());
        }
    }

    @Test
    public void constructor_invalidInput3_exceptionThrown() {
        try {
            Event event = new Event("test1", "24/2/2024 1200", "");
            fail();
        } catch (HenryException e) {
            assertEquals("Missing time!!!\n", e.getMessage());
        }
    }
}
