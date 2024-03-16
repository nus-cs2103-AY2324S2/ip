package ellie.command;

import ellie.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object.
     */
    public ExitCommand() {
        super.isExit = true;
    }

    public String runAndReturnResponse(TaskList tasklist) {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        // If the object is compared with itself, or is instance of ExitCommand
        // then return true
        if (o instanceof ExitCommand) {
            return true;
        } else {
            return false;
        }
    }

}
