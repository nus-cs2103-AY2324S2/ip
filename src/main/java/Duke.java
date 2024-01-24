import java.util.Scanner;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + from + " to: " + to + ")";
    }
}

public class Duke {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello from");
        System.out.println(" ____        _");
        System.out.println("|  _ \\ _   _| | _____ ");
        System.out.println("| | | | | | | |/ / _ \\");
        System.out.println("| |_| | |_| |   <  __/");
        System.out.println("|____/ \\__,_|_|\\_\\___|");

        System.out.println("Hello! I'm SCZL");
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();

            System.out.println("____________________________________________________________");

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                listTasks();
            } else {
                processTaskInput(userInput);
            }
        }

        scanner.close();
    }

    private static void processTaskInput(String userInput) {
        if (userInput.startsWith("todo")) {
            addTodoTask(userInput.substring(5).trim());
        } else if (userInput.startsWith("deadline")) {
            addDeadlineTask(userInput.substring(9).trim());
        } else if (userInput.startsWith("event")) {
            addEventTask(userInput.substring(6).trim());
        } else if (userInput.startsWith("mark")) {
            markTask(userInput);
        } else if (userInput.startsWith("unmark")) {
            unmarkTask(userInput);
        } else {
            System.out.println("Invalid command.");
        }
    }

    private static void addTodoTask(String description) {
        tasks[taskCount] = new Todo(description);
        taskCount++;
        printTaskAddedMessage(tasks[taskCount - 1]);
    }

    private static void addDeadlineTask(String input) {
        int byIndex = input.indexOf("/by");
        if (byIndex != -1) {
            String description = input.substring(0, byIndex).trim();
            String by = input.substring(byIndex + 3).trim();
            tasks[taskCount] = new Deadline(description, by);
            taskCount++;
            printTaskAddedMessage(tasks[taskCount - 1]);
        } else {
            System.out.println("Invalid deadline command format.");
        }
    }

    private static void addEventTask(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex != -1 && toIndex != -1) {
            String description = input.substring(0, fromIndex).trim();
            String from = input.substring(fromIndex + 5, toIndex).trim();
            String to = input.substring(toIndex + 3).trim();
            tasks[taskCount] = new Event(description, from, to);
            taskCount++;
            printTaskAddedMessage(tasks[taskCount - 1]);
        } else {
            System.out.println("Invalid event command format.");
        }
    }

    private static void markTask(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(5).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskIndex].getStatusIcon() + tasks[taskIndex].getDescription());
            } else {
                System.out.println(" Invalid task number.");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println(" Invalid command format.");
        }
    }

    private static void unmarkTask(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsNotDone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[taskIndex].getStatusIcon() + tasks[taskIndex].getDescription());
            } else {
                System.out.println(" Invalid task number.");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println(" Invalid command format.");
        }
    }

    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");

        if (taskCount == 0) {
            System.out.println(" No tasks yet.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + "." + tasks[i].getStatusIcon() + tasks[i].getDescription());
            }
        }
    }

    private static void printTaskAddedMessage(Task task) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.getStatusIcon() + task.getDescription());
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }
}
