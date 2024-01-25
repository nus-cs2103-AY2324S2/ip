import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dav {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greetUser();
        startChat();
        exit();
    }

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" What's up! I'm Dav");
        System.out.println(" How may I help you?");
        System.out.println("____________________________________________________________");
    }

    public static void startChat() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = getUserInput(scanner);
            processUserInput(userInput);

        } while (!userInput.equalsIgnoreCase("bye"));
    }

    public static String getUserInput(Scanner scanner) {
        System.out.print("    ");
        return scanner.nextLine();
    }

    public static void processUserInput(String input) {
        System.out.println("____________________________________________________________");

        try {
            if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5).trim());
                markTaskDone(taskIndex);
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7).trim());
                unmarkTaskDone(taskIndex);
            } else if (input.startsWith("todo")) {
                addTodoTask(input.substring(4).trim());
            } else if (input.startsWith("deadline")) {
                addDeadlineTask(input.substring(8).trim());
            } else if (input.startsWith("event")) {
                addEventTask(input.substring(5).trim());
            } else if (input.startsWith("delete")) {
                int taskIndex = Integer.parseInt(input.substring(6).trim());
                deleteTask(taskIndex);
            } else if (!input.equalsIgnoreCase("bye")) {
                throw new IllegalArgumentException("Huh? what's that?");
            }
        } catch (NumberFormatException e) {
            System.out.println("This is not valid task index.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("____________________________________________________________");
    }

    public static void addTodoTask(String taskDescription) {
        if (taskDescription.isEmpty()) {
            System.out.println("Do nothing?");
        } else {
            addTask(new TodoTask(taskDescription));
        }
    }

    public static void addDeadlineTask(String taskDetails) {
        String[] details = taskDetails.split(" /by ");
        if (details.length == 2) {
            String description = details[0].trim();
            String by = details[1].trim();

            if (description.isEmpty()) {
                System.out.println("No deadline?");
            } else {
                addTask(new DeadlineTask(description, by));
            }
        } else {
            System.out.println("Invalid deadline task format.");
        }
    }

    public static void addEventTask(String taskDetails) {
        String[] details = taskDetails.split(" /from ");
        if (details.length == 2) {
            String description = details[0].trim();
            String[] timeDetails = details[1].split(" /to ");

            if (description.isEmpty()) {
                System.out.println("No event?");
            } else if (timeDetails.length == 2) {
                addTask(new EventTask(description, timeDetails[0], timeDetails[1]));
            } else {
                System.out.println("Invalid event task format.");
            }
        } else {
            System.out.println("Invalid event task format.");
        }
    }

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks added yet.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    public static void markTaskDone(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskIndex - 1));
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    public static void unmarkTaskDone(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            tasks.get(taskIndex - 1).unmarkAsDone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(taskIndex - 1));
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    public static void deleteTask(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            Task removedTask = tasks.remove(taskIndex - 1);
            System.out.println(" Task removed:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    public static boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    public static void exit() {
        System.out.println(" Goodbye. ");
        System.out.println("____________________________________________________________");
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with [X]
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class EventTask extends Task {
    protected String from;
    protected String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}