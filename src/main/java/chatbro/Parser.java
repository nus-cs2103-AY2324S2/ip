package chatbro;

import java.util.Scanner;
/**
 * Parser class parses user input and executes commands.
 */
public class Parser {

    /**
     * Parses the user input and executes the appropriate command.
     */
    public static String parseCommand(String input) {
        String command = input.split(" ")[0].toLowerCase();
        switch (command) {
        case "bye":
            return Command.BYE.execute(input);
        case "list":
            return Command.LIST.execute(input);
        case "mark":
            return Command.MARK.execute(input);
        case "unmark":
            return Command.UNMARK.execute(input);
        case "delete":
            return Command.DELETE.execute(input);
        case "todo":
            return Command.ADD_TODO.execute(input);
        case "deadline":
            return Command.ADD_DEADLINE.execute(input);
        case "event":
            return Command.ADD_EVENT.execute(input);
        case "help":
            return Command.HELP.execute(input);
        case "find":
            return Command.FIND.execute(input);
        default:
            return "Sorry bro, I don't understand that command.";
        }
    }
}
