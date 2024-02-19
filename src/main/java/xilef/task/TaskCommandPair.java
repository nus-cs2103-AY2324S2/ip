package xilef.task;

import xilef.command.Command;

public class TaskCommandPair {
    private final Task task;
    private final Command command;

    public TaskCommandPair(Task task, Command command) {
        this.task = task;
        this.command = command;
    }

    public Task getTask() {
        return task;
    }

    public Command getCommand() {
        return command;
    }
}
