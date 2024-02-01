package lex.parser.command;

public interface Command {
    // Returns true if the command is an exit command.
    boolean execute() throws Exception;
}
