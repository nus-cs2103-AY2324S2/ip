package commands;

import task.Event;
import ui.Ui;

import java.util.Objects;

/**
 * Encapsulates an event command.
 */
public class EventCommand extends Command {

    public static final String COMMAND = "event";
    private final String name;
    private final String from;
    private final String to;

    public EventCommand(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds an event into TaskList.
     *
     * @return String representation after adding an event.
     */
    @Override
    public String executeCommand() {
        Event eventTask = new Event(name, from.trim(), to.trim());
        assert Objects.equals(eventTask.getTask(), name.trim()) : "Failed at EventCommand: Task name is not the same.";
        taskList.add(eventTask);
        String addedEventMessage = "Got it! Event has been added:\n" + eventTask + "\nNow you have "
                + taskList.getList().size() + " tasks in the list.";
        printMessage(addedEventMessage);
        return addedEventMessage;
    }

    private static void printMessage(String addedEventMessage) {
        Ui.printVLine();
        System.out.println(addedEventMessage);
        Ui.printVLine();
    }
}
