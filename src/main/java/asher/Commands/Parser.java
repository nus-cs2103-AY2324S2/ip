package asher.Commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import asher.BotException;
import asher.Tasks.Deadline;
import asher.Tasks.Event;
import asher.Tasks.Task;
import asher.Tasks.TaskList;
import asher.Tasks.Todo;
import asher.Ui.Ui;

/**
 * The Parser class handles the parsing of user commands and return the corresponding actions for each command.
 */
public class Parser {
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Constructs a Parser object with the given Ui and the TaskList.
     * @param ui The Ui object to interact with user.
     * @param taskList The TaskList object to manage tasks.
     */
    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Parses the user input command and executes the actions for a command.
     * @param input The user input command.
     * @throws BotException BotException is thrown if there is an invalid command.
     */
    public void parseCommand(String input) throws BotException {
        String[] word = input.split(" ");
        String inputType = word[0];
        switch (inputType) {
            case "bye":
                break;
            case "list":
                ui.showTaskList(taskList);
                break;  
            case "mark":
                parseMarkCommand(input, taskList, ui);
                break;
            case "unmark":
                parseUnmarkCommand(input, taskList, ui);
                break;
            case "todo":
                parseToDoCommand(input);
                break;
            case "deadline":
                parseDeadlineCommand(input);
                break;
            case "event":
                parseEventCommand(input);
                break;
            case "delete":
                parseDeleteCommand(input, taskList, ui);
                break;
            case "find":
                parseFindCommand(input);
                break;
            default:
                throw new BotException("Invalid Command!");
        }
    }

    /**
     * Create a Todo task based on the user input.
     * @param input The user input command.
     * @return The created Todo List.
     * @throws BotException BotException is thrown if there is an invalid Todo command or the description is empty.
     */
    public static Todo createToDoCommand(String input) throws BotException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new BotException("Todo command is invalid!");
        }
        String description = parts[1].trim();

        if (description.isEmpty()) {
            throw new BotException("Todo description cannot be empty!");
        }
        return new Todo(description);
    }

    /**
     * Parses the user input command to create a Todo task.
     * @param input The user input command.
     * @throws BotException Bot Exception is thrown when there is an invalid command.
     */
    public void parseToDoCommand(String input) throws BotException {
        Todo todo = createToDoCommand(input);
        taskList.addTask(todo);
        ui.showAddTaskMessage(todo);
        int totalTask = taskList.getSize();
        ui.showNumberOfTaskInListMessage(totalTask);
    }

    /**
     * Create a Deadline task based on the user input.
     * @param input The user input command.
     * @return The created Deadline List.
     * @throws BotException BotException is thrown if there is an invalid Deadline command or the description/deadline is empty.
     */
    public static Deadline createDeadlineCommand(String input) throws BotException {
        int split = input.indexOf("/by");
        if (split == -1) {
            throw new BotException("Due date not found!");
        }
        if (split + 4 >= input.length()) {
            throw new BotException("No such deadline!");
        }

        String[] parts = input.substring(9).split("/by", 2);
        String description = parts[0].trim();
        String deadline = parts[1].trim();

        String[] deadlineParts = deadline.split(" ", 2);
        String dueDateInString = deadlineParts[0].trim();
        String dueTimeInString = deadlineParts[1].trim();
        LocalDate dueDate = LocalDate.parse(dueDateInString);
        LocalTime dueTime = LocalTime.parse(dueTimeInString);

        if (description.isEmpty() || deadline.isEmpty()) {
            throw new BotException("Description and deadline cannot be empty!");
        }
        return new Deadline(description, dueDate, dueTime);
    }

    /**
     * Parses the user input command to create a Deadline task.
     * @param input The user input command.
     * @throws BotException Bot Exception is thrown when there is an invalid command.
     */
    public void parseDeadlineCommand(String input) throws BotException {
        Deadline deadline = createDeadlineCommand(input);
        taskList.addTask(deadline);
        ui.showAddTaskMessage(deadline);
        int totalTask = taskList.getSize();
        ui.showNumberOfTaskInListMessage(totalTask);
    }

    /**
     * Create an Event task based on the user input.
     * @param input The user input command.
     * @return The created Event List.
     * @throws BotException BotException is thrown if there is an invalid Event command or when the endDate/endTime is
     * before the startDate/startTime or when the description/start/end is empty.
     */
    public static Event createEventCommand(String input) throws BotException {
        int split1 = input.indexOf("/from");
        int split2 = input.indexOf("/to");
        if (split1 == -1 || split2 == -1) {
            throw new BotException("Start and End time not found!");
        }

        if (split2 + 4 >= input.length()) {
            throw new BotException("End time not found!");
        }

        String description = input.substring(6, split1).trim();

        String start = input.substring(split1 + 6, split2).trim();
        String[] startParts = start.split(" ", 2);
        String startDateInString = startParts[0].trim();
        LocalDate startDate = LocalDate.parse(startDateInString);
        String startTimeInString = startParts[1].trim();
        LocalTime startTime = LocalTime.parse(startTimeInString);

        String end = input.substring(split2 + 4).trim();
        String[] endParts = end.split(" ", 2);
        String endDateInString = endParts[0].trim();
        LocalDate endDate = LocalDate.parse(endDateInString);
        String endTimeInString = endParts[1].trim();
        LocalTime endTime = LocalTime.parse(endTimeInString);

        if ((endDate.isBefore(startDate)) || ((endDate.isEqual(startDate)) && (endTime.isBefore(startTime)))) {
            throw new BotException("Start Date/Time is after End Date/Time!");
        }

        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new BotException("Description, StartTiming or EndTiming not found!");
        }

        return new Event(description, startDate, startTime, endDate, endTime);
    }

    /**
     * Parses the user input command to create an Event task.
     * @param input The user input command.
     * @throws BotException Bot Exception is thrown when there is an invalid command.
     */
    public void parseEventCommand(String input) throws BotException {
        Event event = createEventCommand(input);
        taskList.addTask(event);
        ui.showAddTaskMessage(event);
        int totalTask = taskList.getSize();
        ui.showNumberOfTaskInListMessage(totalTask);
    }

    /**
     * Parses the user input command to mark a task done and update the task status.
     * @param command The user input command.
     * @param taskList The TaskList containing the tasks.
     * @param ui The Ui object for displaying messages that the task is marked.
     * @throws BotException Bot Exception is thrown when the task is not found.
     */
    private static void parseMarkCommand(String command, TaskList taskList, Ui ui) throws BotException {
        int taskNumber = taskList.getTaskNumber(command);
        if (taskNumber != -1) {
            taskList.getTasks().get(taskNumber).markDone();
            ui.showMarkTaskMessage(taskList.getTasks().get(taskNumber));
        } else {
            throw new BotException("Invalid Task!");
        }
    }

    /**
     * Parses the user input command to unmark a task done and update the task status.
     * @param command The user input command.
     * @param taskList The TaskList containing the tasks.
     * @param ui The Ui object for displaying messages that the task is unmarked.
     * @throws BotException Bot Exception is thrown when the task is not found.
     */
    private static void parseUnmarkCommand(String command, TaskList taskList, Ui ui) throws BotException {
        int taskNumber = taskList.getTaskNumber(command);
        if (taskNumber != -1) {
            taskList.getTasks().get(taskNumber).markUndone();
            ui.showUnmarkTaskMessage(taskList.getTasks().get(taskNumber));
        } else {
            throw new BotException("Invalid Task!");
        }
    }

    /**
     * Parses the user input command to delete a task and remove it from the TaskList.
     * @param input The user input command.
     * @param taskList The TaskList containing the tasks.
     * @param ui The Ui object for displaying messages that delete is successful.
     * @throws BotException Bot Exception is thrown if there is an invalid command.
     */
    private static void parseDeleteCommand(String input, TaskList taskList, Ui ui) throws BotException {
        try {
            String[] words = input.split(" ");
            if (words.length < 2) {
                throw new BotException("Invalid format of input!");
            }
            int taskId = Integer.parseInt(words[1]);
            int taskIndex = taskList.getTaskIndexById(taskId);
            if (taskIndex != -1) {
                Task removedTask = taskList.deleteTask(taskIndex);
                if (removedTask != null) {
                    ui.showRemoveTaskMessage(removedTask);
                    ui.showNumberOfTaskInListMessage(taskList.getTasks().size());
                } else {
                    System.out.println("Task not found!");
                }
                taskList.updateTaskIds();
            } else {
                System.out.println("Task not found!");
            }
        } catch (Exception e) {
            throw new BotException("Task not found!");
        }
    }

    /**
     * Parses the user input command to find the tasks with the keyword.
     * @param input The user input command.
     */
    private void parseFindCommand(String input) {
        String keyword = input.substring(5).trim();
        ArrayList<Task> matchingTasks = taskList.searchKeyword(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}
