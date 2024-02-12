package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) {
        Task[] taskArr = tasks.getTasks();
        int counter = tasks.getCounter();
        return ui.showTaskList(taskArr, counter);
    }
}
