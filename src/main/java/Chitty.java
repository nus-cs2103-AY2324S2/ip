import java.util.Scanner;

public class Chitty {
    private static final String CHATBOT_NAME = "Chitty";
    private static final String GREETING_MESSAGE = String.format("Hello! I'm %s\nWhat can I do for you?\n", CHATBOT_NAME);
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!\n";
    private static final String ADD_TASK = "Got it. I've added this task:\n";
    private static final String LIST_TASKS = "Here are the tasks in your list:\n";
    private static final String TASK_LENGTH = "Now you have %d tasks in the list.\n";
    private static final String MARK_TASK = "Nice! I've marked this task as done: \n";
    private static final String UNMARK_TASK = "OK, I've marked this task as not done yet: \n";
    private static final String DELETE_TASK = "Noted. I've removed this task:\n";
    private static final String UNKNOWN_INPUT = "I'm sorry, but I don't know what that means.\n";
    private static final String INVALID_INPUT = "The description of a %s cannot be empty.\n";
    private static final String SPACING = "---------------------------------------------------\n";
    private static final TaskList taskList = new TaskList();

    private static void greet() {
        System.out.println(SPACING + GREETING_MESSAGE + SPACING);
    }

    private static void addTodo(String taskDescription) {
        Task newTask = new Todo(taskDescription);
        taskList.addTask(newTask);
        System.out.println(SPACING + ADD_TASK + newTask + "\n" +
                String.format(TASK_LENGTH, taskList.getLength())+ SPACING);
    }

    private static void addDeadline(String[] deadlineDetails) {
        String deadlineDescription = deadlineDetails[0];
        String deadline = deadlineDetails[1].split(" ", 2)[1];
        Task newTask = new Deadline(deadlineDescription, deadline);
        taskList.addTask(newTask);
        System.out.println(SPACING + ADD_TASK + newTask + "\n" +
                String.format(TASK_LENGTH, taskList.getLength())+ SPACING);
    }

    private static void addEvent(String[] eventDetails) {
        String eventDescription = eventDetails[0];
        String[] fromToDetails = eventDetails[1].split("/", 2);
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

    private static void invalid() { System.out.println(SPACING + UNKNOWN_INPUT + SPACING); }

    private static void invalid(String taskType) {
        System.out.println(SPACING + String.format(INVALID_INPUT, taskType) + SPACING);
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ", 2);
            String command = input[0];
            try {
                switch (command.toUpperCase()) {
                    case "LIST":
                        listTasks();
                        break;
                    case "BYE":
                        bye();
                        return;
                    case "TODO":
                        addTodo(input[1]);
                        break;
                    case "DEADLINE":
                        addDeadline(input[1].split("/", 2));
                        break;
                    case "EVENT":
                        addEvent(input[1].split("/", 2));
                        break;
                    case "MARK":
                        markTask(Integer.parseInt(input[1]));
                        break;
                    case "UNMARK":
                        unmarkTask(Integer.parseInt(input[1]));
                        break;
                    case "DELETE":
                        delete(Integer.parseInt(input[1]));
                        break;
                    default:
                        invalid();
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                invalid(command);
            }
        }

    }
}
