package jerome.commands;

/**
 * Represents the result of a command execution.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 */
public class CommandResult {


    /**
     * Displays the feedback message to be shown to the user.
     * Contains a description of the execution result
     * */
    public final String feedbackToUser;

    /**
     * Represents the result of a command execution.
     *
     * @param feedbackToUser message to be displayed to user.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }
}
