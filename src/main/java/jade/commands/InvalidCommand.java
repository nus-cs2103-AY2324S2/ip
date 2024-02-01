package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;
import jade.ui.Ui;

public class InvalidCommand extends Command {
    private JadeException exception;

    public InvalidCommand(JadeException e) {
        this.exception = e;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("\tInput is invalid, please retry.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
