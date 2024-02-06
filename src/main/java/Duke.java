import java.util.ArrayList;
import java.util.Scanner;

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

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
        ArrayList<Task> tasks = new ArrayList<>();

        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            System.out.println("____________________________________________________________");
            try {
                if (command.equals("list")) {
                    if (tasks.isEmpty()) {
                        System.out.println("You have no tasks in your list.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                    }
                } else if (command.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        throw new DukeException("Invalid task index.");
                    }
                    tasks.get(taskIndex).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(taskIndex));
                } else if (command.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        throw new DukeException("Invalid task index.");
                    }
                    tasks.get(taskIndex).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(taskIndex));
                } else if (command.startsWith("delete")) {
                    int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        throw new DukeException("Invalid task index.");
                    }
                    Task deletedTask = tasks.remove(taskIndex);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + deletedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    if (command.startsWith("todo")) {
                        String description = command.substring(5).trim();
                        if (description.isEmpty()) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        tasks.add(new ToDoTask(description));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else if (command.startsWith("deadline")) {
                        String[] parts = command.substring(9).split("/by");
                        String description = parts[0].trim();
                        String by = (parts.length > 1) ? parts[1].trim() : "";
                        if (description.isEmpty() || by.isEmpty()) {
                            throw new DukeException("The description and/or deadline of a deadline task cannot be empty.");
                        }
                        tasks.add(new DeadlineTask(description, by));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else if (command.startsWith("event")) {
                        String[] parts = command.substring(6).split("/from");
                        if (parts.length < 2) {
                            throw new DukeException("Invalid command format. Please use 'event <description> /from <start time> /to <end time>'.");
                        }
                        String description = parts[0].trim();
                        String[] eventParts = parts[1].split("/to");
                        if (eventParts.length < 2) {
                            throw new DukeException("Invalid command format. Please use 'event <description> /from <start time> /to <end time>'.");
                        }
                        String from = eventParts[0].trim();
                        String to = eventParts[1].trim();
                        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                            throw new DukeException("The description and/or timing of an event task cannot be empty.");
                        }
                        tasks.add(new EventTask(description, from, to));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid command format. Please provide a valid task index.");
            } catch (DukeException e) {
                System.out.println("____________________________________________________________");
                System.out.println("OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
            System.out.println("____________________________________________________________");
            command = scanner.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

// Task, ToDoTask, DeadlineTask, EventTask classes remain unchanged

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