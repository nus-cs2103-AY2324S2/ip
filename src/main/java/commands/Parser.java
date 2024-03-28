package commands;

import exceptions.TaskYapperException;

/**
 * The {@code Parser} class is responsible for interpreting user input messages and converting them into
 * command actions that the application can execute. It serves as a crucial component in the user command
 * processing pipeline, mapping specific keywords or phrases to their corresponding command types.
 *
 * <p>The parser checks the input message against a predefined set of command patterns and, upon finding a match,
 * returns the corresponding command type. If the input does not correspond to any recognized command pattern,
 * the parser throws a {@link TaskYapperException} to indicate the ambiguity or invalidity of the user's instruction.
 *
 * <p>This class is essential for enabling user interaction with the application by providing a way to understand
 * and act upon user commands.
 */
public class Parser {

    /**
     * Parses the given input message and returns the corresponding Command.
     * The method maps specific keywords or phrases in the input message to predefined commands in the application.
     * If the input message does not match any known command patterns,
     * the method throws a TaskYapperException with a message prompting the user to provide a clearer instruction.
     *
     * @param message The user input message to be parsed.
     * @return The Command corresponding to the user's input message.
     * @throws TaskYapperException If the input message does not match any known command patterns, indicating that the
     *                       user's instruction is unclear or invalid within the context of the application.
     */
    public static Command parse(String message) throws TaskYapperException {
        if (message.equals("bye")) {
            return Command.BYE;
        } else if (message.equals("yap")) {
            return Command.YAP;
        } else if (message.startsWith("mark")) {
            return Command.MARK;
        } else if (message.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (message.startsWith("todo")) {
            return Command.ADD_TODO;
        } else if (message.startsWith("deadline")) {
            return Command.ADD_DEADLINE;
        } else if (message.startsWith("event")) {
            return Command.ADD_EVENT;
        } else if (message.startsWith("delete")) {
            return Command.DELETE;
        } else if (message.startsWith("find")) {
            return Command.FIND;
        } else if (message.startsWith("schedule")) {
            return Command.SCHEDULE;
        } else {
            throw new TaskYapperException("What's YAPpening??!! Please yap your instruction more clearly");
        }
    }

}
