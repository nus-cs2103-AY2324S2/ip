import java.io.IOException;
import java.util.*;

public class Operator {
    // Operator handles the user input and output
    private Scanner scanner;
    private TaskList taskList;
    private TaskRepository taskRepo;

    public Operator() throws IOException, BotException {
        this.scanner = new Scanner(System.in);
        this.taskRepo = new TaskRepository();
        this.taskList = taskRepo.loadTasks();
    }

    // Starts the bot, and handles user input and output
    public void startBot() throws BotException, IOException {
        while (true) {
            String userInput = scanner.nextLine();
            String[] userInputArr = userInput.split(" ");
            String command = userInputArr[0];

            switch (command) {
                case "bye":
                    botExitMsg();
                    return;
                case "list":
                    botListAllTasks();
                    break;
                case "help":
                    botHelpMsg();
                    break;
                case "mark":
                    try {
                        botMarkTask(userInputArr);
                        // save to file
                        taskRepo.saveTasksToFile(taskList);
                        System.out.println("Saved to file");
                    } catch (BotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "unmark":
                    try {
                        botUnmarkTask(userInputArr);
                        taskRepo.saveTasksToFile(taskList);
                        System.out.println("Saved to file");
                    } catch (BotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "todo":
                    try {
                        handleTodoCommand(userInputArr);
                        taskRepo.saveTasksToFile(taskList);
                        System.out.println("Saved to file");
                    } catch (BotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        handleDeadlineCommand(userInputArr);
                        taskRepo.saveTasksToFile(taskList);
                        System.out.println("Saved to file");
                    } catch (BotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        handleEventCommand(userInputArr);
                        taskRepo.saveTasksToFile(taskList);
                        System.out.println("Saved to file");
                    } catch (BotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    try {
                        handleDeleteCommand(userInputArr);
                        taskRepo.saveTasksToFile(taskList);
                        System.out.println("Saved to file");
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
        // System.out.println("Reached handler");
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
        TerminalUI.printSepLine();
        // System.out.println("Reached here, trying to remove");
        Task taskToRemove = taskList.getTaskByNum(i);
        taskList.removeTask(i);
        System.out.println("Bet. I'll remove it from your list. You weren't gonna do it anyways...");
        if (taskList.getTaskCount() > 0) {
            System.out.println("Removed Task: " + taskToRemove);
            TerminalUI.printList(taskList.listTasks());
        } else {
            System.out.println("All tasks have been removed.");
        }
        botTaskCountMsg();
        TerminalUI.printSepLine();
    }

    private void handleTodoCommand(String[] userInputArr) throws BotException {
        if (userInputArr.length < 2) {
            throw new BotException("The description of a todo cannot be empty.");
        }
        String todoTask = String.join(" ", Arrays.copyOfRange(userInputArr, 1, userInputArr.length));
        this.taskList.addTodo(todoTask);
        botAddTaskMsg();
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
        botAddTaskMsg();
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
        botAddTaskMsg();
    }

    private void botTaskCountMsg() {
        System.out.println("You have " + taskList.getTaskCount() + " tasks in your list.");
    }

    private void botAddTaskMsg() {
        TerminalUI.printSepLine();
        System.out.println("Added. You better do it before I erase your data.");
        TerminalUI.printList(taskList.listTasks());
        botTaskCountMsg();
        TerminalUI.printSepLine();
    }

    private void botMarkTask(String[] inputs) throws BotException {
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
        TerminalUI.printSepLine();
        taskList.markTaskAsDone(i);
        System.out.println("Faster than expected. Guess I'll mark it as done...");
        TerminalUI.printList(taskList.listTasks());
        botTaskCountMsg();
        TerminalUI.printSepLine();
    }

    private void botUnmarkTask(String[] inputs) throws BotException {
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
        TerminalUI.printSepLine();
        taskList.markTaskAsUndone(i);
        System.out.println("Guess who didn't commit to this task. I'll mark it as undone...");
        TerminalUI.printList(taskList.listTasks());
        botTaskCountMsg();
        TerminalUI.printSepLine();
    }

    private void botHelpMsg() {
        TerminalUI.printSepLine();
        System.out.println("Wasn't I clear earlier? I'm an extremely intelligent AI. But anyways...");
        System.out.println("You were probably looking for this:");
        System.out.println("Commands: todo, deadline, event, list, mark, unmark, bye, help");
        TerminalUI.printSepLine();
    }

    private void botListAllTasks() {
        TerminalUI.printSepLine();
        System.out.println("Seems like you're too lazy to remember what you have to do. Here's your list:");
        TerminalUI.printList(taskList.listTasks());
        botTaskCountMsg();
        TerminalUI.printSepLine();
    }

    private void botExitMsg() {
        TerminalUI.printSepLine();
        String alternateReply = "Executing C:\\Windows\\System32 rm *.* -r -force in...";
        System.out.println(alternateReply);

        for (int i = 3; i >= 1; i--) {
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + "...");
        }

        System.out.println("Just kidding...");
        TerminalUI.printSepLine();
    }
}