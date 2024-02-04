import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dav {

    private static final String FILE_PATH = "./data/dav.txt";
    private static TaskList tasks;
    private static Ui ui;
    private static Storage storage;

    public Dav() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greetUser();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = ui.getUserInput(scanner);
            Parser.parseUserInput(userInput, tasks, ui, storage);

        } while (!userInput.equalsIgnoreCase("bye"));

        ui.exit();
    }

    public static void main(String[] args) {
        new Dav().run();
    }
}

class Ui {

    public void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" What's up! I'm Dav");
        System.out.println(" How may I help you?");
        System.out.println("____________________________________________________________");
    }

    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    public void exit() {
        System.out.println(" Goodbye. ");
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
}

class Storage {

    private static final String FILE_PATH = "./data/dav.txt";

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        try {
            List<Task> tasks = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                Task task = Task.parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }

            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }
    }

    public void save(List<Task> tasks) throws DukeException {
        try {
            Path filePath = Paths.get(FILE_PATH);
            StringBuilder data = new StringBuilder();

            for (Task task : tasks) {
                data.append(task.toDataString()).append("\n");
            }

            Files.write(filePath, data.toString().getBytes());
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage());
        }
    }
}

class Parser {

    public static void parseUserInput(String input, TaskList tasks, Ui ui, Storage storage) {
        System.out.println("____________________________________________________________");

        try {
            if (input.equalsIgnoreCase("list")) {
                tasks.listTasks();
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5).trim());
                tasks.markTaskDone(taskIndex);
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7).trim());
                tasks.unmarkTaskDone(taskIndex);
            } else if (input.startsWith("todo")) {
                tasks.addTodoTask(input.substring(4).trim());
            } else if (input.startsWith("deadline")) {
                tasks.addDeadlineTask(input.substring(8).trim());
            } else if (input.startsWith("event")) {
                tasks.addEventTask(input.substring(5).trim());
            } else if (input.startsWith("check ")) {
                String dateString = input.substring(6).trim();
                tasks.checkTasksOnDate(dateString);
            } else if (input.startsWith("delete")) {
                int taskIndex = Integer.parseInt(input.substring(6).trim());
                tasks.deleteTask(taskIndex);
            } else if (!input.equalsIgnoreCase("bye")) {
                throw new IllegalArgumentException("Huh? what's that?");
            }
        } catch (NumberFormatException e) {
            System.out.println("This is not a valid task index.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("____________________________________________________________");
    }
}

class TaskList {

    private List<Task> tasks;
    private Storage storage;

    public TaskList(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTodoTask(String taskDescription) {
        if (taskDescription.isEmpty()) {
            System.out.println("Do nothing?");
        } else {
            addTask(new TodoTask(taskDescription));
        }
    }

    public void addDeadlineTask(String taskDetails) {
        String[] details = taskDetails.split(" /by ");
        if (details.length == 2) {
            String description = details[0].trim();
            String dateTime = details[1].trim();

            if (description.isEmpty()) {
                System.out.println("No deadline?");
            } else {
                try {
                    String[] dateTimeParts = dateTime.split(" ");
                    String date = dateTimeParts[0];
                    String time = dateTimeParts[1];

                    addTask(new DeadlineTask(description, date, time));
                } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid date or time format. Please use yyyy-MM-dd HHmm.");
                }
            }
        } else {
            System.out.println("Invalid deadline task format.");
        }
    }

    public void addEventTask(String taskDetails) {
        String[] details = taskDetails.split(" /from ");
        if (details.length == 2) {
            String description = details[0].trim();
            String[] timeDetails = details[1].split(" /to ");

            if (description.isEmpty()) {
                System.out.println("No event?");
            } else if (timeDetails.length == 2) {
                try {
                    LocalDateTime fromDateTime = LocalDateTime.parse(timeDetails[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                    LocalDateTime toDateTime = LocalDateTime.parse(timeDetails[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

                    addTask(new EventTask(description, fromDateTime, toDateTime));
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date or time format. Please use yyyy-MM-dd HHmm.");
                }
            } else {
                System.out.println("Invalid event task format.");
            }
        } else {
            System.out.println("Invalid event task format.");
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        saveTasks(); // Save tasks after adding
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks added yet.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void markTaskDone(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskIndex - 1));
            saveTasks();
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    public void unmarkTaskDone(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            tasks.get(taskIndex - 1).unmarkAsDone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(taskIndex - 1));
            saveTasks();
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    public void deleteTask(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            Task removedTask = tasks.remove(taskIndex - 1);
            System.out.println(" Task removed:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            saveTasks();
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    public void checkTasksOnDate(String dateString) {
        try {
            LocalDateTime targetDate = LocalDateTime.parse(dateString + " 0000", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            List<Task> tasksOnDate = new ArrayList<>();

            for (Task task : tasks) {
                if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    if (deadlineTask.byDateTime.toLocalDate().isEqual(targetDate.toLocalDate())) {
                        tasksOnDate.add(deadlineTask);
                    }
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    if (eventTask.fromDateTime.toLocalDate().isEqual(targetDate.toLocalDate()) ||
                            eventTask.toDateTime.toLocalDate().isEqual(targetDate.toLocalDate())) {
                        tasksOnDate.add(eventTask);
                    }
                }
            }

            if (tasksOnDate.isEmpty()) {
                System.out.println("No tasks on " + targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            } else {
                System.out.println("Tasks on " + targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ":");
                for (int i = 0; i < tasksOnDate.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + tasksOnDate.get(i));
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    private void saveTasks() {
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}

class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String toDataString() {
        return "";
    }

    public static Task parseTask(String data) {
        if (data.startsWith("T")) {
            return TodoTask.parseTask(data);
        } else if (data.startsWith("D")) {
            return DeadlineTask.parseTask(data);
        } else if (data.startsWith("E")) {
            return EventTask.parseTask(data);
        }

        System.out.println("Error parsing task from data: " + data);
        return null;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    public static Task parseTask(String data) {
        String[] parts = data.split(" \\| ");
        if (parts.length == 3) {
            TodoTask todoTask = new TodoTask(parts[2]);
            todoTask.isDone = parts[1].equals("1");
            return todoTask;
        }
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class DeadlineTask extends Task {

    protected LocalDateTime byDateTime;

    public DeadlineTask(String description, String date, String time) {
        super(description);
        this.byDateTime = parseDateTime(date, time);
    }

    private LocalDateTime parseDateTime(String date, String time) {
        String dateTimeString = date + " " + time;
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                byDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public static Task parseTask(String data) {
        String[] parts = data.split(" \\| ");
        if (parts.length == 4) {
            String[] dateTimeParts = parts[3].split(" ");
            if (dateTimeParts.length == 2) {
                DeadlineTask deadlineTask = new DeadlineTask(parts[2], dateTimeParts[0], dateTimeParts[1]);
                deadlineTask.isDone = parts[1].equals("1");
                return deadlineTask;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}

class EventTask extends Task {

    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    public EventTask(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    public EventTask(String description, String from, String to) {
        super(description);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description +
                " | " + fromDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) +
                " | " + toDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public static Task parseTask(String data) {
        String[] parts = data.split(" \\| ");
        if (parts.length == 5) {
            String[] dateTimeParts = parts[3].split(" ");
            String[] dateTimePartsTo = parts[4].split(" ");
            if (dateTimeParts.length == 2 && dateTimePartsTo.length == 2) {
                EventTask eventTask = new EventTask(parts[2], dateTimeParts[0] + " " + dateTimeParts[1], dateTimePartsTo[0] + " " + dateTimePartsTo[1]);
                eventTask.isDone = parts[1].equals("1");
                return eventTask;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) +
                " to: " + toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}