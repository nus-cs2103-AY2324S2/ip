package Duke.phrase;

import Duke.Commands.*;
import Duke.Exception.CommandException;

/**
 * The {@code Phrase} class is a utility class that parses a given input phrase and returns the corresponding
 * {@link Command} object. It is designed to recognize specific keywords in the input and instantiate the
 * appropriate command class based on those keywords.
 */
public class phrase {

    /**
     * Parses the given input phrase and returns the corresponding command.
     *
     * @param input The input phrase to be parsed into a command.
     * @return The command corresponding to the parsed input phrase.
     * @throws CommandException If an error occurs during command parsing.
     */
    public static Command phraseCommand(String input) throws CommandException {
        assert input != null : "input should not be null";
        if (input.trim().isEmpty()) {
            throw new CommandException("It's all space! Please enter a valid command.");
        }
        String firstWord = input.split(" ")[0];
        int trimIndex = firstWord.length();
        String remainingWord = input.substring(trimIndex).trim();
        switch (firstWord) {
            case "todo":
                return new AddTodo(remainingWord);
            case "deadline":
                return new AddDeadline(remainingWord);
            case "event":
                return new AddEvent(remainingWord);
            case "list":
                return new ListActivity(remainingWord);
            case "mark":
                return new MarkActivity(remainingWord);
            case "unmark":
                return new UnmarkActivity(remainingWord);
            case "find":
                return new FindActivity(remainingWord);
            case "delete":
                return new DeleteActivity(remainingWord);
            case "bye":
                return new Terminate(remainingWord);
            default:
                return new Unknown("I'm sorry, but I don't know what that means");
        }
    }
}
