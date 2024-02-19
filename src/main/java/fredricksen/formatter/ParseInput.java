package fredricksen.formatter;

import fredricksen.commands.ByeCommand;
import fredricksen.commands.Command;
import fredricksen.commands.DeadlineCommand;
import fredricksen.commands.DeleteCommand;
import fredricksen.commands.EventCommand;
import fredricksen.commands.FindCommand;
import fredricksen.commands.HelpCommand;
import fredricksen.commands.ListCommand;
import fredricksen.commands.MarkCommand;
import fredricksen.commands.TodoCommand;
import fredricksen.commands.UnknownCommand;
import fredricksen.commands.UnmarkCommand;
import fredricksen.tasks.TaskList;

/**
 * Represents a ParseInput class that is used to parse user input command.
 * The ParseInput object can call the getCommand function which will be
 * responsible for sending out Command executable to handle the different user input commands.
 */
public class ParseInput {
    public ParseInput() {}

    /**
     * A function to check if the first element in the commands Array is equals to
     * either "list", "help" or "bye".
     *
     * @param commands An Array of each word from the user input command.
     * @return A boolean if the first element of commands is equals to "list", "help" or "bye".
     */
    public boolean isInputEquals(String[] commands) {
        return commands[0].equals("list") || commands[0].equals("help") || commands[0].equals("bye");
    }

    /**
     * A function to return a Command executable based on the first String
     * of the user input command.
     *
     * @param userInput The user input command
     * @param tasks The TaskList that stores the current list of Task type task.
     * @return A Command executable that based on the first string in the user input command.
     */
    public Command getCommand(String userInput, TaskList tasks) {
        String[] inputCommands = userInput.split(" ");

        if (inputCommands.length <= 1 && !isInputEquals(inputCommands)) {
            return new UnknownCommand();
        }

        switch (inputCommands[0]) {
        case "list":
            return new ListCommand(tasks);
        case "todo":
            return new TodoCommand(userInput, tasks);
        case "event":
            return new EventCommand(userInput, tasks);
        case "deadline":
            return new DeadlineCommand(userInput, tasks);
        case "mark":
            return new MarkCommand(inputCommands, tasks);
        case "unmark":
            return new UnmarkCommand(inputCommands, tasks);
        case "delete":
            return new DeleteCommand(inputCommands, tasks);
        case "find":
            return new FindCommand(userInput, inputCommands, tasks);
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        default:
            return new UnknownCommand();
        }
    }
}
