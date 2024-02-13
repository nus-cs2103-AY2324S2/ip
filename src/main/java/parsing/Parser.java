package parsing;

import exceptions.YpxmmException;
import java.util.ArrayList;

/**
 * Class for parsing user input into command arguments.
 */
public class Parser {

    /**
     * Parses the input string into an ArrayList of command arguments.
     *
     * @param input the input string
     * @return an ArrayList containing the parsed command arguments
     * @throws YpxmmException if an error occurs during parsing
     */
    public static ArrayList<String> parse(String input) throws YpxmmException {
        ArrayList<String> parsedResult = new ArrayList<>();
        String[] splitInput = input.split(" ");
        String command = splitInput[0];
        switch (command) {
        case "bye":
        case "list":
        case "getcommands":
            parsedResult.add(command);
            return parsedResult;
        case "find":
            try {
                String[] info = input.split("find ");
                parsedResult.add(command);
                parsedResult.add(info[1]);
                return parsedResult;
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("What u want me to find??");
            }
        case "mark":
        case "unmark":
        case "delete":
            try {
                int index = Integer.parseInt(splitInput[1]);
                parsedResult.add(command);
                parsedResult.add(splitInput[1]);
                return parsedResult;
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Brother, key in " + command + " <space> then a valid number");
            } catch (NumberFormatException n) {
                throw new YpxmmException("You tell me now what task am I supposed to "
                        + command + " if you don't provide me with a number?");
            }
        case "todo":
            try {
                parsedResult.add(command);
                String[] info = input.split("todo ");
                if (info[1].isBlank()) {
                    throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
                }
                parsedResult.add(info[1].trim());
                return parsedResult;
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("You trying to test my patience ah? Type \"get commands\" if"
                        + "u blur and dunno how to use me properly.");
            }
        case "deadline":
            try {
                parsedResult.add(command);
                String[] info = input.split("/");
                if (info[0].split("deadline ")[1].isBlank() || info[1].isBlank()) {
                    throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
                }
                parsedResult.add(info[0].substring(9).trim());
                parsedResult.add(info[1].trim());
                return parsedResult;
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("You trying to test my patience ah? Check that u got key"
                        + "in the deadline lehhh\n Type \"get commands\" if u blur and dunno how"
                        + "to use me properly.");
            }
        case "event":
            try {
                parsedResult.add(command);
                String[] info = input.split("/");
                if (info[0].split("event ")[1].isBlank() || info[1].isBlank() || info[2].isBlank()) {
                    throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
                }
                parsedResult.add(info[0].substring(6).trim());
                parsedResult.add(info[1].trim());
                parsedResult.add(info[2].trim());
                return parsedResult;
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh brother last warning ah. Check that u got key in the start and end time\n"
                        + "Type \"get commands\" if u blur and dunno how to use me properly.");
            }
        default:
            throw new YpxmmException("Sorry bro, idk what that means. You try type in \"getcommands\" then see"
                    + "if got what u want.");
        }
    }
}
