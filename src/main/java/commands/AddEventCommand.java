package commands;

import tasks.Task;

/**
 *  Extends {@link AddCommand} and specifies the adding of event task
 */

public class AddEventCommand extends AddCommand {
    public AddEventCommand(Task task) {
        super(task);
    }
}
