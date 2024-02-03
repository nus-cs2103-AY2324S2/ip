package raphael.parser;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import raphael.command.Command;
import raphael.command.DeleteCommand;
import raphael.command.ExitCommand;
import raphael.command.CheckCommand;
import raphael.command.EditCommand;
import raphael.command.AddCommand;
import raphael.command.ListCommand;
import raphael.task.Todo;
import raphael.task.Event;
import raphael.task.Deadline;
import raphael.task.Task;

/**
 * Parses the command line input
 */
public class Parser {
    private static final Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Returns a command object after interpreting the input received from the command line. This method will throw
     * exception if the input does not fulfill the required format.
     *
     * @param input the input string read from command line
     * @return a command that corresponding to the keyword entered
     * @throws raphael.exception.RaphaelException exception exclusive to Raphael
     */
    public static Command parse(String input) throws raphael.exception.RaphaelException {
        if (input.isEmpty()) {
            throw new raphael.exception.RaphaelException("The command can't be empty!");
        }
        final String[] inputArr = input.split(" ", 2);
        switch(inputArr[0]) {
            case "bye":
                return new ExitCommand();
            case "todo":
                if (inputArr.length == 1 || (inputArr[1] = inputArr[1].trim()).isEmpty()) {
                    throw new raphael.exception.RaphaelException(
                            raphael.exception.RaphaelException.invalidFormat("TODO"));
                }
                Task toDo = new Todo(inputArr[1]);
                return new AddCommand(toDo);
            case "deadline":
                if (inputArr.length == 1 || (inputArr[1] = inputArr[1].trim()).isEmpty()) {
                    throw new raphael.exception.RaphaelException(
                            raphael.exception.RaphaelException.invalidFormat("DEADLINE"));
                }
                final String[] descriptionDeadline = inputArr[1].split("/by ");
                if (descriptionDeadline.length != 2
                        || (descriptionDeadline[0] = descriptionDeadline[0].trim()).isEmpty()) {
                    throw new raphael.exception.RaphaelException(
                            raphael.exception.RaphaelException.invalidFormat("DEADLINE"));
                }
                Task deadline = new Deadline(descriptionDeadline[0], descriptionDeadline[1]);
                return new AddCommand(deadline);
            case "event":
                if (inputArr.length == 1 || (inputArr[1] = inputArr[1].trim()).isEmpty()) {
                    throw new raphael.exception.RaphaelException(
                            raphael.exception.RaphaelException.invalidFormat("EVENT"));
                }
                final String[] descriptionTime = inputArr[1].split("/from ");
                if (descriptionTime.length != 2
                        || (descriptionTime[0] = descriptionTime[0].trim()).isEmpty()) {
                    throw new raphael.exception.RaphaelException(
                            raphael.exception.RaphaelException.invalidFormat("EVENT"));
                }
                final String[] timeRange = descriptionTime[1].split("/to ");
                if (timeRange.length != 2
                        || (timeRange[0] = timeRange[0].trim()).isEmpty()) {
                    throw new raphael.exception.RaphaelException(
                            raphael.exception.RaphaelException.invalidFormat("EVENT"));
                }
                Event event = new Event(descriptionTime[0], timeRange[0], timeRange[1]);
                return new AddCommand(event);
            case "mark":
                return Parser.processTaskWithIndex(inputArr, Command.TYPE.CHECK);
            case "unmark":
                return Parser.processTaskWithIndex(inputArr, Command.TYPE.UNCHECK);
            case "delete":
                return Parser.processTaskWithIndex(inputArr, Command.TYPE.DELETE);
            case "list":
                return new ListCommand();
            case "any":
                return new CheckCommand();
            default:
                throw new raphael.exception.RaphaelException("I'm sorry that I can't recognize the command!");
        }
    }
    private static Command processTaskWithIndex(String[] inputArr, Command.TYPE commandType)
            throws raphael.exception.RaphaelException {
        final String invalidFormatType = commandType.equals(Command.TYPE.CHECK) ? "CHECK_TASk"
                : commandType.equals(Command.TYPE.UNCHECK)
                ? "UNCHECK_TASK"
                : commandType.equals(Command.TYPE.DELETE)
                ? "DELETE_TASK"
                : "";
        if (inputArr.length != 2) {
            throw new raphael.exception.RaphaelException(
                    raphael.exception.RaphaelException.invalidFormat(invalidFormatType));
        }
        try {
            int idx = Integer.parseInt(inputArr[1]) - 1;
            if (commandType.equals(Command.TYPE.DELETE)) {
                return new DeleteCommand(idx);
            }
            return new EditCommand(idx, commandType.equals(Command.TYPE.CHECK));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new raphael.exception.RaphaelException(
                    raphael.exception.RaphaelException.invalidFormat(invalidFormatType));
        }
    }
}
