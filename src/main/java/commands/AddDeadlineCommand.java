package commands;

import tasks.Task;

/**
 *  Extends {@link AddCommand} and specifies the adding of deadline task
 */
public class AddDeadlineCommand extends AddCommand {
    public AddDeadlineCommand(Task task) {
        super(task);
    }

    public AddDeadlineCommand() {
        super(null);
    }

    @Override
    public String showUsage() {
        return "Usage: Deadline {task description} by {date} "
                + "date is of the form: d/M/yyyy HHmm, yyyy-MM-dd HH:mm "
                + "or dd MMM yyyy h:mm a";
    }
}
