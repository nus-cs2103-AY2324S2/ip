package jerome.commands;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 */
public class IncorrectCommand extends Command {

    /**
     * Represents feedback provided to the user when command is bad.
     */
    public final String feedbackToUser;

    /**
     * Creates a new IncorrectCommand object with the given feedback message.
     * @param feedbackToUser the feedback message to be displayed to the user
     */
    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }
    /**
     * Executes the command by returning a command result.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
    }

}
