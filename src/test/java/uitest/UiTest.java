package uitest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import tasks.Task;
import ui.Ui;

public class UiTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void testTriggerAddMessage() {
        Ui ui = new Ui();
        Task task = new Task("hi");
        String addMessage = ui.triggerAddMessage(task);
        assertEquals("Added task:\n" + task.getStatusIcon() + " " + task.getDescription(), addMessage);
    }

    @Test
    public void testTriggerDeleteMessage() {
        Ui ui = new Ui();
        Task task = new Task("hi");
        String deleteMessage = ui.triggerDeleteMessage(task);
        assertEquals("Okay, I'll stop yapping about this task:\n"
                + task.getStatusIcon() + " " + task.getDescription(), deleteMessage);
    }

    public static void main(String[] args) {
        UiTest test = new UiTest();
        test.testTriggerAddMessage();
        test.testTriggerDeleteMessage();
    }
}
