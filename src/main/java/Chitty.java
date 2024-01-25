import java.util.Scanner;

/**
 * Chatbot-style application for managing tasks.
 */
public class Chitty {
    // Constants
    private static final String CHATBOT_NAME = "Chitty";
    private static final String GREETING_MESSAGE = String.format("Hello! I'm %s\nWhat can I do for you?\n", CHATBOT_NAME);
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!\n";
    private static final String ADD_TASK = "Got it. I've added this task:\n";
    private static final String LIST_TASKS = "Here are the tasks in your list:\n";
    private static final String TASK_LENGTH = "Now you have %d tasks in the list.\n";
    private static final String MARK_TASK = "Nice! I've marked this task as done:\n";
    private static final String UNMARK_TASK = "OK, I've marked this task as not done yet:\n";
    private static final String DELETE_TASK = "Noted. I've removed this task:\n";
    private static final String INVALID_INPUT = "Invalid input, please double check your input values!\n";
    private static final String INVALID_COMMAND = "Invalid command, please only use the following commands:\n" +
            "todo, deadline, event, list, mark, unmark, delete, bye \n";
    private static final String SPACING = "----------------------------------------------------------\n";
    private static final TaskList taskList = new TaskList();

    /**
     * Displays a greeting message to the user.
     */
    private static void greet() {
        System.out.println(SPACING + GREETING_MESSAGE + SPACING);
    }

    /**
     * Adds a new To-do task to the task list.
     *
     * @param taskDescription The description of the To-do task.
     * @throws InvalidInputFormatException If the task description is invalid.
     */
    private static void addTodo(String taskDescription) throws InvalidInputFormatException {
        if (taskDescription.trim().isEmpty()) {
            throw new InvalidInputFormatException("todo");
        }
        Task newTask = new Todo(taskDescription);
        taskList.addTask(newTask);
        System.out.println(SPACING + ADD_TASK + newTask + "\n" +
                String.format(TASK_LENGTH, taskList.getLength())+ SPACING);
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param deadlineDetails Array containing the deadline task description and deadline date.
     * @throws InvalidInputFormatException If the input format for the deadline is invalid.
     */
    private static void addDeadline(String[] deadlineDetails) throws InvalidInputFormatException {
        if (deadlineDetails.length < 2 || deadlineDetails[1].split(" ", 2).length < 2) {
            throw new InvalidInputFormatException("deadline");
        }
        String deadlineDescription = deadlineDetails[0];
        String deadline = deadlineDetails[1].split(" ", 2)[1];
        Task newTask = new Deadline(deadlineDescription, deadline);
        taskList.addTask(newTask);
        System.out.println(SPACING + ADD_TASK + newTask + "\n" +
                String.format(TASK_LENGTH, taskList.getLength())+ SPACING);
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param eventDetails Array containing the event description and its start and end times.
     * @throws InvalidInputFormatException If the input format for the event is invalid.
     */
    private static void addEvent(String[] eventDetails) throws InvalidInputFormatException {
        if (eventDetails.length < 2) {
            throw new InvalidInputFormatException("event");
        }
        String eventDescription = eventDetails[0];
        String[] fromToDetails = eventDetails[1].split("/", 2);
        if (fromToDetails.length < 2
                || fromToDetails[0].split(" ", 2).length < 2
                || fromToDetails[1].split(" ", 2).length < 2) {
            throw new InvalidInputFormatException("event");
        }
        String from = fromToDetails[0].split(" ", 2)[1];
        String to = fromToDetails[1].split(" ", 2)[1];
        Task newTask = new Event(eventDescription, from, to);
        taskList.addTask(newTask);
        System.out.println(SPACING + ADD_TASK + newTask + "\n" +
                String.format(TASK_LENGTH, taskList.getLength())+ SPACING);
    }

    /**
     * Lists all tasks in the task list.
     */
    private static void listTasks() {
        System.out.println(SPACING + LIST_TASKS + taskList + SPACING);
    }

    /**
     * Marks a task as completed based on the given index.
     *
     * @param i The index of the task in the list to be marked.
     */
    private static void markTask(int i) {
        Task updatedTask = taskList.markTask(i - 1);
        System.out.println(SPACING + MARK_TASK + updatedTask + "\n" + SPACING);
    }

    /**
     * Unmarks a task, indicating it is not completed, based on the given index.
     *
     * @param i The index of the task in the list to be unmarked.
     */
    private static void unmarkTask(int i) {
        Task updatedTask = taskList.unmarkTask(i - 1);
        System.out.println(SPACING + UNMARK_TASK + updatedTask + "\n" + SPACING);
    }

    /**
     * Deletes a task from the task list based on the given index.
     *
     * @param i The index of the task to delete.
     */
    private static void delete(int i) {
        Task deletedTask = taskList.deleteTask(i - 1);
        System.out.println(SPACING + DELETE_TASK + deletedTask + "\n" +
                String.format(TASK_LENGTH, taskList.getLength()) + SPACING);
    }

    /**
     * Displays a goodbye message to the user and terminates the application.
     */
    private static void bye() {
        System.out.println(SPACING + GOODBYE_MESSAGE + SPACING);
    }

    /**
     * Displays a message indicating that the user input is invalid.
     */
    private static void invalidInput() { System.out.println(SPACING + INVALID_INPUT + SPACING); }

    /**
     * Displays a specific message indicating why the user input is invalid.
     *
     * @param message The specific message about invalid input.
     */
    private static void invalidInput(String message) { System.out.println(SPACING + message + SPACING); }

    /**
     * Displays a message indicating that the user command is invalid.
     */
    private static void invalidCommand() { System.out.println(SPACING + INVALID_COMMAND + SPACING); }

    /**
     * Processes the user input and executes the corresponding command.
     *
     * @param input The user input split into command and arguments.
     */
    public static int handleInput(String[] input) {
        String commandString = input[0];
        try {
            Command command = Command.valueOf(commandString.toUpperCase());
            switch (command) {
                case LIST:
                    listTasks();
                    return 0;
                case TODO:
                    addTodo(input[1]);
                    return 0;
                case DEADLINE:
                    addDeadline(input[1].split("/", 2));
                    return 0;
                case EVENT:
                    addEvent(input[1].split("/", 2));
                    return 0;
                case MARK:
                    markTask(Integer.parseInt(input[1]));
                    return 0;
                case UNMARK:
                    unmarkTask(Integer.parseInt(input[1]));
                    return 0;
                case DELETE:
                    delete(Integer.parseInt(input[1]));
                    return 0;
                case BYE:
                    bye();
                    return 1;
            }
        }
        catch (InvalidInputFormatException exception) {
            invalidInput(exception.getMessage() + "\n");
        } catch (IllegalArgumentException exception) {
            invalidCommand();
        } catch (Exception exception) {
            invalidInput();
        }
        return 0;
    }

    /**
     * The main method to start the chatbot. Initializes the application and handles user input.
     */
    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ", 2);
            int exit = handleInput(input);
            if (exit == 1) {
                return;
            }
        }
    }
}
