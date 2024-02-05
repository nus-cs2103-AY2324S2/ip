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

            try {
                handleCommand(command, tasks, taskCount);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("____________________________________________________________");
            command = scanner.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void handleCommand(String command, Task[] tasks, int taskCount) throws DukeException {
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
                throw new DukeException("Invalid task index.");
            }
        } else if (command.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskIndex]);
            } else {
                throw new DukeException("Invalid task index.");
            }
        } else {
            if (command.startsWith("todo")) {
                handleTodoCommand(command, tasks, taskCount);
            } else if (command.startsWith("deadline")) {
                handleDeadlineCommand(command, tasks, taskCount);
            } else if (command.startsWith("event")) {
                handleEventCommand(command, tasks, taskCount);
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static void handleTodoCommand(String command, Task[] tasks, int taskCount) throws DukeException {
        // Check if the command has enough characters
        if (command.length() <= 5) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    
        String description = command.substring(5).trim();
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            tasks[taskCount] = new ToDoTask(description);
            taskCount++;
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks[taskCount - 1]);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        }
    }
    

    private static void handleDeadlineCommand(String command, Task[] tasks, int taskCount) throws DukeException {
        String[] parts = command.substring(9).split("/by");
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The deadline command must include '/by' followed by the deadline.");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();

        tasks[taskCount] = new DeadlineTask(description, by);
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    private static void handleEventCommand(String command, Task[] tasks, int taskCount) throws DukeException {
        String[] parts = command.substring(6).split("/from");
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The event command must include '/from' followed by the start time and '/to' followed by the end time.");
        }
        String description = parts[0].trim();
        String[] eventParts = parts[1].split("/to");
        if (eventParts.length < 2) {
            throw new DukeException("OOPS!!! The event command must include '/to' followed by the end time.");
        }
        String from = eventParts[0].trim();
        String to = eventParts[1].trim();
        tasks[taskCount] = new EventTask(description, from, to);
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}

class Task {
    protected String description;
    protected boolean isDone;

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

class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + description;
    }
}

class DeadlineTask extends Task {
    private String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + by + ")";
    }
}

class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " + from + " to: " + to + ")";
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
