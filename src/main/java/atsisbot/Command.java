package atsisbot;

import atsisbot.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Command {
    public static void delete(String args, TaskList taskList) {
        int index = Integer.parseInt(args);
        try {
            Ui.printDeleteTaskMessage(taskList.getTask(index), taskList);
            taskList.removeTask(index);
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidTaskNumberMessage();
            return;
        }
    }

    public static void mark(String args, TaskList taskList) {
        int index = Integer.parseInt(args);
        try {
            taskList.getTask(index).markAsDone();
            Ui.printMarkMessage(taskList.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidTaskNumberMessage();
            return;
        }
    }

    public static void unmark(String args, TaskList taskList) {
        int index = Integer.parseInt(args);
        try {
            taskList.getTask(index).markAsUndone();
            Ui.printUnmarkMessage(taskList.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidTaskNumberMessage();
            return;
        }
    }

    public static void todo(String args, TaskList taskList) {
        if (args.equals("")) {
            Ui.printNoDescriptionMessage("todo");
        } else {
            taskList.addTask(new Todo(args));
            Ui.printAddTaskMessage(taskList.getTask(taskList.size()), taskList);
        }
    }

    public static void event(String args, TaskList taskList) {
        String[] descriptionAndFromTo = args.split(" /from ");
        if (descriptionAndFromTo.length != 2) {
            Ui.printInvalidEventFormatMessage();
            return;
        }
        String[] fromTo = descriptionAndFromTo[1].split(" /to ", 2);
        if (fromTo.length != 2) {
            Ui.printInvalidEventFormatMessage();
            return;
        }
        try {
            LocalDateTime startDateTime = LocalDateTime.parse(fromTo[0], Task.formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(fromTo[1], Task.formatter);
            taskList.addTask(new Event(descriptionAndFromTo[0], startDateTime, endDateTime));
            Ui.printAddTaskMessage(taskList.getTask(taskList.size()), taskList);
        } catch (DateTimeParseException e) {
            Ui.printInvalidEventFormatMessage();
        }
    }

    public static void deadline(String args, TaskList taskList) {
        String[] descriptionAndBy = args.split(" /by ");
        if (descriptionAndBy.length != 2) {
            Ui.printInvalidDeadlineFormatMessage();
            return;
        }
        try {
            LocalDateTime deadline = LocalDateTime.parse(descriptionAndBy[1], Task.formatter);
            taskList.addTask(new Deadline(descriptionAndBy[0], deadline));
            Ui.printAddTaskMessage(taskList.getTask(taskList.size()), taskList);
        } catch (DateTimeParseException e) {
            Ui.printInvalidDeadlineFormatMessage();
        }
    }

    /**
     * Finds tasks in the task list that match the given search query.
     * Prints the matching tasks to the user.
     *
     * @param args     the search query
     * @param taskList the task list to search in
     */
    public static void find(String args, TaskList taskList) {
        if (args.isBlank()) {
            Ui.printInvalidInputFormatMessage();
            return;
        }
        Ui.printFindMessage(taskList.findTasks(args));
    }

    public static void list(TaskList taskList) {
        Ui.printList(taskList);
    }
}
