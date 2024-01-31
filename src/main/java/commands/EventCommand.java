package commands;

import commands.Command;
import exceptions.DukeException;
import task.Event;
import ui.Ui;

public class EventCommand extends Command {

    public static final String COMMAND = "event";
    private String name;
    private String from;
    private String to;

    public EventCommand(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() throws DukeException {
        if (name.isEmpty()) {
            Ui.emptyTaskMessage();
        } else if (from.isEmpty() || to.isEmpty()) {
            Ui.invalidFormat();
        } else {
            Event eventTask = new Event(name, from.trim(), to.trim());
            tasks.add(eventTask);
            Ui.printVLine();
            System.out.println("Got it! Event has been added:\n" + eventTask + "\nNow you have "
                    + tasks.getList().size() + " tasks in the list.");
            Ui.printVLine();
        }
    }
}
