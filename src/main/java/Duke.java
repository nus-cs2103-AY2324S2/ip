import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    //private static int taskCount = 0;
    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();

            if ("bye".equals(input)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equals(input)) {
                listTasks();
            } else if (input.startsWith("delete ")) {
                deleteTask(input);
            } else if (input.startsWith("mark ")) {
                try {
                    markTask(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    unmarkTask(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("todo")) {
                if (input.length() <= 5) {
                    System.out.println("That's not a valid todo!");
                } else {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        System.out.println("That's not a valid todo!");
                    } else {
                        addTask(new ToDo(description));
                    }
                }
            } else if (input.startsWith("deadline")) {
                if (input.length() <= 9) {
                    System.out.println("That's not a valid Deadline!");
                } else {
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length == 2) {
                        addTask(new Deadline(parts[0], parts[1]));
                    } else {
                        System.out.println("That's not a valid Deadline!");
                    }
                }
            } else if (input.startsWith("event")) {
                int fromIndex = input.indexOf(" /from");
                int toIndex = input.indexOf(" /to");

                if (fromIndex != -1 && toIndex != -1 && fromIndex < toIndex && fromIndex >= 6 && toIndex >= fromIndex + 7 && input.length() >= toIndex + 5) {
                    String description = input.substring(6, fromIndex).trim();
                    String startTime = input.substring(fromIndex + 7, toIndex).trim();
                    String endTime = input.substring(toIndex + 5).trim();

                    if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                        System.out.println("The description, start time, and end time of an event cannot be empty.");
                    } else {
                        addTask(new Event(description, startTime, endTime));
                    }
                } else {
                    System.out.println("That's not a valid Event!");
                }
            } else {
                System.out.println("That's not a valid task!\n");
            }
        }

        scanner.close();
    }
    
    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("");
    }

    private static void markTask(String input) throws DukeException {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskIndex).toString() + "\n");
        } else {
            throw new DukeException("Task does not exist.");
            //System.out.println("Task does not exist.");
        }
    }

    private static void unmarkTask(String input) throws DukeException {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1; // subtract 1 for array index
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskIndex).toString() + "\n");
        } else {
            throw new DukeException("Task does not exist.");
            //System.out.println("Task does not exist.");
        }
    }

    private static void deleteTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1; // Convert input string to task index
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                System.out.println("Task does not exist.");
            } else {
                Task removedTask = tasks.remove(taskIndex);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + removedTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number to delete.");
        }
    }
}
