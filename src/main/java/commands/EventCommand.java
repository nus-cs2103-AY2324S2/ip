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
     * @return
     */
    @Override
    public String execute() {
        Event eventTask = new Event(name, from.trim(), to.trim());
        taskList.add(eventTask);
        Ui.printVLine();
        System.out.println("Got it! Event has been added:\n" + eventTask + "\nNow you have "
                + taskList.getList().size() + " tasks in the list.");
        Ui.printVLine();

        assert Objects.equals(eventTask.getTask(), name.trim()) : "Failed at EventCommand: Task name is not the same.";

        return "Got it! Event has been added:\n" + eventTask + "\nNow you have "
                + taskList.getList().size() + " tasks in the list.";
    }
}
