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
            throw new HenryException("Missing /from and /to");
        }
        String[] eventParams = args.split(" /from | /to ");
        if (eventParams[0].isBlank() || eventParams[1].isBlank() || eventParams[2].isBlank()) {
            throw new HenryException("Please provide desription, /from, and /to");
        }
        this.description = eventParams[0];
        this.from = eventParams[1];
        this.to = eventParams[2];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException {
        tasks.addTask(new Event(this.description, this.from, this.to));
        storage.save(tasks);
    }
}
