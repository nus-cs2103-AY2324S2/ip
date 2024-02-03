package exceptions;

/**
 * Represents an exception when the command is not recognized by the application.
 */
public class UnknownCommandException extends RuntimeException {
    protected final String got;
    protected final String[] knownCommands;

    /**
     * Constructs a UnknownCommandException with the specified detail message.
     *
     * @param message the detail message
     */
    public UnknownCommandException(String message, String got, String... knownCommands) {
        super(message);
        this.got = got;
        this.knownCommands = knownCommands;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage() + "\n";
        String badCommandMessage = String.format("Unknown command provided: %s\n", got);
        String knownCommandsMessage = knownCommands == null ? ""
                : String.format("Known commands: %s\n", String.join(", ", knownCommands));
        return String.format("%s%s%s",
                badCommandMessage,
                message,
                knownCommandsMessage);
    }
}
