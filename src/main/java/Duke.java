import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
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
            } else if (input.startsWith("mark ")) {
                markTask(input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
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
    private static void addTask(Task task) throws DukeException {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + taskCount + " tasks in the list.\n");
        } else {
            throw new DukeException("OOPS! Task list is full! I can't store any more tasks!");
            //System.out.println("OOPS! Task list is full! I can't store any more tasks!");
        }
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
        System.out.println("");
    }

    private static void markTask(String input) throws DukeException {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[taskIndex].toString() + "\n");
        } else {
            //throw new DukeException("Task does not exist.");
            System.out.println("Task does not exist.");
        }
    }

    private static void unmarkTask(String input) throws DukeException {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1; // subtract 1 for array index
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[taskIndex].toString() + "\n");
        } else {
            //throw new DukeException("Task does not exist.");
            System.out.println("Task does not exist.");
        }
    }
}
