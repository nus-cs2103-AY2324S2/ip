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
     */
    @Override
    public void execute() {
        Event eventTask = new Event(name, from.trim(), to.trim());
        tasks.add(eventTask);
        Ui.printVLine();
        System.out.println("Got it! Event has been added:\n" + eventTask + "\nNow you have "
                + tasks.getList().size() + " tasks in the list.");
        Ui.printVLine();
    }
}
