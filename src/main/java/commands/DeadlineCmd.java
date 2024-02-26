package commands;

import destiny.Deadline;
import destiny.DestinyException;
import destiny.TaskList;

/**
 * Command that creates new Deadline task and adds it to the task list.
 */
public class DeadlineCmd extends Command {
    private String details;

    /**
     * Constructor for the DeadlineCmd class.
     *
     * @param details Information for the Deadline task.
     */
    public DeadlineCmd(String details) {
        this.details = details;
    }
    @Override
    public String execute(TaskList tasks) throws DestinyException {
        if (details == "" || details == null) {
            throw new DestinyException("Please enter a description for the Deadline command");
        }

        String[] splitDetails = details.toLowerCase().split("/by ", 2);

        // splits details into relevant parameters
        try {
            Deadline newDL = new Deadline(splitDetails[0], splitDetails[1]);
            return tasks.addTask(newDL);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DestinyException("After entering the deadline task name,\n"
                    + "add '/by' followed by your desired deadline");
        }
    }
}
