package duke;

import duke.command.*;
import duke.task.*;

/**
 * Parser class responsible for interpreting user input and translating it
 * into commands that can be executed by the Duke application.
 */
public class Parser {

    private String userInput;

    /**
     * Constructs a Parser instance with a given user input string.
     *
     * @param userInput The string input by the user to be parsed.
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     * This method interprets the user input and translates it into specific
     * commands understood by the Duke application, such as adding or deleting tasks.
     *
     * @param userInput The user input string to be parsed.
     * @return Command object representing the action to be taken.
     * @throws JamieException If the user input cannot be understood or is invalid.
     */
    public static Command parse(String userInput) throws JamieException {
        String[] words = userInput.split(" ", 2);
        if (words.length < 1) {
            throw new JamieException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        String command = words[0].toLowerCase();
        String details = words.length > 1 ? words[1] : "";

        switch (command) {
            case "bye":
                return new ExitTaskCommand();

            case "list":
                return new ListTasksCommand();

            case "done":
                return new CompleteTaskCommand(Integer.parseInt(details));

            case "delete":
                return new DeleteTaskCommand(Integer.parseInt(details));

            case "todo":
                if (details.trim().isEmpty()) {
                    throw new JamieException("OOPS!!! The description of a todo cannot be empty.");
                }
                return new AddTaskCommand(new ToDo(details));

            case "deadline":
                String[] deadlineDetails = details.split(" /by ");
                if (deadlineDetails.length != 2
                        || deadlineDetails[0].trim().isEmpty() || deadlineDetails[1].trim().isEmpty()) {
                    throw new JamieException("OOPS!!! Invalid format for deadline. "
                            + "Please use: deadline <description> /by <date>");
                }
                return new AddTaskCommand(new Deadline(deadlineDetails[0], deadlineDetails[1]));

            case "event":
                String[] eventDetails = details.split(" /from ");
                if (eventDetails.length != 2) {
                    throw new JamieException("OOPS!!! Invalid format for event. "
                            + "Please use: event <description> /from <start> /to <end>");
                }
                String[] eventTiming = eventDetails[1].split(" /to ");
                if (eventTiming.length != 2 || eventTiming[0].trim().isEmpty() || eventTiming[1].trim().isEmpty()) {
                    throw new JamieException("OOPS!!! Invalid format for event timing. "
                            + "Please use: event <description> /from <start> /to <end>");
                }
                return new AddTaskCommand(new Event(eventDetails[0], eventTiming[0], eventTiming[1]));

            case "find":
                return new FindTaskCommand(details);

            default:
                throw new JamieException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}