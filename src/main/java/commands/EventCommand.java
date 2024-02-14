package commands;

import task.Event;
import ui.Ui;

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
     * @return
     */
    @Override
    public String executeCommand() {
        Event eventTask = new Event(name, from.trim(), to.trim());
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
