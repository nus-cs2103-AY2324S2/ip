package henry.command;

import henry.Storage;
import henry.TaskList;
import henry.Ui;
import henry.HenryException;
import henry.task.Event;

public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String args) throws HenryException {
        if (!args.contains("/from") || !args.contains("/to")) {
            throw new HenryException("Missing /from or /to");
        }
        String description, from = "", to = "";
        String[] parts = args.split(" /from | /to ");

        if (parts.length < 3) {
            throw new HenryException("Incorrect command format");
        }

        description = parts[0];
        if (args.indexOf("/from") < args.indexOf("/to")) {
            from = parts[1];
            to = parts[2];
        } else {
            from = parts[2];
            to = parts[1];
        }

        if (description.isBlank()) {
            throw new HenryException("Please provide description");
        }
        if (from.isBlank()) {
            throw new HenryException("Missing /from");
        }
        if (to.isBlank()) {
            throw new HenryException("Missing /to");
        }

        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException {
        tasks.addTask(new Event(this.description, this.from, this.to));
        storage.save(tasks);
    }
}
