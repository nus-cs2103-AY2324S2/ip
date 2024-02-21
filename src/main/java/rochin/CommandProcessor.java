package rochin;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represent a command processor for handling user commands related to tasks.
 */
public class CommandProcessor {
    private static boolean isExitCommand;

    /**
     * Process the command based on its operation.
     *
     * @param tasks The TaskList to be modified.
     * @param ui    The user interface for displaying messages.
     */
    public static void process(String input, TaskList tasks, Ui ui) {
        if (!isExitCommand) {
            String[] splitCommand = input.split("\\s+");
            String operation = splitCommand[0].toLowerCase();

            switch (operation) {
                case "list":
                    ui.showTaskList(tasks.getAllTasks());
                    break;
                case "todo":
                    processTodoCommand(input, tasks, ui);
                    break;
                case "deadline":
                    processDeadlineCommand(input, tasks, ui);
                    break;
                case "event":
                    processEventCommand(input, tasks, ui);
                    break;
                case "delete":
                    processDeleteCommand(input, tasks, ui);
                    break;
                case "mark":
                    processMarkCommand(input, tasks, ui);
                    break;
                case "unmark":
                    processUnmarkCommand(input, tasks, ui);
                    break;
                case "find":
                    processFindCommand(input, tasks, ui);
                    break;
                default:
                    ui.showUnknownCommandError();
            }
        }
    }

    /**
     * Process a "todo" command and adds a new todo task to the TaskList.
     *
     * @param tasks The TaskList to be modified.
     * @param ui    The user interface for displaying messages.
     */
    public static void processTodoCommand(String input,TaskList tasks, Ui ui) {
        try {
            String description = input.substring("todo".length()).trim();
            if (description.isEmpty()) {
                throw new RochinException("OOPS!!! The description of a todo cannot be empty.");
            }
            tasks.addTask(new TodoTask(description));
            ui.showTaskAddedMessage(tasks.getAllTasks());
        } catch (RochinException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Process a "deadline" command and adds a new deadline task to the TaskList.
     *
     * @param tasks The TaskList to be modified.
     * @param ui    The user interface for displaying messages.
     */
    public static void processDeadlineCommand(String input,TaskList tasks, Ui ui) {
        try {
            String descriptionWithDate = input.substring("deadline".length()).trim();
            // Splitting the description and deadline by "/by"
            String[] parts = descriptionWithDate.split("/by");
            if (parts.length != 2) {
                throw new RochinException("OOPS!!! Please provide both a description and a deadline for a deadline task.");
            }
            String description = parts[0].trim();
            String deadline = parts[1].trim();
            // Parse the deadline string to LocalDateTime
            LocalDateTime deadlineDateTime = DateAndTime.parseDateTime(deadline);
            DeadlineTask ddlTask = new DeadlineTask(description, deadlineDateTime);
            tasks.addTask(ddlTask);
            ui.showTaskAddedMessage(tasks.getAllTasks());
        } catch (RochinException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Process an "event" command and adds a new event task to the TaskList.
     *
     * @param tasks The TaskList to be modified.
     * @param ui    The user interface for displaying messages.
     */
    public static void processEventCommand(String input,TaskList tasks, Ui ui) {
        try {
            String descriptionWithDate = input.substring("event".length()).trim();
            // Splitting the description, starting datetime, and ending datetime by "/from" and "/to"
            String[] parts = descriptionWithDate.split("/from");
            if (parts.length != 2) {
                throw new RochinException("OOPS!!! Please provide a description, start time, and end time for an event task.");
            }
            String description = parts[0].trim();
            String[] dateTimeParts = parts[1].split("/to");
            if (dateTimeParts.length != 2) {
                throw new RochinException("OOPS!!! Please provide both starting and ending date/time for the event.");
            }
            String fromDateTime = dateTimeParts[0].trim();
            String toDateTime = dateTimeParts[1].trim();
            // Parse the starting and ending date/time strings to LocalDateTime
            LocalDateTime fromDateTimeObject = DateAndTime.parseDateTime(fromDateTime);
            LocalDateTime toDateTimeObject = DateAndTime.parseDateTime(toDateTime);
            EventTask eventTask = new EventTask(description, fromDateTimeObject, toDateTimeObject);
            tasks.addTask(eventTask);
            ui.showTaskAddedMessage(tasks.getAllTasks());
        } catch (RochinException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Process a "delete" command and deletes a task from the TaskList.
     *
     * @param tasks The TaskList to be modified.
     * @param ui    The user interface for displaying messages.
     */
    public static void processDeleteCommand(String input, TaskList tasks, Ui ui) {
        int taskIndex = getTaskIndex(input);
        tasks.deleteTask(taskIndex);
        ui.showTaskDeletedMessage(tasks.getAllTasks());
    }

    /**
     * Process a "mark" command and marks a task as done in the TaskList.
     *
     * @param tasks The TaskList to be modified.
     * @param ui    The user interface for displaying messages.
     */
    public static void processMarkCommand(String input,TaskList tasks, Ui ui) {
        int taskIndex = getTaskIndex(input);
        tasks.markTaskAsDone(taskIndex);
        ui.showTaskMarkedAsDoneMessage(tasks.getAllTasks());
    }

    /**
     * Process an "unmark" command and unmarks a task as done in the TaskList.
     *
     * @param tasks The TaskList to be modified.
     * @param ui    The user interface for displaying messages.
     */
    public static void processUnmarkCommand(String input,TaskList tasks, Ui ui) {
        int taskIndex = getTaskIndex(input);
        tasks.unmarkTaskAsDone(taskIndex);
        ui.showTaskUnmarkedAsDoneMessage(tasks.getAllTasks());
    }

    /**
     * Extract the task index from the command.
     *
     * @return The index of the task.
     */
    public static int getTaskIndex(String input) {
        try {
            String[] splitCommand = input.split("\\s+");
            return Integer.parseInt(splitCommand[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.showInvalidCommandError();
            return -1;
        }
    }

    /**
     * Process the "find" command, searching for tasks containing the specified keyword.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui    The Ui for displaying user interface messages.
     */
    public static void processFindCommand(String input,TaskList tasks, Ui ui) {
        try {
            String keyword = input.substring("find".length()).trim();
            if (keyword.isEmpty()) {
                throw new RochinException("OOPS!!! Please provide a keyword to search for.");
            }
            List<Task> matchingTasks = tasks.findTasks(keyword);
            ui.showTaskList(matchingTasks);
        } catch (RochinException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Check if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExitCommand(String input) {
        isExitCommand = input.equalsIgnoreCase("bye");
        return isExitCommand;
    }
}
