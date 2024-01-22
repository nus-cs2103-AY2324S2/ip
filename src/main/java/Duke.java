import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public String toString() {
        return "Ummm, " + getMessage();
    }
}

class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    public Task(String type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public static void numberOfTasks(ArrayList < Task > arr) {
        System.out.println(
                arr.size() == 1 ? "Now you have 1 task in the list." :
                        "Now you have " + arr.size() + " tasks in the list.");
    }

    public String markStatus() throws DukeException {
        if (isDone) {
            throw new DukeException("This task is already marked as done.");
        }
        isDone = true;
        return "Nice! I've marked this as done: \n " + "[" + this.type + "][" + getStatusIcon() + "] " + description;
    }

    public String unmarkStatus() throws DukeException {
        if (!isDone) {
            throw new DukeException("This task is already marked as not done.");
        }
        isDone = false;
        return "OK, I've marked this task as not done yet: \n " + "[" + this.type + "][" + getStatusIcon() + "] " + description;
    }

    public static void getList(ArrayList < Task > arr) throws DukeException {
        for (int i = 0; i < arr.size(); i++) {
            Task task = arr.get(i);
            String taskDetails = (i + 1) + ".[" + task.type + "][" + (task != null ? task.getStatusIcon() : "") + "] " + task.description;
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                taskDetails += " (by: " + deadlineTask.by + ")";
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                taskDetails += " (from: " + eventTask.from + " to: " + eventTask.to + ")";
            }
            System.out.println(taskDetails);
        }
    }
}

class ToDo extends Task {
    private ArrayList < Task > arr = new ArrayList < > ();

    public ToDo(String type, String description) throws DukeException {
        super(type, description);
        arr.add(this);
    }

    @Override
    public String toString() {
        return "Got it. I've added this task: \n [T][" + getStatusIcon() + "] " + getDescription();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String type, String description, String by) throws DukeException {
        super(type, description);

        if (!description.contains("/by")) {
            throw new DukeException("By when? You forgot to enter \"/by\"");
        }

        int index = description.indexOf("/by");

        if (index == 0 || index == description.length() - 3) {
            throw new DukeException("You forgot to enter the task for which you have to complete it by");
        }

        this.by = description.substring(index + 3).trim();
        this.description = description.substring(0, index).trim();
    }

    @Override
    public String toString() {
        return "Got it. I've added this task: \n [D][" + getStatusIcon() + "] " + getDescription() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String type, String description, String from, String to) throws DukeException {
        super(type, description);

        if (description.isEmpty() && (!from.isEmpty() && !to.isEmpty())) {
            throw new DukeException("You didn't specify the event!");
        } else if (description.isEmpty() && (!from.isEmpty() || !to.isEmpty())) {
            throw new DukeException("You didn't specify the details or give the duration correctly! Please re-enter");
        } else if (!description.isEmpty() && (from.isEmpty() && to.isEmpty())) {
            throw new DukeException("You did not mention the duration! Please re-enter correctly!");
        } else if (!description.isEmpty() && from.isEmpty()) {
            throw new DukeException("You did not mention from when! Please re-enter correctly!");
        } else if (!description.isEmpty() && to.isEmpty()) {
            throw new DukeException("You did not mention till when! Please re-enter correctly!");
        }

        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n [E][" + getStatusIcon() + "] " + getDescription() +
                (from.isEmpty() ? "" : " (from: " + from) + (to.isEmpty() ? "" : " to: " + to + ")");
    }
}

public class Duke {
    public static void main(String[] args) {
        ArrayList < Task > tasks = new ArrayList < > ();
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------------");
        System.out.println("Hello! I'm Tango. \nWhat can I do for you today?");
        System.out.println("-------------------------------");

        while (true) {
            String input = scan.nextLine().trim();
            String[] words = input.split("\\s+");

            if (words[0].equalsIgnoreCase("bye")) {
                System.out.println("-------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-------------------------------");
                break;
            } else if (words[0].equalsIgnoreCase("list")) {
                System.out.println("-------------------------------");
                System.out.println(tasks.size() == 0 ? "You have no tasks in your list!" :
                        tasks.size() == 1 ? "Here is the task in your list:" :
                                "Here are the tasks in your list:");
                try {
                    Task.getList(tasks);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                System.out.println("-------------------------------");
            } else if (words[0].equalsIgnoreCase("mark") && words.length > 1) {
                System.out.println("-------------------------------");
                int index = Integer.parseInt(words[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    Task task = tasks.get(index);
                    try {
                        System.out.println(task.markStatus());
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                } else {
                    try {
                        throw new DukeException("You have not created a task " + words[1] + " yet!");
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                }
                System.out.println("-------------------------------");
            } else if (words[0].equalsIgnoreCase("unmark") && words.length > 1) {
                System.out.println("-------------------------------");
                int index = Integer.parseInt(words[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    Task task = tasks.get(index);
                    try {
                        System.out.println(task.unmarkStatus());
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                } else {
                    try {
                        throw new DukeException("You have not created a task " + words[1] + " yet!");
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                }
                System.out.println("-------------------------------");
            } else if (words[0].equalsIgnoreCase("todo")) {
                System.out.println("-------------------------------");
                try {
                    if (words.length == 1) {
                        throw new DukeException("You gotta enter some task TO DO");
                    }
                    Task task = new ToDo("T", input.substring(5).trim());
                    tasks.add(task);
                    System.out.println(task.toString());
                    task.numberOfTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                System.out.println("-------------------------------");
            } else if (words[0].equalsIgnoreCase("deadline")) {
                System.out.println("-------------------------------");
                try {
                    if (input.length() >= 9) {
                        Task task = new Deadline("D", input.substring(9).trim(), words.length > 2 ? words[2] : "");
                        tasks.add(task);
                        System.out.println(task);
                        task.numberOfTasks(tasks);
                    } else {
                        throw new DukeException("Deadline for what and by when? Please re-enter correctly");
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
                System.out.println("-------------------------------");
            } else if (words[0].equalsIgnoreCase("event")) {
                System.out.println("-------------------------------");
                try {
                    Matcher fromMatcher = Pattern.compile("/from\\s+(\\S+)").matcher(input);
                    Matcher toMatcher = Pattern.compile("/to\\s+(\\S+)").matcher(input);
                    String eventDescription = "";

                    if (words.length > 1) {
                        eventDescription = (words[1].equalsIgnoreCase("/from") || words[1].equalsIgnoreCase("/to")) ? "" : words[1];
                    } else {
                        throw new DukeException("You didn't enter the details or duration!");
                    }
                    String from = fromMatcher.find() ? fromMatcher.group(1).trim() : "";
                    String to = toMatcher.find() ? toMatcher.group(1).trim() : "";

                    Task task = new Event("E", eventDescription, from, to);
                    tasks.add(task);
                    System.out.println(task.toString());
                    task.numberOfTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("-------------------------------");
            } else {
                System.out.println("-------------------------------");
                try {
                    throw new DukeException("I don't know what you are saying :(");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("-------------------------------");
            }
        }
    }
}