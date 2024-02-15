package commands;

import tasks.TaskList;
import tasks.taskType.ToDo;

public class TodoCommand extends Command {
    public TaskList tasks;
    private String fullCommand;
    public TodoCommand(String fullCommand, TaskList tasks) {
        this.fullCommand = fullCommand;
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        ToDo newTdTask = new ToDo(this.fullCommand, "T", false);
        this.tasks.addTask(newTdTask);
        return displayTask(newTdTask, tasks);
    }
}
