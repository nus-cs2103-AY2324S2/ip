import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    public DeadlineTask(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}

class EventTask extends Task {
    private String startTime;
    private String endTime;

    public EventTask(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public EventTask(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";

    private static void loadTasksFromFile(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("File path is incorrect... Quitting...");
            return; // Handle the case where the file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() < 7) {
                    System.out.println("Error found: Bad input");
                    System.out.println(line);
                }
                char taskType = line.charAt(1);
                char isDoneX = line.charAt(4);
                if (taskType == 'T') {
                    String todoDescription = line.substring(7);
                    if (isDoneX == 'X') {
                        Task newTodo = new TodoTask(todoDescription, true);
                        tasks.add(newTodo);
                    } else if (isDoneX == ' ') {
                        Task newTodo = new TodoTask(todoDescription);
                        tasks.add(newTodo);
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Unrecognized todo's Done.");
                        System.out.println(line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
//                        return;
                    }
                } else if (taskType == 'D') {
                    if (!line.contains(" (by: ")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Deadline task without a deadline.");
                        System.out.println(line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    String deadlineDescription = line.substring(7, line.indexOf(" (by: "));
                    Pattern pattern = Pattern.compile("\\(by: (.*?)\\)");
                    Matcher matcher = pattern.matcher(line);
                    String deadline = "";
                    if (matcher.find()) {
                        deadline = matcher.group(1);
                    }
                    if (isDoneX == 'X') {
                        Task newDeadline = new DeadlineTask(deadlineDescription, deadline, true);
                        tasks.add(newDeadline);
                    } else if (isDoneX == ' ') {
                        Task newDeadline = new DeadlineTask(deadlineDescription, deadline);
                        tasks.add(newDeadline);
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Unrecognized deadline's Done.");
                        System.out.println(line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                    }
                } else if (taskType == 'E') {
                    if (!line.contains(" (from: ") && !line.contains(" to: ")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Event task without a starting time and an ending time.");
                        System.out.println(line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    if (!line.contains(" (from: ")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Event task without a starting time.");
                        System.out.println(line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    if (!line.contains(" to: ")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Deadline task without an ending time.");
                        System.out.println(line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    String eventDescription = line.substring(7, line.indexOf(" (from: "));
                    Pattern pattern = Pattern.compile("\\(from: (.*?) to: (.*?)\\)");
                    Matcher matcher = pattern.matcher(line);
                    String startTime = "";
                    String endTime = "";
                    if (matcher.find()) {
                        startTime = matcher.group(1);
                        endTime = matcher.group(2);
                    }
                    if (isDoneX == 'X') {
                        Task newEvent = new EventTask(eventDescription, startTime, endTime, true);
                        tasks.add(newEvent);
                    } else if (isDoneX == ' ') {
                        Task newEvent = new EventTask(eventDescription, startTime, endTime);
                        tasks.add(newEvent);
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Unrecognized event's Done.");
                        System.out.println(line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Error found: Unrecognized task type (task type should be one of T, D, E).");
                    System.out.println(line);
                    System.out.println("Removed from list");
                    System.out.println("____________________________________________________________");
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    public static void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.println(task);
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void messageWithHorizontalLines(String message) {
        System.out.println("____________________________________________________________\n" +
                           message + "\n" +
                           "____________________________________________________________");
    }

    private static void messageTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            messageWithHorizontalLines("There are no tasks!");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println(" " + (i + 1) + "." + task);
            }
            System.out.println("____________________________________________________________");
        }
    }

    private static void markTaskAsDone(ArrayList<Task> tasks, int taskIndex) {
        if (isValidTaskIndex(tasks, taskIndex)) {
            Task task = tasks.get(taskIndex - 1);
            task.markAsDone();
            messageWithHorizontalLines("Nice! I've marked this task as done:\n" + "  " + task);
        } else {
            messageWithHorizontalLines("Invalid task index!");
        }
    }

    private static void markTaskAsUndone(ArrayList<Task> tasks, int taskIndex) {
        if (isValidTaskIndex(tasks, taskIndex)) {
            Task task = tasks.get(taskIndex - 1);
            task.markAsUndone();
            messageWithHorizontalLines("OK, I've marked this task as not done yet:\n" + "  " + task);
        } else {
            messageWithHorizontalLines("Invalid task index!");
        }
    }

    public static String printSucessfulAdded() {
        String str = "Got it. I have added this task:\n";
        return str;
    }

    public static String printCurrentTaskAmount(ArrayList<Task> tasks) {
        int amount = tasks.size();
        String str = "Now you have " + amount + " tasks in the list.";
        return str;
    }

    private static boolean isValidTaskIndex(ArrayList<Task> tasks, int taskIndex) {
        return taskIndex >= 1 && taskIndex <= tasks.size();
    }

    private static void deleteTask(ArrayList<Task> tasks, int taskIndex) {
        if (isValidTaskIndex(tasks, taskIndex)) {
            Task removedTask = tasks.remove(taskIndex - 1);
            messageWithHorizontalLines("Task has been successfully removed:\n" +
                                       "  " + removedTask + "\n" +
                                       printCurrentTaskAmount(tasks));
        } else {
            messageWithHorizontalLines("Invalid task index!");
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String botName = "Hammy";
        String welcomeStr = " Hello! I'm " + botName + "\n What can I do for you?";
        String byeStr = "Bye. Hope to see you again soon!";
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println(welcomeStr);

        loadTasksFromFile(tasks);

        while (true) {
            String userInput = scanner.nextLine();
            String[] words = userInput.split(" ");
            String command = words[0].toLowerCase();

            switch (command) {
                case "bye":
                    saveTasksToFile(tasks);
                    messageWithHorizontalLines(byeStr);
                    return;
                case "list":
                    messageTaskList(tasks);
                    break;
                case "done":
                    if (words.length > 1) {
                        int taskIndex = Integer.parseInt(words[1]);
                        markTaskAsDone(tasks, taskIndex);
                    } else {
                        messageWithHorizontalLines("Please provide the task index to mark as done.");
                    }
                    break;
                case "undone":
                    if (words.length > 1) {
                        int taskIndex = Integer.parseInt(words[1]);
                        markTaskAsUndone(tasks, taskIndex);
                    } else {
                        messageWithHorizontalLines("Please provide the task index to mark as undone.");
                    }
                    break;
                case "delete":
                    if (words.length > 1) {
                        int taskIndex = Integer.parseInt(words[1]);
                        deleteTask(tasks, taskIndex);
                    } else {
                        messageWithHorizontalLines("Please provide the task index to delete.");
                    }
                    break;
                case "todo":
                    if (words.length > 1) {
                        String todoDescription = userInput.substring(command.length()).trim();
                        Task newTodo = new TodoTask(todoDescription);
                        tasks.add(newTodo);
                        String todoStr = printSucessfulAdded() +
                                         newTodo + "\n" +
                                         printCurrentTaskAmount(tasks);
                        messageWithHorizontalLines(todoStr);
                    } else {
                        messageWithHorizontalLines("Please provide a description for the ToDo task.");
                    }
                    break;
                case "deadline":
                    if (words.length > 1 && userInput.contains("/by")) {
                        String deadlineDescription = userInput.substring(command.length() + 1, userInput.indexOf("/by")).trim();
                        String by = userInput.substring(userInput.indexOf("/by") + 3).trim();
                        Task newDeadline = new DeadlineTask(deadlineDescription, by);
                        tasks.add(newDeadline);
                        String deadlineStr = printSucessfulAdded() +
                                             newDeadline + "\n" +
                                             printCurrentTaskAmount(tasks);
                        messageWithHorizontalLines(deadlineStr);
                    } else {
                        messageWithHorizontalLines("Please provide both description and deadline for the Deadline task.");
                    }
                    break;
                case "event":
                    if (words.length > 1 && userInput.contains("/from") && userInput.contains("/to")) {
                        String eventDescription = userInput.substring(command.length() + 1, userInput.indexOf("/from")).trim();
                        String from = userInput.substring(userInput.indexOf("/from") + 5, userInput.indexOf("/to")).trim();
                        String to = userInput.substring(userInput.indexOf("/to") + 3).trim();
                        Task newEvent = new EventTask(eventDescription, from, to);
                        tasks.add(newEvent);
                        String eventStr = printSucessfulAdded() +
                                          newEvent + "\n" +
                                          printCurrentTaskAmount(tasks);
                        messageWithHorizontalLines(eventStr);
                    } else {
                        messageWithHorizontalLines("Please provide both description, start time, and end time for the Event task.");
                    }
                    break;
                default:
                    messageWithHorizontalLines(botName + " does not understand you :((");
                    break;
            }
        }
    }
}