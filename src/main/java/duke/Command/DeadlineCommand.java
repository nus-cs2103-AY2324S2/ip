package duke.Command;

import duke.*;
import duke.Tasks.*;
import duke.Tasks.TaskList;

public class DeadlineCommand extends Command {
    private String description;
    private String deadline;

    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new DeadlineTask(description, deadline);
        tasks.addTask(task);
        int count = tasks.size();;
        ui.showAddedMessage(task, count);
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

