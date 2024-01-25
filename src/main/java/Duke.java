import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "James";
        System.out.println("Hello! I'm " + name + "\n");
        System.out.println("What can I do for you?\n");

        while(true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }

            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

            switch (command) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                    break;
                case "blah":
                    System.out.println("blah");
                    break;
                case "todo":
                    Todo newTodo = new Todo(parts[1]);
                    tasks.add(newTodo);
                    System.out.println("Got it. I've added this task:\n" + newTodo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case "deadline":
                    String[] deadlineParts = parts[1].split(" /by ");
                    Deadline newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                    tasks.add(newDeadline);
                    System.out.println("Got it. I've added this task:\n" + newDeadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case "event":
                    String[] eventParts = parts[1].split(" /from ");
                    String[] fromto = eventParts[1].split("/to");
                    Event newEvent = new Event(eventParts[0], fromto[0], fromto[1]);
                    tasks.add(newEvent);
                    System.out.println("Got it. I've added this task:\n" + newEvent);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case "mark":
                    int taskIndexToMark = Integer.parseInt(parts[1]) - 1;
                    tasks.get(taskIndexToMark).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskIndexToMark));
                    break;
                case "unmark":
                    int taskIndexToUnmark = Integer.parseInt(parts[1]) - 1;
                    tasks.get(taskIndexToUnmark).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(taskIndexToUnmark));
                    break;
                default:
            }
        }
        scanner.close();
    }
}
