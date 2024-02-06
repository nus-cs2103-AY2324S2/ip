package liv.processor;

import liv.exception.LivException;
import liv.container.TaskList;
import liv.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws LivException {
        Ui.displayByeCommand();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
