//converts the input string from user to a Command object
public class Parser {
    public static Command parse(String input) {
        String clean = input.trim().toLowerCase();
        String[] tokens = input.trim().split("\\s+", 2);

        if (tokens.length == 0) {
            return new Command.InvalidCommand("Please enter something!");
        } else if (clean.equals("bye")) {
            return new Command.ByeCommand();
        } else if (clean.equals("list")) {
            return new Command.ListCommand();
        }

        String command = tokens[0].toLowerCase();

        try {
            switch (command) {
                case "mark":
                    return Parser.parseMarkCommand(tokens[1]);
                case "unmark":
                    return parseUnmarkCommand(tokens[1]);
                case "todo":
                    return parseTodoCommand(tokens[1]);
                case "deadline":
                    return parseDeadlineCommand(tokens[1]);
                case "event":
                    return parseEventCommand(tokens[1]);
                default:
                    throw new DukeException.InvalidCommandException("I dont understand you!" +
                                        " Please be dont scold me and be gentle with me! Try again!");
            }
        } catch (DukeException e) {
            return new Command.InvalidCommand(e.getMessage());
        }
    }
    private static Command parseMarkCommand(String tokens) throws DukeException.IllegalParamException {
        try {
            int taskId = Integer.parseInt(tokens.trim());
            return new Command.MarkCommand(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException.IllegalParamException("I dont know which task you are trying to mark");
        }
    }

    private static Command parseUnmarkCommand(String tokens) throws DukeException.IllegalParamException {
        try {
            int taskId = Integer.parseInt(tokens.trim());
            return new Command.UnmarkCommand(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException.IllegalParamException("I dont know which task you are trying to unmark");
        }
    }

    private static Command parseTodoCommand(String tokens) throws DukeException.MissingInfoException {
        if (tokens.trim().equals("")) {
            throw new DukeException.MissingInfoException("Bro u gotta describe the task!");
        }
        String taskName = tokens.trim();
        return new Command.ToDoCommand(taskName);
    }

    private static Command parseDeadlineCommand(String tokens) throws DukeException.MissingInfoException,
                                                                      DukeException.InvalidCommandException {
        String[] parts = tokens.split("/by");

        if (parts.length != 2) {
            throw new DukeException.InvalidCommandException("Looks like you are missing '/by'! Use '/by' to tell me the deadline!");
        }

        String task = parts[0].trim();
        String deadline = parts[1].trim();

        if (task.isEmpty()) {
            throw new DukeException.MissingInfoException("You have to tell me the description too! Or I cant remember it!");
        } else if (deadline.isEmpty()) {
            throw new DukeException.MissingInfoException("You need a deadline! Or you will never get to it!");
        }

        return new Command.DeadlineCommand(task, deadline);
    }

    private static Command parseEventCommand(String tokens) throws DukeException{
        String[] parts = tokens.split("/from|/to");

        if (parts.length != 3) {
            throw new DukeException.InvalidCommandException("Missing /from or /to! Use those to indicate times!");
        }

        String task = parts[0].trim();
        String startTime = parts[1].trim();
        String endTime = parts[2].trim();

        if (tokens.indexOf("/from") > tokens.indexOf("/to")) {
            String temp = startTime;
            startTime = endTime;
            endTime = temp;
        }

        if (task.isEmpty()) {
            throw new DukeException.MissingInfoException("Tasks needs a name! Or I cant remember it!");
        } else if (startTime.isEmpty()) {
            throw new DukeException.MissingInfoException("Please tell me when it starts!");
        } else if (endTime.isEmpty()) {
            throw new DukeException.MissingInfoException("Please tell me when it ends!");
        }
        return new Command.EventCommand(task, startTime, endTime);
    }
}
