package Ping;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Command.AddCommand;
import Command.BlahCommand;
import Command.Command;
import Command.DeleteCommand;
import Command.ExitCommand;
import Command.FindCommand;
import Command.HiCommand;
import Command.ListCommand;
import Command.MarkCommand;
import Command.UnmarkCommand;
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
            assert restCommands.length >= 2 : "Invalid number of arguments";
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
            assert delCommand.length >= 2 : "Invalid number of arguments";
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
            int check = idx + 1;
            for (int j = idx + 1; j < dlCommand.length; j++) {
                if (check != dlCommand.length - 1) {
                    date.append(dlCommand[j]).append(" ");
                    check++;
                } else {
                    date.append(dlCommand[j]);
                }
            }

            LocalDate dt = DateTimeCheck.timeCheckOnDate(date.toString());
            Deadline dl = new Deadline(rest.toString(), dt);
            if (rest.length() > 0 && dt != null) {
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

    private static Command parseEvent(String[] evCommand) throws PingException {
        StringBuilder rest = new StringBuilder();
        StringBuilder date1 = new StringBuilder();
        StringBuilder date2 = new StringBuilder();
        try {
            int idx = 0;
            for (int i = 1; i < evCommand.length; i++) {
                if (evCommand[i].equals("/from")) {
                    idx = i;
                    break;
                } else {
                    rest.append(evCommand[i]).append(" ");
                }
            }
            int idx2 = 0;
            for (int j = idx + 1; j < evCommand.length; j++) {
                if (evCommand[j].equals("/to")) {
                    idx2 = j;
                    break;
                } else {
                    date1.append(evCommand[j]).append(" ");
                }
            }
            int check = idx2 + 1;
            for (int k = idx2 + 1; k < evCommand.length; k++) {
                if (check != evCommand.length - 1) {
                    date2.append(evCommand[k]).append(" ");
                    check++;
                } else {
                    date2.append(evCommand[k]);
                }
            }
            LocalDateTime dt1 = DateTimeCheck.timeCheckOnTime(date1.toString().stripTrailing());
            LocalDateTime dt2 = DateTimeCheck.timeCheckOnTime(date2.toString());
            boolean compareOfTime = DateTimeCheck.timeCompare(dt1, dt2);
            Event e = new Event(rest.toString(), dt1, dt2);
            if ((rest.length() > 0) && dt1 != null && dt2 != null && compareOfTime) {
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
