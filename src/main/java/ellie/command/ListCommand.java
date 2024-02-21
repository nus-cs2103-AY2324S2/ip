package ellie.command;

import ellie.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand and returns a response containing the list of tasks.
     * {@inheritDoc}
     *
     * @param tasklist The TaskList to be operated on by the command.
     * @return A response containing the list of tasks.
     */
    public String runAndReturnResponse(TaskList tasklist) {
        String response = tasklist.listTasks();
        return response;
    }

    /**
     * Checks whether this ListCommand is equal to another object.
     *
     * @param o The object to compare with this ListCommand.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        // If the object is compared with itself, or is instance of this,
        // then return true
        if (o instanceof ListCommand) {
            return true;
        } else {
            return false;
        }
    }

}
