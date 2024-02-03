package duke.command;

import duke.Parser;

/**
 * Represents a command that the user has input.
 * A <code>Command</code> object corresponds to a command that the user has input.
 * It contains the command word and the arguments that the user has input.
 * It also contains the logic to interpret the command and execute it.
 * It is also the parent class of all other command classes.
 */
public abstract class Command {
    /**
     * Interprets the input and returns the corresponding command.
     *
     * @param input the input to interpret
     * @return the corresponding command
     */
    public static Command interpret(String input) {
        Parser parser = new Parser(input);

        switch (parser.parseCommand()) {
        case AddTaskCommand.ADD_TODO_COMMAND:
            // Fallthrough
        case AddTaskCommand.ADD_DEADLINE_COMMAND:
            // Fallthrough
        case AddTaskCommand.ADD_EVENT_COMMAND:
            return new AddTaskCommand(parser.parseCommand(), parser.parseArguments());
        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();
        case MarkTaskCommand.COMMAND_WORD:
            return new MarkTaskCommand(parser.parseTaskID());
        case UnmarkTaskCommand.COMMAND_WORD:
            return new UnmarkTaskCommand(parser.parseTaskID());
        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommand(parser.parseTaskID());
        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommand(parser.parseArguments());
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new UnknownCommand(parser.parseCommand());
        }
    }

    /**
     * Executes the command.
     *
     * @return the output of the command that will be printed to the user
     */
    public abstract String execute();

    /**
     * Checks if the command is a termination command.
     *
     * @return true if the command is a termination command, false otherwise
     */
    public boolean terminate() {
        return false;
    }
}
