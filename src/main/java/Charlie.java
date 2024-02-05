import java.util.ArrayList;
import java.util.Scanner;

public class Charlie {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, I'm Charlie");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            if (input.startsWith("todo")) {
                handleTodo(taskList, input);
            } else if (input.startsWith("deadline")) {
                handleDeadline(taskList, input);
            } else if (input.startsWith("event")) {
                handleEvent(taskList, input);
            } else if (input.equals("list")) {
                listTasks(taskList);
            }
            // Optionally, implement a way to exit the loop.
        }
    }

    private static void handleTodo(ArrayList<Task> taskList, String input) {
        String description = input.substring(5); // Assuming "todo " is 5 characters
        Todo todo = new Todo(description);
        taskList.add(todo);
        System.out.println("Got it. I've added this task:\n  " + todo);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void handleDeadline(ArrayList<Task> taskList, String input) {
        String[] parts = input.substring(9).split(" /by "); // Assuming "deadline " is 9 characters
        Deadline deadline = new Deadline(parts[0], parts[1]);
        taskList.add(deadline);
        System.out.println("Got it. I've added this task:\n  " + deadline);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void handleEvent(ArrayList<Task> taskList, String input) {
        String[] parts = input.substring(6).split(" /from "); // Assuming "event " is 6 characters
        String[] timeParts = parts[1].split(" /to ");
        Event event = new Event(parts[0], timeParts[0], timeParts[1]);
        taskList.add(event);
        System.out.println("Got it. I've added this task:\n  " + event);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void listTasks(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
    }

}
