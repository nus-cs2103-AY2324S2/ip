package duke.parser;

import duke.command.Command;
import duke.command.CommandEnum;
import duke.command.InvalidCommand;
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
    public static boolean parse(String input) {
        int cmdSplit = input.indexOf(" ");
        String command = input;
        String task = null;
        if (cmdSplit == -1) {
            if (command.equals(CommandEnum.MARK.COMMAND_NAME) | command.equals(CommandEnum.UNMARK.COMMAND_NAME) |
                    command.equals(CommandEnum.DELETE.COMMAND_NAME)) {
                Ui.showErrorIncorrectNumFormat(command);
            } else if (command.equals(CommandEnum.TODO.COMMAND_NAME)) {
                System.out.println("-------------------------------- \n" +
                        "Oops, wrong format! Please indicate task details (e.g. todo CS2103 Lab 1) \n" +
                        "-------------------------------- \n");
            } else if (command.equals(CommandEnum.LIST.COMMAND_NAME)) {
                System.out.println(TaskList.getTaskListInString());
            } else if (command.equals(CommandEnum.BYE.COMMAND_NAME)) {
                return true;
            } else {
                Ui.showErrorAndPrintCommands();
            }
        } else {
            command = input.substring(0, cmdSplit);
            task = input.substring(input.indexOf(" ") + 1);

            if (command.equals(CommandEnum.MARK.COMMAND_NAME) | command.equals(CommandEnum.UNMARK.COMMAND_NAME) |
                    command.equals(CommandEnum.DELETE.COMMAND_NAME)) {
                try {
                    int taskNo = Integer.parseInt(task) - 1;
                    if (command.equals(CommandEnum.MARK.COMMAND_NAME)) {
                        TaskList.markTask(taskNo);
                    } else if (command.equals(CommandEnum.UNMARK.COMMAND_NAME)) {
                        TaskList.unmarkTask(taskNo);
                    } else if (command.equals(CommandEnum.DELETE.COMMAND_NAME)) {
                        TaskList.removeTask(taskNo);
                    }
                } catch (NumberFormatException e) {
                    Ui.showErrorNumbersOnly();
                }
            } else if (command.equals(CommandEnum.TODO.COMMAND_NAME) | command.equals(CommandEnum.DEADLINE.COMMAND_NAME) |
                    command.equals(CommandEnum.EVENT.COMMAND_NAME)) {
                try {
                    Task t = null;
                    boolean success = false;
                    if (command.equals(CommandEnum.TODO.COMMAND_NAME)) {
                        t = new ToDo(task, false);
                        success = true;
                    } else if (command.equals(CommandEnum.DEADLINE.COMMAND_NAME)) {
                        if (task == null || !task.contains(" /by ")) {
                            Ui.showErrorDeadlineFormat();
                        } else {
                            String[] deadline = task.split(" /by ");
                            t = new Deadline(deadline[0], false,
                                    LocalDateTime.parse(deadline[1], dateTimeFormatter));
                            success = true;
                        }
                    } else {
                        if (task == null || !(task.contains(" /from ") && task.contains(" /to "))) {
                            Ui.showErrorEventFormat();
                        } else {
                            String event = task.substring(0, task.indexOf(" /from "));
                            try {
                                LocalDateTime from = LocalDateTime.parse(task.substring(task.indexOf("/from ") + 6,
                                        task.indexOf(" /to ")), dateTimeFormatter);
                                LocalDateTime to = LocalDateTime.parse(task.substring((task.indexOf("/to ") + 4)),
                                        dateTimeFormatter);
                                t = new Event(event, false, from, to);
                                success = true;
                            } catch (IndexOutOfBoundsException e) {
                                Ui.showErrorEventTimingFormat();
                            }
                        }
                    }
                    if (success) {
                        TaskList.addTask(t);
                    }
                } catch (DateTimeException e) {
                    Ui.showErrorDatetimeFormat();
                }
            } else if (command.equals(CommandEnum.FIND.COMMAND_NAME)) {
                Ui.printMatchingTasks(TaskList.findTaskByKeyword(task));
            }
            else {
                Ui.showErrorAndPrintCommands();
            }
        }
        return false;
    }

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


}