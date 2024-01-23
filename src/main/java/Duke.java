import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    public static void main(String[] args) throws DukeException {
        Scanner scanner = new Scanner(System.in);
        int taskCount = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        System.out.println("     Hello! I'm Cleo");
        System.out.println("     What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            // Read user input
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            // Check for the exit condition
            if (command.toLowerCase().equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.toLowerCase().equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                if (taskCount == 0) {
                    System.out.println("     No tasks added yet.");
                    continue;
                }
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("     " + (i + 1) + "." + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            } else if ((command.toLowerCase().equals("mark") || command.toLowerCase().equals("unmark")) && parts.length == 2) {
                int index = Integer.parseInt(parts[1]) - 1;
                String action = command.toLowerCase();
                // if there exists a task, and you are referring to a valid task to mark
                if (index >= 0 && index < taskCount) {
                    if (action.equals("mark")) {
                        tasks.get(index).markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("       " + tasks.get(index));
                        System.out.println("____________________________________________________________");
                    } else if (action.equals("unmark")) {
                        tasks.get(index).markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("     OK, I've marked this task as not done yet:");
                        System.out.println("       " + tasks.get(index));
                        System.out.println("____________________________________________________________");
                    }
                }
            } else if (command.toLowerCase().equals("delete")) {
                try {
                    if (parts.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
                    } else if (parts.length != 2) {
                        throw new DukeException("Invalid delete format. Please use the following format:\n"
                                + "     delete <task number>");
                    }
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        throw new DukeException("Invalid task number. Index out of bounds");
                    }
                    Task removedTask = tasks.remove(taskIndex);
                    System.out.println("____________________________________________________________");
                    System.out.println("     Noted. I've removed this task:");
                    System.out.println("       " + removedTask);
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } catch (DukeException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                } catch (NumberFormatException e) {
                // Handle case where the part after delete is not a number
                System.out.println("____________________________________________________________");
                System.out.println("     Invalid task number format.");
                System.out.println("____________________________________________________________");
            }
            } else {
                // Add task to the array and echo it back
                try {
                    if (parts.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a command cannot be empty.");
                    }
                    if (taskCount < MAX_TASKS) {
                        Task task = null;
                        if (command.toLowerCase().equals("todo")) {
                            task = new Todo(input.substring(5));
                        } else if (command.toLowerCase().equals("deadline")) {
                            String[] parts2 = input.split(" /by ");
                            if (parts2.length != 2) {
                                throw new DukeException("Invalid deadline format. Please use the following format:\n"
                                        + "     deadline <description> /by <end time>");
                            }
                            task = new Deadline(parts2[0].substring(9), parts2[1]);
                        } else if (command.toLowerCase().equals("event")) {
                            String[] parts2 = input.split(" /from ");
                            if (parts2.length != 2) {
                                throw new DukeException("Invalid deadline format. Please use the following format:\n"
                                        + "     deadline <description> /by <end time>");
                            }
                            String[] partsWithTo = parts2[1].split(" /to ");
                            if (partsWithTo.length != 2) {
                                throw new DukeException("Invalid event format. Please use the following format:\n"
                                        + "     event <description> /from <start time> /to <end time>");
                            }
                            String eventDescription = parts2[0].substring(6);
                            task = new Event(eventDescription, partsWithTo[0], partsWithTo[1]);
                        }
                        if (task == null) {
                            throw new DukeException("Invalid command. Please try again.");
                        }
                        tasks.add(task);
                        taskCount++;
                        System.out.println("____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + task);
                        System.out.println("     Now you have " + taskCount + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new DukeException("Maximum tasks reached. Cannot add more tasks.");
                    }
                } catch (DukeException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            }
        }

        scanner.close();
    }
}
