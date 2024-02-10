package ChatBro;

import java.util.Scanner;
/**
 * ChatBro.Parser class parses user input and executes commands.
 */
public class Parser {
    private static Scanner sc;

    public Parser() {
        sc = new Scanner(System.in);
    }
    public static String readInput() {
        return sc.nextLine();
    }
    public static void parseCommand() {
        String input = readInput();
        String command = input.split(" ")[0].toLowerCase();
        switch (command) {
        case "bye":
            Command.BYE.execute(input);
            break;
        case "list":
            Command.LIST.execute(input);
            break;
        case "mark":
            Command.MARK.execute(input);
            break;
        case "unmark":
            Command.UNMARK.execute(input);
            break;
        case "delete":
            Command.DELETE.execute(input);
            break;
        case "todo":
            Command.ADD_TODO.execute(input);
            break;
        case "deadline":
            Command.ADD_DEADLINE.execute(input);
            break;
        case "event":
            Command.ADD_EVENT.execute(input);
            break;
        case "help":
            Command.HELP.execute(input);
            break;
        case "find":
            Command.FIND.execute(input);
            break;
        default:
            Ui.printMessage("Sorry bro, I don't understand that command.");
        }
    }
    public static void closeScanner() {
        sc.close();
    }
}
