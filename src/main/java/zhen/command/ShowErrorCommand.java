package zhen.command;

import zhen.Storage;
import zhen.TaskList;
import zhen.Ui;

public class ShowErrorCommand extends Command {

    String errorMessage;
    public ShowErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.print_message(errorMessage);
        return errorMessage;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}