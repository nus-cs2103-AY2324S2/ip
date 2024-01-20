import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static void printWithLines(String... messages) {
        System.out.println("------------------------------------------");
        for (String message : messages) {
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        String message;
        Scanner scanner = new Scanner(System.in);
        printWithLines("Hello! I'm Bob!", "What can I do for you?");
        do {
            message = scanner.nextLine();
            if (message.startsWith("todo ")) {
                String description = message.substring(5);
                Task task = new Task(description);
                list.add(task);
                printWithLines("Got it. I've added this task:", task.toString(),
                        "Now you have " + list.size() + " tasks in the list.");
            } else if (message.startsWith("deadline ")) {
                String[] parts = message.split("/by", 2);
                String description = parts[0].substring(9);
                String by = parts[1].trim();
                Deadline task = new Deadline(description, by);
                list.add(task);
                printWithLines("Got it. I've added this task:", task.toString(),
                        "Now you have " + list.size() + " tasks in the list.");
            } else if (message.startsWith("event ")) {
                String[] parts = message.split(" /from ", 2);
                String description = parts[0].substring(6);
                String[] timeParts = parts[1].split(" /to ", 2);
                String fromTime = timeParts[0].trim();
                String toTime = timeParts[1].trim();
                Event task = new Event(description, fromTime, toTime);
                list.add(task);
                printWithLines("Got it. I've added this task:", task.toString(),
                        "Now you have " + list.size() + " tasks in the list.");
            } else if (message.equals("list")) {
                ArrayList<String> taskDescriptions = new ArrayList<>();
                taskDescriptions.add("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    taskDescriptions.add((i + 1) + ". " + list.get(i).toString());
                }
                printWithLines(taskDescriptions.toArray(new String[0]));
            } else if (message.startsWith("mark ")) {
                int index = Integer.parseInt(message.substring(5)) - 1;
                Task task = list.get(index);
                task.markAsDone();
                printWithLines("Nice! I've marked this task as done:", task.toString());
            } else if (message.startsWith("unmark ")) {
                int index = Integer.parseInt(message.substring(7)) - 1;
                Task task = list.get(index);
                task.unMarkAsDone();
                printWithLines("OK, I've marked this task as not done yet:", task.toString());
            }
        } while (!message.equals("bye"));
        printWithLines("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");
    }
}
