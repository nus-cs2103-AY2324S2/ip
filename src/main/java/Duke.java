import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


class Duke {
    private TaskList taskList;

    Duke() {
        ArrayList<Task> tasks = Storage.loadTasksFromFile();
        taskList = new TaskList(tasks);
    }

    void run() {
        Ui.printLogo();
        Ui.printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String command = Ui.getUserCommand(scanner);

        while (!command.equals("bye")) {
            try {
                Command cmd = Parser.parseCommand(command);
                Ui.printMessage(cmd.execute(taskList, command));
            } catch (DukeException e) {
                Ui.printMessage("OOPS!!! " + e.getMessage());
            }
            command = Ui.getUserCommand(scanner);
        }

        Storage.saveTasksToFile(taskList.getTasks());
        Ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

class Ui {
    private static final String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    static void printLogo() {
        System.out.println("Hello from\n" + logo);
    }

    static void printWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm CharmBot ");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    static void printGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    
    static String getUserCommand(Scanner scanner) {
        return scanner.nextLine();
    }

    static void printMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}

class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }
}


abstract class Command {
    abstract String execute(TaskList taskList, String command) throws DukeException;
}

class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, String command) {
        if (taskList.getTasks().isEmpty()) {
            return "You have no tasks in your list.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.getTasks().size(); i++) {
                sb.append((i + 1) + "." + taskList.getTask(i) + "\n");
            }
            return sb.toString();
        }
    }
}

class AddCommand extends Command {
    @Override
    public String execute(TaskList taskList, String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2) {
            throw new DukeException("Please provide a description for the task.");
        }
        String taskType = parts[0].trim();
        String taskDetails = parts[1].trim();

        Task newTask;
        switch (taskType) {
            case "todo":
                newTask = new ToDoTask(taskDetails);
                break;
            case "deadline":
                String[] deadlineParts = taskDetails.split("/by");
                if (deadlineParts.length < 2) {
                    throw new DukeException("Please provide both description and deadline for the task.");
                }
                String deadlineDescription = deadlineParts[0].trim();
                String deadlineTime = deadlineParts[1].trim();
                LocalTime deadline = LocalTime.parse(deadlineTime, DateTimeFormatter.ofPattern("HHmm"));
                newTask = new DeadlineTask(deadlineDescription, deadline);
                break;
            case "event":
                String[] eventParts = taskDetails.split("/at");
                if (eventParts.length < 2) {
                    throw new DukeException("Please provide both description and time for the event.");
                }
                String eventDescription = eventParts[0].trim();
                String[] eventTimeParts = eventParts[1].split("-");
                if (eventTimeParts.length < 2) {
                    throw new DukeException("Please provide both start and end times for the event.");
                }
                String startTime = eventTimeParts[0].trim();
                String endTime = eventTimeParts[1].trim();
                LocalTime eventStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm"));
                LocalTime eventEndTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HHmm"));
                newTask = new EventTask(eventDescription, eventStartTime, eventEndTime);
                break;
            default:
                throw new DukeException("Invalid task type.");
        }

        taskList.addTask(newTask);
        return "Got it. I've added this task:\n" + newTask + "\nNow you have " + taskList.getTasks().size() + " tasks in the list.";
    }
}


class DeleteCommand extends Command {
    @Override
    public String execute(TaskList taskList, String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        int index = Integer.parseInt(parts[1].trim()) - 1;
        if (index < 0 || index >= taskList.getTasks().size()) {
            throw new DukeException("Invalid task number.");
        }
        Task removedTask = taskList.removeTask(index);
        return "Noted. I've removed this task:\n" + removedTask + "\nNow you have " + taskList.getTasks().size() + " tasks in the list.";
    }
}

class MarkCommand extends Command{
    @Override
    public String execute(TaskList taskList, String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        int index = Integer.parseInt(parts[1].trim()) - 1;
        if (index < 0 || index >= taskList.getTasks().size()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = taskList.getTask(index);
        task.markAsDone();
        return "Nice! I've marked this task as done:\n" + task;
    }

}

class UnmarkCommand extends Command {
    @Override
    public String execute(TaskList taskList, String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        int index = Integer.parseInt(parts[1].trim()) - 1;
        if (index < 0 || index >= taskList.getTasks().size()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = taskList.getTask(index);
        task.markAsNotDone();
        return "Nice! I've marked this task as not done:\n" + task;
    }
}


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

    public String toFileString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
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
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + description;
    }
}

class DeadlineTask extends Task {

    private LocalTime deadline;

    public DeadlineTask(String description, LocalTime by) {
        super(description);
        this.deadline = by;
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " +deadline + ")";
    }
}

class EventTask extends Task {
    private LocalTime startTime;
    private LocalTime endTime;

    public EventTask(String description, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startTime.format(DateTimeFormatter.ofPattern("HHmm")) + " | " + endTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " + startTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " to: " + endTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}
// Other command classes for mark, unmark, exit, etc.

class Parser {
    static Command parseCommand(String command) throws DukeException {
        String[] parts = command.split(" ", 2);
    
        // Ensure there's at least one part
        if (parts.length < 1) {
            throw new DukeException("Command is empty.");
        }
    
        String keyword = parts[0].toLowerCase(); // Convert to lowercase for case-insensitive comparison
    
        switch (keyword) {
            case "list":
                return new ListCommand();
            case "todo":
            case "deadline":
            case "event":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("The description of a " + keyword + " cannot be empty.");
                }
                return new AddCommand();
            case "delete":
                return new DeleteCommand();
            case "mark":
                return new MarkCommand();
            case "unmark":
                return new UnmarkCommand();
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
    
    
    

    static Task parseTaskFromString(String line) throws DukeException {
        String[] parts = line.split("\\|");
    
        if (parts.length < 3) {
            throw new DukeException("Invalid task format: " + line);
        }
    
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1"); // "1" represents task is done
        String description = parts[2].trim();
    
        switch (type) {
            case "D":
                if (parts.length < 4) {
                    throw new DukeException("Invalid deadline task format: " + line);
                }
                String by = parts[3].trim();
                LocalTime byTime = LocalTime.parse(by, DateTimeFormatter.ofPattern("HHmm"));
                DeadlineTask deadlineTask = new DeadlineTask(description, byTime);
                if (isDone) {
                    deadlineTask.markAsDone();
                }
                return deadlineTask;
            case "E":
                if (parts.length < 5) {
                    throw new DukeException("Invalid event task format: " + line);
                }
                String from = parts[3].trim();
                String to = parts[4].trim();
                LocalTime startTime = LocalTime.parse(from, DateTimeFormatter.ofPattern("HHmm"));
                LocalTime endTime = LocalTime.parse(to, DateTimeFormatter.ofPattern("HHmm"));
                EventTask eventTask = new EventTask(description, startTime, endTime);
                if (isDone) {
                    eventTask.markAsDone();
                }
                return eventTask;
            default:
                ToDoTask toDoTask = new ToDoTask(description);
                if (isDone) {
                    toDoTask.markAsDone();
                }
                return toDoTask;
        }
    }
    
}



class Storage {
    private static final String FILE_PATH = "duke.txt";

    static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    
                    try {
                        Task task = Parser.parseTaskFromString(line);
                        tasks.add(task);
                    } catch (DukeException e) {
                        System.out.println("Error parsing task from file: " + e.getMessage());
                        // Decide how to handle the DukeException, e.g., logging or skipping the task
                    }

                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    static void saveTasksToFile(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}

class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    ArrayList<Task> getTasks() {
        return tasks;
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    Task removeTask(int index) {
        return tasks.remove(index);
    }

    Task getTask(int index) {
        return tasks.get(index);
    }
}


