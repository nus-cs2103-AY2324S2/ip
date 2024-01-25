import java.util.Scanner;

public class Chitty {
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

    private static void greet() {
        System.out.println(SPACING + GREETING_MESSAGE + SPACING);
    }

    private static void addTodo(String taskDescription) throws InvalidInputFormatException {
        if (taskDescription.trim().isEmpty()) {
            throw new InvalidInputFormatException("todo");
        }
        Task newTask = new Todo(taskDescription);
        taskList.addTask(newTask);
        System.out.println(SPACING + ADD_TASK + newTask + "\n" +
                String.format(TASK_LENGTH, taskList.getLength())+ SPACING);
    }

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

    private static void listTasks() {
        System.out.println(SPACING + LIST_TASKS + taskList + SPACING);
    }

    private static void markTask(int i) {
        Task updatedTask = taskList.markTask(i - 1);
        System.out.println(SPACING + MARK_TASK + updatedTask + "\n" + SPACING);
    }

    private static void unmarkTask(int i) {
        Task updatedTask = taskList.unmarkTask(i - 1);
        System.out.println(SPACING + UNMARK_TASK + updatedTask + "\n" + SPACING);
    }

    private static void delete(int i) {
        Task deletedTask = taskList.deleteTask(i - 1);
        System.out.println(SPACING + DELETE_TASK + deletedTask + "\n" +
                String.format(TASK_LENGTH, taskList.getLength()) + SPACING);
    }

    private static void bye() {
        System.out.println(SPACING + GOODBYE_MESSAGE + SPACING);
    }

    private static void invalidInput() { System.out.println(SPACING + INVALID_INPUT + SPACING); }

    private static void invalidInput(String message) { System.out.println(SPACING + message + SPACING); }

    private static void invalidCommand() { System.out.println(SPACING + INVALID_COMMAND + SPACING); }

    public static void handleInput(String[] input) {
        String commandString = input[0];
        try {
            Command command = Command.valueOf(commandString.toUpperCase());
            switch (command) {
                case LIST:
                    listTasks();
                    break;
                case TODO:
                    addTodo(input[1]);
                    break;
                case DEADLINE:
                    addDeadline(input[1].split("/", 2));
                    break;
                case EVENT:
                    addEvent(input[1].split("/", 2));
                    break;
                case MARK:
                    markTask(Integer.parseInt(input[1]));
                    break;
                case UNMARK:
                    unmarkTask(Integer.parseInt(input[1]));
                    break;
                case DELETE:
                    delete(Integer.parseInt(input[1]));
                    break;
                case BYE:
                    bye();
                    break;
            }
        }
        catch (InvalidInputFormatException exception) {
            invalidInput(exception.getMessage() + "\n");
        } catch (IllegalArgumentException exception) {
            invalidCommand();
        } catch (Exception exception) {
            invalidInput();
        }
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ", 2);
            handleInput(input);
        }
    }
}
