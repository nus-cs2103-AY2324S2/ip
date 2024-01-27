package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CompleteCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

/**
 * Used to parse user commands.
 */
public class Parser {
    /**
     * Parse one user command.
     * The user command is parsed from a string into a subclass of {@link Command} which can be then executed.
     * @throws InvalidCommandType when the given command (first word) is unknown
     * @throws InvalidCommandData when the inputs of the command aren't as expected
     */
    public static Command parse(String commandText) throws InvalidCommandType, InvalidCommandData {
        Scanner scanner = new Scanner(commandText);
        String type = scanner.next();

        if (Objects.equals(type, "bye")) {
            return new ExitCommand();
        }

        if (!scanner.hasNextLine()) {
            throw new InvalidCommandData();
        }

        Command command;
        switch (type) {
        case "todo":
            command = new AddCommand(AddCommand.Type.Todo, parseComponents(scanner.nextLine()));
            break;
        case "deadline":
            command = new AddCommand(AddCommand.Type.Deadline, parseComponents(scanner.nextLine()));
            break;
        case "event":
            command = new AddCommand(AddCommand.Type.Event, parseComponents(scanner.nextLine()));
            break;
        case "mark":
            command = new CompleteCommand(parseIndex(scanner.nextLine()), true);
            break;
        case "unmark":
            command = new CompleteCommand(parseIndex(scanner.nextLine()), false);
            break;
        case "delete":
            command = new DeleteCommand(parseIndex(scanner.nextLine()));
            break;
        case "list":
            command = new ListCommand();
            break;
        default:
            throw new InvalidCommandType(type);
        }
        return command;
    }

    private static int parseIndex(String input) {
        return Integer.parseInt(input.trim()) - 1;
    }

    private static HashMap<String, String> parseComponents(String data) throws InvalidCommandData {
        HashMap<String, StringBuilder> builders = new HashMap<>();

        String key = "DESCRIPTION";
        String[] words = data.split(" +");
        for (String word : words) {
            if (word.startsWith("/")) {
                // Check if the previous key had any data given to it
                if (builders.get(key).length() == 0) {
                    throw new InvalidCommandData(key);
                }

                key = word;
            } else {
                builders.compute(key, (k, v) -> (v == null) ? new StringBuilder(word) : v.append(" ").append(word));
            }
        }

        HashMap<String, String> components = new HashMap<>();
        builders.forEach((k, v) -> components.put(k, v.toString()));
        return components;
    }

    /**
     *
     */
    public static class InvalidCommandType extends Exception {
        private final String command;

        public InvalidCommandType(String command) {
            super("Command \"" + command + "\" is invalid or not yet implemented.");
            this.command = command;
        }

        public String getCommand() {
            return command;
        }
    }
    
    public static class InvalidCommandData extends Exception {
        public InvalidCommandData() {
            super("Parameters to command not given.");
        }

        public InvalidCommandData(String key) {
            super("No value given to " + key);
        }
    }
}
