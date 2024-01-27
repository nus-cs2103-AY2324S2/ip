public class Parser {
//    public static Command parseCommand(String userInput) {
//        String[] tokens = userInput.trim().split("\\s+", 2);
//        String commandType = tokens[0].toLowerCase();
//
//        switch (commandType) {
//            case "bye":
//                return new ExitCommand();
//            case "list":
//                return new ListCommand();
//            case "todo":
//                return parseTodoCommand(tokens);
//            case "deadline":
//                return parseDeadlineCommand(tokens);
//            case "event":
//                return parseEventCommand(tokens);
//            case "done":
//                return parseDoneCommand(tokens);
//            case "delete":
//                return parseDeleteCommand(tokens);
//            default:
//                return new InvalidCommand();
//        }
//    }
//
//    private static Command parseTodoCommand(String[] tokens) {
//        if (tokens.length < 2) {
//            return new InvalidCommand();
//        }
//        String description = tokens[1].trim();
//        return new AddTodoCommand(description);
//    }
//
//    private static Command parseDeadlineCommand(String[] tokens) {
//        if (tokens.length < 2) {
//            return new InvalidCommand();
//        }
//        String[] parts = tokens[1].split("/by");
//        if (parts.length < 2) {
//            return new InvalidCommand();
//        }
//        String description = parts[0].trim();
//        String by = parts[1].trim();
//        return new AddDeadlineCommand(description, by);
//    }
//
//    private static Command parseEventCommand(String[] tokens) {
//        if (tokens.length < 2) {
//            return new InvalidCommand();
//        }
//        String[] parts = tokens[1].split("/from");
//        if (parts.length < 2) {
//            return new InvalidCommand();
//        }
//        String description = parts[0].trim();
//        String[] fromTo = parts[1].split("/to");
//        if (fromTo.length < 2) {
//            return new InvalidCommand();
//        }
//        String from = fromTo[0].trim();
//        String to = fromTo[1].trim();
//        return new AddEventCommand(description, from, to);
//    }
//
//    private static Command parseDoneCommand(String[] tokens) {
//        if (tokens.length < 2) {
//            return new InvalidCommand();
//        }
//        int taskIndex;
//        try {
//            taskIndex = Integer.parseInt(tokens[1].trim()) - 1;
//        } catch (NumberFormatException e) {
//            return new InvalidCommand();
//        }
//        return new DoneCommand(taskIndex);
//    }
//
//    private static Command parseDeleteCommand(String[] tokens) {
//        if (tokens.length < 2) {
//            return new InvalidCommand();
//        }
//        int taskIndex;
//        try {
//            taskIndex = Integer.parseInt(tokens[1].trim()) - 1;
//        } catch (NumberFormatException e) {
//            return new InvalidCommand();
//        }
//        return new DeleteCommand(taskIndex);
//    }
}