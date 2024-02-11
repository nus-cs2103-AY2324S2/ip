package duke;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Parser handles the user input and output Parses the user input and calls the
 * appropriate methods
 */
public class Parser {
    private Scanner scanner;
    private TaskList taskList;
    private TaskRepository taskRepo;

    /*
     * Constructor for the Parser class It initializes the scanner and taskList
     * objects
     */
    public Parser() throws IOException, BotException {
        this.scanner = new Scanner(System.in);
        this.taskRepo = new TaskRepository();
        this.taskList = taskRepo.loadTasks();
    }

    /**
     * Starts the operator for user input and executes corresponding commands based
     * on user input.
     * This is the entry point for the bot.
     *
     * @throws BotException if there is an error in executing a command
     * @throws IOException  if there is an error in saving the task list to a file
     */
    public void startOperator() throws BotException, IOException {
        while (true) {
            System.out.print("> "); // print ">" before user input
            String userInput = scanner.nextLine();
            String[] userInputArr = userInput.split(" ");
            String command = userInputArr[0];

            switch (command) {
                case "bye":
                    Bot.botExitMsg();
                    return;
                case "list":
                    botListAllTasks();
                    break;
                case "help":
                    Bot.botHelpMsg();
                    break;
                case "mark":
                    try {
                        markTaskHandler(userInputArr);
                        // save to file
                        taskRepo.saveTasksToFile(taskList);
                    } catch (BotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "unmark":
                    try {
                        unmarkTaskHandler(userInputArr);
                        taskRepo.saveTasksToFile(taskList);
                    } catch (BotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "todo":
                    try {
                        handleTodoCommand(userInputArr);
                        taskRepo.saveTasksToFile(taskList);
                    } catch (BotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        handleDeadlineCommand(userInputArr);
                        taskRepo.saveTasksToFile(taskList);
                    } catch (BotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        handleEventCommand(userInputArr);
                        taskRepo.saveTasksToFile(taskList);
                    } catch (BotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    try {
                        handleDeleteCommand(userInputArr);
                        taskRepo.saveTasksToFile(taskList);
                    } catch (BotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    try {
                        handleInvalidCommand();
                    } catch (BotException e) {
                        System.out.println(e.getMessage());
                    }
            }
        }
    }

    /*
     * Helper methods for handling user input
     * 
     * 
     * 
     */

    /**
     * Handles an invalid command by throwing a BotException
     * 
     * @throws BotException if the command is invalid
     */
    private void handleInvalidCommand() throws BotException {
        throw new BotException("Eh, invalid command. I get what you're saying but I'm not gonna do it. Try again?");
    }

    /**
     * Handles the delete command by removing a task from the task list
     * 
     * @param userInputArr the array containing the user input
     * @throws BotException if the user input is invalid or the task number is out
     *                      of range
     */
    private void handleDeleteCommand(String[] userInputArr) throws BotException {
        if (userInputArr.length < 2) {
            throw new BotException("Please enter a task number to delete.");
        }
        int i;
        try {
            i = Integer.parseInt(userInputArr[1]);
        } catch (NumberFormatException e) {
            throw new BotException("Task number should be numeric.");
        }
        if (i <= 0 || i > taskList.getTaskCount()) {
            throw new BotException("Task number is out of range.");
        }
        Ui.printSeparatorLine();
        Task taskToRemove = taskList.getTaskByNum(i);
        taskList.removeTask(i);
        Bot.printDeleteTaskMsg();
        if (taskList.getTaskCount() > 0) {
            System.out.println("Removed Task: " + taskToRemove);
            Ui.printList(taskList.listTasks());
        } else {
            System.out.println("All tasks have been removed.");
        }
        TaskCountMsg();
        Ui.printSeparatorLine();
    }

    /**
     * Handles the "todo" command by adding a new todo task to the task list
     * 
     * @param userInputArr the array containing the user input
     * @throws BotException if the description of the todo is empty
     */
    private void handleTodoCommand(String[] userInputArr) throws BotException {
        if (userInputArr.length < 2) {
            throw new BotException("The description of a todo cannot be empty.");
        }
        String todoTask = String.join(" ", Arrays.copyOfRange(userInputArr, 1, userInputArr.length));
        this.taskList.addTodo(todoTask);
        addTaskMsg();
    }

    /**
     * Handles the "deadline" command by adding a deadline task to the task list
     * 
     * @param userInputArr the array containing the user input
     * @throws BotException if the user input is incomplete
     */
    private void handleDeadlineCommand(String[] userInputArr) throws BotException {
        if (userInputArr.length < 3) {
            throw new BotException("Please give some description and due date in deadline");
        }
        String deadlineTask = String.join(" ", Arrays.copyOfRange(userInputArr, 1, userInputArr.length))
                .split("/by", 2)[0].trim();
        String dueDate = String.join(" ", Arrays.copyOfRange(userInputArr,
                Arrays.asList(userInputArr).indexOf("/by") + 1, userInputArr.length));
        taskList.addDeadline(deadlineTask, dueDate);
        addTaskMsg();
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
    private void handleEventCommand(String[] userInputArr) throws BotException {
        if (userInputArr.length < 3) {
            throw new BotException("The description and time of an event cannot be empty.");
        }
        String eventTask = String.join(" ", Arrays.copyOfRange(userInputArr, 1, userInputArr.length))
                .split("/from", 2)[0].trim();
        int fromIndex = Arrays.asList(userInputArr).indexOf("/from") + 1;
        int toIndex = Arrays.asList(userInputArr).indexOf("/to");
        String startTime = String.join(" ", Arrays.copyOfRange(userInputArr, fromIndex, toIndex));
        String endTime = String.join(" ", Arrays.copyOfRange(userInputArr, toIndex + 1, userInputArr.length));
        taskList.addEvent(eventTask, startTime, endTime);
        addTaskMsg();
    }

    /**
     * Prints the add task message, displays the task list, and prints the task
     * count message
     */
    private void addTaskMsg() {
        Ui.printSeparatorLine();
        Bot.printAddTaskMsg();
        Ui.printList(taskList.listTasks());
        TaskCountMsg();
        Ui.printSeparatorLine();
    }

    /**
     * Handles the marking of a task as done
     *
     * @param inputs The user inputs
     * @throws BotException If the task number is missing, not numeric, or out of
     *                      range
     */
    private void markTaskHandler(String[] inputs) throws BotException {
        if (inputs.length < 2) {
            throw new BotException("Please enter a task number to mark.");
        }
        int i;
        try {
            i = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException e) {
            throw new BotException("Task number should be numeric.");
        }
        if (i <= 0 || i > taskList.getTaskCount()) {
            throw new BotException("Task number is out of range.");
        }
        Ui.printSeparatorLine();
        taskList.markTaskAsDone(i);
        Bot.printMarkTaskMsg();
        Ui.printList(taskList.listTasks());
        TaskCountMsg();
        Ui.printSeparatorLine();
    }

    /**
     * Handles the command to unmark a task
     *
     * @param inputs The array of inputs containing the task number to unmark
     * @throws BotException If the task number is not provided, is not numeric, or
     *                      is out of range
     */
    private void unmarkTaskHandler(String[] inputs) throws BotException {
        if (inputs.length < 2) {
            throw new BotException("Please enter a task number to unmark.");
        }
        int i;
        try {
            i = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException e) {
            throw new BotException("Task number should be numeric.");
        }
        if (i <= 0 || i > taskList.getTaskCount()) {
            throw new BotException("Task number is out of range.");
        }
        Ui.printSeparatorLine();
        taskList.markTaskAsUndone(i);
        Bot.printUnmarkTaskMsg();
        Ui.printList(taskList.listTasks());
        TaskCountMsg();
        Ui.printSeparatorLine();
    }

    /**
     * Prints all tasks in the task list.
     */
    private void botListAllTasks() {
        Ui.printSeparatorLine();
        Bot.botListAllMsg();
        Ui.printList(taskList.listTasks());
        TaskCountMsg();
        Ui.printSeparatorLine();
    }

    /**
     * Prints the number of tasks in the task list.
     */
    private void TaskCountMsg() {
        System.out.println("You have " + taskList.getTaskCount() + " tasks in your list.");
    }

}