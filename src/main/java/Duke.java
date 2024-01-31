import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

/**
 * Duke class
 * Contains main method
 */
public class Duke {
    public static void main(String[] args) {
        ListAdder newList = new ListAdder();
        newList.start();
    }    
}


/**
 * ListAdder class
 * Contains methods to add tasks to a list
 */
class ListAdder {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int taskIndex;
    private static final String line = "____________________________________________________________";
    private static final String FILE_PATH = "./data/tasklist.txt";

    public ListAdder() {
        this.taskIndex = 1;
        loadData();
    }


    /**
     * Starts the program
     * 
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws NumberFormatException if input is not a number
     * @throws StringIndexOutOfBoundsException if input is not a number
     */
    public void start() {
        greeting();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a task:");
        String input = sc.nextLine();
        
        while (true) {
            printLine();
            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) { // show list
                this.taskIndex = 1;
                printList();
            } else if (input.startsWith("mark done")) { // mark as done
                try {
                    int index = Integer.parseInt(input.substring(9).trim()) - 1;
                    markDone(index);
                } catch (NumberFormatException e) {
                    invalidTaskIndex();
                }
            } else if (input.startsWith("mark undone")) { // mark as undone
                try {
                    int index = Integer.parseInt(input.substring(11).trim()) - 1;
                    markUndone(index);
                } catch (NumberFormatException e) {
                    invalidTaskIndex();
                }
            } else {
                addTask(input);
            }
            input = sc.nextLine();
        }
        goodbye();
    }

    /**
     * Loads data from the file into taskList
     */
    private void loadData() {
        try {
            Path filePath = Paths.get(FILE_PATH);

            if (Files.notExists(filePath)) { // Create the file if it doesn't exist
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error loading data from file: error in loadData()");
        }
    }


    /**
     * Greets user and prints instructions
     */
    private void greeting() {
        printLine();
        helpline();
        printLine();
    }

    /**
     * Prints line
     */
    private void printLine() {
        System.out.println(line);
    }

    /**
     * Prints instructions
     */
    private void helpline() {
        System.out.println("\t" + "Help: ");
        System.out.println("\t" + "1. Add a todo task: todo <task>");
        System.out.println("\t" + "2. Add a deadline: deadline <task> /by <deadline>");
        System.out.println("\t" + "3. Add an event: event <task> /from <start time> /to <end time>");
        System.out.println("\t" + "4. Mark task as done: mark done <task index>");
        System.out.println("\t" + "5. Mark task as undone: mark undone <task index>");
        System.out.println("\t" + "6. Show list: list");
        System.out.println("\t" + "7. Exit: bye");
    }

    /**
     * Prints goodbye message
     */
    private void goodbye() {
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }

    /** 
     * Invalid task index message
     */
    private void invalidTaskIndex() {
        System.out.println("\t" + "Oops, that wasn't a valid task index :P");
    }

    /**
     * Adds task to taskList
     * 
     * @param task task to be added
     */
    private void addTask(String task) {
        TaskType taskType = getTaskType(task);

        switch (taskType) {
        case TODO:
            addTodoTask(task);
            break;
        case DEADLINE:
            addDeadline(task);
            break;
        case EVENT:
            addEvent(task);
            break;
        case HELP:
            helpline();
            break;
        case DELETE:
            try {
                int index = Integer.parseInt(task.substring(6).trim()) - 1;
                deleteTask(index);
            } catch (NumberFormatException e) {
                System.out.println("\t" + "Invalid input. Please enter a valid task index.");
            }
            break;
        default:
            System.out.println("\t" + "Sorry, that's not a command :( Enter 'help' for instructions.");
        }
        printLine();
    }

    /**
     * Returns task type
     * 
     * @param task task to be added
     * @return TaskType
     */
    private TaskType getTaskType(String task) {
        if (task.startsWith("todo")) {
            return TaskType.TODO;
        } else if (task.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else if (task.startsWith("event")) {
            return TaskType.EVENT;
        } else if (task.equals("help")) {
            return TaskType.HELP;
        } else if (task.startsWith("delete")) {
            return TaskType.DELETE;
        } else {
            return TaskType.UNKNOWN;
        }
    }

    /**
     * Adds todoTask to taskList
     * 
     * @param task task to be added
     */
    private void addTodoTask(String task) {

        String todoDescription = task.substring(4).trim();
        if (todoDescription.isEmpty()) {
            System.out.println("\t" + "Invalid input. Please enter a valid todo task.");
        } else {
            Todo newTodo = new Todo (todoDescription);
            this.taskList.add(newTodo);
            
            System.out.println("\t" + "Added todo: " + todoDescription);
        }
        
    }

    /** 
     * Adds deadline to taskList
     * 
     * @param task task to be added
     */
    private void addDeadline(String task) {

        String[] deadlineDescription = task.substring(8).trim().split("/by", 2);
        if (deadlineDescription.length != 2 || deadlineDescription[0].trim().isEmpty() 
            || deadlineDescription[1].trim().isEmpty()) {
            System.out.println("\t" + "Invalid input. Enter 'deadline <task> /by <deadline>'");
        } else {
            String description = deadlineDescription[0].trim();
            String by = deadlineDescription[1].trim();
            Deadline newDeadline = new Deadline(description, by);
            this.taskList.add(newDeadline);
            
            System.out.println("\t" + "Added deadline: " + description + " (by: " + by + ")");
        }
    }

    /** 
     * Adds eventTask to taskList
     * 
     * @param task task to be added
     */
    private void addEvent(String task) {

        String[] eventParts = task.substring(6).trim().split("/from");
        if (eventParts.length == 2) {
            String[] durationParts = eventParts[1].trim().split("/to");
            if (durationParts.length == 2) {
                // addEvent(eventParts[0].trim(), durationParts[0].trim(), durationParts[1].trim());
                String desc = eventParts[0].trim();
                String from = durationParts[0].trim();
                String to = durationParts[1].trim();
                Events newEvent = new Events(desc, from, to);
                this.taskList.add(newEvent);
                
                System.out.println("\t" + "Added event: " + desc + " (from: " + from + ", to: " + to + ")");

            } else {
                System.out.println("\t" + "Invalid input for event. Please use the format: event <task> /from <start time> /to <end time>");
            }
        } else {
            System.out.println("\t" + "Invalid input for event. Please use the format: event <task> /from <start time> /to <end time>");
        }
    }

    /** 
     * Deletes task from taskList
     * 
     * @param index index of task to be deleted
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws NumberFormatException if input is not a number
     */
    private void deleteTask(int index) {
        try {
            System.out.println("\t" + "Noted. I've removed this task:" + "\n" + this.taskList.get(index));
            this.taskList.remove(index);
            System.out.println("\t" + "Now you have " + this.taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            invalidTaskIndex();
        } catch (NumberFormatException e) {
            invalidTaskIndex();
        }
    }

    /** 
     * Prints taskList
     */
    private void printList() {
        System.out.println("\t" + "Here is your to-do list:");
        this.taskIndex = 1;
        for (Task task : this.taskList) {
            System.out.println("\t" + this.taskIndex + ". " + task);
            this.taskIndex++;
        }
        printLine();
    }

    /** 
     * Marks task as done
     * If task is already done, prints error message
     * If task is undone, marks as done
     * If task does not exist, prints error message
     * If input is not a number, prints error message
     * 
     * @param index index of task to be marked as done
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws NumberFormatException if input is not a number
     */
    private void markDone(int index) {
        try {
            if (this.taskList.get(index).isDone()) {
                System.out.println("\t" + "You completed this task already!");
                printLine();
            } else {
                this.taskList.get(index).markDone();
                System.out.println("\t" + "Good job completing the task!");
                printList();
            }
        } catch (IndexOutOfBoundsException e) {
            invalidTaskIndex();
        } catch (NumberFormatException e) {
            invalidTaskIndex();
        }
    }

    /** 
     * Marks task as undone
     * If task is already undone, prints error message
     * If task is done, marks as undone
     * If task does not exist, prints error message
     * If input is not a number, prints error message
     * 
     * @param index index of task to be marked as undone
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws NumberFormatException if input is not a number
     */
    private void markUndone(int index) {
        try {
            if (!this.taskList.get(index).isDone()) {
                System.out.println("\t" + "Oops! You still haven't done this task!");
            } else {
                this.taskList.get(index).markUndone();
                System.out.println("\t" + "Better get to it soon!");
                printList();
                printLine();
            }
        } catch (IndexOutOfBoundsException e) {
            invalidTaskIndex();
        } catch (NumberFormatException e) {
            invalidTaskIndex();
        }
    }
}

/**
 * TaskType enum
 */
enum TaskType {
    TODO,
    DEADLINE,
    EVENT,
    HELP,
    DELETE,
    UNKNOWN
}

/**
 * Task class
 */
class Task {
    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}


/**
 * Todo class
 */
class Todo extends Task {
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


/**
 * Deadline class
 */
class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}


/**
 * Events class
 */
class Events extends Task {
    protected String at;

    public Events(String description, String from, String to) {
        super(description);
        this.at = from + ", to: " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + at + ")";
    }
}