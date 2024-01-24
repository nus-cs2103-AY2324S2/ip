import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}

public class Duke {

    public static void messageWithHorizontalLines(String message) {
        System.out.println("____________________________________________________________\n" +
                message + "\n" +
                "____________________________________________________________");
    }

    private static void displayTaskList(ArrayList<Task> tasks) {
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

    public static String printSucessfulAdded() {
        String str = "Got it. I have added this task:\n";
        return str;
    }
    public static String printCurrentTaskAmount(ArrayList<Task> tasks) {
        int amount = tasks.size();
        String str = "Now you have " + amount + " tasks in the list.";
        return str;
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

    private static boolean isValidTaskIndex(ArrayList<Task> tasks, int taskIndex) {
        return taskIndex >= 1 && taskIndex <= tasks.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String botName = "Hammy";
        String welcomeStr = " Hello! I'm " + botName + "\n What can I do for you?";
        String byeStr = "Bye. Hope to see you again soon!";
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(welcomeStr);
        while (true) {
            String userInput = scanner.nextLine();
            String[] words = userInput.split(" ");
            String command = words[0].toLowerCase();

            switch (command) {
                case "bye":
                    messageWithHorizontalLines(byeStr);
                    return;
                case "list":
                    displayTaskList(tasks);
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