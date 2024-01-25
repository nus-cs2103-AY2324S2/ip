import java.util.List;

public class CommandParser {
    public static Command parseInput(String userInput) {
        String[] parts = userInput.split(" ", 2);
        String command = parts[0].toLowerCase();
        List<String> arguments = parts.length > 1 ? List.of(parts[1].split("\\s+")) : List.of();

        String taskDescription = extractTaskDescription(arguments);
        String deadline = "Error: No deadline set.";
        String start = "Error: No start time set.";
        String end = "Error: No end time set.";

        // Get Deadline
        if (arguments.contains("/by")) {
            int index = arguments.indexOf("/by");
            if (index < arguments.size() - 1) {
                deadline = String.join(" ", arguments.subList(index + 1, arguments.size()));
            }
        }

        // Get From / To values
        if (arguments.contains("/from") && arguments.contains("/to")) {
            int fromIndex = arguments.indexOf("/from");
            int toIndex = arguments.indexOf("/to");
            if (fromIndex < toIndex && toIndex < arguments.size() - 1) {
                start = String.join(" ", arguments.subList(fromIndex + 1, toIndex));
                end = String.join(" ", arguments.subList(toIndex + 1, arguments.size()));
            }
        }


        switch (command) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(arguments);
            case "bye":
                return new ByeCommand();
            case "unmark":
                return new UnmarkCommand(arguments);
            case "todo":
                return new AddCommand(new ToDo(taskDescription));
            case "deadline":
                return new AddCommand(new Deadline(taskDescription, deadline));
            case "event":
                return new AddCommand(new Event(taskDescription, start, end));

            default:
                return new UnknownCommand();
        }
    }

    private static String extractTaskDescription(List<String> arguments) {
        int keywordIndex = findKeywordIndex(arguments, "/by", "/from", "/to");
        if (keywordIndex != -1) {
            return String.join(" ", arguments.subList(0, keywordIndex));
        }
        return String.join(" ", arguments);
    }

    private static int findKeywordIndex(List<String> arguments, String... keywords) {
        for (String keyword : keywords) {
            if (arguments.contains(keyword)) {
                return arguments.indexOf(keyword);
            }
        }
        return -1;
    }
}
