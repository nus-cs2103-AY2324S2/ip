package sam.command;

import sam.Storage;
import sam.TaskList;
import sam.Ui;
import sam.SamException;
import sam.task.Event;

public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;
    public EventCommand(String taskInfo) throws SamException {
        if (!taskInfo.contains("/from") || !taskInfo.contains("/to")) {
            throw new SamException("Invalid format for event, please provide event details by using /from and /to.");
        }
        String[] details = taskInfo.split(" /from | /to ");
        if (details[0].isBlank() || details[1].isBlank() || details[2].isBlank()) {
            throw new SamException("Please provide description, /from, and /to");
        }
        this.description = details[0];
        this.from = details[1];
        this.to = details[2];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SamException {
        tasks.addTask(new Event(this.description, this.from, this.to));
        storage.save(tasks);
    }
}