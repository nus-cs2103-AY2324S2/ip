package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder res = new StringBuilder();
        int idx = 1;
        for (Task msg : tasks) {
            res.append(String.format("%d. %s\n", idx, msg));
            idx++;
        }
        return "Here are the tasks in your list:\n" + res + "\n";
    }

    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
