package duke.commands;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {


    /** The feedback message to be shown to the user. Contains a description of the execution result */
    // TODO: Code referenced from: https://github.com/se-edu/addressbook-level2
    public final String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }
}
