package liv.processor;

import liv.exception.LivException;
import liv.container.TaskList;
import liv.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws LivException {
        Ui.displayListCommand();
    }
}
