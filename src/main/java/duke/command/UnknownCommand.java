package duke.command;

import java.util.List;

import duke.task.TaskList;

public class UnknownCommand extends Command {
    public UnknownCommand() {
        super("", List.of());
    }

    public TaskList execute(TaskList tasks) {
        return null;
    }
}
