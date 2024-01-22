import java.util.Scanner;
public class Duke {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    public static void main(String[] args) {
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
                    System.out.println("     " + (i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if ((command.toLowerCase().equals("mark") || command.toLowerCase().equals("unmark")) && parts.length == 2) {
                int index = Integer.parseInt(parts[1]) - 1;
                String action = command.toLowerCase();
                // if there exists a task, and you are referring to a valid task to mark
                if (index >= 0 && index < taskCount) {
                    if (action.equals("mark")) {
                        tasks[index].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("       " + tasks[index]);
                        System.out.println("____________________________________________________________");
                    } else if (action.equals("unmark")) {
                        tasks[index].markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("     OK, I've marked this task as not done yet:");
                        System.out.println("       " + tasks[index]);
                        System.out.println("____________________________________________________________");
                    }
                }
            } else {
                    // Add task to the array and echo it back
                    if (parts.length == 1) {
                        System.out.println("____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a " + command + " cannot be empty.");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    if (taskCount < MAX_TASKS) {
                        Task task = null;
                        if (command.toLowerCase().equals("todo")) {
                            task = new Todo (input.substring(5));
                        } else if (command.toLowerCase().equals("deadline")) {
                            String[] parts2 = input.split(" /by ");
                            if (parts2.length != 2) {
                                System.out.println("____________________________________________________________");
                                System.out.println("     Invalid event format. Please use the following format:");
                                System.out.println("            deadline <description> /by  <end time>");
                                System.out.println("____________________________________________________________");
                                continue;
                            }
                            task = new Deadline(parts2[0].substring(9), parts2[1]);
                        } else if (command.toLowerCase().equals("event")) {
                            String[] parts2 = input.split(" /from ");
                            String eventDescription = parts2[0].substring(6);
                            String[] partsWithTo = parts2[1].split(" /to ");
                            if (parts2.length != 2 || partsWithTo.length != 2) {
                                System.out.println("____________________________________________________________");
                                System.out.println("     Invalid event format. Please use the following format:");
                                System.out.println("     event <description> /from <start time> to <end time>");
                                System.out.println("____________________________________________________________");
                                continue;
                            }
                            task = new Event(eventDescription, partsWithTo[0], partsWithTo[1]);
                        }
                        if (task == null) {
                            System.out.println("____________________________________________________________");
                            System.out.println("     Invalid command. Please try again.");
                            System.out.println("____________________________________________________________");
                            continue;
                        }
                        tasks[taskCount] = task;
                        taskCount++;
                        System.out.println("____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + task);
                        System.out.println("     Now you have " + taskCount + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("     Maximum tasks reached. Cannot add more tasks.");
                        System.out.println("____________________________________________________________");
                    }
                }
            }
            scanner.close();
        }

}
