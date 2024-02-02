package Oak.Feedback;

import Oak.Feedback.enums.CommandEnum;
import Oak.exceptions.InvalidInputException;
import Oak.task.TaskService;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FeedbackService {
    private TaskService taskService = new TaskService();

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
            case Bye:
                feedback = this.getExitMessage();
                break;
            case List:
                feedback = this.taskService.getAllTasks();
                break;
            case Mark:
                if (cur.length <= 1) {
                    throw new InvalidInputException.InvalidFormatException("No TaskId detected, please provide a TaskId", cur[0]);
                }

                try {
                    taskId = Integer.parseInt(cur[1]) - 1;
                }
                catch (NumberFormatException e) {
                    throw new InvalidInputException.InvalidFormatException("Invalid TaskId detected, please provide the TaskId Number as seen in 'list' command", cur[0]);
                }

                feedback = this.taskService.markTaskCompleted(taskId);
                break;
            case Unmark:
                if (cur.length <= 1) {
                    throw new InvalidInputException.InvalidFormatException("No TaskId detected, please provide a TaskId", cur[0]);
                }

                try {
                    taskId = Integer.parseInt(cur[1]) - 1;
                }
                catch (NumberFormatException e) {
                    throw new InvalidInputException.InvalidFormatException("Invalid TaskId detected, please provide the TaskId Number as seen in 'list' command", cur[0]);
                }

                feedback = this.taskService.markTaskUncompleted(taskId);
                break;
            case Todo:
                String taskName = this.parseTodoInput(cur);
                feedback = this.taskService.addTodo(taskName);
                break;
            case Deadline:
                String[] deadLineValues = this.parseDeadlineInput(cur);
                feedback = this.taskService.addDeadline(deadLineValues[0], deadLineValues[1]);
                break;
            case Event:
                String[] eventValues = this.parseEventInput(cur);
                feedback = this.taskService.addEvent(eventValues[0], eventValues[1], eventValues[2]);
                break;
            case Delete:
                if (cur.length <= 1) {
                    throw new InvalidInputException.InvalidFormatException("No TaskId detected, please provide a TaskId", cur[0]);
                }

                try {
                    taskId = Integer.parseInt(cur[1]) - 1;
                }
                catch (NumberFormatException e) {
                    throw new InvalidInputException.InvalidFormatException("Invalid TaskId detected, please provide the TaskId Number as seen in 'list' command", cur[0]);
                }

                feedback = this.taskService.deleteTask(taskId);
                break;
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

    private String parseTodoInput(String[] input) {
        // Sample Todo Input: todo borrow book

        // @@author SherisseTJW-reused
        // Reused from https://stackoverflow.com/a/34440330
        // with minor modifications
        return Arrays.stream(input).skip(1).map(String::trim).collect(Collectors.joining(" "));
    }

    private String[] parseDeadlineInput(String[] input) throws InvalidInputException {
        // Sample Deadline Input: deadline return book /by Sunday

        // @@author SherisseTJW-reused
        // Reused from https://stackoverflow.com/a/34440330
        String fullInput = Arrays.stream(input).skip(1).collect(Collectors.joining(" "));

        String[] temp = fullInput.split("/by");

        if (temp.length <= 1) {
            throw new InvalidInputException("No Due Date for Deadline detected. Please provide a Due Date by using '/by'");
        }

        return new String[] { temp[0].strip(), temp[1].strip() };
    }

    private String[] parseEventInput(String[] input) throws InvalidInputException {
        // Sample Event Input: event project meeting /from Mon 2pm /to 4pm

        // @@author SherisseTJW-reused
        // Reused from https://stackoverflow.com/a/34440330
        String fullInput = Arrays.stream(input).skip(1).collect(Collectors.joining(" "));

        String[] temp = fullInput.split("/from");
        if (temp.length <= 1) {
            throw new InvalidInputException("No From Datetime for Event detected. Please provide a From Datetime by using '/from'");
        }

        String[] datetimes = temp[1].split("/to");
        if (datetimes.length <= 1) {
            throw new InvalidInputException("No To Datetime for Event detected. Please provide a To Datetime by using '/to'");
        }

        return new String[] { temp[0].strip(), datetimes[0].strip(), datetimes[1].strip() };
    }
}