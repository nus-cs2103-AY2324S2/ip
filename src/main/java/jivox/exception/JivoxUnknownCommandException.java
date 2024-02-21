package jivox.exception;

import jivox.Jivox;

/**
 * Represents a duke exception when an unknown command is provided.
 */
public class JivoxUnknownCommandException extends JivoxException {
    private String command;

    /**
     * Constructor for DukeUnknownCommandException.
     *
     * @param command the unknown command
     */
    public JivoxUnknownCommandException(String command) {
        this.command = command;
    }


    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s I am unable to Understand your Command '%s'!",
                super.toString(),
                this.command
        );
    }
}
