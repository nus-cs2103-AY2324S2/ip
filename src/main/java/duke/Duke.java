package duke;

import java.io.IOException;
import java.util.Arrays;

/**
 * The Duke class is the main entry point for the bot application itself
 * It uses an Operator to connect the user to the bot
 * The operator is responsible for handling user input and bot output
 * It's the entry point for the bot
 */
public class Duke {
    public static void main(String[] args) throws BotException, IOException {
        // Initial
        System.out.println(Ui.wrapWithSepLine(Bot.getGreeting()));

        // Starting parser to work with bot
        Parser operator = new Parser();
        operator.startOperator();

    }

    private TaskList taskList;
    private TaskRepository taskRepository;

    /*
     * Constructor for the Parser class It initializes the scanner and taskList
     * objects
     */
    public Duke() throws BotException {
        this.taskRepository = new TaskRepository();
        this.taskList = taskRepository.loadTasks();
    }

    // Handle various user inputs
    public String processInput(String userInput) {
        String[] userInputArray = userInput.split(" ");
        String command = userInputArray[0];

        switch (command) {
            case "bye":
                return Bot.botExitMsgGui();
            case "help":
                return Bot.botHelpMsgGui();
            case "list":
                return listAllMsg(taskList);
            case "mark":
                try {
                    markTaskHandler(userInputArray);
                    // save to file
                    taskRepository.saveTasksToFile(taskList);
                    return markMsg();
                } catch (BotException e) {
                    return e.getMessage();
                }
            case "unmark":
                try {
                    unmarkTaskHandler(userInputArray);
                    taskRepository.saveTasksToFile(taskList);
                    return unmarkMsg();
                } catch (BotException e) {
                    return e.getMessage();
                }
            case "todo":
                try {
                    handleTodoCommand(userInputArray);
                    taskRepository.saveTasksToFile(taskList);
                    return addTaskMsg();
                } catch (BotException e) {
                    return e.getMessage();
                }
            case "deadline":
                try {
                    handleDeadlineCommand(userInputArray);
                    taskRepository.saveTasksToFile(taskList);
                    return addTaskMsg();
                } catch (BotException e) {
                    return e.getMessage();
                }
            case "event":
                try {
                    handleEventCommand(userInputArray);
                    taskRepository.saveTasksToFile(taskList);
                    return addTaskMsg();
                } catch (BotException e) {
                    return e.getMessage();
                }
                // break;
                // case "delete":
                // try {
                // handleDeleteCommand(userInputArray);
                // taskRepository.saveTasksToFile(taskList);
                // } catch (BotException e) {
                // System.out.println(e.getMessage());
                // }
                // break;
                // case "find":
                // handleFindCommand(userInputArray);
                // break;
            default:
                return Bot.invalidInputMsgGui();
        }
    }

    /**
     * Returns a string representing all tasks in the task list.
     *
     * @param taskList The task list to be listed.
     * @return A string representing all tasks in the task list.
     */
    private String listAllMsg(TaskList taskList) {
        StringBuilder tasksMsg = new StringBuilder();
        tasksMsg.append(Bot.botListAllMsgGui()).append("\n");
        tasksMsg.append(this.taskList.toString()).append("\n");
        tasksMsg.append(TaskCountMsg()).append("\n");
        return tasksMsg.toString();
    }

    private String addTaskMsg() {
        return Bot.printAddTaskMsgGui() + "\n" + this.taskList.toString() + "\n" + TaskCountMsg() + "\n";
    }

    private String markMsg() {
        return Bot.printMarkTaskMsgGui() + "\n" + this.taskList.toString() + "\n" + TaskCountMsg() + "\n";
    }

    private String unmarkMsg() {
        return Bot.printUnmarkTaskMsgGui() + "\n" + this.taskList.toString() + "\n" + TaskCountMsg() + "\n";
    }

    /**
     * Prints the number of tasks in the task list.
     */
    private String TaskCountMsg() {
        return "You have " + taskList.getTaskCount() + " tasks in your list.";
    }

    /**
     * Handles the marking of a task as done
     *
     * @param inputs The user inputs
     * @throws BotException If the task number is missing, not numeric, or out of
     *                      range
     */
    private void markTaskHandler(String[] userInputArray) throws BotException {
        if (userInputArray.length < 2) {
            throw new BotException("Please enter a task number to mark.");
        }
        int i;
        try {
            i = Integer.parseInt(userInputArray[1]);
        } catch (NumberFormatException e) {
            throw new BotException("Task number should be numeric.");
        }
        if (i <= 0 || i > taskList.getTaskCount()) {
            throw new BotException("Task number is out of range.");
        }
        taskList.markTaskAsDone(i);
    }

    /**
     * Handles the command to unmark a task
     *
     * @param inputs The array of inputs containing the task number to unmark
     * @throws BotException If the task number is not provided, is not numeric, or
     *                      is out of range
     */
    private void unmarkTaskHandler(String[] userInputArray) throws BotException {
        if (userInputArray.length < 2) {
            throw new BotException("Please enter a task number to unmark.");
        }
        int i;
        try {
            i = Integer.parseInt(userInputArray[1]);
        } catch (NumberFormatException e) {
            throw new BotException("Task number should be numeric.");
        }
        if (i <= 0 || i > taskList.getTaskCount()) {
            throw new BotException("Task number is out of range.");
        }
        taskList.markTaskAsUndone(i);
    }

    /**
     * Handles the "todo" command by adding a new todo task to the task list
     * 
     * @param userInputArr the array containing the user input
     * @throws BotException if the description of the todo is empty
     */
    private void handleTodoCommand(String[] userInputArray) throws BotException {
        if (userInputArray.length < 2) {
            throw new BotException("The description of a todo cannot be empty.");
        }
        String todoTask = String.join(" ", Arrays.copyOfRange(userInputArray, 1, userInputArray.length));
        this.taskList.addTodo(todoTask);
    }

    /**
     * Handles the "deadline" command by adding a deadline task to the task list
     * 
     * @param userInputArr the array containing the user input
     * @throws BotException if the user input is incomplete
     */
    private void handleDeadlineCommand(String[] userInputArray) throws BotException {
        if (userInputArray.length < 3) {
            throw new BotException("Please give some description and due date in deadline");
        }
        String deadlineTask = String.join(" ", Arrays.copyOfRange(userInputArray, 1,
                userInputArray.length)).split("/by", 2)[0].trim();

        String dueDate = String.join(" ", Arrays.copyOfRange(userInputArray,
                Arrays.asList(userInputArray).indexOf("/by") + 1, userInputArray.length));

        taskList.addDeadline(deadlineTask, dueDate);
    }

    /**
     * Handles the event command by extracting the event task, start time, and end
     * time from the user input array
     * Adds the event task to the task list with the specified start time and end
     * time
     *
     * @param userInputArr the user input array containing the event command and its
     *                     arguments
     * @throws BotException if the description and time of an event are empty
     */
    private void handleEventCommand(String[] userInputArray) throws BotException {
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
    }
}