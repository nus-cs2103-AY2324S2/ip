package ping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import ping.command.AddCommand;
import ping.command.BlahCommand;
import ping.command.Command;
import ping.command.DeleteCommand;
import ping.command.ExitCommand;
import ping.command.FindCommand;
import ping.command.HiCommand;
import ping.command.ListCommand;
import ping.command.MarkCommand;
import ping.command.UnmarkCommand;
import ping.exceptions.PingException;
import ping.job.Deadline;
import ping.job.Event;
import ping.job.Todo;
import ping.timematch.DateTimeCheck;




/**
 * This class is used to parse the command input by the user
 */
public class Parser {
    /**
     * This method is used to parse the command input by the user
     * @param input the command input by the user
     * @return the command object
     */
    public static Command parseCommand(TaskList tasks, String input) throws PingException {
        String[] restCommands = input.split(" ");
        String command = restCommands[0];
        switch (command.toLowerCase()) {
        case "hi":
            return new HiCommand();
        case "blah":
            return new BlahCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
            return parseMark(restCommands);
        case "unmark":
            return parseUnmark(restCommands);
        case "todo":
            return parseTodo(restCommands);
        case "list":
            return new ListCommand();
        case "delete":
            return parseDelete(restCommands);
        case "deadline":
            return parseDeadline(restCommands);
        case "event":
            return parseEvent(restCommands);
        case "find":
            return parseFind(restCommands);
        default:
            throw new PingException("Please fill in the valid command\n"
                    + "Valid commands are: bye, list, blah, todo, event, deadline, mark, unmark, delete, hi");
        }
    }

    // Add assert to check the input
    private static Command parseMark(String[] restCommands) throws PingException {
        try {
            assert restCommands.length >= 2 : "Assert: Invalid number of arguments";
            int i = Integer.parseInt(restCommands[1]) - 1;
            return new MarkCommand(i);
        } catch (Exception e) {
            throw new PingException("Invalid number");
        }
    }

    private static Command parseUnmark(String[] restCommands) throws PingException {
        try {
            assert restCommands.length >= 2 : "Assert: Invalid number of arguments";
            int i = Integer.parseInt(restCommands[1]) - 1;
            return new UnmarkCommand(i);
        } catch (Exception e) {
            throw new PingException("Invalid number");
        }
    }

    private static Command parseTodo(String[] todoCommand) throws PingException {
        StringBuilder rest = new StringBuilder();
        try {
            for (int i = 1; i < todoCommand.length; i++) {
                rest.append(todoCommand[i]).append(" ");
            }
            if (rest.length() > 0) {
                return new AddCommand(new Todo(rest.toString()));
            } else {
                throw new PingException("Did you type right?");
            }
        } catch (Exception e) {
            throw new PingException("Incorrect number or command");
        }
    }

    private static Command parseDelete(String[] delCommand) throws PingException {
        try {
            assert delCommand.length >= 2 : "Assert: Invalid number of arguments";
            int i = Integer.parseInt(delCommand[1]) - 1;
            return new DeleteCommand(i);
        } catch (Exception e) {
            throw new PingException("Invalid number");
        }

    }

    private static Command parseDeadline(String[] dlCommand) throws PingException {
        StringBuilder rest = new StringBuilder();
        StringBuilder date = new StringBuilder();
        try {
            List<StringBuilder> list = parseDeadlineAlgorithm(rest, date, dlCommand);
            rest = list.get(0);
            date = list.get(1);

            LocalDate dateTime = DateTimeCheck.timeCheckOnDate(date.toString());
            Deadline dl = new Deadline(rest.toString(), dateTime);

            boolean isRestBiggerThanZero = rest.length() > 0;
            boolean isDateNotNull = dateTime != null;

            if (isRestBiggerThanZero && isDateNotNull) {
                return new AddCommand(dl);
            } else {
                throw new PingException("Did you type right?");
            }
        } catch (PingException e) {
            throw new PingException(e.getMessage());
        } catch (Exception e) {
            throw new PingException("Incorrect number or command");
        }
    }

    private static List<StringBuilder> parseDeadlineAlgorithm(StringBuilder rest, StringBuilder date, String[] dlCommand) {
        int idx = 0;
        for (int i = 1; i < dlCommand.length; i++) {
            if (dlCommand[i].equals("/by")) {
                idx = i;
                break;
            } else {
                rest.append(dlCommand[i]).append(" ");
            }
        }
        // Check for weekdays or month
        int check = idx + 1; // Can't declare at the top of this method
        for (int j = idx + 1; j < dlCommand.length; j++) {
            if (check != dlCommand.length - 1) {
                date.append(dlCommand[j]).append(" ");
                check++;
            } else {
                date.append(dlCommand[j]);
            }
        }
        return List.of(rest, date);
    }        

    private static Command parseEvent(String[] evCommand) throws PingException {
        StringBuilder rest = new StringBuilder();
        StringBuilder dateFromString = new StringBuilder();
        StringBuilder dateToString = new StringBuilder();
        try {
            List<StringBuilder> list = parseEventAlgorithm(rest, dateFromString, dateToString, evCommand);
            rest = list.get(0);
            dateFromString = list.get(1);
            dateToString = list.get(2);

            LocalDateTime dateFrom = DateTimeCheck.timeCheckOnTime(dateFromString.toString().stripTrailing());
            LocalDateTime dateTo = DateTimeCheck.timeCheckOnTime(dateToString.toString());

            boolean isRestBiggerThanZero = rest.length() > 0;
            boolean isDateFromNotNull = dateFrom != null;
            boolean isDateToNotNull = dateTo != null;
            boolean compareOfTime = DateTimeCheck.timeCompare(dateFrom, dateTo);

            Event e = new Event(rest.toString(), dateFrom, dateTo);
            if (isRestBiggerThanZero && isDateFromNotNull && isDateToNotNull && compareOfTime) {
                return new AddCommand(e);
            } else {
                throw new PingException("Did you type right?");
            }
        } catch (PingException e) {
            throw new PingException(e.getMessage());
        } catch (Exception e) {
            throw new PingException("Incorrect number or command");
        }
    }

    private static List<StringBuilder> parseEventAlgorithm(StringBuilder rest, StringBuilder dateFromString, StringBuilder dateToString, String[] evCommand) {
        int idx = 0;
        int idxForTo = 0;
        for (int i = 1; i < evCommand.length; i++) {
            if (evCommand[i].equals("/from")) {
                idx = i;
                break;
            } else {
                rest.append(evCommand[i]).append(" ");
            }
        }
        for (int j = idx + 1; j < evCommand.length; j++) {
            if (evCommand[j].equals("/to")) {
                idxForTo = j;
                break;
            } else {
                dateFromString.append(evCommand[j]).append(" ");
            }
        }
        int check = idxForTo + 1; // Can't declare at the top of this method
        for (int k = idxForTo + 1; k < evCommand.length; k++) {
            if (check != evCommand.length - 1) {
                dateToString.append(evCommand[k]).append(" ");
                check++;
            } else {
                dateToString.append(evCommand[k]);
            }
        }
        return List.of(rest, dateFromString, dateToString);
    }

    // This function after prCommand[0] is the string that need to be found in ArrayList<Task>
    // After matching, it will return the list of tasks that contain the string
    private static Command parseFind(String[] prCommand) {
        StringBuilder rest = new StringBuilder();
        for (int i = 1; i < prCommand.length; i++) {
            rest.append(prCommand[i]).append(" ");
        }
        return new FindCommand(rest.toString());
    }
}
