package atsisbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import atsisbot.task.Deadline;
import atsisbot.task.Event;
import atsisbot.task.Priority;
import atsisbot.task.Task;
import atsisbot.task.TaskList;
import atsisbot.task.Todo;

/**
 * The Command class represents various commands that can be executed on a
 * TaskList.
 * It provides methods for deleting tasks, marking tasks as done, marking tasks
 * as undone,
 * adding a todo task, adding an event task, adding a deadline task, and listing
 * all tasks.
 */
public class Command {
    /**
     * Deletes a task from the task list based on the given index.
     *
     * @param args     The index of the task to be deleted.
     * @param taskList The task list from which the task will be deleted.
     */
    public static void delete(String args, TaskList taskList) {
        int index = Integer.parseInt(args);
        try {
            Ui.printDeleteTaskMessage(taskList.getTask(index), taskList);
            taskList.removeTask(index);
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidTaskNumberMessage();
        }
    }

    /**
     * Marks a task as done based on the given index.
     *
     * @param args     the index of the task to be marked as done
     * @param taskList the list of tasks
     */
    public static void mark(String args, TaskList taskList) {
        int index = Integer.parseInt(args);
        try {
            taskList.getTask(index).markAsDone();
            Ui.printMarkMessage(taskList.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidTaskNumberMessage();
        }
    }

    /**
     * Marks a task as undone based on the given index.
     *
     * @param args     the index of the task to be marked as undone
     * @param taskList the list of tasks
     */
    public static void unmark(String args, TaskList taskList) {
        int index = Integer.parseInt(args);
        try {
            taskList.getTask(index).markAsUndone();
            Ui.printUnmarkMessage(taskList.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidTaskNumberMessage();
        }
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param args     The description of the todo task.
     * @param taskList The task list to add the task to.
     */
    public static void todo(String args, TaskList taskList) {
        if (args.equals("")) {
            Ui.printNoDescriptionMessage("todo");
        } else {
            taskList.addTask(new Todo(args));
            Ui.printAddTaskMessage(taskList.getTask(taskList.size()), taskList);
        }
    }

    /**
     * Parses the input arguments and creates a new Event task.
     * The input arguments should be in the format "description /from startDateTime
     * /to endDateTime".
     * If the input arguments are not in the correct format, an error message is
     * printed.
     * If the startDateTime or endDateTime cannot be parsed, an error message is
     * printed.
     * The created Event task is added to the taskList and a success message is
     * printed.
     *
     * @param args     the input arguments for creating the Event task
     * @param taskList the list of tasks to add the Event task to
     */
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
            LocalDateTime startDateTime = LocalDateTime.parse(fromTo[0], Task.getDateTimeFormatter());
            LocalDateTime endDateTime = LocalDateTime.parse(fromTo[1], Task.getDateTimeFormatter());
            taskList.addTask(new Event(descriptionAndFromTo[0], startDateTime, endDateTime));
            Ui.printAddTaskMessage(taskList.getTask(taskList.size()), taskList);
        } catch (DateTimeParseException e) {
            Ui.printInvalidEventFormatMessage();
        }
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param args     the input arguments containing the task description and
     *                 deadline
     * @param taskList the task list to add the task to
     */
    public static void deadline(String args, TaskList taskList) {
        String[] descriptionAndBy = args.split(" /by ");
        if (descriptionAndBy.length != 2) {
            Ui.printInvalidDeadlineFormatMessage();
            return;
        }
        try {
            LocalDateTime deadline = LocalDateTime.parse(descriptionAndBy[1], Task.getDateTimeFormatter());
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

    /**
     * Sets the priority of a task based on the given arguments.
     * The arguments should be in the format "<taskIndex> /priority <priority>".
     * If the arguments are not in the correct format, an error message is printed.
     * If the task index is invalid or the priority is not valid, respective error messages are printed.
     *
     * @param args      the arguments containing the task index and priority
     * @param taskList  the task list to modify
     */
    public static void setPriority(String args, TaskList taskList) {
        String[] descriptionAndPriority = args.split(" /priority ");
        if (descriptionAndPriority.length != 2) {
            Ui.printInvalidPriorityFormatMessage();
            return;
        }
        try {
            int index = Integer.parseInt(descriptionAndPriority[0]);
            taskList.getTask(index).setPriority(Priority.valueOf(descriptionAndPriority[1]));
            Ui.printSetPriorityMessage(taskList.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            Ui.printInvalidTaskNumberMessage();
        } catch (IllegalArgumentException e) {
            Ui.printInvalidPriorityFormatMessage();
        }
    }

    /**
     * Prints the list of tasks in the given task list.
     *
     * @param taskList the task list containing the tasks to be printed
     */
    public static void list(TaskList taskList) {
        Ui.printList(taskList);
    }
}
