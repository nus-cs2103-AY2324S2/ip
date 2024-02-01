package Duke;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserInput() {
        return scanner.nextLine();
    }

    public static int parseTaskIndex(String userInput) throws DukeException {
        try {
            // Assuming the input is in the format "mark 2" or "unmark 2"
            String indexString = userInput.split(" ", 2)[1].trim();
            return Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task index :(");
        }
    }
    public static boolean isExitCommand(String userInput) {
        List<String> exitCommands = Arrays.asList("bye", "exit", "quit"); // Add more exit commands if needed
        return exitCommands.contains(userInput.toLowerCase());
    }

    public static Command parseCommand(String userInput) throws DukeException {
        String[] parts = userInput.split(" ", 2);
        String commandType = parts[0].toLowerCase();

        switch (commandType) {
            case "bye":
            case "exit":
            case "quit":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                int markIndex = parseTaskIndex(userInput);
                return new MarkCommand(markIndex);
            case "unmark":
                int unmarkIndex = parseTaskIndex(userInput);
                return new UnmarkCommand(unmarkIndex);
            case "delete":
                int deleteIndex = parseTaskIndex(userInput);
                return new DeleteCommand(deleteIndex);
            case "find":
                String keyword = parts.length > 1 ? parts[1].trim() : "";
                return new FindCommand(keyword);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(userInput);
            default:
                throw new DukeException("I'm sorry, but I don't understand that command :(");
        }
    }

}