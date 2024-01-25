import java.util.Scanner;
import java.util.ArrayList;

/**
 * Task - Represents a basic task with a description and completion status.
 */
class Task {
    protected String userInput;
    protected boolean isDone;

    public Task(String input) {
        this.userInput = input;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + userInput;
    }
}

/**
 * Todo - Represents a todo task, a subclass of Task.
 */
class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * Event - Represents an event task with start and end dates, a subclass of Task.
 */
class Event extends Task {
    private String fromDate;
    private String toDate;

    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }
}

/**
 * Deadlines - Represents a task with a deadline, a subclass of Task.
 */
class Deadlines extends Task {
    private String byDate;
    public Deadlines(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }
}

/**
 * Duke - Main class that handles user interactions and task management.
 */
public class Duke {
    private static ArrayList<Task> storage = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //SAY HI, don't change
        String name = "____________________________________________________________ \n"
                + "Hello! I'm RATZCHAT \n"
                + "How can I help you today?";
        System.out.println(name);
        printLine();

        //RESPONSIVE
        while(true) {
            String input = scanner.nextLine();
            printLine();

            if("bye".equalsIgnoreCase(input)) {
                System.out.println("BYEBYE. Thank you for using RATZCHAT!");
                printLine();
                break;
            }

            if("list".equalsIgnoreCase(input)) {
                System.out.println("These are your to-dos: ");
                printList(storage);
                printLine();
                continue;
            }

            if(input.startsWith("mark") || input.startsWith("unmark")) {
                markingHandler(input);
                printLine();
                continue;
            }

            if(input.startsWith("deadline")) {
                if (!validateDeadlineInput(input)) {
                    System.out.println("Sorry, please complete your request by specifying the details of the task!");
                    printLine();
                    continue;
                }

                handleDeadlines(input);
                printLine();
                continue;
            }

            if(input.startsWith("todo")) {
                if (!validateTodoInput(input)) {
                    System.out.println("Sorry, please complete your request by specifying the details of the task!");
                    printLine();
                    continue;
                }

                handleTodos(input);
                printLine();
                continue;
            }

            if(input.startsWith("event")) {
                if (!validateEventInput(input)) {
                    System.out.println("Sorry, please complete your request by specifying the details of the task!");
                    printLine();
                    continue;
                }

                handleEvents(input);
                printLine();
                continue;
            }

            if (input.startsWith("remove")) {
                handleRemove(input);
                printLine();
                continue;
            }

            System.out.println("I'm sorry, I don't understand! Please type your request again.");
            printLine();
        }
    }

    /**
     * Prints a line of underscores for formatting purposes.
     */
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the list of tasks with their corresponding indices.
     * @param list ArrayList of Task objects.
     */
    private static void printList(ArrayList<Task> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    /**
     * Handles marking or unmarking tasks as done based on user input.
     * @param input User input specifying the action and task index.
     */

    private static void markingHandler(String input) {
        String[] split = input.split(" ");
        if (split.length < 2) {
            System.out.println("Please specify the task number!");
            return;
        }

        try {
            int index = Integer.parseInt(split[1]) - 1;
            Task task = storage.get(index);

            if ("mark".equalsIgnoreCase(split[0])) {
                task.markAsDone();
                System.out.println("I've marked this task as done:\n  " + task);
            }

            else if ("unmark".equalsIgnoreCase(split[0])) {
                task.unmarkTask();
                System.out.println("I've unmarked this task! It is now not done yet:\n  " + task);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please refer to your to-do list again.");
        }
    }

    /**
     * Handles the creation and addition of Todo tasks to the task list.
     * @param input User input specifying the Todo description.
     */
    private static void handleTodos(String input) {
        String description = input.substring(5).trim();
        Todo todo = new Todo(description);
        storage.add(todo);
        System.out.println("Ok! I've added this todo: " + todo);
        System.out.println("Now you have " + storage.size() + " tasks in your list.");
    }

    /**
     * Handles the creation and addition of Deadline tasks to the task list.
     * @param input User input specifying the Deadline description and due date.
     */
    private static void handleDeadlines(String input) {
        String[] splitParts = input.substring(9).split("/by", 2);

        if (splitParts.length > 1) {
            String description = splitParts[0].trim();
            String date = splitParts[1].trim();
            Deadlines deadline = new Deadlines(description, date);
            storage.add(deadline);
            System.out.println("Ok! I've added this deadline: " + deadline);
            System.out.println("Now you have " + storage.size() + " tasks in your list.");
        }
        else {
            System.out.println("Invalid input format for deadline. Please provide a valid date/time.");
        }
    }

    /**
     * Handles the creation and addition of Event tasks to the task list.
     * @param input User input specifying the Event description and date range.
     */
    private static void handleEvents(String input) {
        String[] splitParts = input.substring(6).split("/from", 2);
        String[] splitTo = splitParts[1].split("/to", 2);

        if (splitParts.length > 1) {
            String description = splitParts[0].trim();
            String fromDate = splitTo[0].trim();
            String toDate = splitTo[1].trim();
            Event event = new Event(description, fromDate, toDate);
            storage.add(event);
            System.out.println("Ok! I've added this event: " + event);
            System.out.println("Now you have " + storage.size() + " tasks in your list.");
        }
        else {
            System.out.println("Invalid input format for event. Please provide a valid date/time.");
        }
    }

    /**
     * Validates the input format for creating Deadlines.
     * @param input User input specifying the Deadline description and due date.
     * @return True if the input format is valid, false otherwise.
     */
    private static boolean validateDeadlineInput(String input) {
        String[] splitParts = input.substring(9).split("/by", 2);
        return splitParts.length > 1;
    }

    /**
     * Validates the input format for creating Todo tasks.
     * @param input User input specifying the Todo description.
     * @return True if the input format is valid, false otherwise.
     */
    private static boolean validateTodoInput(String input) {
        return input.length() > 5;
    }

    /**
     * Validates the input format for creating Events.
     * @param input User input specifying the Event description and date range.
     * @return True if the input format is valid, false otherwise.
     */
    private static boolean validateEventInput(String input) {
        String[] splitParts = input.substring(6).split("/from", 2);
        return splitParts.length > 1;
    }

    private static void handleRemove(String input) {
        String[] splitParts = input.split(" ");
        if (splitParts.length < 2) {
            System.out.println("Please specify which task number you want to remove!");
            return;
        }

        try {
            int index = Integer.parseInt(splitParts[1]) - 1;
            Task removedTask = storage.remove(index);
            System.out.println("Ok! I have removed this task from your list:\n  " + removedTask);
            System.out.println("Now you have " + storage.size() + " tasks in your list.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please refer to your to-do list again.");
        }
    }
}

