package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.ui.Ui;
import jade.storage.Storage;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JadeException {
        ui.printMessage("\tBye. Hope to see you again soon.");
        storage.saveChange(taskList);
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
