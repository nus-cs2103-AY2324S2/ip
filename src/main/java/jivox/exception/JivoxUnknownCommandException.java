package jivox.exception;

/**
 * Represents a Jivox exception when an unknown command is provided.
 */
public class JivoxUnknownCommandException extends JivoxException {
    private String command;

    /**
     * Constructor for JivoxUnknownCommandException.
     *
     * @param command the unknown command
     */
    public JivoxUnknownCommandException(String command) {
        this.command = command;
    }


    @Override
    public String toString() {
        return String.format("%s I am unable to Understand your Command '%s'!",
                super.toString(),
                this.command
        );
    }
}
