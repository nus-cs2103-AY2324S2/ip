package jade.commands;

import jade.data.Task;
import jade.data.TaskList;
import jade.exception.JadeException;
import jade.ui.Ui;
import jade.storage.Storage;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JadeException {
        if (index <= 0 || index > taskList.size()) {
            throw new JadeException("\tPlease input a valid number to mark done.");
        }
        taskList.mark(index-1);
        ui.printMessage(String.format("\tNice, I've marked this task as done:\n\t  %s",  taskList.get(index-1)));
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
