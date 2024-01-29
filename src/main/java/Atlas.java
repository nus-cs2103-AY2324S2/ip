import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Atlas {
    private static final String DATA_PATH = "./data/Atlas.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCounter = 0;

    public static void main(String[] args) {
        loadTasks();
        Scanner scanner = new Scanner(System.in);

        greet();
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    listTasks();
                } else if (input.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                    markTask(taskNumber);
                } else if (input.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1; // Adjust for array index
                    unmarkTask(taskNumber);
                } else if (input.startsWith("delete ")) {
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                    deleteTask(taskNumber);
                } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                    addTask(input);
                } else if (input.startsWith("tasks on ")) {
                    String dateString = input.substring(9).trim();
                    LocalDate date;
                    try {
                        date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        listTasksOnDate(date);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use 'yyyy-MM-dd'.");
                    }
                } else {
                    throw new AtlasException("Invalid Command!!");
                }
            } catch (AtlasException e) {
                System.out.println(e.getMessage());
            }
        }
        saveTasks();
        exit();
    }

    private static void greet() {
        System.out.println("Hello! I'm Atlas");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(String input) throws AtlasException {
        if (input.startsWith("todo ")) {
            String description = input.substring(5);
            tasks.add(new ToDo(description));
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2) {
                throw new AtlasException("Invalid deadline format. Please use 'deadline [description] /by [due date]'.");
            }
            String description = parts[0];
            LocalDateTime by;
            try {
                by = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new AtlasException("Invalid date format. Please use 'yyyy-MM-dd HHmm'.");
            }
            tasks.add(new Deadline(description, by));
        } else if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /");
            if (parts.length < 3) {
                throw new AtlasException("Invalid event format. Please use 'event [description] /from [start date] /to [end date]'.");
            }
            String description = parts[0];
            LocalDateTime start, end;
            try {
                start = LocalDateTime.parse(parts[1].substring(5).trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                end = LocalDateTime.parse(parts[2].substring(3).trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new AtlasException("Invalid date and time format. Please use 'yyyy-MM-dd HHmm'.");
            }
            tasks.add(new Event(description, start, end));
        }
        taskCounter++;
        System.out.println("Got it. I've added this task:\n" + tasks.get(taskCounter - 1));
        System.out.println("Now you have " + String.valueOf(taskCounter) + " tasks in the list.");
    }

    private static void listTasks() {
        for (int i = 0; i < taskCounter; i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    private static void markTask(int i) throws AtlasException {
        if (i < 0 || i >= taskCounter) {
            throw new AtlasException("Task number " + (i + 1) + " does not exist.");
        }
        tasks.get(i).toggle();
        System.out.println("Nice! I've marked this task as done:");
        String str = tasks.get(i).toString();
        System.out.println(str);
    }

    private static void unmarkTask(int i) throws AtlasException {
        if (i < 0 || i >= taskCounter) {
            throw new AtlasException("Task number " + (i + 1) + " does not exist.");
        }
        tasks.get(i).toggle();
        System.out.println("OK, I've marked this task as not done yet");
        String str = tasks.get(i).toString();
        System.out.println(str);
    }

    private static void deleteTask(int taskNumber) throws AtlasException {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new AtlasException("Task number " + (taskNumber + 1) + " does not exist in the list.");
        }
        Task removedTask = tasks.remove(taskNumber);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        taskCounter--;
    }

    private static void saveTasks() {
        try (PrintWriter writer = new PrintWriter(DATA_PATH)) {
            for (Task task : tasks) {
                writer.println(task.toFileFormat());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void ensureFileExists() {
        File file = new File(DATA_PATH);
        if (!file.exists()) {
            try {
                File directory = file.getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    private static void loadTasks() {
        File file = new File(DATA_PATH);
        ensureFileExists();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parseLineToTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to load tasks: " + e.getMessage());
        }

        taskCounter = tasks.size();
    }

    private static Task parseLineToTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        switch (type) {
        case "T":
            ToDo todo = new ToDo(description);
            if (isDone) todo.toggle();
            return todo;
        case "D":
            try {
                LocalDateTime by = LocalDateTime.parse(parts[3].trim());
                Deadline deadline = new Deadline(description, by);
                if (isDone) deadline.toggle();
                return deadline;
            } catch (DateTimeParseException e) {
                System.err.println("Failed to parse deadline date: " + parts[3]);
                return null;
            }
        case "E":
            try {
                LocalDateTime start = LocalDateTime.parse(parts[3].trim());
                LocalDateTime end = LocalDateTime.parse(parts[4].trim());
                Event event = new Event(description, start, end);
                if (isDone) event.toggle();
                return event;
            } catch (DateTimeParseException e) {
                System.err.println("Failed to parse event dates: " + parts[3] + " and " + parts[4]);
                return null;
            }
        default:
            return null;
        }
    }

    private static void listTasksOnDate(LocalDate date) {
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().toLocalDate().equals(date)) {
                    System.out.println(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStart().toLocalDate().equals(date) || event.getEnd().toLocalDate().equals(date)) {
                    System.out.println(event);
                }
            }
        }
    }

}
