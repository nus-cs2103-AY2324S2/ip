package duke.command;

import java.util.List;

import duke.task.TaskList;

public abstract class Command {
    protected String command;
    protected List<String> arguments;

    public Command(String command, List<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public abstract TaskList execute(TaskList tasks);

    public String getCommand() {
        return this.command;
    }

    //  equals method to compare string
}
