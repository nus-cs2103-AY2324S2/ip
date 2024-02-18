package linus.commands;

import linus.Ui;
import linus.tasks.TaskList;

/**
 * It is the command when the user inputs a bye
 */
public class CommandBye extends Command {

    public CommandBye(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(String description) {
        ui.goodbye();
    }
    
}
