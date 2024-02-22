package oak.feedback;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import oak.exceptions.InvalidInputException;
import oak.feedback.enums.CommandEnum;
import oak.task.ReminderService;
import oak.task.TaskService;

/**
 * The type Feedback service.
 */
public class FeedbackService {
    /** TaskService instance to be used */
    private TaskService taskService = new TaskService();
    /** ReminderService instance to be used */
    private ReminderService reminderService = new ReminderService();

    /**
     * Runs the feedback service on the user input
     * Parses the user input and calls the appropriate method in the Task Service
     *
     * @param userInput the user input
     * @return the output string to the user in the terminal
     * @throws InvalidInputException the invalid input exception
     * @throws IOException           the io exception
     */
    public String run(String userInput) throws InvalidInputException, IOException {
        String[] cur = userInput.split(" ");
        CommandEnum curCommand = CommandEnum.getCommandEnum(cur[0]);
        String feedback;

        int taskId = -1;

        if (curCommand == null) {
            throw new InvalidInputException.InvalidCommandException(cur[0]);
        }

        switch (curCommand) {
        case REMINDER:
            feedback = handleReminderCommand();
            break;
        case BYE:
            feedback = handleByeCommand();
            break;
        case LIST:
            feedback = handleListCommand();
            break;
        case FIND:
            feedback = handleFindCommand(cur);
            break;
        case MARK:
            feedback = handleMarkCommand(cur);
            break;
        case UNMARK:
            feedback = handleUnmarkCommand(cur);
            break;
        case TODO:
            feedback = handleTodoCommand(cur);
            break;
        case DEADLINE:
            feedback = handleDeadlineCommand(cur);
            break;
        case EVENT:
            feedback = handleEventCommand(cur);
            break;
        case DELETE:
            feedback = handleDeleteCommand(cur);
            break;
        default:
            throw new InvalidInputException.InvalidCommandException(cur[0]);
        }

        return feedback;
    }

    /**
     * Handles the reminder command, calling the appropriate method in Reminder Service to get all reminders
     *
     * @return the formatted String containing all reminders
     */
    private String handleReminderCommand() {
        return reminderService.getReminders();
    }

    /**
     * Handles the bye command, returning the exit message
     *
     * @return the formatted String of the exit message
     */
    private String handleByeCommand() {
        return "Goodbye! Hope to see you again!";
    }

    /**
     * Handles the list command, returning all marked and unmarked tasks stored in Oak-Dex
     *
     * @return the formatted String of all the tasks
     */
    private String handleListCommand() {
        return this.taskService.getAllTasks();
    }

    /**
     * Handles the find command, returning all tasks for which the input is a substring
     *
     * @return the formatted String of all the matching tasks
     */
    private String handleFindCommand(String[] input) {
        String matchingValue = (this.parseFindInput(input));
        return this.taskService.findTasks(matchingValue);
    }

    /**
     * Handles the mark command, marking the task as completed
     *
     * @param input
     * @return the formatted String of the status of marking the task
     * @throws InvalidInputException
     * @throws IOException
     */
    private String handleMarkCommand(String[] input) throws InvalidInputException, IOException {
        if (input.length <= 1) {
            throw new InvalidInputException.InvalidFormatException(
                    "No TaskId detected, please provide a TaskId", input[0]);
        }

        int taskId;
        try {
            taskId = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException.InvalidFormatException(
                    "Invalid TaskId detected, please provide the TaskId Number as seen in 'list' command", input[0]);
        }

        return this.taskService.markTaskCompleted(taskId);
    }

    /**
     * Handles the unmark command, marking the task as not completed
     *
     * @param input
     * @return the formatted String of the status of unmarking the task
     * @throws InvalidInputException
     * @throws IOException
     */
    private String handleUnmarkCommand(String[] input) throws InvalidInputException, IOException {
        if (input.length <= 1) {
            throw new InvalidInputException.InvalidFormatException(
                    "No TaskId detected, please provide a TaskId", input[0]);
        }

        int taskId;
        try {
            taskId = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException.InvalidFormatException(
                    "Invalid TaskId detected, please provide the TaskId Number as seen in 'list' command", input[0]);
        }

        return this.taskService.markTaskUncompleted(taskId);
    }

    /**
     * Handles the todo command, adding the todo item to tasks
     *
     * @param input
     * @return the formatted String of the status of adding the todo
     * @throws IOException
     */
    private String handleTodoCommand(String[] input) throws IOException {
        String taskName = this.parseTodoInput(input);
        return this.taskService.addTodo(taskName);
    }

    /**
     * Handles the Deadline command, adding the deadline item to tasks
     *
     * @param input
     * @return the formatted String of the status of adding the Deadline
     * @throws IOException
     * @throws InvalidInputException
     */
    private String handleDeadlineCommand(String[] input) throws IOException, InvalidInputException {
        String[] deadLineValues = this.parseDeadlineInput(input);
        return this.taskService.addDeadline(deadLineValues[0], deadLineValues[1]);
    }

    /**
     * Handles the Event command, adding the event item to tasks
     *
     * @param input
     * @return the formatted String of the status of adding the Event
     * @throws InvalidInputException
     * @throws IOException
     */
    private String handleEventCommand(String[] input) throws InvalidInputException, IOException {
        String[] eventValues = this.parseEventInput(input);
        return this.taskService.addEvent(eventValues[0], eventValues[1], eventValues[2]);
    }

    /**
     * Handles the Delete Command, removing the task from tasks
     *
     * @param input
     * @return the formatted String of the status of deleting the task
     * @throws InvalidInputException
     * @throws IOException
     */
    private String handleDeleteCommand(String[] input) throws InvalidInputException, IOException {
        if (input.length <= 1) {
            throw new InvalidInputException.InvalidFormatException(
                    "No TaskId detected, please provide a TaskId", input[0]);
        }

        int taskId;
        try {
            taskId = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException.InvalidFormatException(
                    "Invalid TaskId detected, please provide the TaskId Number as seen in 'list' command", input[0]);
        }

        return this.taskService.deleteTask(taskId);
    }

    /**
     * Parses the input provided by the user for the 'Todo' command
     *
     * @param input
     * @return the name of the Todo item
     */
    private String parseTodoInput(String[] input) {
        // @@author SherisseTJW-reused
        // Reused from https://stackoverflow.com/a/34440330
        // with minor modifications
        return Arrays.stream(input).skip(1).map(String::trim).collect(Collectors.joining(" "));
    }

    /**
     * Parses the input provided by the user for the 'Deadline' command
     *
     * @param input
     * @return A string array of the name of the task item, and the deadline provided by the user
     * @throws InvalidInputException
     */
    private String[] parseDeadlineInput(String[] input) throws InvalidInputException {
        // @@author SherisseTJW-reused
        // Reused from https://stackoverflow.com/a/34440330
        String fullInput = Arrays.stream(input).skip(1).collect(Collectors.joining(" "));

        String[] temp = fullInput.split("/by");

        if (temp.length <= 1) {
            throw new InvalidInputException(
                    "No Due Date for Deadline detected. Please provide a Due Date by using '/by'\n" +
                            "in the format: YYYY-MM-dd @ HH:mm");
        }

        return new String[] { temp[0].strip(), temp[1].strip() };
    }

    /**
     * Parses the input provided by the user for the 'Event' command
     *
     * @param input
     * @return A String Array of the name of the Task, the from datetime and the to datetime
     * @throws InvalidInputException
     */
    private String[] parseEventInput(String[] input) throws InvalidInputException {
        // Sample Event Input: event project meeting /from Mon 2pm /to 4pm

        // @@author SherisseTJW-reused
        // Reused from https://stackoverflow.com/a/34440330
        String fullInput = Arrays.stream(input).skip(1).collect(Collectors.joining(" "));

        String[] temp = fullInput.split("/from");
        if (temp.length <= 1) {
            throw new InvalidInputException(
                    "No From Datetime for Event detected. Please provide a From Datetime by using '/from'\n" +
                            "in the format: YYYY-MM-dd @ HH:mm");
        }

        String[] datetimes = temp[1].split("/to");
        if (datetimes.length <= 1) {
            throw new InvalidInputException(
                    "No To Datetime for Event detected. Please provide a To Datetime by using '/to'\n" +
                            "in the format: YYYY-MM-dd @ HH:mm");
        }

        return new String[] { temp[0].strip(), datetimes[0].strip(), datetimes[1].strip() };
    }

    /**
     * Parses the input provided by the user for the 'Find' command
     *
     * @param input
     * @return the matching value substring to compare tasks against
     */
    private String parseFindInput(String[] input) {
        // @@author SherisseTJW-reused
        // Reused from https://stackoverflow.com/a/34440330
        // with minor modifications
        return Arrays.stream(input).skip(1).map(String::trim).collect(Collectors.joining(" "));
    }
}
