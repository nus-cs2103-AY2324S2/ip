package parsing;

import java.util.ArrayList;

import exceptions.YpxmmException;

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
        parsedResult.add(command);
        switch (command) {
        case "bye":
        case "list":
        case "getcommands":
            return miscCommand(parsedResult);
        case "find":
            return findCommand(parsedResult, input);
        case "mark":
        case "unmark":
        case "delete":
            return markUnmarkDeleteCommand(parsedResult, splitInput, command);
        case "todo":
            return todoCommand(parsedResult, input);
        case "deadline":
            return deadlineCommand(parsedResult, input);
        case "event":
            return eventCommand(parsedResult, input);
        case "prioritise":
            return prioritiseCommand(parsedResult, splitInput, command);
        default:
            return unknownCommand();
        }
    }

    /**
     * Handles miscellaneous commands.
     *
     * @param toParse the ArrayList of strings to be returned
     * @return the same ArrayList of strings provided as input
     */
    private static ArrayList<String> miscCommand(ArrayList<String> toParse) {
        return toParse;
    }

    /**
     * Parses the input for the "find" command.
     *
     * @param toParse the ArrayList to which the parsed result will be added
     * @param input the input string
     * @return an ArrayList containing the parsed command arguments
     * @throws YpxmmException if an error occurs during parsing
     */
    private static ArrayList<String> findCommand(ArrayList<String> toParse,
                                                 String input) throws YpxmmException {
        try {
            String[] info = input.split("find ");
            toParse.add(info[1]);
            return toParse;
        } catch (IndexOutOfBoundsException e) {
            throw new YpxmmException("What u want me to find??");
        }
    }

    /**
     * Parses the input for the "mark", "unmark", and "delete" commands.
     *
     * @param toParse the ArrayList to which the parsed result will be added
     * @param splitInput the input string split into an array
     * @param command the command string
     * @return an ArrayList containing the parsed command arguments
     * @throws YpxmmException if an error occurs during parsing
     */
    private static ArrayList<String> markUnmarkDeleteCommand(ArrayList<String> toParse,
                                                             String[] splitInput,
                                                             String command) throws YpxmmException {
        try {
            //way to check if splitInput[1] is an integer. throws exception if it is not
            int index = Integer.parseInt(splitInput[1]);
            toParse.add(splitInput[1]);
            return toParse;
        } catch (IndexOutOfBoundsException e) {
            throw new YpxmmException("Brother, key in " + command + " <space> then a valid number");
        } catch (NumberFormatException n) {
            throw new YpxmmException("You tell me now what task am I supposed to "
                    + command + " if you don't provide me with a number?");
        }
    }

    /**
     * Parses the input for the "prioritise" command.
     *
     * @param toParse the ArrayList to which the parsed result will be added
     * @param splitInput the input string split into an array
     * @param command the command string
     * @return an ArrayList containing the parsed command arguments
     * @throws YpxmmException if an error occurs during parsing
     */
    private static ArrayList<String> prioritiseCommand(ArrayList<String> toParse,
                                                             String[] splitInput,
                                                             String command) throws YpxmmException {
        try {
            //way to check if splitInput[1] is an integer. throws exception if it is not
            int index = Integer.parseInt(splitInput[1]);
            toParse.add(splitInput[1]);
            toParse.add(splitInput[2]);
            return toParse;
        } catch (IndexOutOfBoundsException e) {
            throw new YpxmmException("Brother, key in " + command + " <space>, a valid number <space>"
                    + " then your priority level (high, medium or low).");
        } catch (NumberFormatException n) {
            throw new YpxmmException("You tell me now what task am I supposed to "
                    + command + " if you don't provide me with a number?");
        }
    }

    /**
     * Parses the input for the "todo" command.
     *
     * @param toParse the ArrayList to which the parsed result will be added
     * @param input the input string
     * @return an ArrayList containing the parsed command arguments
     * @throws YpxmmException if an error occurs during parsing
     */
    private static ArrayList<String> todoCommand(ArrayList<String> toParse,
                                                 String input) throws YpxmmException {
        try {
            String[] info = input.split("todo ");
            if (info[1].isBlank()) {
                throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
            }
            toParse.add(info[1].trim());
            return toParse;
        } catch (IndexOutOfBoundsException e) {
            throw new YpxmmException("You trying to test my patience ah? Type \"get commands\" if"
                    + "u blur and dunno how to use me properly.");
        }
    }

    /**
     * Parses the input for the "deadline" command.
     *
     * @param toParse the ArrayList to which the parsed result will be added
     * @param input the input string
     * @return an ArrayList containing the parsed command arguments
     * @throws YpxmmException if an error occurs during parsing
     */
    private static ArrayList<String> deadlineCommand(ArrayList<String> toParse,
                                                     String input) throws YpxmmException {
        try {
            String[] info = input.split("/");
            if (info[0].split("deadline ")[1].isBlank() || info[1].isBlank()) {
                throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
            }
            toParse.add(info[0].substring(9).trim());
            toParse.add(info[1].trim());
            return toParse;
        } catch (IndexOutOfBoundsException e) {
            throw new YpxmmException("You trying to test my patience ah? Check that u got key"
                    + "in the deadline lehhh\n Type \"get commands\" if u blur and dunno how"
                    + "to use me properly.");
        }
    }

    /**
     * Parses the input for the "event" command.
     *
     * @param toParse the ArrayList to which the parsed result will be added
     * @param input the input string
     * @return an ArrayList containing the parsed command arguments
     * @throws YpxmmException if an error occurs during parsing
     */
    private static ArrayList<String> eventCommand(ArrayList<String> toParse,
                                                     String input) throws YpxmmException {
        try {
            String[] info = input.split("/");
            if (info[0].split("event ")[1].isBlank() || info[1].isBlank() || info[2].isBlank()) {
                throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
            }
            toParse.add(info[0].substring(6).trim());
            toParse.add(info[1].trim());
            toParse.add(info[2].trim());
            return toParse;
        } catch (IndexOutOfBoundsException e) {
            throw new YpxmmException("Eh brother last warning ah. Check that u got key in the start and end time\n"
                    + "Type \"get commands\" if u blur and dunno how to use me properly.");
        }
    }

    /**
     * Throws a YpxmmException for unknown commands.
     *
     * @return The method does not actually return an ArrayList, but is declared as such
     *      as the method that calls this returns ArrayList
     * @throws YpxmmException always thrown to indicate an unknown command
     */
    private static ArrayList<String> unknownCommand() throws YpxmmException {
        throw new YpxmmException("Sorry bro, idk what that means. You try type in \"getcommands\" then see"
                + " if got what u want.");
    }
}
