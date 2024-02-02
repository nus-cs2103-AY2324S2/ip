package commands;

import exceptions.DukeException;

public class Parser {

    /**
     * Parses the given input message and returns the corresponding Command.
     * The method maps specific keywords or phrases in the input message to predefined commands in the application.
     * If the input message does not match any known command patterns, the method throws a DukeException with a message
     * prompting the user to provide a clearer instruction.
     *
     * @param message The user input message to be parsed.
     * @return The Command corresponding to the user's input message.
     * @throws DukeException If the input message does not match any known command patterns, indicating that the
     *                       user's instruction is unclear or invalid within the context of the application.
     */
    public static Command parse(String message) throws DukeException {
        if (message.equals("bye")) {
            return Command.BYE;
        } else if (message.equals("yap")) {
            return Command.YAP;
        } else if (message.startsWith("mark ")) {
            return Command.MARK;
        } else if (message.startsWith("unmark ")) {
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
        } else {
            throw new DukeException("What's YAPpening??!! Please yap your instruction more clearly");
        }
    }

}
