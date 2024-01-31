import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private LocalDate deadline;

    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }
    public DeadlineTask(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        LocalDate tempDeadline = deadline.plusDays(0);
        return "[D]" + super.toString() + " (by: " + tempDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

class EventTask extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    public EventTask(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public EventTask(String description, LocalDate startTime, LocalDate endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        LocalDate tempStartTime = startTime.plusDays(0);
        LocalDate tempEndTime = endTime.plusDays(0);
        return "[E]" + super.toString() + " (from: " + tempStartTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + tempEndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

class TaskList {
    ArrayList<Task> tasks;
    Ui ui;

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskListSize() {
        return this.tasks.size();
    }

    private boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 1 && taskIndex <= this.tasks.size();
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
        ui.addNewTask(newTask, getTaskListSize());
    }

    public void addTaskSilent(Task newTask){
        tasks.add(newTask);
    }

    public void deleteTask(int taskIndex) {
        if (!isValidTaskIndex(taskIndex)) {
            ui.invalidTaskIndex();
        }
        Task removedTask = this.tasks.remove(taskIndex - 1);
        ui.deleteTask(removedTask, getTaskListSize());
    }
    public void markTaskAsDone(int taskIndex) {
        if (!isValidTaskIndex(taskIndex)) {
            ui.invalidTaskIndex();
        }
        Task doneTask = this.tasks.get(taskIndex - 1);
        doneTask.markAsDone();
        ui.markAsDone(doneTask);
    }

    public void markTaskAsUndone(int taskIndex) {
        if (!isValidTaskIndex(taskIndex)) {
            ui.invalidTaskIndex();
        }
        Task undoneTask = this.tasks.get(taskIndex - 1);
        undoneTask.markAsUndone();
        ui.markAsUndone(undoneTask);
    }
}

class Storage {
    String filePath;
    ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
    }

    private static boolean isValidDates(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

    public ArrayList<Task> loadTasksFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File path is incorrect... Quitting...");
            return null; // Handle the case where the file doesn't exist
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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
                        this.tasks.add(newTodo);
                    } else if (isDoneX == ' ') {
                        Task newTodo = new TodoTask(todoDescription);
                        this.tasks.add(newTodo);
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Unrecognized todo's Done.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                    }
                } else if (taskType == 'D') {
                    if (!line.contains(" (by: ")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Deadline task without a deadline.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    String deadlineDescription = line.substring(7, line.indexOf(" (by: "));
                    Pattern pattern = Pattern.compile("\\(by: (.*?)\\)");
                    Matcher matcher = pattern.matcher(line);
                    String deadlineString = "";
                    if (matcher.find()) {
                        deadlineString = matcher.group(1);
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                    LocalDate deadline = LocalDate.parse(deadlineString, formatter);
                    if (isDoneX == 'X') {
                        Task newDeadline = new DeadlineTask(deadlineDescription, deadline, true);
                        this.tasks.add(newDeadline);
                    } else if (isDoneX == ' ') {
                        Task newDeadline = new DeadlineTask(deadlineDescription, deadline);
                        this.tasks.add(newDeadline);
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Unrecognized deadline's Done.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                    }
                } else if (taskType == 'E') {
                    if (!line.contains(" (from: ") && !line.contains(" to: ")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Event task without a starting time and an ending time.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    if (!line.contains(" (from: ")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Event task without a starting time.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    if (!line.contains(" to: ")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Deadline task without an ending time.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    String eventDescription = line.substring(7, line.indexOf(" (from: "));
                    Pattern pattern = Pattern.compile("\\(from: (.*?) to: (.*?)\\)");
                    Matcher matcher = pattern.matcher(line);
                    String startTimeString = "";
                    String endTimeString = "";
                    if (matcher.find()) {
                        startTimeString = matcher.group(1);
                        endTimeString = matcher.group(2);
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                    LocalDate startTime = LocalDate.parse(startTimeString, formatter);
                    LocalDate endTime = LocalDate.parse(endTimeString, formatter);
                    if (!isValidDates(startTime, endTime)) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Start time should be earlier than end time.");
                        System.out.println(line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                        continue;
                    }
                    if (isDoneX == 'X') {
                        Task newEvent = new EventTask(eventDescription, startTime, endTime, true);
                        this.tasks.add(newEvent);
                    } else if (isDoneX == ' ') {
                        Task newEvent = new EventTask(eventDescription, startTime, endTime);
                        this.tasks.add(newEvent);
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("Error found: Unrecognized event's Done.");
                        System.out.println("--> " + line);
                        System.out.println("Removed from list");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Error found: Unrecognized task type (task type should be one of T, D, E).");
                    System.out.println("--> " + line);
                    System.out.println("Removed from list");
                    System.out.println("____________________________________________________________");
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasksToFile(ArrayList<Task> taskList) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                writer.println(task);
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}

class Ui {
    String botName;
    public Ui(String botName) {
        this.botName = botName;
    }

    public static void messageWithHorizontalLines(String message) {
        System.out.println("____________________________________________________________\n" +
                message + "\n" +
                "____________________________________________________________");
    }

    public void sendWelcome() {
        String welcomeStr = " Hello! I'm " + botName + "\n What can I do for you?";
        messageWithHorizontalLines(welcomeStr);
    }

    public void sendGoodbye() {
        String byeStr = "Bye. Hope to see you again soon!";
        messageWithHorizontalLines(byeStr);
    }

    public void badUserInput() {
        messageWithHorizontalLines(botName + " does not understand you :((");
    }

    public void showTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            messageWithHorizontalLines("There are no tasks!");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println(" " + (i + 1) + "." + task);
            }
            System.out.println("____________________________________________________________");
        }
    }

    public void addNewTask(Task addedTask, int taskListSize) {
        messageWithHorizontalLines("Got it. I have added " + addedTask + " into Task List.");
    }

    public void deleteTask(Task deletedTask, int taskListSize) {
        messageWithHorizontalLines("Task has been successfully removed:\n"
                                    + " " + deletedTask + "\n"
                                    + "There are " + taskListSize + " in the task list currently.");
    }

    public void invalidTaskIndex() {
        messageWithHorizontalLines("Invalid Task Index!");
    }

    public void markAsDone(Task doneTask) {
        messageWithHorizontalLines("Nice! I've marked this task as done:\n" + "  " + doneTask);
    }

    public void markAsUndone(Task undoneTask) {
        messageWithHorizontalLines("OK, I've marked this task as not done:\n" + "  " + undoneTask);
    }

    public void noIndexDeleteTask() {
        System.out.println("Please provide the task index you want to delete.");
    }

    public void noIndexMarkAsDone() {
        System.out.println("Please provide the task index to mark as done.");
    }

    public void noIndexMarkAsUndone() {
        System.out.println("Please provide the task index to mark as not done.");
    }

    public void invalidDateInput() {
        System.out.println("Error input: Date format is invalid (Date format: YYYY-MM-DD)");
    }

    public void insufficientTodoDescription() {
        System.out.println("Please provide a description for your Todo task.");
    }

    public void insufficientDeadline() {
        System.out.println("Please provide a deadline for your Deadline task.");
    }

    public void insufficientEventStartTimeEndTime() {
        System.out.println("Please provide both starting time and ending time for your event task.");
    }

    public void insufficientEventStartTime() {
        System.out.println("Please provide a starting time for your event task.");
    }

    public void insufficientEventEndTime() {
        System.out.println("Please provide a ending time for your event task.");
    }

    public void invalidEventStartingTimeAndEndingTime() {
        System.out.println("Error input: Start time should be earlier than end time.");
    }
}

class Parser {
    Ui ui;
    Storage storage;
    TaskList taskList;

    public Parser(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    private static boolean isValidDate(String dateString) {
        String parsedInPattern = "\\d{4}-\\d{2}-\\d{2}";
        return Pattern.matches(parsedInPattern, dateString);
    }
    private static boolean isValidDates(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

    public void execute(String userInput) throws IOException {
        String[] words = userInput.split(" ");
        String command = words[0].toLowerCase();

        switch (command) {
            case "bye":
                this.storage.saveTasksToFile(taskList.getTasks());
                this.ui.sendGoodbye();
                return;
            case "list":
                this.ui.showTaskList(taskList.getTasks());
                break;
            case "done":
                if (words.length > 1) {
                    int taskIndex = Integer.parseInt(words[1]);
                    this.taskList.markTaskAsDone(taskIndex);
                } else {
                    this.ui.noIndexMarkAsDone();
                }
                break;
            case "undone":
                if (words.length > 1) {
                    int taskIndex = Integer.parseInt(words[1]);
                    this.taskList.markTaskAsUndone(taskIndex);
                } else {
                    this.ui.noIndexMarkAsUndone();
                }
                break;
            case "delete":
                if (words.length > 1) {
                    int taskIndex = Integer.parseInt(words[1]);
                    this.taskList.deleteTask(taskIndex);
                } else {
                    this.ui.noIndexDeleteTask();
                }
                break;
            case "todo":
                if (words.length > 1) {
                    String todoDescription = userInput.substring(command.length()).trim();
                    Task newTodo = new TodoTask(todoDescription);
                    this.taskList.addTask(newTodo);
                } else {
                    this.ui.insufficientTodoDescription();
                }
                break;
            case "deadline":
                if (words.length > 1 && userInput.contains("/by")) {
                    String deadlineDescription = userInput.substring(command.length() + 1, userInput.indexOf("/by")).trim();
                    String byString = userInput.substring(userInput.indexOf("/by") + 3).trim();
                    if (!isValidDate(byString)) {
                        this.ui.invalidDateInput();
                        return;
                    }
                    LocalDate by = LocalDate.parse(byString);
                    Task newDeadline = new DeadlineTask(deadlineDescription, by);
                    this.taskList.addTask(newDeadline);
                } else {
                    this.ui.insufficientDeadline();
                }
                break;
            case "event":
                if (words.length > 1 && userInput.contains("/from") && userInput.contains("/to")) {
                    String eventDescription = userInput.substring(command.length() + 1, userInput.indexOf("/from")).trim();
                    String fromString = userInput.substring(userInput.indexOf("/from") + 5, userInput.indexOf("/to")).trim();
                    String toString = userInput.substring(userInput.indexOf("/to") + 3).trim();
                    LocalDate from = LocalDate.parse(fromString);
                    LocalDate to = LocalDate.parse(toString);
                    if (!isValidDates(from, to)) {
                        this.ui.invalidEventStartingTimeAndEndingTime();
                        return;
                    }
                    Task newEvent = new EventTask(eventDescription, from, to);
                    this.taskList.addTask(newEvent);
                } else {
                    this.ui.insufficientEventStartTimeEndTime();
                }
                break;
            default:
                ui.badUserInput();
                break;
        }
    }
}

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath, String botName) {
        this.storage = new Storage(filePath);
        this.ui = new Ui(botName);
        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();
        this.taskList = new TaskList(loadedTasks, this.ui);
        this.parser = new Parser(this.ui, this.storage, this.taskList);
    }

    public void run() throws IOException {
        ui.sendWelcome();
        boolean isBye = false;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            parser.execute(userInput);
            if (userInput.equals("bye")) {
                break;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Duke("./data/duke.txt", "Hammy").run();
    }
}
