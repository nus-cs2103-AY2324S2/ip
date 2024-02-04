import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parseUserInput(String userInput, TaskList taskList) throws InvalidCommandException {

        //Check first word
        String[] commandAndRemaining = userInput.strip().split(" ", 2);
        String commandUpper = commandAndRemaining[0].toUpperCase();
        Command cmd;
        switch (commandUpper) {
        case "ADD":
            try {
                cmd = parseAdd(commandAndRemaining[1].strip(), taskList);
                break;
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidCommandException(AddCommand.getUsage());
            }
        case "LIST":
            cmd = new ListCommand(taskList);
            break;
        case "MARK":
            try {
                cmd = parseMark(commandAndRemaining[1].strip(), taskList);
                break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new InvalidCommandException(MarkCommand.getUsage());
            }
        case "UNMARK":
            try {
                cmd = parseUnmark(commandAndRemaining[1].strip(), taskList);
                break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new InvalidCommandException(UnmarkCommand.getUsage());
            }
        case "DELETE":
            try {
                cmd = parseDelete(commandAndRemaining[1].strip(), taskList);
                break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new InvalidCommandException(DeleteCommand.getUsage());
            }
        case "END":
            cmd = new ExitCommand();
            break;
        default:
            throw new InvalidCommandException();
        }
        return cmd;

    }

    public static AddCommand parseAdd(String inputWithoutAdd, TaskList taskList) throws InvalidCommandException {

        String[] typeAndRemaining = inputWithoutAdd.split(" ", 2);
        String typeUpper = typeAndRemaining[0].toUpperCase();
        switch (typeUpper) {
        case "TODO":
            try {
                String TodoDesc = typeAndRemaining[1].strip();
                return new AddTodoCommand(taskList, TodoDesc);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandException(AddTodoCommand.getUsage());
            }
        case "DEADLINE":
            try {
                String[] descAndBy = typeAndRemaining[1].split("/by", 2);
                String[] bydateAndBytime = descAndBy[1].strip().split(" ", 2);
                LocalDate byDate = LocalDate.parse(bydateAndBytime[0]);
                LocalTime byTime = null;
                if (bydateAndBytime.length == 2) {
                    byTime = LocalTime.parse(bydateAndBytime[1].strip());
                }
                return new AddDeadlineCommand(taskList, descAndBy[0], byDate, byTime);
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                throw new InvalidCommandException(AddDeadlineCommand.getUsage());
            }
        case "EVENT":
            try {
                String[] descAndRemaining = typeAndRemaining[1].split("/from", 2);
                String[] fromAndTo = descAndRemaining[1].split("/to", 2);
                String[] fromdateAndFromtime = fromAndTo[0].strip().split(" ", 2);
                String[] todateAndTotime = fromAndTo[1].strip().split(" ", 2);

                String eventDesc = descAndRemaining[0];
                LocalDate fromDate = LocalDate.parse(fromdateAndFromtime[0]);
                LocalTime fromTime = null;
                if (fromdateAndFromtime.length == 2) {
                    fromTime = LocalTime.parse(fromdateAndFromtime[1].strip());
                }
                LocalDate toDate = LocalDate.parse(todateAndTotime[0]);
                LocalTime toTime = null;
                if (todateAndTotime.length == 2) {
                    toTime = LocalTime.parse(todateAndTotime[1].strip());
                }
                return new AddEventCommand(taskList, eventDesc, fromDate, fromTime, toDate, toTime);
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                throw new InvalidCommandException(AddEventCommand.getUsage());
            }
        default:
            throw new InvalidCommandException(AddCommand.getUsage());
        }
    }

    public static MarkCommand parseMark(String inputWithoutMark, TaskList taskList) {
        int index = Integer.parseInt(inputWithoutMark) - 1;
        return new MarkCommand(taskList, index);
    }

    public static UnmarkCommand parseUnmark(String inputWithoutUnmark, TaskList taskList) {
        int index = Integer.parseInt(inputWithoutUnmark) - 1;
        return new UnmarkCommand(taskList, index);
    }

    public static DeleteCommand parseDelete(String inputWithoutDelete, TaskList taskList) {
        int index = Integer.parseInt(inputWithoutDelete) - 1;
        return new DeleteCommand(taskList, index);
    }
}
