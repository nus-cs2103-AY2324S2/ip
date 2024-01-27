package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CompleteCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

import java.util.HashMap;
import java.util.Scanner;

public class Parser {
    public static Command parse(String commandText) throws InvalidCommand {
        Scanner scanner = new Scanner(commandText);
        String type = scanner.next();

        Command command;
        switch (type) {
        case "bye":
            command = new ExitCommand();
            break;
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
            throw new InvalidCommand(type);
        }
        return command;
    }

    private static int parseIndex(String input) {
        return Integer.parseInt(input.trim()) - 1;
    }

    private static HashMap<String, String> parseComponents(String data) {
        HashMap<String, StringBuilder> builders = new HashMap<>();

        String key = "DESCRIPTION";
        String[] words = data.split(" +");
        for (String word : words) {
            if (word.startsWith("/")) {
                key = word;
            } else {
                builders.compute(key, (k, v) -> (v == null) ? new StringBuilder(word) : v.append(" ").append(word));
            }
        }

        HashMap<String, String> components = new HashMap<>();
        builders.forEach((k, v) -> components.put(k, v.toString()));
        return components;
    }

    public static class InvalidCommand extends Exception {
        private final String command;

        public InvalidCommand(String command) {
            super("Command \"" + command + "\" is invalid or not yet implemented.");
            this.command = command;
        }

        public String getCommand() {
            return command;
        }
    }
}
