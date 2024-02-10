package duke.command;

import java.util.List;

import duke.task.TaskList;


public class ByeCommand extends Command {
    protected String command;
    protected List<String> arguments;

    public ByeCommand() {
        super("bye", List.of());
    }

    public TaskList execute(TaskList tasks) {
        return tasks;
    }
}
