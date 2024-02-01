package jade.commands;

import jade.data.TaskList;
import jade.ui.Ui;
import jade.storage.Storage;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("\tBye. Hope to see you again soon.");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
