import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    //public static DateTimeFormatter dateTimeString = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");


    public static boolean parse(String input, Ui ui) {
        int cmdSplit = input.indexOf(" ");
        String command = input;
        String task = null;
        if (cmdSplit == -1) {
            if (command.equals(Command.MARK.commandName) | command.equals(Command.UNMARK.commandName) | command.equals(Command.DELETE.commandName)) {
                System.out.println("-------------------------------- \n" +
                        "Oops, I'm not sure which task you are referring to! Please indicate a task number (e.g. " + command + " 1) \n" +
                        "-------------------------------- \n");
            } else if (command.equals(Command.TODO.commandName)) {
                System.out.println("-------------------------------- \n" +
                        "Oops, wrong format! Please indicate task details (e.g. todo CS2103 Lab 1) \n" +
                        "-------------------------------- \n");
            } else if (command.equals(Command.LIST.commandName)) {
                TaskList.printTaskList();
            } else if (command.equals(Command.TERMINATE.commandName)) {
                return true;
            } else {
                Ui.showErrorAndPrintCommands();
            }
        } else {
            command = input.substring(0, cmdSplit);
            task = input.substring(input.indexOf(" ") + 1);

            if (command.equals(Command.MARK.commandName) | command.equals(Command.UNMARK.commandName) | command.equals(Command.DELETE.commandName)) {
                try {
                    int taskNo = Integer.parseInt(task) - 1;
                    if (command.equals(Command.MARK.commandName)) {
                        TaskList.markTask(taskNo);
                    } else if (command.equals(Command.UNMARK.commandName)) {
                        TaskList.unmarkTask(taskNo);
                    } else if (command.equals(Command.DELETE.commandName)) {
                        TaskList.removeTask(taskNo);
                    }
                } catch (NumberFormatException e) {
                    Ui.showErrorNumbersOnly();
                }
            } else if (command.equals(Command.TODO.commandName) | command.equals(Command.DEADLINE.commandName) | command.equals(Command.EVENT.commandName)) {
                try {
                    Task t = null;
                    boolean success = true;
                    if (command.equals(Command.TODO.commandName)) {
                        t = new ToDo(task, false);
                    } else if (command.equals(Command.DEADLINE.commandName)) {
                        if (task == null || !task.contains(" /by ")) {
                            success = false;
                            Ui.showErrorDeadlineFormat();
                        } else {
                            String[] deadline = task.split(" /by ");
                            t = new Deadline(deadline[0], false, LocalDateTime.parse(deadline[1], dateTimeFormatter));
                        }
                        //test
                    } else {
                        if (task == null || !(task.contains(" /from ") && task.contains(" /to "))) {
                            success = false;
                            Ui.showErrorEventFormat();
                        } else {
                            String event = task.substring(0, task.indexOf(" /from "));
                            try {
                                LocalDateTime from = LocalDateTime.parse(task.substring(task.indexOf("/from ") + 6, task.indexOf(" /to ")), dateTimeFormatter);
                                LocalDateTime to = LocalDateTime.parse(task.substring((task.indexOf("/to ") + 4)), dateTimeFormatter);
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