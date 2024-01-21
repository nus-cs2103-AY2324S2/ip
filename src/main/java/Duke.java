import java.io.*;
import java.util.*;

public class Duke {
    // constants
    static final String LINE = "\t――――――――――――――――――――――――――――――――――――――――\n";
    static final String CHAT_BOT_NAME = "Bob";
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
                    if (tasks.isEmpty()) {
                        System.out.println("\t Congrats, you have cleared all your tasks!");
                    }
                    for (int i = 0; i < tasks.size(); i++){
                        System.out.println("\t " + (i+1) + ". " + tasks.get(i));
                    }
                    break;
                case "mark":
                    int index = Integer.parseInt(message);
                    if (index <= tasks.size() && index > 0) {
                        tasks.get(index - 1).mark();
                        System.out.println("\t Nice! I've marked this task as done:\n\t\t"
                                + tasks.get(index - 1));
                    } else {
                        System.out.println("\t Error: index out of bounds");
                    }
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(message);
                    if (unmarkIndex <= tasks.size() && unmarkIndex > 0) {
                        tasks.get(unmarkIndex - 1).unmark();
                        System.out.println("\t OK, I've marked this task as not done yet:\n\t\t"
                                + tasks.get(unmarkIndex - 1));
                    } else {
                        System.out.println("\t Error: index out of bounds");
                    }
                    break;
                case "todo":
                    Task todo = new Todo(message);
                    updateTasks(todo);
                    break;
                case "deadline":
                    if (!message.contains("/by")) {
                        System.out.println("\t Please specify deadline!");
                        break;
                    }
                    String[] deadlineArgs = message.split("/by");
                    String deadline_desc = deadlineArgs[0].trim();
                    String by = deadlineArgs[1].trim();
                    Task deadline = new Deadline(deadline_desc, by);
                    updateTasks(deadline);
                    break;
                case "event":
                    String[] eventArgs = message.split("/from");
                    String event_desc = eventArgs[0].trim();
                    String[] eventDurationArgs = eventArgs[1].split("/to");
                    String start = eventDurationArgs[0].trim();
                    String end = eventDurationArgs[1].trim();
                    Task event = new Event(event_desc, start, end);
                    updateTasks(event);
                    break;
                default:
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
}