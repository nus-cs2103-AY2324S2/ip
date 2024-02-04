import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Dav {
    private static final String FILE_PATH = "./data/dav.txt";
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        greetUser();
        startChat();
        exit();
    }

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" What's up! I'm Dav");
        System.out.println(" How may I help you?");
        System.out.println("____________________________________________________________");
    }

    public static void startChat() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = getUserInput(scanner);
            processUserInput(userInput);

        } while (!userInput.equalsIgnoreCase("bye"));
    }

    public static String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    public static void processUserInput(String input) {
        System.out.println("____________________________________________________________");

        try {
            if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5).trim());
                markTaskDone(taskIndex);
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7).trim());
                unmarkTaskDone(taskIndex);
            } else if (input.startsWith("todo")) {
                addTodoTask(input.substring(4).trim());
            } else if (input.startsWith("deadline")) {
                addDeadlineTask(input.substring(8).trim());
            } else if (input.startsWith("event")) {
                addEventTask(input.substring(5).trim());
            } else if (input.startsWith("check ")) {
                String dateString = input.substring(6).trim();
                checkTasksOnDate(dateString);
            } else if (input.startsWith("delete")) {
                int taskIndex = Integer.parseInt(input.substring(6).trim());
                deleteTask(taskIndex);
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

    public static void checkTasksOnDate(String dateString) {
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

    public static void addTodoTask(String taskDescription) {
        if (taskDescription.isEmpty()) {
            System.out.println("Do nothing?");
        } else {
            addTask(new TodoTask(taskDescription));
        }
    }

    public static void addDeadlineTask(String taskDetails) {
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

    public static void addEventTask(String taskDetails) {
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

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        saveTasks(); // Save tasks after adding
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks added yet.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    public static void markTaskDone(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskIndex - 1));
            saveTasks();
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    public static void unmarkTaskDone(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            tasks.get(taskIndex - 1).unmarkAsDone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(taskIndex - 1));
            saveTasks();
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    public static void deleteTask(int taskIndex) {
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

    public static boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    public static void saveTasks() {
        try {
            Path filePath = Paths.get(FILE_PATH);
            StringBuilder data = new StringBuilder();

            for (Task task : tasks) {
                data.append(task.toDataString()).append("\n");
            }

            Files.write(filePath, data.toString().getBytes());
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void loadTasks() {
        try {
            Path filePath = Paths.get(FILE_PATH);

            if (Files.exists(filePath)) {
                List<String> lines = Files.readAllLines(filePath);
                tasks.clear();

                for (String line : lines) {
                    Task task = Task.parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    public static void exit() {
        saveTasks();
        System.out.println(" Goodbye. ");
        System.out.println("____________________________________________________________");
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