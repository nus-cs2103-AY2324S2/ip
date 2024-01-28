package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Here are the tasks in your list:\n" + tasks + "\n";
    }

    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
