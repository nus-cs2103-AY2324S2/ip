public class Parser {
    public static void processCommand(String userInput) throws DukeException {
        Command command = parseCommand(userInput);
        if (command == null) {
            throw new DukeException(" | I'm sorry, but I don't know what that means :-(");
        }
        switch (command) {
            case LIST:
                Duke.listTasks();
                break;
            case TODO:
                Duke.addTask(parseTodoTask(userInput));
                break;
            case DEADLINE:
                Duke.addTask(parseDeadlineTask(userInput));
                break;
            case EVENT:
                Duke.addTask(parseEventTask(userInput));
                break;
            case MARK:
                Duke.markTaskAsDone(parseTaskIndex(userInput));
                break;
            case UNMARK:
                Duke.unmarkTask(parseTaskIndex(userInput));
                break;
            case DELETE:
                Duke.deleteTask(parseTaskIndex(userInput));
                break;
            case BYE:
                break;
        }
    }

    private static Command parseCommand(String userInput) {
        String command = userInput.split("\\s+")[0].toLowerCase();
        switch (command) {
            case "list":
                return Command.LIST;
            case "todo":
                return Command.TODO;
            case "deadline":
                return Command.DEADLINE;
            case "event":
                return Command.EVENT;
            case "mark":
                return Command.MARK;
            case "unmark":
                return Command.UNMARK;
            case "delete":
                return Command.DELETE;
            case "bye":
                return Command.BYE;
            default:
                return null;
        }
    }

    private static Task parseTodoTask(String userInput) throws DukeException {
        if (userInput.length() <= 5) {
            throw new DukeException(" | The description of a todo cannot be empty.");
        }
        String description = userInput.substring(5).trim();
        return new Task(description);
    }


    private static Task parseDeadlineTask(String userInput) throws DukeException {
        String[] parts = userInput.substring(9).split("/by");
        if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException(" | Invalid deadline format. Please include '/by' followed by the deadline.");
        }
        return new Task(parts[0].trim() + " (by: " + parts[1].trim() + ")");
    }

    private static Task parseEventTask(String userInput) throws DukeException {
        String[] parts = userInput.substring(6).split("/from");
        if (parts.length != 2) {
            throw new DukeException(" | Invalid event format. Please include '/from' followed by start time and '/to' followed by end time.");
        }
        String description = parts[0].trim();
        String[] dateAndTime = parts[1].split("/to");
        if (dateAndTime.length != 2 || dateAndTime[0].trim().isEmpty() || dateAndTime[1].trim().isEmpty()) {
            throw new DukeException(" | Invalid event format. Please include '/from' followed by start time and '/to' followed by end time.");
        }
        return new Task(description + " (from: " + dateAndTime[0].trim() + " to: " + dateAndTime[1].trim() + ")");
    }

    private static int parseTaskIndex(String userInput) throws DukeException {
        try {
            return Integer.parseInt(userInput.split("\\s+")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(" | Invalid task number. Please provide a valid task number.");
        }
    }
}
