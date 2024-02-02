import java.util.Scanner;

class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskDescription;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
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
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] task = new Task[100];
        int count = 0;

        System.out.println("Hello! I'm Bentley\n" + "What can I do for you?\n");

        while (count < 100) {
            try {
                String userInput = scanner.nextLine();

                if (userInput.equals("Bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equals("List")) {

                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + task[i]);
                    }

                } else if (userInput.startsWith("todo")) {
                    if (userInput.length() <= 5) {
                        throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
                    }
                    String description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException("looks like the description is missing");
                    }
                    task[count] = new Todo(userInput.substring(5));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task[count]);
                    System.out.println("Now you have " + (count + 1) + " tasks in the list.");
                    count++;

                } else if (userInput.startsWith("deadline")) {
                    if (userInput.length() <= 9) {
                        throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
                    }
                    String[] parts = userInput.substring(9).split("/by");
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    if (description.isEmpty() || by.isEmpty()) {
                        throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
                    }
                    task[count] = new Deadline(parts[0].trim(), parts[1].trim());
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task[count]);
                    System.out.println("Now you have " + (count + 1) + " tasks in the list.");
                    count++;

                } else if (userInput.startsWith("event")) {
                    if (userInput.length() <= 6) {
                        throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
                    }
                    String[] parts = userInput.substring(6).split("/from");
                    String description = parts[0].trim();
                    String[] eventParts = parts[1].trim().split("/to");
                    String from = eventParts[0].trim();
                    String to = eventParts[1].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new IllegalArgumentException(
                                "looks like something is missing (description/ start date/ end date)");
                    }
                    task[count] = new Event(parts[0].trim(), eventParts[0].trim(), eventParts[1].trim());
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task[count]);
                    System.out.println("Now you have " + (count + 1) + " tasks in the list.");
                    count++;

                } else if (userInput.startsWith("mark")) {
                    System.out.println(" Nice! I've marked this task as done:");
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    task[taskNumber - 1].markAsDone();

                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + task[i]);
                    }

                } else if (userInput.startsWith("unmark")) {
                    System.out.println(" OK, I've marked this task as not done yet:");
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    task[taskNumber - 1].markAsUndone();

                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + ". " + task[i]);
                    }

                } else {
                    System.out.println("please input a valid task code");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}