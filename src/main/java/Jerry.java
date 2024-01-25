import java.util.Scanner;
import java.util.ArrayList;

public class Jerry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = "Hello! I'm Jerry.\n" + "Anything I can do for you?";
        System.out.println(message);

        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            int taskIndex;
            String command = parts[0].toLowerCase();

            switch (command) {
                case "bye":
                    System.out.println("Bye!");
                    scanner.close();
                    return;

                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int x = 0; x < tasks.size() ; x++) {
                        System.out.println((x + 1) + "." + tasks.get(x));
                    }
                    break;

                case "mark":
                    taskIndex = Integer.parseInt(parts[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markDone();
                        System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(taskIndex));
                    }
                    break;

                case "unmark":
                    taskIndex = Integer.parseInt(parts[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markNotDone();
                        System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(taskIndex));
                    }
                    break;

                case "delete":
                    if (parts.length < 2) {
                        System.out.println("Error: Please specify the task number to delete\nUsage: delete <task number>");
                    } else {
                        try {
                            taskIndex = Integer.parseInt(parts[1]) - 1;
                            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                                Task removedTask = tasks.remove(taskIndex);
                                System.out.println("I've removed this task:\n  " + removedTask);
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            } else {
                                System.out.println("Error: Task does not exist.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Please enter a number.");
                        }
                    }
                    break;

                case "todo":
                    if (parts.length < 2) {
                        System.out.println("Error: Wrong format \nUsage: todo <task description>");
                    } else {
                        ToDo todo = new ToDo(parts[1]);
                        tasks.add(todo);
                        System.out.println("Got it. I've added this task:\n  " + todo);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    }
                    break;

                case "deadline":
                    if (parts.length < 2 || !parts[1].contains(" /by ")) {
                        System.out.println("Error: Wrong format, please include deadline \nUsage: deadline <task description> /by <date/time>");
                    } else {
                        String[] deadlineParts = parts[1].split(" /by ", 2);
                        Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                        tasks.add(deadline);
                        System.out.println("Got it. I've added this task:\n  " + deadline);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                    break;

                case "event":
                    if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
                        System.out.println("Error: Wrong format, please include start and end time \nUsage: event <task description> /from <start time> /to <end time>");
                    } else {
                        String[] eventParts = parts[1].split(" /from ", 2);
                        String[] fromTo = eventParts[1].split(" /to ", 2);
                        Event event = new Event(eventParts[0], fromTo[0], fromTo[1]);
                        tasks.add(event);
                        System.out.println("Got it. I've added this task:\n  " + event);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                    break;

                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }
    }
}

