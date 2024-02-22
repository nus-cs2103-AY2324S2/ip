package alpa.commands;

import alpa.exceptions.AlpaException;

/**
 * The Parser class is responsible for parsing user input and returning the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param userInput the user input to be parsed
     * @return the Command object corresponding to the user input
     * @throws AlpaException if there is an error during parsing
     */
    public static Command parse(String userInput) throws AlpaException {
        // Split user input into command word and task information (if any).
        String[] words = userInput.trim().split("\\s+", 2);
        String commandWord = words[0];
        String taskInfo = words.length > 1 ? words[1] : "";

        CommandType commandType = CommandType.fromString(commandWord);

        switch (commandType) {
        case HELP:
            // Returns a command to display help information.
            return new HelpCommand();
        case BYE:
            // Returns a command to terminate the application.
            return new ByeCommand();
        case LIST:
            // Returns a command to list all tasks.
            return new ListCommand();
        case FIND:
            // Returns a command to find tasks matching a keyword, if specified.
            if (taskInfo.isEmpty()) {
                throw new AlpaException("I don't know what you're trying to find, human!");
            }
            return new FindCommand(taskInfo);
        case MARK:
            // Returns a command to mark a task as done, if a valid task number is provided.
            if (taskInfo.isEmpty()) {
                throw new AlpaException("I don't know what task you're referring to, human!");
            }
            return new MarkCommand(taskInfo);
        case UNMARK:
            // Returns a command to mark a task as not done, if a valid task number is provided.
            if (taskInfo.isEmpty()) {
                throw new AlpaException("I don't know what task you're referring to, human!");
            }
            return new UnmarkCommand(taskInfo);
        case TODO:
            // Returns a command to add a todo task, if a description is provided.
            if (taskInfo.isEmpty()) {
                throw new AlpaException("You need to tell me what your todo is, human!");
            }
            return new TodoCommand(taskInfo);
        case DEADLINE:
            // Returns a command to add a deadline task, if a description and deadline are provided.
            if (taskInfo.isEmpty()) {
                throw new AlpaException("What and when is your deadline, human? I need more information!");
            }
            return new DeadlineCommand(taskInfo);
        case EVENT:
            // Returns a command to add an event task, if a description, start time, and end time are provided.
            if (taskInfo.isEmpty()) {
                throw new AlpaException("What and when is your event, human? I need more information!");
            }
            return new EventCommand(taskInfo);
        case DELETE:
            // Returns a command to delete a task, if a valid task number is provided.
            if (taskInfo.isEmpty()) {
                throw new AlpaException("I don't know what you're trying to delete, human!");
            }
            return new DeleteCommand(taskInfo);
        case INVALID:
        default:
            // Returns a command indicating the input was not recognized as a valid command.
            return new InvalidCommand("Sorry human, but I don't know what that means :-(");
        }
    }
}
