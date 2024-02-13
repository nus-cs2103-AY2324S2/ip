package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.IncorrectCommand;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param input full user input string
     * @return the command based on the user input
     */
    public Command parse(String input) {
        String[] words = input.split("\\s+");
        String command = words[0];
        switch (command) {
        case "find":
        case "todo":
        case "deadline":
        case "event":
        case "list":
        case "mark":
        case "unmark":
        case "delete":
            return new Command(command, input);
        case "bye":
            System.out.println(command);
            return new ExitCommand();
        default:
            return new IncorrectCommand();
        }
    }


}
