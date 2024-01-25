import java.util.Scanner;
import java.util.ArrayList;

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

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

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
                handleDeadlines(input);
                printLine();
                continue;
            }

            if(input.startsWith("todo")) {
                handleTodos(input);
                printLine();
                continue;
            }

            if(input.startsWith("event")) {
                handleEvents(input);
                printLine();
                continue;
            }

            System.out.println("Invalid command. Please try again.");
            printLine();
        }
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printList(ArrayList<Task> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

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

    private static void handleTodos(String input) {
        String description = input.substring(5).trim();
        Todo todo = new Todo(description);
        storage.add(todo);
        System.out.println("Ok! I've added this task: " + todo);
        System.out.println("Now you have " + storage.size() + " tasks in your list.");
    }

    private static void handleDeadlines(String input) {
        String[] splitParts = input.substring(9).split("/by", 2);

        if (splitParts.length > 1) {
            String description = splitParts[0].trim();
            String date = splitParts[1].trim();
            Deadlines deadline = new Deadlines(description, date);
            storage.add(deadline);
            System.out.println("Ok! I've added this task: " + deadline);
            System.out.println("Now you have " + storage.size() + " tasks in your list.");
        }
        else {
            System.out.println("Invalid input format for deadline. Please provide a valid date/time.");
        }
    }

    private static void handleEvents(String input) {
        String[] splitParts = input.substring(6).split("/from", 2);
        String[] splitTo = splitParts[1].split("/to", 2);

        if (splitParts.length > 1) {
            String description = splitParts[0].trim();
            String fromDate = splitTo[0].trim();
            String toDate = splitTo[1].trim();
            Event event = new Event(description, fromDate, toDate);
            storage.add(event);
            System.out.println("Ok! I've added this task: " + event);
            System.out.println("Now you have " + storage.size() + " tasks in your list.");
        }
        else {
            System.out.println("Invalid input format for event. Please provide a valid date/time.");
        }
    }
}

