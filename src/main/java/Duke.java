import java.io.*;
import java.util.*;

public class Duke {
    // constants
    static final String LINE = "\t――――――――――――――――――――――――――――――――――――――――\n";
    static final String CHAT_BOT_NAME = "Uncle Bob";
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        greet();
        boolean isExiting = false;
        while (!isExiting) {
            String[] inputs = br.readLine().split(" ");
            String command = inputs[0];
            String message = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
            System.out.print(LINE);
            switch (command) {
                case "bye":
                    isExiting = true;
                    System.out.println("\t Bye! Hope to see you again soon!");
                    break;
                case "list":
                    handleList();
                    break;
                case "mark":
                    handleMark(message);
                    break;
                case "unmark":
                    handleUnmark(message);
                    break;
                case "todo":
                    handleTodo(message);
                    break;
                case "deadline":
                    handleDeadline(message);
                    break;
                case "event":
                    handleEvent(message);
                    break;
                default:
                    System.out.println("Sorry uncle don't understand what you want");
                    break;
            }
            System.out.print(LINE);
        }
    }

    // greet user
    public static void greet() {
        System.out.print(LINE + "\t Hello! I'm " + CHAT_BOT_NAME + "\n\t "
                + "What can I do for you?\n" + LINE);
    }

    public static void updateTasks(Task task) {
        tasks.add(task);
        if (tasks.size() > 1) {
            System.out.println("\t Got it. I've added this task:\n\t\t " + task
                    + "\n\t Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("\t Got it. I've added this task:\n\t\t " + task
                    + "\n\t Now you have 1 task in the list.");
        }
    }

    public static void handleList() {
        if (tasks.isEmpty()) {
            System.out.println("\t Congrats, you have cleared all your tasks!");
        }
        for (int i = 0; i < tasks.size(); i++){
            System.out.println("\t " + (i+1) + ". " + tasks.get(i));
        }
    }

    public static void handleMark(String message) {
        int index = Integer.parseInt(message);
        if (index <= tasks.size() && index > 0) {
            tasks.get(index - 1).mark();
            System.out.println("\t Nice! I've marked this task as done:\n\t\t"
                    + tasks.get(index - 1));
        } else {
            System.out.println("\t Error: index out of bounds");
        }
    }

    public static void handleUnmark(String message) {
        int index = Integer.parseInt(message);
        if (index <= tasks.size() && index > 0) {
            tasks.get(index - 1).unmark();
            System.out.println("\t OK, I've marked this task as not done yet:\n\t\t"
                    + tasks.get(index - 1));
        } else {
            System.out.println("\t Error: index out of bounds");
        }
    }

    public static void handleTodo(String message) {
        Task todo = new Todo(message);
        updateTasks(todo);
    }

    public static void handleDeadline(String message) {
        String[] args = message.split("/by");
        String desc = args[0].trim();
        String by = args[1].trim();
        Task deadline = new Deadline(desc, by);
        updateTasks(deadline);
    }

    public static void handleEvent (String message) {
        String[] args = message.split("/from");
        String desc = args[0].trim();
        String[] duration = args[1].split("/to");
        String start = duration[0].trim();
        String end = duration[1].trim();
        Task event = new Event(desc, start, end);
        updateTasks(event);
    }
}