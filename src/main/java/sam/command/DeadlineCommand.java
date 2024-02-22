package sam.command;

import sam.Storage;
import sam.TaskList;
import sam.Ui;
import sam.SamException;
import sam.task.Deadline;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String taskInfo) throws SamException {
        if (!taskInfo.contains("/by")) {
            throw new SamException("Invalid format for deadline, please provide a deadline using /by.");
        }
        String[] details = taskInfo.split(" /by ");
        if (details[0].isBlank()) {
            throw new SamException("No description and /by provided");
        }
        if (details[1].isBlank()) {
            throw new SamException("When this has to be done by?");
        }
        this.description = details[0];
        this.by = details[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SamException {
        tasks.addTask(new Deadline(description, by));
        storage.save(tasks);
    }
}
