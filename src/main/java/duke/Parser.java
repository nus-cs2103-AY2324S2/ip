package duke;

import java.util.HashMap;
import java.util.Scanner;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CompleteCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

/**
 * Used to parse user commands.
 */
public class Parser {
    private static final String DESCRIPTION_IDENTIFIER = "description";

    private enum CommandType {
        Todo,
        Deadline,
        Event,
        Mark,
        Unmark,
        Delete,
        Find,
        List,
        Bye;

        private static CommandType parse(String command) throws InvalidCommandType {
            switch (command) {
            case "todo":
                return Todo;
            case "deadline":
                return Deadline;
            case "event":
                return Event;
            case "mark":
                return Mark;
            case "unmark":
                return Unmark;
            case "delete":
                return Delete;
            case "find":
                return Find;
            case "list":
                return List;
            case "bye":
                return Bye;
            default:
                throw new InvalidCommandType(command);
            }
        }
    }

    /**
     * Parse one user command.
     * The user command is parsed from a string into a subclass of {@link Command} which can be then executed.
     *
     * @throws InvalidCommandType when the given command (first word) is unknown
     * @throws InvalidCommandData when the inputs of the command aren't as expected
     */
    public static Command parse(String commandText) throws InvalidCommandType, InvalidCommandData {
        assert commandText != null : "Command to be parsed must not be null";

        Scanner scanner = new Scanner(commandText);
        var type = CommandType.parse(scanner.next());

        switch (type) {
        case Bye:
            return new ExitCommand();
        case List:
            return new ListCommand();
        default:
            // continue to other commands
        }

        String parameter = getParameter(scanner);

        switch (type) {
        case Todo:
            return new AddCommand(AddCommand.Type.Todo, parseComponents(parameter));
        case Deadline:
            return new AddCommand(AddCommand.Type.Deadline, parseComponents(parameter));
        case Event:
            return new AddCommand(AddCommand.Type.Event, parseComponents(parameter));
        case Mark:
            return new CompleteCommand(parseIndex(parameter), true);
        case Unmark:
            return new CompleteCommand(parseIndex(parameter), false);
        case Delete:
            return new DeleteCommand(parseIndex(parameter));
        case Find:
            return new FindCommand(parameter);
        default:
            throw new IllegalStateException("Unexpected type: " + type);
        }
    }

    private static String getParameter(Scanner scanner) throws InvalidCommandData {
        if (!scanner.hasNextLine()) {
            throw new InvalidCommandData();
        }
        return scanner.nextLine().trim();
    }

    private static int parseIndex(String input) {
        return Integer.parseInt(input.trim()) - 1;
    }

    private static HashMap<String, String> parseComponents(String data) throws InvalidCommandData {
        assert data != null : "data must not be null";

        HashMap<String, StringBuilder> componentBuilders = new HashMap<>();

        String key = DESCRIPTION_IDENTIFIER;
        String[] words = data.split(" +");
        for (String word : words) {
            if (word.startsWith("/")) {
                validateBuilderNotNull(componentBuilders, key);

                key = word;
                continue;
            }
            componentBuilders
                    .compute(key, (k, v) -> (v == null) ? new StringBuilder(word) : v.append(" ").append(word));
        }

        HashMap<String, String> components = new HashMap<>();
        componentBuilders.forEach((k, v) -> components.put(k, v.toString()));
        return components;
    }

    private static void validateBuilderNotNull(
            HashMap<String, StringBuilder> componentBuilders, String key) throws InvalidCommandData {
        boolean isPreviousBuilderNull = componentBuilders.get(key) == null;
        if (isPreviousBuilderNull) {
            throw new InvalidCommandData(key);
        }
    }

    /**
     * Exception when the command is unknown.
     */
    public static class InvalidCommandType extends Exception {
        private final String command;

        /**
         * Creates an exception about an unknown command string.
         *
         * @param command The name of the unknown command
         */
        public InvalidCommandType(String command) {
            super("Command \"" + command + "\" is invalid or not yet implemented.");
            this.command = command;
        }

        public String getCommand() {
            return command;
        }
    }

    /**
     * Exception when the data given to a particular
     * command doesn't match what is expected. The problem
     * can either be that no parameters are given, or that
     * a parameter is missing.
     */
    public static class InvalidCommandData extends Exception {
        public InvalidCommandData() {
            super("Parameters to command not given.");
        }

        public InvalidCommandData(String key) {
            super("No value given to " + key);
        }
    }
}
