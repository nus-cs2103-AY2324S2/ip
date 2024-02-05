package shon.command;

import shon.TaskList;
import shon.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.print(tasks.getTasks());
    }
}
