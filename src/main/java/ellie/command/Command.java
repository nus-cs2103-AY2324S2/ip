package ellie.command;

import ellie.TaskList;

/**
 * Abstract class representing a command in the task management application.
 */
public abstract class Command {

    protected boolean isExit = false;

    /**
     * Executes the command using the provided TaskList.
     *
     * @param tasklist The TaskList to be operated on by the command.
     */
    public abstract String runAndReturnResponse(TaskList tasklist);

    /**
     * Checks if the command signals an exit from the application.
     *
     * @return True if the command signals an exit, otherwise false.
     */
    public boolean isExit() {
        return isExit;
    }

}
