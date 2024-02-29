package duke;

import java.util.Arrays;
import java.util.List;

import duke.Commands.Command;

/**
 * The Duke class is the main entry point for the bot application itself
 * It uses an Operator to connect the user to the bot
 * The operator is responsible for handling user input and bot output
 * It's the entry point for the bot
 */
public class Duke {
    private TaskList taskList;
    private TaskRepository taskRepository;

    /**
     * Constructor for the Duke class.
     * It initializes the taskRepository and taskList objects.
     * 
     * @throws BotException if there is an error loading tasks from the repository.
     */
    public Duke() throws BotException {
        this.taskRepository = new TaskRepository();
        this.taskList = taskRepository.loadTasksFromFile();
    }

    /**
     * Processes user input and returns a response.
     * 
     * @param userInput The user's input as a string.
     * @return The bot's response as a string.
     */
    public String processInput(String userInput) {
        assert userInput != null : "User input should not be null";
        assert !userInput.trim().isEmpty() : "User input should not be empty";
        String[] userInputArray = userInput.split(" ");
        String command = userInputArray[0];
        Command commandEnum = Commands.getCommand(command);

        try {
            switch (commandEnum) {
                case BYE:
                    return Bot.getBotExitMsg();
                case HELP:
                    return Bot.getBotHelpMsg();
                case LIST:
                    return listAllMsg(taskList);
                case MARK:
                    return handleMarkTask(userInputArray);
                case UNMARK:
                    return handleUnmarkTask(userInputArray);
                case TODO:
                    return handleTodoCommand(userInputArray);
                case DEADLINE:
                    return handleDeadlineCommand(userInputArray);
                case EVENT:
                    return handleEventCommand(userInputArray);
                case DELETE:
                    return handleDeleteCommand(userInputArray);
                case FIND:
                    return handleFindCommand(userInputArray);
                case EMPTY:
                    return Bot.getEmptyInputMsg();
                case INVALID:
                default:
                    return Bot.getInvalidInputMsg();
            }
        } catch (BotException e) {
            return e.getMessage();
        }
    }

    /* ------------------------- Helpers and Handlers ------------------------- */

    /**
     * Returns a string representing bot's response with tasklist
     *
     * @param taskList The task list to be listed.
     * @return A string representing all tasks in the task list.
     */
    private String listAllMsg(TaskList taskList) {
        assert taskList != null : "Task list should not be null";

        StringBuilder tasksMsg = new StringBuilder();
        tasksMsg.append(Bot.getBotListAllMsg())
                .append("\n");
        tasksMsg.append(this.taskList.toString())
                .append("\n");
        tasksMsg.append(TaskCountMsg())
                .append("\n");
        return tasksMsg.toString();
    }

    /*
     * @return A string representing the bot's response to the add command
     */
    private String addTaskMsg() {
        assert this.taskList != null : "Task list should not be null";
        return Bot.getAddTaskMsg() + "\n" + this.taskList.toString() + "\n" + TaskCountMsg() + "\n";
    }

    /*
     * @return A string representing the bot's response to the mark command
     */
    private String markMsg() {
        assert this.taskList != null : "Task list should not be null";
        return Bot.getMarkTaskMsg() + "\n" + this.taskList.toString() + "\n" + TaskCountMsg() + "\n";
    }

    /*
     * @return A string representing the bot's response to the unmark command
     */
    private String unmarkMsg() {
        assert this.taskList != null : "Task list should not be null";
        return Bot.getUnmarkTaskMsg() + "\n" + this.taskList.toString() + "\n" + TaskCountMsg() + "\n";
    }

    /*
     * @param status The status of the delete operation
     * 
     * @return A string representing the bot's response to the delete command
     */
    private String deleteMsg(String status) {
        assert this.taskList != null : "Task list should not be null";
        assert status != null : "Status should not be null";
        return Bot.getBotDeleteMsg() + "\n" + this.taskList.toString()
                + "\n" + TaskCountMsg() + "\n" + status + "\n";
    }

    /*
     * @param The tasks found by the find command
     * 
     * @return A string representing the bot's response to the find command
     */
    private String findMsg(String tasks) {
        assert tasks != null : "Tasks to find should not be null";
        return Bot.getBotFindMsg() + "\n" + tasks + "\n" + TaskCountMsg() + "\n";
    }

    /*
     * @return A string representing the number of tasks in the task list
     */
    private String TaskCountMsg() {
        assert this.taskList != null : "Task list should not be null";
        return "You have " + taskList.getTaskCount() + " tasks in your list.";
    }

    /* ------------------------------ Handlers ------------------------------ */

    /**
     * Handles the "find" command by searching for tasks that contain a specified
     * keyword
     *
     * @param userInputArray the array of user input tokens
     * @throws BotException if the keyword is not provided
     * 
     * @return A string representing the tasks found
     */
    private String handleFindCommand(String[] userInputArray) throws BotException {
        assert userInputArray != null : "User input array should not be null";
        assert userInputArray.length > 0 : "User input array should not be empty";

        if (userInputArray.length < 2) {
            throw new BotException("Please enter a keyword to search for.");
        }
        String keyword = userInputArray[1];
        List<Task> matchingTasks = taskList.getTasksByKeyword(keyword);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append((i + 1) + "." + matchingTasks.get(i)).append("\n");
        }
        return findMsg(sb.toString());
    }

    /*
     * Handles the delete command by removing a task from the task list
     * 
     * @param userInputArr the array containing the user input
     * 
     * @throws BotException if the user input is invalid or the task number is out
     * of range
     * 
     * @return A string representing the status of the delete operation
     */
    private String handleDeleteCommand(String[] userInputArray) throws BotException {
        assert userInputArray != null : "User input array should not be null";
        assert userInputArray.length > 0 : "User input array should not be empty";

        StringBuilder status = new StringBuilder();
        if (userInputArray.length < 2) {
            throw new BotException("Please enter a task number to delete.");
        }
        int i;
        try {
            i = Integer.parseInt(userInputArray[1]);
            assert i > 0 : "Task number should be greater than 0";
        } catch (NumberFormatException e) {
            throw new BotException("Task number should be numeric.");
        }
        if (i <= 0 || i > taskList.getTaskCount()) {
            throw new BotException("Task number is out of range.");
        }
        Task taskToRemove = taskList.getTaskByNum(i);
        taskList.removeTask(i);
        if (taskList.getTaskCount() > 0) {
            status.append("Task removed: ").append(taskToRemove).append("\n");
        } else {
            status.append("No more tasks in the list.");
        }
        taskRepository.saveTasksToFile(taskList);
        return deleteMsg(status.toString());
    }

    /**
     * Handles the marking of a task as done
     *
     * @param userInputArray The user inputs
     * @throws BotException If the task number is missing, not numeric, or out of
     *                      range
     */
    private String handleMarkTask(String[] userInputArray) throws BotException {
        assert userInputArray != null : "User input array should not be null";
        assert userInputArray.length > 0 : "User input array should not be empty";

        if (userInputArray.length < 2) {
            throw new BotException("Please enter a task number to mark.");
        }
        int i;
        try {
            i = Integer.parseInt(userInputArray[1]);
            assert i > 0 : "Task number should be greater than 0";
        } catch (NumberFormatException e) {
            throw new BotException("Task number should be numeric.");
        }
        if (i <= 0 || i > taskList.getTaskCount()) {
            throw new BotException("Task number is out of range.");
        }
        taskList.markTaskAsDone(i);
        taskRepository.saveTasksToFile(taskList);
        return markMsg();
    }

    /**
     * Handles the command to unmark a task
     *
     * @param userInputArray The array of inputs containing the task number to unmark
     * @throws BotException If the task number is not provided, is not numeric, or
     *                      is out of range
     */
    private String handleUnmarkTask(String[] userInputArray) throws BotException {
        assert userInputArray != null : "User input array should not be null";
        assert userInputArray.length > 0 : "User input array should not be empty";

        if (userInputArray.length < 2) {
            throw new BotException("Please enter a task number to unmark.");
        }
        int i;
        try {
            i = Integer.parseInt(userInputArray[1]);
            assert i > 0 : "Task number should be greater than 0";
        } catch (NumberFormatException e) {
            throw new BotException("Task number should be numeric.");
        }
        if (i <= 0 || i > taskList.getTaskCount()) {
            throw new BotException("Task number is out of range.");
        }
        taskList.unmarkTaskAsDone(i);
        taskRepository.saveTasksToFile(taskList);
        return unmarkMsg();
    }

    /**
     * Handles the "todo" command by adding a new todo task to the task list
     * 
     * @param userInputArray the array containing the user input
     * @throws BotException if the description of the todo is empty
     */
    private String handleTodoCommand(String[] userInputArray) throws BotException {
        assert userInputArray != null : "User input array should not be null";

        if (userInputArray.length < 2) {
            throw new BotException("The description of a todo cannot be empty.");
        }
        String todoTask = String.join(" ", Arrays.copyOfRange(userInputArray, 1, userInputArray.length));
        this.taskList.addTodo(todoTask);
        taskRepository.saveTasksToFile(taskList);
        return addTaskMsg();
    }

    /**
     * Handles the "deadline" command by adding a deadline task to the task list
     * 
     * @param userInputArray the array containing the user input
     * @throws BotException if the user input is incomplete
     */
    private String handleDeadlineCommand(String[] userInputArray) throws BotException {
        assert userInputArray != null : "User input array should not be null";

        if (userInputArray.length < 3) {
            throw new BotException("Please give some description and due date in deadline");
        }
        String deadlineTask = String.join(" ", Arrays.copyOfRange(userInputArray, 1,
                userInputArray.length)).split("/by", 2)[0].trim();

        String dueDate = String.join(" ", Arrays.copyOfRange(userInputArray,
                Arrays.asList(userInputArray).indexOf("/by") + 1, userInputArray.length));

        taskList.addDeadline(deadlineTask, dueDate);
        taskRepository.saveTasksToFile(taskList);
        return addTaskMsg();
    }

    /**
     * Handles the event command by extracting the event task, start time, and end
     * time from the user input array
     * Adds the event task to the task list with the specified start time and end
     * time
     *
     * @param userInputArray the user input array containing the event command and its
     *                     arguments
     * @throws BotException if the description and time of an event are empty
     */
    private String handleEventCommand(String[] userInputArray) throws BotException {
        assert userInputArray != null : "User input array should not be null";

        if (userInputArray.length < 3) {
            throw new BotException("The description and time of an event cannot be empty.");
        }
        String eventTask = String.join(" ", Arrays.copyOfRange(userInputArray, 1,
                userInputArray.length))
                .split("/from", 2)[0].trim();
        int fromIndex = Arrays.asList(userInputArray).indexOf("/from") + 1;
        int toIndex = Arrays.asList(userInputArray).indexOf("/to");
        String startTime = String.join(" ", Arrays.copyOfRange(userInputArray,
                fromIndex, toIndex));
        String endTime = String.join(" ", Arrays.copyOfRange(userInputArray, toIndex
                + 1, userInputArray.length));
        taskList.addEvent(eventTask, startTime, endTime);
        taskRepository.saveTasksToFile(taskList);
        return addTaskMsg();
    }
}