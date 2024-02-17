package duke;

import java.util.Scanner;

/**
 * Manages the user interface for the Duke application. This class handles input
 * and output, displaying
 * greetings, errors, and other messages to the user.
 */
public class Ui {
    private static final String LINE = "\t____________________________________________________________\n";
    protected static String hello = String.format("\tHello! I'm %s\n", Duke.name) + "\tWhat can I do for you?\n";
    private static String goodbye = "\tBye. Hope to see you again soon!\n";

    private Storage storage = null;
    private TaskList taskList = null;

    /**
     * Manages the user interface for the Duke application. This class handles input
     * and output, displaying
     * greetings, errors, and other messages to the user.
     */
    Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Displays a message indicating an error loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks!");
    }

    /**
     * Initiates the main application loop, handling user input and coordinating
     * responses.
     */
    public void start() {
        System.out.println(this.hello);
        this.storage.loadTasks();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.print(Ui.LINE);

            Parser.ParsedCommand parsedCommand = Parser.parse(input);
            if (parsedCommand.getCommandType() == CommandType.INVALID) {
                Ui.getInvalidString();
                continue;
            }
            int taskIndex = parsedCommand.getTaskNumber() - 1;
            switch (parsedCommand.getCommandType()) {
            case INVALID:
                break;
            case BYE:
                System.out.println(this.goodbye);
                scanner.close();
                this.storage.saveTasks();
                return;
            case LIST:
                this.taskList.listTasks();
                break;
            case MARK:
                this.taskList.getTask(taskIndex).markComplete();
                break;
            case UNMARK:
                this.taskList.getTask(taskIndex).unmarkComplete();
                break;
            case DELETE:
                Task deletedTask = this.taskList.getTask(taskIndex);
                this.taskList.deleteTask(taskIndex);
                System.out.println("\tNoted. I've removed this task:\n\t" + deletedTask);
                break;
            case FIND:
                this.taskList.findTasks(parsedCommand.getInput());
            case EVENT:
            case TODO:
            case DEADLINE:
                Task task = Parser.createTask(parsedCommand.getCommandType(), input);
                if (task != null) {
                    this.taskList.addTask(task);
                    System.out.println("\tGot it. I've added this task:\n\t" + task);
                    System.out.println("\tNow you have " + TaskList.storageFill + " tasks in the list.");
                }
                break;
            default:
                break;
            }

            System.out.println(Ui.LINE);
        }
    }

    /**
     * Prints a message indicating the user has entered an invalid command.
     */
    public static String getInvalidString() {
        return "\tOOPS!!! That is not a valid command!\n"
                + "\tTry the following: \n"
                + "\tlist\n"
                + "\tmark x\n"
                + "\tunmark x\n"
                + "\tdelete x\n"
                + "\tfind xxx\n"
                + "\ttodo xxx\n"
                + "\tdeadline xxx /by xxx\n"
                + "\tevent xxx /from xxx /to xxx";
    }

    public String getResponse(String input) {
        StringBuilder output = new StringBuilder();
    
        Parser.ParsedCommand parsedCommand = Parser.parse(input);
        if (parsedCommand.getCommandType() == CommandType.INVALID) {
            return Ui.getInvalidString();
        }
        int taskIndex = parsedCommand.getTaskNumber() - 1;
        switch (parsedCommand.getCommandType()) {
        case BYE:
            output.append(Ui.goodbye).append("\n");
            this.storage.saveTasks();
            break;
        case LIST:
            output.append(this.taskList.listTasks());
            break;
        case MARK:
            output.append(this.taskList.getTask(taskIndex).markComplete());
            break;
        case UNMARK:
            output.append(this.taskList.getTask(taskIndex).unmarkComplete());
            break;
        case DELETE:
            Task deletedTask = this.taskList.getTask(taskIndex);
            this.taskList.deleteTask(taskIndex);
            output.append("\tNoted. I've removed this task:\n\t").append(deletedTask).append("\n");
            break;
        case FIND:
            output.append(this.taskList.findTasks(parsedCommand.getInput()));
            break;
        case EVENT:
        case TODO:
        case DEADLINE:
            Task task = Parser.createTask(parsedCommand.getCommandType(), input);
            if (task != null) {
                this.taskList.addTask(task);
                output.append("\tGot it. I've added this task:\n\t").append(task);
                output.append("\tNow you have ").append(TaskList.storageFill).append(" tasks in the list.\n");
            }
            break;
        default:
            break;
        }
        
        return output.toString();
    }
    
}
