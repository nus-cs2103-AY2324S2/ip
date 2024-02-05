import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm CharmBot ");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            System.out.println("____________________________________________________________");
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
            } else if (command.startsWith("mark")) {
                int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[taskIndex]);
                } else {
                    System.out.println("Invalid task index.");
                }
            } else if (command.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[taskIndex]);
                } else {
                    System.out.println("Invalid task index.");
                }
            } else {
                tasks[taskCount] = new Task(command);
                taskCount++;
                System.out.println("added: " + command);
            }
            System.out.println("____________________________________________________________");
            command = scanner.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}