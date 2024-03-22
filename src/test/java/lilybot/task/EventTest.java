package lilybot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class EventTest {
    @Test
    public void checkEventCreation() {
        //Check if correctly format the input by trimming
        Event event = new Event("project meeting \n \t", "\n2pm", "3pm Wed\t");
        String expectedString = "[E][ ] project meeting ( 2pm to 3pm Wed)";
        assertEquals(expectedString, event.toString());
    }

    @Test
    public void checkEventTime() {
        Event event = new Event("project meeting", "\n 2pm", "  3pm Wed\t");
        String expectedString = "2pm 3pm Wed";
        assertEquals(expectedString, event.getFromTo());
    }

    @Test
    public void checkMark() {
        Event event = new Event("project meeting", "2pm", "3pm Wed");
        event.mark();
        String expectedString = "[E][X] project meeting ( 2pm to 3pm Wed)";
        assertEquals(expectedString, event.toString());
    }

    @Test
    public void checkUnmark() {
        Event event = new Event("project meeting", "2pm", "3pm Wed");
        event.mark();
        event.unmark();
        String expectedString = "[E][ ] project meeting ( 2pm to 3pm Wed)";
        assertEquals(expectedString, event.toString());
    }
}
