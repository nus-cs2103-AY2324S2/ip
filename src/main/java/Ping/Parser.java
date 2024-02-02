package ping;

import java.time.LocalDate;
import java.time.LocalDateTime;

import command.AddCommand;
import command.BlahCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.HiCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnMarkCommand;


public class Parser {
  public static Command parseCommand(String input) {
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
            return parseUnMark(restCommands);
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
        default:
            System.out.println("Pleas fill in the valid command\n"
                    + "Valid commands are: bye, list, blah, todo, event, deadline, mark, unmark, delete, hi");
        }

        return null;
    }

    private static Command parseMark(String[] restCommands) {
        try {
            int i = Integer.parseInt(restCommands[1]) - 1;
            return new MarkCommand(i);
        } catch (Exception e) {
            System.out.println("Make sure you follow the right format of mark <number>");
        }
        return null;
    }

    private static Command parseUnMark(String[] restCommands) {
        try {
            int i = Integer.parseInt(restCommands[1]) - 1;
            return new UnMarkCommand(i);
        } catch (Exception e) {
            System.out.println("Make sure you follow the right format of unmark <number>");
        }
        return null;
    }

    private static Command parseTodo(String[] todoCommand) {
        String rest = "";
        try {
            for (int i = 1; i < todoCommand.length; i++) {
                rest = rest + todoCommand[i] + " ";
            }
            Todo j = new Todo(rest);
            if (!rest.isEmpty()) {
                return new AddCommand(j);
            } else {
                System.out.println("Todo what? you can't to do nothing right?");
            }
        } catch (Exception e) {
            System.out.println("Make sure you follow the right format of todo <Things you want to add>");
        }
        return null;
    }

    private static Command parseDelete(String[] delCommand) {
        try {
            int i = Integer.parseInt(delCommand[1]) - 1;
            return new DeleteCommand(i);
        } catch (Exception e) {
            System.out.println("Incorrect number or command");
        }
        return null;
    }

    private static Command parseDeadline(String[] dlCommand) {
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
                System.out.println("Did you type right?");
            }
        } catch (Exception e) {
            System.out.println("Incorrect number or command");
        }
        return null;
    }

    private static Command parseEvent(String[] evCommand) {
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
                System.out.println("Did you type right?");
            }
        } catch (Exception e) {
            System.out.println("Incorrect number or command");
        }
        return null;
    }
}
