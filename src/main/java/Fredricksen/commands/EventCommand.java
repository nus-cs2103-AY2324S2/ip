package Fredricksen.commands;

import Fredricksen.commands.Command;
import Fredricksen.tasks.TaskList;
import Fredricksen.tasks.taskType.Event;

public class EventCommand extends Command {
    public TaskList tasks;
    private String fullCommand;

    public EventCommand(String fullCommand, TaskList tasks) {

        assert fullCommand.equals("") : "Should not happen";

        this.fullCommand = fullCommand;
        this.tasks = tasks;
    }
    @Override
    public String execute() {
        Event newEventTask = new Event(this.fullCommand, "E", false);
        this.tasks.addTask(newEventTask);
        return displayTask(newEventTask, tasks);
    }
}
