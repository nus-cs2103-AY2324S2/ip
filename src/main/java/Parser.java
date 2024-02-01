import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//converts the input string from user to a Command object
public class Parser {
    /**
     * Parses user input and converts it to a command object.
     *
     * The command object can then be executed to perform the command.
     *
     * @param input user input as a string
     * @return a Command object
     * @throws DukeException
     * @see Command
     */
    public static Command parse(String input) throws DukeException {
        String clean = input.trim().toLowerCase();
        String[] tokens = input.trim().split("\\s+", 2);

        if (tokens.length == 0) {
            throw new DukeException.ParserException("Master, please give me a command!");
        } else if (clean.equals("bye")) {
            return new Command.ByeCommand();
        } else if (clean.equals("list")) {
            return new Command.ListCommand();
        }

        String command = tokens[0].toLowerCase();

        switch (command) {
        case "mark":
            return parseMarkCommand(tokens[1]);
        case "unmark":
            return parseUnmarkCommand(tokens[1]);
        case "delete":
            return parseDeleteCommand(tokens[1]);
        case "todo":
            return parseTodoCommand(tokens[1]);
        case "deadline":
            return parseDeadlineCommand(tokens[1]);
        case "event":
            return parseEventCommand(tokens[1]);
        default:
            throw new DukeException.ParserException("I dont understand you!"
                    + " Please be dont scold me and be gentle with me! Try again!");
        }
    }

    private static Command parseMarkCommand(String tokens) throws DukeException.IllegalParamException {
        try {
            int taskId = Integer.parseInt(tokens.trim());
            return new Command.MarkCommand(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException.IllegalParamException("I dont know which task you are trying to mark! Try a number");
        }
    }

    private static Command parseUnmarkCommand(String tokens) throws DukeException.IllegalParamException {
        try {
            int taskId = Integer.parseInt(tokens.trim());
            return new Command.UnmarkCommand(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException.IllegalParamException("I dont know which task you are trying to unmark! Try a number");
        }
    }

    private static Command parseDeleteCommand(String tokens) throws DukeException.IllegalParamException{
        try {
            int taskId = Integer.parseInt(tokens.trim());
            return new Command.DeleteCommand(taskId);
        } catch (NumberFormatException e) {
            throw new DukeException.IllegalParamException("I dont know which task you are trying to delete! Try a number");
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
                                                                      DukeException.ParserException {
        String[] parts = tokens.split("/by");

        if (parts.length != 2) {
            throw new DukeException.ParserException("Looks like you are missing '/by'! Use '/by' to tell me the deadline!");
        }

        String task = parts[0].trim();
        String deadlineString = parts[1].trim();

        //check if fields have values
        if (task.isEmpty()) {
            throw new DukeException.MissingInfoException("You have to tell me the description too! Or I cant remember it!");
        } else if (deadlineString.isEmpty()) {
            throw new DukeException.MissingInfoException("You need a deadline! Or you will never get to it!");
        }

        //parse string
        try {
            LocalDate deadline = LocalDate.parse(deadlineString, Task.getDateFormat());
            return new Command.DeadlineCommand(task, deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException.ParserException("Invalid date/time format for the deadline!"
                    + "Please use a dd MMM yyyy format (e.g. 21 Jan 2000).");
        }
    }

    private static Command parseEventCommand(String tokens) throws DukeException{
        String[] parts = tokens.split("/from|/to");

        if (parts.length != 3) {
            throw new DukeException.ParserException("Missing /from or /to! Use those to indicate times!");
        }

        String task = parts[0].trim();
        String startString = parts[1].trim();
        String endString = parts[2].trim();

        if (tokens.indexOf("/from") > tokens.indexOf("/to")) {
            String temp = startString;
            startString = endString;
            endString = temp;
        }

        if (task.isEmpty()) {
            throw new DukeException.MissingInfoException("Tasks needs a name! Or I cant remember it!");
        } else if (startString.isEmpty()) {
            throw new DukeException.MissingInfoException("Please tell me when it starts!");
        } else if (endString.isEmpty()) {
            throw new DukeException.MissingInfoException("Please tell me when it ends!");
        }

        //parse string
        try {
            LocalDate startDate = LocalDate.parse(startString, Task.getDateFormat());
            LocalDate endDate = LocalDate.parse(endString, Task.getDateFormat());

            return new Command.EventCommand(task, startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new DukeException.ParserException("Invalid date/time format for the deadline!"
                    + "Please use a dd MMM yyyy format (e.g. 21 Jan 2000).");
        }

    }
}
