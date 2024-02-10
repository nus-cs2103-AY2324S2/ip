package duke;

import java.io.IOException;
import java.util.*;

public class Parser {
    // Parser handles the user input and output
    // Parses the user input and calls the appropriate methods
    private Scanner scanner;
    private TaskList taskList;
    private TaskRepository taskRepo;

    public Parser() throws IOException, BotException {
        this.scanner = new Scanner(System.in);
        this.taskRepo = new TaskRepository();
        this.taskList = taskRepo.loadTasks();
    }

    // Starts the bot, and handles user input and output
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

    // All the methods below are private and are used to handle the different
    // commands
    // helper functions

    private void handleInvalidCommand() throws BotException {
        // System.out.println("Reached haere");
        throw new BotException("Eh, invalid command. I get what you're saying but I'm not gonna do it. Try again?");
    }

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
        Ui.printSepLine();
        // System.out.println("Reached here, trying to remove");
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
        Ui.printSepLine();
    }

    private void handleTodoCommand(String[] userInputArr) throws BotException {
        if (userInputArr.length < 2) {
            throw new BotException("The description of a todo cannot be empty.");
        }
        String todoTask = String.join(" ", Arrays.copyOfRange(userInputArr, 1, userInputArr.length));
        this.taskList.addTodo(todoTask);
        addTaskMsg();
    }

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

    private void addTaskMsg() {
        Ui.printSepLine();
        Bot.printAddTaskMsg();
        Ui.printList(taskList.listTasks());
        TaskCountMsg();
        Ui.printSepLine();
    }

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
        Ui.printSepLine();
        taskList.markTaskAsDone(i);
        Bot.printMarkTaskMsg();
        Ui.printList(taskList.listTasks());
        TaskCountMsg();
        Ui.printSepLine();
    }

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
        Ui.printSepLine();
        taskList.markTaskAsUndone(i);
        Bot.printUnmarkTaskMsg();
        Ui.printList(taskList.listTasks());
        TaskCountMsg();
        Ui.printSepLine();
    }

    private void botListAllTasks() {
        Ui.printSepLine();
        Bot.botListAllMsg();
        Ui.printList(taskList.listTasks());
        TaskCountMsg();
        Ui.printSepLine();
    }

    private void TaskCountMsg() {
        System.out.println("You have " + taskList.getTaskCount() + " tasks in your list.");
    }

}