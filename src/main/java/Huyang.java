import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Huyang {
    private Storage storage;
    private ArrayList<Task> tasks;

    public Huyang() {
        storage = new Storage("./data/huyang_tasks.txt");
        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
            tasks = new ArrayList<>();
        } catch (TaskException e) {
            System.err.println("Task format error: " + e.getMessage());
            tasks = new ArrayList<>();
        }
    }

    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, UNKNOWN;

        public static CommandType fromString(String input) {
            if (input.equals("list")) {
                return LIST;
            } else if (input.startsWith("mark ")) {
                return MARK;
            } else if (input.startsWith("unmark ")) {
                return UNMARK;
            } else if (input.startsWith("todo ")) {
                return TODO;
            } else if (input.startsWith("deadline ")) {
                return DEADLINE;
            } else if (input.startsWith("event ")) {
                return EVENT;
            } else if (input.startsWith("delete ")) {
                return DELETE;
            } else if (input.equals("bye")) {
                return BYE;
            } else {
                return UNKNOWN;
            }
        }
    }

    public void greet() {
        System.out.println("Hello! I'm Huyang");
        // System.out.println(logo);
        System.out.println("What can I do for you?");
    }

    public void runChatbot() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (true) {
            CommandType command = CommandType.fromString(input);
            try {
                switch (command) {
                case LIST:
                    listTasks();
                    break;
                case MARK:
                    mark(input);
                    break;
                case UNMARK:
                    unmark(input);
                    break;
                case TODO:
                    addTodoTask(input);
                    break;
                case DEADLINE:
                    addDeadlineTask(input);
                    break;
                case EVENT:
                    addEventTask(input);
                    break;
                case DELETE:
                    deleteTask(input);
                    break;
                case BYE:
                    scanner.close();
                    return;
                case UNKNOWN:
                default:
                    throw TaskException.forUnknownCommand();
                }
            } catch (TaskException e) {
                printErrorMessage(e.getMessage());
            }
            input = scanner.nextLine();
        }
    }

    private void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        updateTasks();
    }

    private void updateTasks() {
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
            System.out.println("Unable to save tasks. Your changes might not be persisted.");
        } catch (TaskException e) {
            System.err.println("Task format error on save: " + e.getMessage());
            System.out.println("There was an error in task format. Changes might not be saved correctly.");
        }
    }

    private void mark(String input) throws TaskException {
        int taskNumber = Integer.parseInt(input.substring(5));
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.check();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } else {
            throw TaskException.forInvalidTaskNumber();
        }
    }

    private void unmark(String input) throws TaskException {
        int taskNumber = Integer.parseInt(input.substring(7).strip());
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.uncheck();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } else {
            throw TaskException.forInvalidTaskNumber();
        }
    }

    private void addTodoTask(String input) throws TaskException {
        if (input.strip().length() <= 5) {
            throw TaskException.forEmptyTaskDescription();
        }
        String description = input.substring(5).trim();
        tasks.add(new ToDo(description));
        printAddedTask();
        updateTasks();
    }

    private void addDeadlineTask(String input) throws TaskException {
        if (input.strip().length() <= 9) {
            throw TaskException.forEmptyTaskDescription();
        }
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw TaskException.forInvalidTaskFormat("deadline");
        }

        try {
            String description = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 4).trim();
            LocalDateTime byTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Deadline(description, byTime));
            printAddedTask();
            updateTasks();
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format. Please use YYYY-MM-DD HHMM, e.g., 2020-12-02 1800.");
        }
    }

    private void addEventTask(String input) throws TaskException {
        if (input.strip().length() <= 6) {
            throw TaskException.forEmptyTaskDescription();
        }
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw TaskException.forInvalidTaskFormat("event");
        }

        try {
            String description = input.substring(6, fromIndex).trim();
            String start = input.substring(fromIndex + 6, toIndex).trim();
            String end = input.substring(toIndex + 4).trim();
            LocalDateTime startTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime endTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Event(description, startTime, endTime));
            printAddedTask();
            updateTasks();
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format. Please use YYYY-MM-DD HHMM, e.g., 2020-12-02 1800.");
        }
    }

    private void printAddedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private void deleteTask(String input) throws TaskException {
        try {
            int taskNumber = Integer.parseInt(input.substring(7).strip());
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                throw new TaskException("Invalid task number. Please check and try again.");
            }
            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new TaskException("Invalid task number format. Please enter a valid number.");
        }
    }

    private void printErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    public void farewell() {
        System.out.print("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Huyang huyang = new Huyang();
        huyang.greet();
        huyang.runChatbot();
        huyang.farewell();
    }
}
