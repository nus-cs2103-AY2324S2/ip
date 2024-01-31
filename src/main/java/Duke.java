import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.*;


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

    public TaskType getType() {
        return this.type;
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

}

class TaskList {

    private ArrayList < Task > tasks;

    public TaskList(ArrayList < Task > tasks) {
        this.tasks = tasks;
    }

    public TaskList() {

    }
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addToDoTask(ToDo task) {
        tasks.add(task);
        System.out.println(task.toString());
    }

    public void addDeadlineTask(Deadline task) {
        tasks.add(task);
        System.out.println(task.toString());
    }

    public void addEventTask(Event task) {
        tasks.add(task);
        System.out.println(task.toString());
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }
    public void deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("You have not created task " + (index + 1) + " for me to delete!");
        }
        Task removedTask = tasks.remove(index);
        Ui.printDeletedTaskMessage(removedTask);
    }

    public void markStatus(Task job) throws DukeException {
        if (job.isDone) {
            throw new DukeException("This task is already marked as done.");
        }
        job.isDone = true;
        Ui.markTask(job);
    }

    public void unmarkStatus(Task job) throws DukeException {
        if (!job.isDone) {
            throw new DukeException("This task is already marked as not done.");
        }
        job.isDone = false;
        Ui.unmarkTask(job);
    }

    public static void getList(TaskList taskList) throws DukeException { //need to put in UI class?
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            String taskDetails = (i + 1) + ".[" + task.type + "][" + (task != null ? task.getStatusIcon() : "") + "] " + task.description;
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                taskDetails += " (by: " + (deadlineTask.by == null ? deadlineTask.byString : deadlineTask.by) + ")";
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                taskDetails += " (from: " + (eventTask.from == null ? eventTask.fromString : eventTask.from) + " to: " +
                        (eventTask.to == null ? eventTask.toString : eventTask.to) + ")";
            }
            System.out.println(taskDetails);
        }
    }
}


class ToDo extends Task {

    public ToDo(String description) throws DukeException {
        super(TaskType.T, description);
    }

    @Override
    public String toString() {
        return "Got it. I've added this task: \n [T][" + getStatusIcon() + "] " + getDescription();
    }
}

class Deadline extends Task {
    protected LocalDateTime by;
    protected String byString;

    public Deadline(String description, String byString) throws DukeException {
        super(TaskType.D, description);
        this.byString = byString.trim();

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            this.by = LocalDateTime.parse(byString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            this.by = null;
        }

        if (this.by == null && this.byString.isEmpty()) {
            throw new DukeException("By when? You forgot to enter \"/by\"");
        } else if (description.isEmpty()) {
            throw new DukeException("You forgot to enter the task for which you have to complete it by");
        } else {
            throw new DukeException("You did not mention the task or deadline! Please re-enter correctly!");
        }
    }
    public Object getBy() {
        return (this.by != null) ? this.by : this.byString;
    }

    @Override
    public String toString() {
        String byStringFormatted = (by != null) ?
                " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")" :
                (this.byString != null ? " (by: " + this.byString + ")" : "");

        return "Got it. I've added this task:\n [D][" + getStatusIcon() + "] " + getDescription() + byStringFormatted; // + by.getClass();
    }
}

class Event extends Task {
    protected LocalDateTime from;
    protected String fromString;
    protected LocalDateTime to;
    protected String toString;

    public Event(String description, String fromString, String toString) throws DukeException {
        super(TaskType.E, description);

        this.fromString = fromString.trim();
        this.toString = toString.trim();

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            this.from = LocalDateTime.parse(fromString, dateTimeFormatter);
            this.to = LocalDateTime.parse(toString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            this.from = null;
            this.to = null;
        }

        validateInputs();
    }

    public Object getFrom() {
        return (this.from != null) ? this.from : this.fromString;
    }
    public Object getTo() {
        return (this.to != null) ? this.to : this.toString;
    }

    private void validateInputs() throws DukeException {
        if ((from == null && to == null) && (fromString.isEmpty() && toString.isEmpty())) {
            throw new DukeException("You did not mention the duration! Please re-enter correctly!");
        } else if (from == null && fromString.isEmpty()) {
            throw new DukeException("You did not mention from when! Please re-enter correctly!");
        } else if (to == null && toString.isEmpty()) {
            throw new DukeException("You did not mention till when! Please re-enter correctly!");
        } else if (description.isEmpty()) {
            throw new DukeException("You didn't specify the event!");
        }
    }


    @Override
    public String toString() {
        String fromStringFormatted = (from != null) ?
                " from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) :
                (this.fromString != null ? " from: " + this.fromString : "");

        String toStringFormatted = (to != null) ?
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) :
                (this.toString != null ? " to: " + this.toString : "");

        return "Got it. I've added this task:\n [E][" + getStatusIcon() + "] " + getDescription() +
                fromStringFormatted + toStringFormatted; //+ from.getClass() + to.getClass();
    }
}

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(tasks, storage, scanner, true);

        while (parser.getRunningStatus()) {
            try {
                String input = scanner.nextLine().trim();
                parser.setCommand(input);
                parser.processCommand();

            } catch (DukeException e) {
                try {
                    ui.showError(e.getMessage());
                } catch (DukeException em) {
                    throw new RuntimeException(em);
                }
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}

class Ui {
    ArrayList < Task > arr;
    public static void showLoadingError() {
        System.out.println("Error loading tasks. Initializing with an empty task list."); //fix?
    }

    public static void showWelcomeMessage() {
        System.out.println("-------------------------------");
        System.out.println("Hello! I'm Tango. \nWhat can I do for you today?");
        System.out.println("-------------------------------");
    }

    public static void printNumberOfTasks(TaskList tasks) {
        System.out.println(tasks.size() == 1 ? " Now you have 1 task in the list." :
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void printDeletedTaskMessage(Task task) {
        String taskDetails = "Noted. I've removed this task:\n" +
                "[" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription();
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            taskDetails += " (by: " + deadlineTask.getBy() + ")";
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            taskDetails += " (from: " + eventTask.getFrom() + " to: " + eventTask.getTo() + ")";
        }

        System.out.println(taskDetails);
    }

    public static void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showError(String errorMessage) throws DukeException {
        System.out.println(errorMessage);
    }

    public static void showTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("You have no tasks in your list!");
        } else {
            System.out.println(tasks.size() == 1 ? "Here is the task in your list:" : "Here are the tasks in your list:");
            try {
                TaskList.getList(tasks);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    public static void markTask(Task task) {
        System.out.println("Nice! I've marked this as done: \n " + "[" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription());
    }

    public static void printDashes() {
        System.out.println("-------------------------------");
    }

    public static void unmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n " + "[" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription());
    }
}

class Parser {
    private String command;
    private boolean hasChanged = false;
    private Ui ui = new Ui();
    private TaskList tasks;
    protected Storage storage;
    protected Scanner scanner;
    protected boolean isRunning;

    public Parser(TaskList tasks, Storage storage, Scanner scanner, boolean isRunning) {
        this.tasks = tasks;
        this.storage = storage;
        this.scanner = scanner;
        this.isRunning = isRunning;
    }

    public boolean getRunningStatus() {
        return this.isRunning;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void processCommand() throws DukeException {
        String[] words = command.split("\\s+");

        try {
            ui.printDashes();
            switch (words[0].toLowerCase()) {
                case "bye":
                    ui.showGoodbyeMessage();
                    this.isRunning = false;
                    break;

                case "list":
                    Ui.showTaskList(tasks);
                    break;
                case "mark":
                    if (words.length > 1) {
                        int index = Integer.parseInt(words[1]) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            Task task = tasks.getTask(index);
                            tasks.markStatus(task);
                            storage.saveTasks(tasks);
                            hasChanged = true; //flag, no need to change
                        } else {
                            Ui.showError("You have not created a task " + words[1] + " yet!");
                        }
                    }
                    break;
                case "unmark":
                    if (words.length > 1) {
                        int index = Integer.parseInt(words[1]) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            Task task = tasks.getTask(index);
                            tasks.unmarkStatus(task);
                            storage.saveTasks(tasks);
                            hasChanged = true; //flag, no need to change
                        } else {
                            Ui.showError("You have not created a task " + words[1] + " yet!");
                        }
                    }
                    break;

                case "delete":
                    if (words.length > 1) {
                        tasks.deleteTask(Integer.parseInt(words[1]) - 1);
                        Ui.printNumberOfTasks(tasks);
                        storage.saveTasks(tasks);
                        hasChanged = true;
                    }
                    break;

                case "todo":
                    if (command.length() <= 5) {
                        ui.showError("You gotta enter some task TO DO!");
                    } else {
                        tasks.addToDoTask(new ToDo(command.substring(5).trim()));
                        storage.saveTasks(tasks);
                        ui.printNumberOfTasks(tasks);
                        hasChanged = true;
                    }
                    break;

                case "deadline":
                      try {
                    int byIndex = command.indexOf("/by");
                        if (byIndex != -1 && byIndex < command.length() - 3) { // Check if "/by" is present
                    String description = command.substring(9, byIndex).trim();
                    String by = command.substring(byIndex + 3).trim();
                    tasks.addDeadlineTask(new Deadline(description, by));
                    storage.saveTasks(tasks);
                    ui.printNumberOfTasks(tasks);
                    hasChanged = true;
                        } else if (byIndex == -1) {
                            throw new DukeException("You did not mention the deadline! Please re-enter correctly");
                        } else {
                            throw new DukeException("Please include both task description and deadline correctly!");
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                break;

                case "event":
                    Matcher fromMatcher = Pattern.compile("/from\\s+(\\S+[^/]+)").matcher(command);
                    Matcher toMatcher = Pattern.compile("/to\\s+(\\S.+)").matcher(command);
                    String eventDescription = "";
                    Matcher descriptionMatcher = Pattern.compile("event\\s+(.+?)\\s*/from").matcher(command);
                    if (words.length > 1) {
                        if (descriptionMatcher.find()) {
                            eventDescription = descriptionMatcher.group(1).trim();
                        }
                    } else {
                        Ui.showError("You didn't enter the details or duration!");
                        break;
                    }
                    String from = fromMatcher.find() ? fromMatcher.group(1).trim() : "";
                    String to = toMatcher.find() ? toMatcher.group(1).trim() : "";
                    tasks.addEventTask(new Event(eventDescription, from, to));
                    storage.saveTasks(tasks);
                    Ui.printNumberOfTasks(tasks);
                    hasChanged = true;
                    break;

                default:
                    ui.showError("I don't know what you are saying :(");
                    break;
            }
        } catch (DukeException | IOException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.printDashes();
        }
    }
}

class Storage {
    private static final String FILE_PATH = "./data/duke.txt";
    private TaskList taskList = new TaskList(new ArrayList < Task > ());

    public void saveTasks(TaskList tasks) throws IOException {
        File file = new File(FILE_PATH);
        File parentFolder = file.getParentFile();

        if (!parentFolder.exists()) {
            if (!parentFolder.mkdirs()) {
                throw new IOException("Failed to create the data folder.");
            }
        }

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                String text = task.getType() + " | " + (task.getStatusIcon().equals("X") ? "1" : "0") + " | " + task.getDescription();
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    if (deadline.by != null) {
                        try {
                            String formattedDateTime = deadline.by.format(DateTimeFormatter.ofPattern("MMM dd YYYY HH:mm"));
                            text += " | " + formattedDateTime;
                        } catch (DateTimeException e) {
                            text += " | " + deadline.byString;
                        }
                    } else {
                        text += " | " + deadline.byString;
                    }
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    String fromString = (event.from != null) ?
                            event.from.format(DateTimeFormatter.ofPattern("MMM dd YYYY HH:mm")) :
                            (event.fromString != null ? event.fromString : "");

                    String toString = (event.to != null) ?
                            event.to.format(DateTimeFormatter.ofPattern("MMM dd YYYY HH:mm")) :
                            (event.toString != null ? event.toString : "");

                    text += " | " + fromString + " - " + toString;
                }
                fileWriter.write(text);
                fileWriter.append("\n");
            }
        }

    }

    public ArrayList < Task > load() throws DukeException {
        ArrayList < Task > loadedTasks = new ArrayList < > ();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("\nData file does not currently exist. However, as you add a task, it will save it to\nthe " +
                    "path specified. You can view your task list after exiting the chatbot.");
            return loadedTasks;
        }

        try (Scanner scanner = new Scanner(file)) {
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

                loadedTasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return loadedTasks;
    }
}