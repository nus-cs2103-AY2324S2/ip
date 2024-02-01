import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.*;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class James {
    private static final String FILE_PATH = "./data/hardDisk.txt";

    private static void saveTasks(ArrayList<Task> tasks) throws IOException {
        List<String> lines = tasks.stream()
                                  .map(Task::toFileFormat) 
                                  .collect(Collectors.toList());
        Path path = Paths.get(FILE_PATH);
        Files.write(path, lines);
    }

    private static void loadTasks(ArrayList<Task> tasks) throws IOException, DukeException {
        Path path = Paths.get(FILE_PATH);
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Task task = parseLineToTask(line); 
                tasks.add(task);
            }
        } else {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
    }

    private static Task parseLineToTask(String line) throws DukeException {
        String[] parts = line.split(" \\| ");

        // Common task components
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
    
        switch (type) {
            case "T": // Todo
                Todo todo = new Todo(description);
                if (isDone) todo.markAsDone();
                return todo;
            case "D": // Deadline
                // Assuming that the deadline part is after the third " | "
                String by = parts[3].trim();
                Deadline deadline = new Deadline(description, by);
                if (isDone) deadline.markAsDone();
                return deadline;
            case "E": // Event
                // Assuming that the event time part is after the third " | " and includes "from" and "to"
                String[] timeParts = parts[3].split(" to ");
                String start = timeParts[0].substring(6).trim(); // Remove "from " prefix
                String end = timeParts[1].trim();
                Event event = new Event(description, start, end);
                if (isDone) event.markAsDone();
                return event;
            default:
                throw new DukeException("Unknown task type in file: " + type);
        }
    }
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "James";

        try {
            loadTasks(tasks);
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Hello! I'm " + name + "\n");
        System.out.println("What can I do for you?\n");

        while(true) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    try {
                        saveTasks(tasks);
                        System.out.println("Bye. Hope to see you again soon!\n");
                        break;
                    } catch (IOException e) {
                        System.out.println("An error occurred while saving tasks: " + e.getMessage());
                    }
                }

                String[] parts = input.split(" ", 2);
                String command = parts[0].toLowerCase();

                switch (command) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        break;
                    case "todo":
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        Todo newTodo = new Todo(parts[1]);
                        tasks.add(newTodo);
                        System.out.println("Got it. I've added this task:\n" + newTodo);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        try {
                            saveTasks(tasks);
                        } catch (IOException e) {
                            System.out.println("An error occurred while saving tasks: " + e.getMessage());
                        }
                        break;
                    case "deadline":
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        String[] deadlineParts = parts[1].split(" /by ");
                        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                            throw new DukeException("The deadline must include a time.");
                        }
                        Deadline newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                        tasks.add(newDeadline);
                        System.out.println("Got it. I've added this task:\n" + newDeadline);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        try {
                            saveTasks(tasks);
                        } catch (IOException e) {
                            System.out.println("An error occurred while saving tasks: " + e.getMessage());
                        }
                        break;
                    case "event":
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                        String[] eventParts = parts[1].split(" /from ");
                        if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
                            throw new DukeException("The event time must include a start and end time.");
                        }
                        String[] fromto = eventParts[1].split("/to");
                        if (fromto.length < 2 || fromto[0].trim().isEmpty() || fromto[1].trim().isEmpty()) {
                            throw new DukeException("Both start and end times must be provided for an event.");
                        }
                        Event newEvent = new Event(eventParts[0].trim(), fromto[0].trim(), fromto[1].trim());
                        tasks.add(newEvent);
                        System.out.println("Got it. I've added this task:\n" + newEvent);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        try {
                            saveTasks(tasks);
                        } catch (IOException e) {
                            System.out.println("An error occurred while saving tasks: " + e.getMessage());
                        }
                        break;
                    case "mark":
                        try {
                            int taskIndexToMark = Integer.parseInt(parts[1]) - 1;
                            if (taskIndexToMark < 0 || taskIndexToMark >= tasks.size()) {
                                throw new DukeException("The task number you are trying to mark does not exist.");
                            }
                            tasks.get(taskIndexToMark).markAsDone();
                            System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskIndexToMark));
                        } catch (NumberFormatException e) {
                            throw new DukeException("Please enter a valid task number to mark.");
                        }
                        try {
                            saveTasks(tasks);
                        } catch (IOException e) {
                            System.out.println("An error occurred while saving tasks: " + e.getMessage());
                        }
                        break;
                    case "unmark":
                        try {
                            int taskIndexToUnmark = Integer.parseInt(parts[1]) - 1;
                            if (taskIndexToUnmark < 0 || taskIndexToUnmark >= tasks.size()) {
                                throw new DukeException("The task number you are trying to unmark does not exist.");
                            }
                            tasks.get(taskIndexToUnmark).markAsNotDone();
                            System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(taskIndexToUnmark));
                        } catch (NumberFormatException e) {
                            throw new DukeException("Please enter a valid task number to unmark.");
                        }
                        try {
                            saveTasks(tasks);
                        } catch (IOException e) {
                            System.out.println("An error occurred while saving tasks: " + e.getMessage());
                        }
                        break;
                    case "delete":
                        try {
                            int taskIndexToDelete = Integer.parseInt(parts[1]) - 1;
                            if (taskIndexToDelete < 0 || taskIndexToDelete >= tasks.size()) {
                                throw new DukeException("The task number you are trying to delete does not exist.");
                            }
                            Task removedTask = tasks.remove(taskIndexToDelete);
                            System.out.println("Noted. I've removed this task:\n" + removedTask);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        } catch (NumberFormatException e) {
                            throw new DukeException("Please enter a valid task number to delete.");
                        }
                        try {
                            saveTasks(tasks);
                        } catch (IOException e) {
                            System.out.println("An error occurred while saving tasks: " + e.getMessage());
                        }
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
