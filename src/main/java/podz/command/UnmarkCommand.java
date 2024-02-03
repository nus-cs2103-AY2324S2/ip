package podz.command;

import podz.exceptions.PodzException;
import podz.ui.Ui;

public class UnmarkCommand  extends Command {
    private int index;

    public UnmarkCommand (int index) {
        this.index = index;
    }

    @Override
    public void execute (Ui ui) {
        try {
            if (index < 0 || index >= super.taskList.getSize()) {
                throw new PodzException("Oh no!!! Invalid task index!");
            }
            super.taskList.unmarkTask(index);
            ui.printUnmarked(super.taskList.getTask(index));
        } catch (PodzException e) {
            ui.printError(e);
        }
        
    }
}
