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
import raphael.exception.DukeException;
public class Parser {
    private static final Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static Command parse(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("The command can't be empty!");
        }
        final String[] inputArr = input.split(" ", 2);
        switch(inputArr[0]) {
            case "bye":
                return new ExitCommand();
            case "todo":
                if (inputArr.length == 1 || (inputArr[1] = inputArr[1].trim()).isEmpty()) {
                    throw new DukeException(DukeException.invalidFormat("TODO"));
                }
                Task toDo = new Todo(inputArr[1]);
                return new AddCommand(toDo);
            case "deadline":
                if (inputArr.length == 1 || (inputArr[1] = inputArr[1].trim()).isEmpty()) {
                    throw new DukeException(DukeException.invalidFormat("DEADLINE"));
                }
                final String[] descriptionDeadline = inputArr[1].split("/by ");
                if (descriptionDeadline.length != 2
                        || (descriptionDeadline[0] = descriptionDeadline[0].trim()).isEmpty()) {
                    throw new DukeException(DukeException.invalidFormat("DEADLINE"));
                }
                Task deadline = new Deadline(descriptionDeadline[0], descriptionDeadline[1]);
                return new AddCommand(deadline);
            case "event":
                if (inputArr.length == 1 || (inputArr[1] = inputArr[1].trim()).isEmpty()) {
                    throw new DukeException(DukeException.invalidFormat("EVENT"));
                }
                final String[] descriptionTime = inputArr[1].split("/from ");
                if (descriptionTime.length != 2
                        || (descriptionTime[0] = descriptionTime[0].trim()).isEmpty()) {
                    throw new DukeException(DukeException.invalidFormat("EVENT"));
                }
                final String[] timeRange = descriptionTime[1].split("/to ");
                if (timeRange.length != 2
                        || (timeRange[0] = timeRange[0].trim()).isEmpty()) {
                    throw new DukeException(DukeException.invalidFormat("EVENT"));
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
                throw new DukeException("I'm sorry that I can't recognize the command!");
        }
    }
    private static Command processTaskWithIndex(String[] inputArr, Command.TYPE commandType) throws DukeException {
        final String invalidFormatType = commandType.equals(Command.TYPE.CHECK) ? "CHECK_TASk"
                : commandType.equals(Command.TYPE.UNCHECK)
                ? "UNCHECK_TASK"
                : commandType.equals(Command.TYPE.DELETE)
                ? "DELETE_TASK"
                : "";
        if (inputArr.length != 2) {
            throw new DukeException(DukeException.invalidFormat(invalidFormatType));
        }
        try {
            int idx = Integer.parseInt(inputArr[1]) - 1;
            if (commandType.equals(Command.TYPE.DELETE)) {
                return new DeleteCommand(idx);
            }
            return new EditCommand(idx, commandType.equals(Command.TYPE.CHECK));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException(DukeException.invalidFormat(invalidFormatType));
        }
    }
    public static String getCommand(String input) {
        if (input.isEmpty()) {
            return null;
        } else {
            return input.split(" ")[0];
        }
    }
    public static String removeCommand(String input) {
        try {
            String[] inputArr = input.split(" ");
            return String.join(" ", Arrays.copyOfRange(inputArr, 1, inputArr.length));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    public static String[] getTaskAndDates(String input, boolean isRange) {
        if (isRange) {
            if (!input.contains("/from ") || !input.contains("/to ")) {
                return null;
            } else {
                String[] inputArr = input.split("/from ");
                String task = inputArr[0];
                String[] dates = inputArr[1].split("/to ");
                try {
                    for (int i = 0; i < dates.length; ++i) {
                        dates[i] = dates[i].trim();
                        dates[i] = LocalDate.parse(dates[i], Parser.DATE_TIME_FORMATTER).toString();
                    }
                    return new String[]{task.trim(), dates[0], dates[1]};
                } catch (DateTimeParseException e) {
                    return null;
                }
            }
        } else {
            if (!input.contains("/by ")) {
                return null;
            } else {
                final String[] res = input.split("/by ");
                if (!Parser.datePattern.matcher(res[1]).matches()) {
                    return null;
                }
                res[0] = res[0].trim();
                res[1] = res[1].trim();
                return res;
            }
        }
    }
    public static String getDate(String input) {
        String[] inputArr = input.split(" ");
        if (inputArr.length != 2) {
            return null;
        } else {
            try {
                return LocalDate.parse(inputArr[1], Parser.DATE_TIME_FORMATTER).toString();
            } catch (DateTimeParseException e) {
                return null;
            }
        }
    }

    public static Integer getInteger(String input, int idx) {
        if (input.length() <= idx) {
            return null;
        } else {
            try {
                return Integer.parseInt(input.split(" ")[idx]);
            } catch (ArrayIndexOutOfBoundsException e) {
                final String output = String.format("Expecting input of at least %d", idx + 1);
                System.out.println(output);
                return null;
            } catch (NumberFormatException e) {
                System.out.println("Argument is not a valid number format!");
                return null;
            }
        }
    }
}
