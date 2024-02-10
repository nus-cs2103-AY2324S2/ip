package bob;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String EXIT = "exit";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";

    public static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public static String[] extractParameters(String parametersString,
                                             String[] parameters) throws ParameterNotFoundException {
        // TODO: return a HashMap rather than a String[]
        int n = parameters.length;
        String[] result = new String[n + 1];

        String[] splitString = new String[] { parametersString };
        for (int i = n - 1; i >= 0; i--) {
            splitString = splitString[0].split(" /" + parameters[i] + ' ', 2);
            if (splitString.length == 1) {
                // This implies the last missing parameter will be displayed, rather than the first
                // "deadline /by 2019-10-11" will trigger parameter "by" not found
                // "event /from 2019-10-02 /to 2019-10-02" will trigger parameter "from" not found
                throw new ParameterNotFoundException(parameters[i]);
            }
            result[i + 1] = splitString[1];
        }

        result[0] = splitString[0];

        return result;
    }

    public static void processDeleteOrMarkCommands(String[] commandArgs) throws NumberFormatException,
            InvalidTaskIndexException, ArrayIndexOutOfBoundsException {
        int taskIndex = Integer.parseInt(commandArgs[1]) - 1;
        if (commandArgs[0].equals(Parser.DELETE)) {
            Bob.handleDelete(taskIndex);
        } else {
            Bob.handleMark(taskIndex, commandArgs[0].equals(Parser.MARK));
        }
    }

    public static void processAddCommands(String[] commandArgs) throws EmptyDescriptionException {
        if (commandArgs.length == 1) {
            throw new EmptyDescriptionException(commandArgs[0]);
        }

        // TODO: map each task type to a list of parameters
        // Undefined behaviour when there are multiple instances of the same parameter
        // e.g. event project meeting /from 2019-10-02 /to 2019-10-02 /from 2019-10-04 /to 2019-10-04
        try {
            String[] parameters;
            switch (commandArgs[0]) {
                case Parser.TODO:
                    parameters = Parser.extractParameters(commandArgs[1], new String[]{});
                    Bob.handleAdd(commandArgs[0], parameters);
                    break;
                case Parser.DEADLINE:
                    parameters = Parser.extractParameters(commandArgs[1], new String[]{ "by" });
                    Bob.handleAdd(commandArgs[0], parameters);
                    break;
                default:
                    parameters = Parser.extractParameters(commandArgs[1], new String[] { "from", "to" });
                    Bob.handleAdd(commandArgs[0], parameters);
            }
        } catch (ParameterNotFoundException e) {
            Ui.print(e.getMessage());
        } catch (DateTimeParseException e) {
            Ui.print(Ui.INVALID_DATE_FORMAT);
        }
    }

    public static boolean listen() {
        String command = SCANNER.nextLine();
        String[] commandArgs = command.split(" ", 2);

        if (commandArgs[0].equals(Parser.EXIT)) {
            Ui.print(Ui.EXIT);
            return false;
        }

        switch (commandArgs[0]) {
        case Parser.LIST:
            Bob.handleList();
            break;
        case Parser.DELETE:
            // Fallthrough
        case Parser.UNMARK:
            // Fallthrough
        case Parser.MARK:
            try {
                processDeleteOrMarkCommands(commandArgs);
            } catch (NumberFormatException e) {
                // The more "correct" way is to throw an InvalidTaskIndexException?
                Ui.print(String.format(Ui.INVALID_TASK_INDEX, commandArgs[1]));
            } catch (InvalidTaskIndexException e) {
                Ui.print(String.format(e.getMessage(), commandArgs[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                // TODO: processParameterisedCommands, which is any command other than exit and list
                Ui.print(String.format(Ui.EMPTY_DESCRIPTION, commandArgs[0]));
            }
            break;
        case Parser.TODO:
            // Fallthrough
        case Parser.DEADLINE:
            // Fallthrough
        case Parser.EVENT:
            try {
                processAddCommands(commandArgs);
            } catch (EmptyDescriptionException e) {
                Ui.print(e.getMessage());
            }
            break;
        default:
            try {
                throw new InvalidCommandException();
            } catch (InvalidCommandException e) {
                Ui.print(e.getMessage());
            }
        }

        return true;
    }
}
