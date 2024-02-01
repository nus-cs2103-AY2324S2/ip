package commands;

import dave.Ui;
import dave.Storage;
import dave.TaskList;
import exceptions.*;

public class InvalidCommand extends Command {
    private ChatbotException exc;

    public InvalidCommand(ChatbotException exc) {
        this.exc = exc;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHorizontalLine();
        System.out.println(exc.getMessage());
        ui.showHorizontalLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
