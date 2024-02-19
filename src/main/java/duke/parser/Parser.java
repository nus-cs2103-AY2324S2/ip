package duke.parser;

import duke.command.Command;
import duke.command.CommandEnum;
import duke.command.InvalidCommand;
import duke.command.find.FindCommand;
import duke.command.list.ListCommand;
import duke.command.mark.ChangeisDoneCommand;
import duke.command.mark.MarkCommand;
import duke.command.mark.UnmarkCommand;
import duke.command.task.DeadlineCommand;
import duke.command.task.EventCommand;
import duke.command.task.TaskCommand;
import duke.command.task.ToDoCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Parser class that deals with making sense of the user command
 * @author se-edu
 * Adapted from https://github.com/se-edu/addressbook-level2
 */
public class Parser {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<args>.*)");

    /**
     * Processes a string input to decide which command to execute
     * @param input
     * @return isExit (i.e. to terminate program or not)
     */
//    public static boolean parse(String input) {
//        int cmdSplit = input.indexOf(" ");
//        String command = input;
//        String task = null;
//        if (cmdSplit == -1) {
//            if (command.equals(CommandEnum.MARK.COMMAND_NAME) | command.equals(CommandEnum.UNMARK.COMMAND_NAME) |
//                    command.equals(CommandEnum.DELETE.COMMAND_NAME)) {
//                Ui.showErrorIncorrectNumFormat(command);
//            } else if (command.equals(CommandEnum.TODO.COMMAND_NAME)) {
//                System.out.println("-------------------------------- \n" +
//                        "Oops, wrong format! Please indicate task details (e.g. todo CS2103 Lab 1) \n" +
//                        "-------------------------------- \n");
//            } else if (command.equals(CommandEnum.LIST.COMMAND_NAME)) {
//                System.out.println(TaskList.getTaskListInString());
//            } else if (command.equals(CommandEnum.BYE.COMMAND_NAME)) {
//                return true;
//            } else {
//                Ui.showErrorAndPrintCommands();
//            }
//        } else {
//            command = input.substring(0, cmdSplit);
//            task = input.substring(input.indexOf(" ") + 1);
//
//            if (command.equals(CommandEnum.MARK.COMMAND_NAME) | command.equals(CommandEnum.UNMARK.COMMAND_NAME) |
//                    command.equals(CommandEnum.DELETE.COMMAND_NAME)) {
//                try {
//                    int taskNo = Integer.parseInt(task) - 1;
//                    if (command.equals(CommandEnum.MARK.COMMAND_NAME)) {
//                        TaskList.markTask(taskNo);
//                    } else if (command.equals(CommandEnum.UNMARK.COMMAND_NAME)) {
//                        TaskList.unmarkTask(taskNo);
//                    } else if (command.equals(CommandEnum.DELETE.COMMAND_NAME)) {
//                        TaskList.removeTask(taskNo);
//                    }
//                } catch (NumberFormatException e) {
//                    Ui.showErrorNumbersOnly();
//                }
//            } else if (command.equals(CommandEnum.TODO.COMMAND_NAME) | command.equals(CommandEnum.DEADLINE.COMMAND_NAME) |
//                    command.equals(CommandEnum.EVENT.COMMAND_NAME)) {
//                try {
//                    Task t = null;
//                    boolean success = false;
//                    if (command.equals(CommandEnum.TODO.COMMAND_NAME)) {
//                        t = new ToDo(task, false);
//                        success = true;
//                    } else if (command.equals(CommandEnum.DEADLINE.COMMAND_NAME)) {
//                        if (task == null || !task.contains(" /by ")) {
//                            Ui.showErrorDeadlineFormat();
//                        } else {
//                            String[] deadline = task.split(" /by ");
//                            t = new Deadline(deadline[0], false,
//                                    LocalDateTime.parse(deadline[1], dateTimeFormatter));
//                            success = true;
//                        }
//                    } else {
//                        if (task == null || !(task.contains(" /from ") && task.contains(" /to "))) {
//                            Ui.showErrorEventFormat();
//                        } else {
//                            String event = task.substring(0, task.indexOf(" /from "));
//                            try {
//                                LocalDateTime from = LocalDateTime.parse(task.substring(task.indexOf("/from ") + 6,
//                                        task.indexOf(" /to ")), dateTimeFormatter);
//                                LocalDateTime to = LocalDateTime.parse(task.substring((task.indexOf("/to ") + 4)),
//                                        dateTimeFormatter);
//                                t = new Event(event, false, from, to);
//                                success = true;
//                            } catch (IndexOutOfBoundsException e) {
//                                Ui.showErrorEventTimingFormat();
//                            }
//                        }
//                    }
//                    if (success) {
//                        TaskList.addTask(t);
//                    }
//                } catch (DateTimeException e) {
//                    Ui.showErrorDatetimeFormat();
//                }
//            } else if (command.equals(CommandEnum.FIND.COMMAND_NAME)) {
//                Ui.printMatchingTasks(TaskList.findTaskByKeyword(task));
//            }
//            else {
//                Ui.showErrorAndPrintCommands();
//            }
//        }
//        return false;
//    }

    public Command parseCommand(String input) {
        final Matcher matcher = COMMAND_FORMAT.matcher(input.trim());

        if (!matcher.matches()) {
            return new InvalidCommand(Command.INVALID_COMMAND);
        }

        String command = matcher.group("command");
        String args = matcher.group("args");

        switch (command) {
            case ToDoCommand.COMMAND:
                return parseToDoCommand(args);

            case DeadlineCommand.COMMAND:
                return parseDeadlineCommand(args);

            case EventCommand.COMMAND:
                return parseEventCommand(args);

            case MarkCommand.COMMAND:
                return parseMarkCommand(args);

            case UnmarkCommand.COMMAND:
                return parseUnmarkCommand(args);

            case FindCommand.COMMAND:
                return parseFindCommand(args);

            case ListCommand.COMMAND:
                return new ListCommand();

            default:
                return new InvalidCommand(Command.INVALID_COMMAND);
        }

    }

    public Command parseToDoCommand(String args) {
        String task = args.trim();
        if (task.isEmpty()) {
            return new InvalidCommand(ToDoCommand.INVALID_COMMAND);
        }

        return new ToDoCommand(task);
    }

    public Command parseDeadlineCommand(String args) {
        final Matcher matcher = DeadlineCommand.ARG_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            return new InvalidCommand(DeadlineCommand.INVALID_COMMAND);
        }

        try {
            final String task = matcher.group("task");
            final LocalDateTime by = LocalDateTime.parse(matcher.group("by"), dateTimeFormatter);

            if (task.isEmpty()) {
                return new InvalidCommand(DeadlineCommand.INVALID_COMMAND);
            }
            return new DeadlineCommand(task, by);
        } catch (DateTimeException e) {
            return new InvalidCommand(TaskCommand.COMMAND_INVALID_DATETIME);
        }
    }

    private Command parseEventCommand(String args) {
        final Matcher matcher = EventCommand.ARG_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            return new InvalidCommand(EventCommand.INVALID_COMMAND);
        }

        try {
            final String task = matcher.group("task");
            final LocalDateTime from = LocalDateTime.parse(matcher.group("from"), dateTimeFormatter);
            final LocalDateTime to = LocalDateTime.parse(matcher.group("to"), dateTimeFormatter);

            if (task.isEmpty()) {
                return new InvalidCommand(EventCommand.INVALID_COMMAND);
            }
            return new EventCommand(task, from, to);
        } catch (DateTimeException e) {
            return new InvalidCommand(TaskCommand.COMMAND_INVALID_DATETIME);
        }
    }

    private Command parseMarkCommand(String args) {
        try {
            int taskNo = Integer.parseInt(args.trim());
            return new MarkCommand(taskNo);
        } catch (NumberFormatException e) {
            return new InvalidCommand(MarkCommand.COMMAND_INVALID_INTEGER);
        } catch (NullPointerException e) {
            return new InvalidCommand(Command.ERROR_LIST_EMPTY);
        } catch (IndexOutOfBoundsException e) {
            return ChangeisDoneCommand.errorTaskNotExist();
        }
    }

    private Command parseUnmarkCommand(String args) {
        try {
            int taskNo = Integer.parseInt(args.trim());
            return new UnmarkCommand(taskNo);
        } catch (NumberFormatException e) {
            return new InvalidCommand(UnmarkCommand.COMMAND_INVALID_INTEGER);
        } catch (NullPointerException e) {
            return new InvalidCommand(Command.ERROR_LIST_EMPTY);
        } catch (IndexOutOfBoundsException e) {
            return ChangeisDoneCommand.errorTaskNotExist();
        }
    }

    private Command parseFindCommand(String args) {
        String keyword = args.trim();
        return new FindCommand(keyword);
    }


}