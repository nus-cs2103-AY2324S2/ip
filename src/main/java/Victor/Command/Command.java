package victor.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.application.Platform;
import victor.storage.Storage;
import victor.tasklist.TaskList;
import victor.tasktype.Deadline;
import victor.tasktype.Event;
import victor.tasktype.Task;
import victor.tasktype.Todo;
import victor.ui.Ui;

/**
 * Took inspiration about the Command from erv-teo command code.
 * Made some changes to better fit my code.
 */
public enum Command {

    /**
     * The list command. Marks a certain task in the list.
     */
    LIST("list") {
        @Override
        public String execute(TaskList tasks, String[] commandLine) {
            return tasks.printList();
        }
    },
    /**
     * The mark command. Marks a certain task in the list.
     */
    MARK("mark") {
        @Override
        public String execute(TaskList tasks, String[] commandLine) {
            try {
                int position = Integer.parseInt(commandLine[1]);
                assert position >= tasks.getSize() : "Position out of list";
                Task currentTask = tasks.getPosValue(position - 1);
                currentTask.markAsDone();
                return "Nice! I've marked this task as done:\n" + currentTask;
            } catch (IndexOutOfBoundsException e) {
                return "Check how many items are in the list again. \n"
                        + "The number you gave is too high\n"
                        + "Can't mark an item not in the list"
                        + "The format to mark a task is: mark (task list number)";
            } catch (NumberFormatException e) {
                return "Sorry, I'm only smart enough to find the task based on numbers."
                        + "Please give a number. If you refuse, too bad, "
                        + "this is all I can do.";
            }
        }
    },
    /**
     * The unmark command. unMarks a certain task in the list.
     */
    UNMARK("unmark") {
        @Override
        public String execute(TaskList tasks, String[] commandLine) {
            try {
                int position = Integer.parseInt(commandLine[1]);
                assert position >= tasks.getSize() : "Position out of list";
                Task currentTask = tasks.getPosValue(position - 1);
                currentTask.unmarkAsDone();
                return "OK, I've marked this task as not done yet:\n" + currentTask;
            } catch (IndexOutOfBoundsException e) {
                return "Check how many items are in the list again.\n"
                        + "The number you gave is too high.\n"
                        + "Can't unmark an item not in the list"
                        + "The format to unmark a task is: unmark (task list number)";
            } catch (NumberFormatException e) {
                return "Sorry, I'm only smart enough to find the task based on numbers.\n"
                        + "Please give a number. If you refuse, too bad, "
                        + "this is all I can do.";
            }
        }
    },
    /**
     * The todo command. Add a todo task to the list.
     */
    TODO("todo") {
        @Override
        public String execute(TaskList tasks, String[] commandLine) {
            try {
                Todo userTask = new Todo(commandLine[1], false);
                if (tasks.detectDuplicates(userTask)) {
                    tasks.addTask(userTask);
                    return "Understood, I've added the task:\n" + userTask
                            + "\nNow you have " + tasks.getSize() + " tasks in the list.";
                }
                return "Todo duplicate detected."
                        + "\nThere is already a todo task for '" + userTask.getDes() + "'.";
            } catch (IndexOutOfBoundsException e) {
                return "Sorry pal, but your description is empty.\n"
                        + "Please redo the command and remember to "
                        + "add a description of the action\n"
                        + "The format to schedule a todo task is: todo (Description)";
            }
        }
    },
    /**
     * The deadline command. Add a deadline task to the list.
     */
    DEADLINE("deadline") {
        @Override
        public String execute(TaskList tasks, String[] commandLine) {
            try {
                String[] differentParts = commandLine[1].split("/", 2);
                String[] deadLine = differentParts[1].split(" ", 2);
                //This is not dead code. This is used to test if the by date
                // can be parsed into a localDate
                LocalDate deadDate = LocalDate.parse(deadLine[1]);
                Deadline userTask = new Deadline(differentParts[0], false, deadLine[1]);
                if (tasks.detectDuplicates(userTask)) {
                    tasks.addTask(userTask);
                    return "Understood, I've added the task:\n" + userTask.toString() + "\n"
                            + "Now you have " + tasks.getSize() + " tasks in the list.";
                }
                return "Deadline duplicate detected.\nThere is already a duplicate dateline for '"
                        + userTask.getDes() + "' by "
                        + userTask.getBy() + ".";
            } catch (IndexOutOfBoundsException e) {
                return "Oh, you forgot to indicate when is the deadline "
                        + "or maybe you forgot the description.\n"
                        + "Please redo the command and remember to "
                        + "add the necessary information.\n"
                        + "The format to schedule a deadline is: deadline (Description) /by (Deadline Date + time)";
            } catch (DateTimeParseException e) {
                return "Incorrect Date format. Please insert format using yyyy-mm-dd.\n"
                        + "E.g. 2019-02-15 is 15 February 2019";
            }
        }
    },
    /**
     * The event command. Add an event task to the list.
     */
    EVENT("event") {
        @Override
        public String execute(TaskList tasks, String[] commandLine) {
            try {
                String[] differentParts = commandLine[1].split("/");
                String[] startDate = differentParts[1].split(" ", 2);
                String[] endDate = differentParts[2].split(" ", 2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String trimStartDate = startDate[1].trim();
                String trimEndDate = endDate[1].trim();
                //This is not dead code. This is used to test if the by date
                // can be parsed into a localDate
                LocalDateTime startDT = LocalDateTime.parse(trimStartDate, formatter);
                LocalDateTime endDT = LocalDateTime.parse(trimEndDate, formatter);
                Event userTask = new Event(differentParts[0], false, trimStartDate, trimEndDate);
                if (tasks.detectDuplicates(userTask)) {
                    tasks.addTask(userTask);
                    return "Understood, I've added the task:\n" + userTask
                            + "\nNow you have " + tasks.getSize() + " tasks in the list.";
                }
                return "Event duplicate detected.\nThere is already a duplicate event for '"
                        + userTask.getDes() + "' from "
                        + userTask.getFrom() + " to " + userTask.getTo() + ".";
            } catch (IndexOutOfBoundsException e) {
                return "You forgot to provide either a description, "
                        + "an start date or an end date for this event.\n"
                        + "Sorry, but mind reading is not installed in me yet.\n"
                        + "Please redo the command and remember to "
                        + "add the necessary information.\n"
                        + "The format to schedule a event is: event"
                        + " (Description) /from (Start date + time) /to (End date + time)";
            } catch (DateTimeParseException e) {
                return "Incorrect Date format. Please insert format using yyyy-MM-dd HHmm.\n"
                        + "E.g. 2019-02-15 10:40 is 15 February 2019 10:40 am";
            }
        }
    },
    /**
     * The delete command. Delete a task from the list.
     */
    DELETE("delete") {
        @Override
        public String execute(TaskList tasks, String[] commandLine) {
            try {
                int position = Integer.parseInt(commandLine[1]);
                assert position >= tasks.getSize() : "Position out of list";
                Task chosenTask = tasks.getPosValue(position - 1);
                tasks.removeTask(position - 1);
                return "Noted. I've removed this task:\n"
                        + chosenTask.toString()
                        + "\nNow you have " + tasks.getSize() + " tasks in the list.";
            } catch (IndexOutOfBoundsException e) {
                return "The number you gave exceeds how many items is in the list.\n"
                        + "Can't delete an item not in the list. "
                        + "Please try again.\n"
                        + "The format to delete a task is: delete (task list number)";
            } catch (NumberFormatException e) {
                return "Sorry, I'm only smart enough to find the task based on numbers.\n"
                        + "Please give a number. If you refuse, too bad, "
                        + "this is all I can do.\n"
                        + "The format to delete a task is: delete (task list number)";
            }
        }
    },
    /**
     * The find command. Finds all tasks with the keyword.
     */
    FIND("find") {
        @Override
        public String execute(TaskList tasks, String[] commandLine) {
            try {
                return tasks.findTask(commandLine[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Sorry, you didn't add anything after the keyword find.\n"
                        + "I'm gonna need you to indicate what you want to search for "
                        + "in the list. Thanks";
            }
        }
    },
    /**
     * The bye command. Displays goodbye message
     */
    BYE("bye") {
        @Override
        public String execute(TaskList tasks, String[] commandLine) throws IOException {
            Ui ui = new Ui();
            Storage storage = new Storage("victor.txt");
            storage.updateFile(tasks.returnList());
            Platform.exit();
            System.exit(0);
            return ui.showEnding();
        }
    };

    private final String command;
    Command(String command) {
        this.command = command;
    }
    public String getCommand() {
        return this.command;
    }

    /**
     * An abstract method that excutes actions according to specific commands
     * @param tasks Get the list of tasks
     * @param commandLine Get the commandLine
     */
    public abstract String execute(TaskList tasks, String[] commandLine) throws IOException;
}
