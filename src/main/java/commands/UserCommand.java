package commands;

/**
 * Represents a command response intended for the user.
 */
public class UserCommand {
    private final String messageToUser;

    /**
     * Constructs a UserCommand object with the given message arguments.
     *
     * @param args The message arguments to be concatenated.
     */
    public UserCommand(String... args) {
        String concatenatedArgs = "";
        for (String arg : args) {
            concatenatedArgs += arg + "\n";
        }
        this.messageToUser = concatenatedArgs;
    }

    /**
     * Gets the message intended for the user.
     *
     * @return The message to be displayed to the user.
     */
    public String getMessageToUser() {
        return this.messageToUser;
    }
}
