package commands;

import tasks.Task;

/**
 *  Extends {@link AddCommand} and specifies the adding of event task
 */

public class AddEventCommand extends AddCommand {
    public AddEventCommand(Task task) {
        super(task);
    }

    public AddEventCommand() {
        super(null);
    }

    @Override
    public String showUsage() {
        return "Usage: Event {task description} from {start date} to {end date} "
                + "date is of the form: d/M/yyyy HHmm, yyyy-MM-dd HH:mm "
                + "or dd MMM yyyy h:mm a";
    }
}
