package podz.command;

import podz.exceptions.PodzException;
import podz.ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand (int index) {
        this.index = index;
    }

    @Override
    public void execute (Ui ui) {
        try {
            if (index < 0 || index >= super.taskList.getSize()) {
                throw new PodzException("Oh no!!! Invalid task index!");
            }
            super.taskList.markTask(index);
            ui.printMarked(super.taskList.getTask(index));
        } catch (PodzException e) {
            ui.printError(e);
        }
        
    }
}