import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Declare Scanner instance

        Task[] tasks = new Task[100];
        int taskCount = 0;

        String chatbotName = "Jamie";
        System.out.println("Hello! I'm " + chatbotName + "\nWhat can I do for you?");

        while (true) {
            System.out.print(">> ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;

            } else if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i].toString());
                }

            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());

                } else {
                    System.out.println("Invalid task index.");
                }

            } else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) -1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsUndone();
                    System.out.println("Ok, I've marked this task as not done yet: \n [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
                } else {
                    System.out.println("Invalid task index.");
                }

            } else {
                Task newTask = TaskParser.parseTask(userInput);
                tasks[taskCount++] = newTask;
                System.out.println("Got it. I've added this task:\n " + newTask.toString() +
                        "\nNow you have " + taskCount + " tasks in the list.");
            }
        }
        scanner.close(); // Close the Scanner to avoid resource leaks
    }
}


class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}


class ToDo extends Task {

    public ToDo(String description) {
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

    public Event(String decription, String from, String to) {
        super(decription);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return  "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}


class TaskParser {
    public static Task parseTask(String userInput) {
        String[] words = userInput.split(" ", 2);
        if (words.length < 2) {
            return null;
        }

        String command = words[0].toLowerCase();
        String details = words[1];

        switch (command) {
            case "todo":
                return new ToDo(details);
            case "deadline":
                String[] deadlineDetails = details.split(" /by ");
                if (deadlineDetails.length == 2) {
                    return new Deadline(deadlineDetails[0], deadlineDetails[1]);
                }
                break;
            case "event":
                String[] eventDetails = details.split(" /from ");
                if (eventDetails.length == 2) {
                    String[] eventTiming = eventDetails[1].split(" /to ");
                    if (eventTiming.length == 2) {
                        return new Event(eventDetails[0], eventTiming[0], eventTiming[1]);
                    }
                }
                break;
        }
        return null;
    }
}