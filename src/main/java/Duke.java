import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

enum TaskType {
    T, D, E
}
class Task {
    protected String description;
    protected TaskType type;
    protected boolean isDone;
    protected String statusIcon;

    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        updateStatusIcon();
    }
    private void updateStatusIcon() {
        this.statusIcon = (isDone ? "X" : " ");
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

    public static void deleteTask(int index, ArrayList<Task> arr) throws DukeException {
        if (index < 0 || index >= arr.size()) {
            throw new DukeException("You have not created task " + (index + 1) + " for me to delete!");
        }
        Task removedTask = arr.remove(index);
        String taskDetails = "Noted. I've removed this task:\n" +
                "[" + removedTask.type + "][" + removedTask.getStatusIcon() + "] " + removedTask.getDescription();
        if (removedTask instanceof Deadline) {
            Deadline deadlineTask = (Deadline) removedTask;
           taskDetails += " (by: " + deadlineTask.by + ")";
        } else if (removedTask instanceof Event) {
            Event eventTask = (Event) removedTask;
            taskDetails += " (from: " + eventTask.from + " to: " + eventTask.to + ")";
        }

        System.out.println(taskDetails);
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

    public ToDo(String description) throws DukeException {
        super(TaskType.T, description);
        arr.add(this);
    }

    @Override
    public String toString() {
        return "Got it. I've added this task: \n [T][" + getStatusIcon() + "] " + getDescription();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(TaskType.D, description);

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

    public Event(String description, String from, String to) throws DukeException {
        super(TaskType.E, description);

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
    private static final String FILE_PATH = "./data/duke.txt";

    public static void main(String[] args) {
        boolean hasChanged = false;
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        try {
            loadTasks(tasks);
        } catch (DukeException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        System.out.println("-------------------------------");
        System.out.println("Hello! I'm Tango. \nWhat can I do for you today?");
        System.out.println("-------------------------------");


        while (true) {
            if (hasChanged) {
                try {
                    saveTasks(tasks);
                    hasChanged = false;
                } catch (IOException e) {
                    System.out.println("Error saving tasks: " + e.getMessage());
                }
            }
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
                        saveTasks(tasks);
                        hasChanged = true;
                    } catch (DukeException | IOException e) {
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
                        saveTasks(tasks);
                        hasChanged = true;
                    } catch (DukeException | IOException e) {
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
            } else if (words[0].equalsIgnoreCase("delete") && words.length > 1) {
                System.out.println("-------------------------------");
                int index = Integer.parseInt(words[1]) - 1;

                try {
                    Task.deleteTask(index, tasks);
                    Task.numberOfTasks(tasks);
                    saveTasks(tasks);
                    hasChanged = true;
                } catch (DukeException | IOException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("-------------------------------");

            } else if (words[0].equalsIgnoreCase("todo")) {
                System.out.println("-------------------------------");
                try {
                    if (words.length == 1) {
                        throw new DukeException("You gotta enter some task TO DO");
                    }
                    Task task = new ToDo(input.substring(5).trim());
                    tasks.add(task);
                    System.out.println(task.toString());
                    task.numberOfTasks(tasks);
                    hasChanged = true;
                } catch (DukeException e) {
                    System.out.println(e);
                }
                System.out.println("-------------------------------");
            } else if (words[0].equalsIgnoreCase("deadline")) {
                System.out.println("-------------------------------");
                try {
                    if (input.length() >= 9) {
                        Task task = new Deadline(input.substring(9).trim(), words.length > 2 ? words[2] : "");
                        tasks.add(task);
                        System.out.println(task);
                        task.numberOfTasks(tasks);
                        hasChanged = true;
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
                    Matcher fromMatcher = Pattern.compile("/from\\s+(\\S+[^/]+)").matcher(input);
                    Matcher toMatcher = Pattern.compile("/to\\s+(\\S.+)").matcher(input);
                    String eventDescription = "";
                    Matcher descriptionMatcher = Pattern.compile("event\\s+(.+?)\\s*/from").matcher(input);

                    if (words.length > 1) {
                        if (descriptionMatcher.find()) {
                            eventDescription = descriptionMatcher.group(1).trim();
                        }
                    } else {
                            throw new DukeException("You didn't enter the details or duration!");
                        }
                    String from = fromMatcher.find() ? fromMatcher.group(1).trim() : "";
                    String to = toMatcher.find() ? toMatcher.group(1).trim() : "";

                    Task task = new Event(eventDescription, from, to);
                    tasks.add(task);
                    System.out.println(task.toString());
                    task.numberOfTasks(tasks);
                    hasChanged = true;
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
    private static void saveTasks(ArrayList<Task> tasks) throws IOException {
        File file = new File(FILE_PATH);
        File parentFolder = file.getParentFile();

        if (!parentFolder.exists()) {
            if (!parentFolder.mkdirs()) {
                throw new IOException("Failed to create the data folder.");
            }
        }

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            for (Task task : tasks) {
                String text = task.type + " | " + (task.getStatusIcon().equals("X") ? "1" : "0") + " | " + task.description;
                if (task instanceof Deadline) {
                    text += " | " + ((Deadline) task).by;
                } else if (task instanceof Event) {
                    text += " | " + ((Event) task).from + "-" + ((Event) task).to;
                }
                fileWriter.write(text);
                fileWriter.append("\n");
            }
        }
    }
    private static void loadTasks(ArrayList<Task> tasks) throws DukeException {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("\nData file does not currently exist. However, as you add a task, it will save it to\nthe " +
                        "path specified. You can view your task list after exiting the chatbot.");
                return;
            }
                try(Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split("\\|");
                    TaskType taskType = TaskType.valueOf(parts[0].trim());
                    boolean isDone = parts[1].trim().equals("1");
                    String description = parts[2].trim();
                    String additionalInfo = (parts.length > 3) ? parts[3].trim() : null;

                    Task task = new Task(null, null);

                    if (taskType == TaskType.T) {
                        task = new ToDo(description);
                    } else if (taskType == TaskType.D) {
                        task = new Deadline(description, additionalInfo);
                    } else if (taskType == TaskType.E) {
                        String[] p = additionalInfo.split("-");
                        task = new Event(description, p[0].trim(), p[1].trim());
                    }
                    tasks.add(task);
                }
            }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}