package lilybot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lilybot.command.EventCommand;
import lilybot.gui.Ui;

class EventTest {
    @Test
    public void checkEventCreation() {
        //Check if correctly format the input by trimming
        Event event = new Event("project meeting \n \t", "\n2pm", "3pm Wed\t");
        String expectedString = "[E][ ] project meeting (2pm 3pm Wed)";
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
        String expectedString = "[E][X] project meeting (2pm 3pm Wed)";
        assertEquals(expectedString, event.toString());
    }

    @Test
    public void checkUnmark() {
        Event event = new Event("project meeting", "2pm", "3pm Wed");
        event.mark();
        event.unmark();
        String expectedString = "[E][ ] project meeting (2pm 3pm Wed)";
        assertEquals(expectedString, event.toString());
    }

    @Test
    public void addEvent_invalidDescription() {
        Ui testUi = new Ui();
        TaskList taskList = new TaskList(new ArrayList<Task>());
        EventCommand cmd = new EventCommand(testUi, taskList);
        String expectedString = "Oops! Sorry, I don't know what that means. Description is empty";
        assertEquals(expectedString, cmd.exceute("event"));
    }

    @Test
    public void addEvent_invalidEventFormat() {
        Ui testUi = new Ui();
        TaskList taskList = new TaskList(new ArrayList<Task>());
        EventCommand cmd = new EventCommand(testUi, taskList);
        String expectedString = "Plz enter a date for the event using '/from' and '/to'";
        assertEquals(expectedString, cmd.exceute("event meeting"));
    }
}
