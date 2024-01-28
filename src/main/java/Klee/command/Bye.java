package Klee.command;

import Klee.Ui;
import Klee.Storage;
import Klee.TaskList;

public class Bye extends Command {
    public Bye () {}

    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) {
        ui.showBye();
    }
}