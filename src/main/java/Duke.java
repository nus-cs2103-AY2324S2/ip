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
            String userInput = scanner.nextLine();

            if (userInput.equals("Bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("List")) {

                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + task[i]);
                }

            } else if (userInput.startsWith("todo")) {
                task[count] = new Todo(userInput.substring(5));
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task[count]);
                System.out.println("Now you have " + (count + 1) + " tasks in the list.");
                count++;
            }

            else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.substring(9).split("/by");
                task[count] = new Deadline(parts[0].trim(), parts[1].trim());
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task[count]);
                System.out.println("Now you have " + (count + 1) + " tasks in the list.");
                count++;

            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.substring(6).split("/from");
                String[] eventParts = parts[1].trim().split("/to");
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
                task[count] = new Task(userInput);
                System.out.println("added: " + task[count]);
                count++;
            }
        }

        scanner.close();
    }
}