import java.util.List;

public class CommandParser {
    public static Command parseInput(String userInput) {
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];
        List<String> arguments = parts.length > 1 ? List.of(parts[1].split("\\s+")) : List.of();

        switch (command) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(arguments);
            case "bye":
                return new ByeCommand();
            case "unmark":
                return new UnmarkCommand(arguments);

            default:
                return new AddCommand(List.of(userInput));
        }
    }

}
