package duke.Command;

import duke.*;
import duke.Tasks.*;
import duke.Tasks.TaskList;

//package duke.command;
public class EventCommand extends Command {
    private String description;
    private String startTime;
    private String endTime;

    public EventCommand(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new EventTask(description, startTime, endTime);
        tasks.addTask(task);
        int count = tasks.size();
        ui.showAddedMessage(task, count);
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

