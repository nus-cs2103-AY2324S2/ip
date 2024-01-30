package duke;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.io.IOException;
import java.io.FileWriter;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit(); // Check if the command signals to exit
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.closeScanner();
    }

    public static void main(String[] args) {

        new Duke("./data/duke.txt/duke.txt").run();
    }
}

class DukeException extends Exception {
    public DukeException(String message) {

        super(message);
    }
}

class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm SCZL");
        System.out.println("What can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
    }

    public String readCommand() {

        return scanner.nextLine();
    }

    public void showLoadingError() {

        System.out.println("Error loading file.");
    }

    public void closeScanner() {
        scanner.close();
    }

    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void showDeletedTask(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}


class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            throw new DukeException("File not found");
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                try {
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    Task task = null;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                    switch (type) {
                        case "T":
                            task = new Todo(description);
                            break;
                        case "D":
                            if (parts.length < 4) throw new DukeException("Invalid deadline format in file.");
                            LocalDateTime byDate = LocalDateTime.parse(parts[3], formatter);
                            task = new Deadline(description, byDate);
                            break;
                        case "E":
                            if (parts.length < 5) throw new DukeException("Invalid event format in file.");
                            LocalDateTime from = LocalDateTime.parse(parts[3], formatter);
                            LocalDateTime to = LocalDateTime.parse(parts[4], formatter);
                            task = new Event(description, from, to);
                            break;
                    }

                    if (task != null) {
                        if (isDone) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                    }
                } catch (DukeException | DateTimeParseException e) {
                    System.out.println("Skipping invalid task: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found: " + e.getMessage());
        }
        return tasks;

    }

    public void save(TaskList tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                writer.println(taskToFileString(task));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    private String taskToFileString(Task task) {
        String type = task instanceof Todo ? "T" :
                task instanceof Deadline ? "D" :
                        task instanceof Event ? "E" : "";
        String status = task.isDone ? "1" : "0";
        String details = task.getDescription();
        String additionalInfo = "";

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            additionalInfo = " | " + deadline.getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else if (task instanceof Event) {
            Event event = (Event) task;
            additionalInfo = " | " + event.getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                    " | " + event.getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

        return type + " | " + status + " | " + details + additionalInfo;
    }

}


class Parser {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static Command parse(String fullCommand) throws DukeException {
        String[] commandParts = fullCommand.split(" ", 2);
        String commandType = commandParts[0];
        String commandArgs = commandParts.length > 1 ? commandParts[1] : "";

        switch (commandType) {
        case "todo":
            if (commandArgs.isEmpty()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new AddTodoCommand(commandArgs);
        case "deadline":
            return parseAddDeadlineCommand(commandArgs);
        case "event":
            return parseAddEventCommand(commandArgs);
        case "list":
            return new ListCommand();
        case "mark":
            try {
                int index = Integer.parseInt(commandArgs) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number format.");
            }
        case "unmark":
            try {
                int index = Integer.parseInt(commandArgs) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number format.");
            }
        case "delete":
            try {
                int index = Integer.parseInt(commandArgs) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number format.");
            }
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeException("Unknown command");
        }
    }

    private static Command parseAddDeadlineCommand(String commandArgs) throws DukeException {
        String[] parts = commandArgs.split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException("Invalid deadline command format.");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        try {
            LocalDateTime byDate = LocalDateTime.parse(by, dateTimeFormatter);
            return new AddDeadlineCommand(description, byDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use yyyy-MM-dd HHmm format.");
        }
    }

    private static Command parseAddEventCommand(String commandArgs) throws DukeException {
        String[] parts = commandArgs.split("/at", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException("Invalid event command format.");
        }
        String description = parts[0].trim();
        String at = parts[1].trim();
        String[] timeParts = at.split("-", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new DukeException("Invalid time format for event command.");
        }
        try {
            LocalDateTime startTime = LocalDateTime.parse(timeParts[0].trim(), dateTimeFormatter);
            LocalDateTime endTime = LocalDateTime.parse(timeParts[1].trim(), dateTimeFormatter);
            return new AddEventCommand(description, startTime, endTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use yyyy-MM-dd HHmm format.");
        }
    }
}


class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    // ... any other methods needed for task management ...
}


enum TaskType {
    TODO,
    DEADLINE,
    EVENT
}

class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return "[" + taskType + "]" + (isDone ? "[X] " : "[ ] ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
    @Override
    public String toString() {
        return getStatusIcon() + getDescription();
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }
}

class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return super.getDescription() + " (by: " + formatter.format(by) + ")";
    }
    public LocalDateTime getBy() {
        return by;
    }
}

class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return super.getDescription() + " (from: " + formatter.format(from) + " to: " + formatter.format(to) + ")";
    }
    public LocalDateTime getFrom() {
        return from;
    }
    public LocalDateTime getTo() {
        return to;
    }
}

abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}

class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.getTask(index);
        task.markAsDone();
        ui.showMarkedTask(task);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.getTask(index);
        task.markAsNotDone();
        ui.showUnmarkedTask(task);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.removeTask(index);
        ui.showDeletedTask(task, tasks.getSize());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true; // Indicate that the application should exit
    }
}



class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
    public boolean isExit() {
        return false;
    }
}

class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        ui.showTaskAdded(newTodo, tasks.getSize());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newDeadline = new Deadline(description, by);
        tasks.addTask(newDeadline);
        ui.showTaskAdded(newDeadline, tasks.getSize());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

class AddEventCommand extends Command {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    public AddEventCommand(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newEvent = new Event(description, start, end);
        tasks.addTask(newEvent);
        ui.showTaskAdded(newEvent, tasks.getSize());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}