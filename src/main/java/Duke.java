import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        printWithLines("Hello! I'm Bob!", "What can I do for you?");
        String message = null;

        do {
            try {
                message = scanner.nextLine();
                if (message.startsWith("todo")) {
                    handleTodo(list, message);
                } else if (message.startsWith("deadline")) {
                    handleDeadline(list, message);
                } else if (message.startsWith("event")) {
                    handleEvent(list, message);
                } else if (message.equals("list")) {
                    handleList(list);
                } else if (message.startsWith("mark")) {
                    handleMark(list, message);
                } else if (message.startsWith("unmark")) {
                    handleUnmark(list, message);
                } else if (message.startsWith("delete")) {
                    deleteTask(list, message);
                } else if (!message.equals("bye")) {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means buddy.");
                }
            } catch (DukeException e) {
                printWithLines(e.getMessage());
            }
        } while (!message.equals("bye"));

        printWithLines("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");
    }

    private static void handleTodo(ArrayList<Task> list, String message) throws DukeException {
        if (message.trim().equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty buddy.");
        }
        String description = message.substring(5).trim();
        Task task = new Task(description);
        list.add(task);
        printWithLines("Got it. I've added this task:", task.toString(), "Now you have " + list.size() + " tasks in the list.");
    }

    private static void handleDeadline(ArrayList<Task> list, String message) throws DukeException {
        String[] parts = message.split("/by", 2);
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The deadline date/time is missing buddy.");
        }
        String description = parts[0].substring(9).trim();
        String by = parts[1].trim();
        Deadline task = new Deadline(description, by);
        list.add(task);
        printWithLines("Got it. I've added this task:", task.toString(), "Now you have " + list.size() + " tasks in the list.");
    }

    private static void handleEvent(ArrayList<Task> list, String message) throws DukeException {
        String[] parts = message.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new DukeException("OOPS!!! The event time is missing or incomplete buddy.");
        }
        String description = parts[0].substring(6).trim();
        String[] timeParts = parts[1].split(" /to ", 2);
        String fromTime = timeParts[0].trim();
        String toTime = timeParts[1].trim();
        Event task = new Event(description, fromTime, toTime);
        list.add(task);
        printWithLines("Got it. I've added this task:", task.toString(), "Now you have " + list.size() + " tasks in the list.");
    }

    private static void handleList(ArrayList<Task> list) {
        ArrayList<String> taskDescriptions = new ArrayList<>();
        taskDescriptions.add("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            taskDescriptions.add((i + 1) + ". " + list.get(i).toString());
        }
        printWithLines(taskDescriptions.toArray(new String[0]));
    }

    private static void handleMark(ArrayList<Task> list, String message) throws DukeException {
        if (message.trim().equals("mark")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(5).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        task.markAsDone();
        printWithLines("Nice! I've marked this task as done:", task.toString());
    }

    private static void handleUnmark(ArrayList<Task> list, String message) throws DukeException {
        if (message.trim().equals("unmark")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(7).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        task.unMarkAsDone();
        printWithLines("OK, I've marked this task as not done yet:", task.toString());
    }

    private static void deleteTask(ArrayList<Task> list, String message) throws DukeException {
        if (message.trim().equals("delete")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(7).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        list.remove(index);
        printWithLines("OK, I've deleted this task:", task.toString());
    }

    private static void printWithLines(String... messages) {
        System.out.println("------------------------------------------");
        for (String message : messages) {
            System.out.println(message);
        }
    }
}
