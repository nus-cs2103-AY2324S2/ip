package duke.parser;

import duke.command.Command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");


    public static boolean parse(String input, Ui ui) {
        int cmdSplit = input.indexOf(" ");
        String command = input;
        String task = null;
        if (cmdSplit == -1) {
            if (command.equals(Command.MARK.COMMAND_NAME) | command.equals(Command.UNMARK.COMMAND_NAME) |
                    command.equals(Command.DELETE.COMMAND_NAME)) {
                System.out.println("-------------------------------- \n" +
                        "Oops, I'm not sure which task you are referring to! " +
                        "Please indicate a task number (e.g. " + command + " 1) \n" +
                        "-------------------------------- \n");
            } else if (command.equals(Command.TODO.COMMAND_NAME)) {
                System.out.println("-------------------------------- \n" +
                        "Oops, wrong format! Please indicate task details (e.g. todo CS2103 Lab 1) \n" +
                        "-------------------------------- \n");
            } else if (command.equals(Command.LIST.COMMAND_NAME)) {
                TaskList.printTaskList();
            } else if (command.equals(Command.TERMINATE.COMMAND_NAME)) {
                return true;
            } else {
                Ui.showErrorAndPrintCommands();
            }
        } else {
            command = input.substring(0, cmdSplit);
            task = input.substring(input.indexOf(" ") + 1);

            if (command.equals(Command.MARK.COMMAND_NAME) | command.equals(Command.UNMARK.COMMAND_NAME) |
                    command.equals(Command.DELETE.COMMAND_NAME)) {
                try {
                    int taskNo = Integer.parseInt(task) - 1;
                    if (command.equals(Command.MARK.COMMAND_NAME)) {
                        TaskList.markTask(taskNo);
                    } else if (command.equals(Command.UNMARK.COMMAND_NAME)) {
                        TaskList.unmarkTask(taskNo);
                    } else if (command.equals(Command.DELETE.COMMAND_NAME)) {
                        TaskList.removeTask(taskNo);
                    }
                } catch (NumberFormatException e) {
                    Ui.showErrorNumbersOnly();
                }
            } else if (command.equals(Command.TODO.COMMAND_NAME) | command.equals(Command.DEADLINE.COMMAND_NAME) |
                    command.equals(Command.EVENT.COMMAND_NAME)) {
                try {
                    Task t = null;
                    boolean success = true;
                    if (command.equals(Command.TODO.COMMAND_NAME)) {
                        t = new ToDo(task, false);
                    } else if (command.equals(Command.DEADLINE.COMMAND_NAME)) {
                        if (task == null || !task.contains(" /by ")) {
                            success = false;
                            Ui.showErrorDeadlineFormat();
                        } else {
                            String[] deadline = task.split(" /by ");
                            t = new Deadline(deadline[0], false,
                                    LocalDateTime.parse(deadline[1], dateTimeFormatter));
                        }
                        //test
                    } else {
                        if (task == null || !(task.contains(" /from ") && task.contains(" /to "))) {
                            success = false;
                            Ui.showErrorEventFormat();
                        } else {
                            String event = task.substring(0, task.indexOf(" /from "));
                            try {
                                LocalDateTime from = LocalDateTime.parse(task.substring(task.indexOf("/from ") + 6,
                                        task.indexOf(" /to ")), dateTimeFormatter);
                                LocalDateTime to = LocalDateTime.parse(task.substring((task.indexOf("/to ") + 4)),
                                        dateTimeFormatter);
                                t = new Event(event, false, from, to);
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
            }
            else {
                Ui.showErrorAndPrintCommands();
            }
        }
        return false;
    }
}