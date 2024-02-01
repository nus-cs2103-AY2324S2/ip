package commands;

import commands.Command;
import excceptions.WeiException;
import taskList.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws WeiException {
        try {
            int order = Integer.parseInt(input.substring(7)) - 1;
            String deletedTask = tasks.delete(order);
            int size = tasks.getSize();
            ui.showDeleteMessage(deletedTask);
            ui.showNumberOfRemainingTasks(size);
        }
        catch (NumberFormatException e) {
            throw new WeiException("which task do you want to delete?");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }

}
