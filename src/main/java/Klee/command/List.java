package Klee.command;

import Klee.Ui;
import Klee.Storage;
import Klee.TaskList;
public class List extends Command {
    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) {
        ui.showTasks(tasks);
    }
}