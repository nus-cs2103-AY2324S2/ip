package commands;

import tasks.Task;

/**
 *  Extends {@link AddCommand} and specifies the adding of deadline task
 */
public class AddDeadlineCommand extends AddCommand {
    public AddDeadlineCommand(Task task) {
        super(task);
    }
}
