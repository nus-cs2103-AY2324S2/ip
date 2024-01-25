import java.util.ArrayList;
import java.util.List;

class Task {
    private static int count = 0;
    private final int id;
    private String title;
    private boolean isDone;

    public Task(String title) {
        this.id = ++count;
        this.title = title;
        this.isDone = false;
    }

    public int getId() {
        return this.id;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[\u2713] " : "[\u2718] ") + title;
    }
}

class Todo extends Task {
    public Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    private String by;

    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    private String at;

    public Event(String title, String at) {
        super(title);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

public class Duke {
    public static void main(String[] args) {
        String logo = "\n /$$      /$$  /$$$$$$  /$$$$$$$  /$$$$$$$$ /$$     /$$\n"
                + "| $$$    /$$$ /$$__  $$| $$__  $$|__  $$__/|  $$   /$$/\n"
                + "| $$$$  /$$$$| $$  \\ $$| $$  \\ $$   | $$    \\  $$ /$$/ \n"
                + "| $$ $$/$$ $$| $$  | $$| $$$$$$$/   | $$     \\  $$$$/  \n"
                + "| $$  $$$| $$| $$  | $$| $$__  $$   | $$      \\  $$/   \n"
                + "| $$\\  $ | $$| $$  | $$| $$  \\ $$   | $$       | $$    \n"
                + "| $$ \\/  | $$|  $$$$$$/| $$  | $$   | $$       | $$    \n"
                + "|__/     |__/ \\______/ |__/  |__/   |__/       |__/    \n";
        System.out.println("\nHello I'm\n" + logo);
        System.out.println("What can I do for you?\n");

        List<Task> tasks = new ArrayList<Task>();
        while (true) {
            String command = System.console().readLine();
            String[] tokens = command.split(" ", 2);
            System.out.println("\n============================================================\n");
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());
                }
            } else if (tokens[0].equals("todo")) {
                String title = tokens[1];
                Todo newTodo = new Todo(title);
                tasks.add(newTodo);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTodo.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (tokens[0].equals("deadline")) {
                String[] deadlineTokens = tokens[1].split(" /by ");
                String title = deadlineTokens[0];
                String by = deadlineTokens[1];
                Deadline newDeadline = new Deadline(title, by);
                tasks.add(newDeadline);
                System.out.println("Got it. I've added this task:");
                System.out.println(newDeadline.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (tokens[0].equals("event")) {
                String[] eventTokens = tokens[1].split(" /at ");
                String title = eventTokens[0];
                String at = eventTokens[1];
                Event newEvent = new Event(title, at);
                tasks.add(newEvent);
                System.out.println("Got it. I've added this task:");
                System.out.println(newEvent.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (tokens[0].equals("done")) {
                int i = Integer.parseInt(tokens[1]) - 1;
                tasks.get(i).markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(i).toString());
            } else {
                Task newTask = new Task(command);
                tasks.add(newTask);
                System.out.println("added: " + newTask.toString());
            }
            System.out.println("\n============================================================\n");
        }
    }
}
