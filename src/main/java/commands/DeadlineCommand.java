package commands;

import tasks.TaskList;
import tasks.taskType.Deadline;
public class DeadlineCommand extends Command {
    public TaskList tasks;
    private String fullCommand;
    public DeadlineCommand(String fullCommand, TaskList tasks) {
        this.fullCommand = fullCommand;
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        Deadline newDlTask = new Deadline(this.fullCommand, "D", false);
        this.tasks.addTask(newDlTask);
        return displayTask(newDlTask, tasks);
    }
}
