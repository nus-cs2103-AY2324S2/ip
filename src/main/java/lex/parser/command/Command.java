package lex.parser.command;

/**
 * Represents a command.
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @return True if the command is an exit command.
     * @throws Exception If an error occurs.
     */
    boolean execute() throws Exception;
}
