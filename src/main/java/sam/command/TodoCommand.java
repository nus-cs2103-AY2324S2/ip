package sam.command;

import sam.Storage;
import sam.TaskList;
import sam.Ui;
import sam.SamException;
import sam.task.ToDo;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String taskInfo) throws SamException {
        if (taskInfo.isBlank()) {
                throw new SamException("Please provide a task description.");
        }
        this.description = taskInfo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SamException {
        tasks.addTask(new ToDo(description));
        storage.save(tasks);
    }
}