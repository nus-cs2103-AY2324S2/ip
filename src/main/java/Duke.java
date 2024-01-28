import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Declare Scanner instance

        ArrayList<Task> tasks = new ArrayList<>();
        String chatbotName = "Jamie";

        System.out.println("Hello! I'm " + chatbotName + "\nWhat can I do for you?");

        while (true) {
            try {
                System.out.print(">> ");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println(" Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    listTasks(tasks);
                } else if (userInput.startsWith("mark")) {
                    markTaskAsDone(userInput, tasks);
                } else if (userInput.startsWith("unmark")) {
                    unmarkTaskAsUndone(userInput, tasks);
                } else if (userInput.startsWith("delete")) {
                    deleteTask(userInput, tasks);
                } else {
                    addTask(userInput, tasks);
                }
            } catch (JamieException e) {
                System.out.println(" " + e.getMessage());
            }
        }
        scanner.close(); // Close the Scanner to avoid resource leaks
    }

    private static void addTask(String userInput, ArrayList<Task> tasks) throws JamieException {
        Task newTask = TaskParser.parseTask(userInput);
        if (newTask != null) {
            tasks.add(newTask);
            System.out.println("Got it. I've added this task:\n " + newTask.toString() +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new JamieException("OOPS!!! I'm sorry, but I don't know what that means");
        }
    }

    private static void listTasks(ArrayList<Task> tasks) throws JamieException {
        if (!tasks.isEmpty()) {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
            }
        } else {
            throw new JamieException("The task list is empty.");
        }
    }

    private static void markTaskAsDone(String userInput, ArrayList<Task> tasks) throws JamieException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskIndex).toString());
            } else {
                throw new JamieException("Invalid task index.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new JamieException("Invalid command format for marking a task as done.");
        }
    }

    private static void unmarkTaskAsUndone(String userInput, ArrayList<Task> tasks) throws JamieException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markAsUndone();
                System.out.println("Ok, I've marked this task as not done yet:\n" + tasks.get(taskIndex).toString());
            } else {
                throw new JamieException("Invalid task index.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new JamieException("Invalid command format for marking a task as not done yet.");
        }
    }

    private static void deleteTask(String userInput, ArrayList<Task> tasks) throws JamieException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task removedTask = tasks.remove(taskIndex);
                System.out.println("Noted. I've removed this task:\n" + removedTask.toString() +
                        "\nNow you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new JamieException("Invalid task index.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new JamieException("Invalid command format for deleting a task.");
        }
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

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return  "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}


class TaskParser {
    public static Task parseTask(String userInput) throws JamieException{
        String[] words = userInput.split(" ", 2);
        if (words.length < 2) {
            throw new JamieException("OOPS!!! The description of a task cannot be empty.");
        }

        String command = words[0].toLowerCase();
        String details = words[1];

        switch (command) {
            case "todo":
                if (details.trim().isEmpty()) {
                    throw new JamieException("OOPS!!! The description of a todo cannot be empty.");
                }
                return new ToDo(details);
            case "deadline":
                String[] deadlineDetails = details.split(" /by ");
                if (deadlineDetails.length != 2 || deadlineDetails[0].trim().isEmpty() || deadlineDetails[1].trim().isEmpty()) {
                    throw new JamieException("OOPS!!! Invalid format for deadline. Please use: deadline <description> /by <date>");
                }
                return new Deadline(deadlineDetails[0], deadlineDetails[1]);
            case "event":
                String[] eventDetails = details.split(" /from ");
                if (eventDetails.length != 2) {
                    throw new JamieException("OOPS!!! Invalid format for event. Please use: event <description> /from <start> /to <end>");
                }
                String[] eventTiming = eventDetails[1].split(" /to ");
                if (eventTiming.length != 2 || eventTiming[0].trim().isEmpty() || eventTiming[1].trim().isEmpty()) {
                    throw new JamieException("OOPS!!! Invalid format for event timing. Please use: event <description> /from <start> /to <end>");
                }
                return new Event(eventDetails[0], eventTiming[0], eventTiming[1]);
        }
        throw new JamieException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

class JamieException extends Exception {
    public JamieException(String message) {
        super(message);
    }
}