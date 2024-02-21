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
        String feedback = null;

        int taskId = -1;

        if (curCommand == null) {
            throw new InvalidInputException.InvalidCommandException(cur[0]);
        }

        // TODO: Exception Handling for incorrect input
        //  - [ ] DateTimeParseException
        switch (curCommand) {
        case REMINDER:
            feedback = reminderService.getReminders();
            break;
        case BYE:
            feedback = this.getExitMessage();
            break;
        case LIST:
            feedback = this.taskService.getAllTasks();
            break;
        case FIND:
            String matchingValue = (this.parseFindInput(cur));
            feedback = this.taskService.findTasks(matchingValue);
            break;
        case MARK:
            if (cur.length <= 1) {
                throw new InvalidInputException.InvalidFormatException(
                        "No TaskId detected, please provide a TaskId", cur[0]);
            }

            try {
                taskId = Integer.parseInt(cur[1]) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidInputException.InvalidFormatException(
                        "Invalid TaskId detected, please provide the TaskId Number as seen in 'list' command", cur[0]);
            }

            feedback = this.taskService.markTaskCompleted(taskId);
            break;
        case UNMARK:
            if (cur.length <= 1) {
                throw new InvalidInputException.InvalidFormatException(
                        "No TaskId detected, please provide a TaskId", cur[0]);
            }

            try {
                taskId = Integer.parseInt(cur[1]) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidInputException.InvalidFormatException(
                        "Invalid TaskId detected, please provide the TaskId Number as seen in 'list' command", cur[0]);
            }

            feedback = this.taskService.markTaskUncompleted(taskId);
            break;
        case TODO:
            String taskName = this.parseTodoInput(cur);
            feedback = this.taskService.addTodo(taskName);
            break;
        case DEADLINE:
            String[] deadLineValues = this.parseDeadlineInput(cur);
            feedback = this.taskService.addDeadline(deadLineValues[0], deadLineValues[1]);
            break;
        case EVENT:
            String[] eventValues = this.parseEventInput(cur);
            feedback = this.taskService.addEvent(eventValues[0], eventValues[1], eventValues[2]);
            break;
        case DELETE:
            if (cur.length <= 1) {
                throw new InvalidInputException.InvalidFormatException(
                        "No TaskId detected, please provide a TaskId", cur[0]);
            }

            try {
                taskId = Integer.parseInt(cur[1]) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidInputException.InvalidFormatException(
                        "Invalid TaskId detected, please provide the TaskId Number as seen in 'list' command", cur[0]);
            }

            feedback = this.taskService.deleteTask(taskId);
            break;
        default:
            throw new InvalidInputException.InvalidCommandException(cur[0]);
        }

        return feedback;
    }

    public String getWelcomeMessage() {
        // Logo generated from : https://patorjk.com/software/taag/#p=display&f=Sub-Zero&t=OAK
        String logo =
                " ______     ______     __  __    \n" +
                        "/\\  __ \\   /\\  __ \\   /\\ \\/ /    \n" +
                        "\\ \\ \\/\\ \\  \\ \\  __ \\  \\ \\  _-.    \n" +
                        " \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \n" +
                        "  \\/_____/   \\/_/\\/_/   \\/_/\\/_/ \n";

        return "Hello from\n" + logo + "\n" +
                "----------------------------------------------\n" +
                "Welcome! I'm Professor Oak\n" +
                "What can I do for you?";

    }

    public String getExitMessage() {
        return "Goodbye! Hope to see you again!";

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
                    "No Due Date for Deadline detected. Please provide a Due Date by using '/by'");
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
                    "No From Datetime for Event detected. Please provide a From Datetime by using '/from'");
        }

        String[] datetimes = temp[1].split("/to");
        if (datetimes.length <= 1) {
            throw new InvalidInputException(
                    "No To Datetime for Event detected. Please provide a To Datetime by using '/to'");
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
