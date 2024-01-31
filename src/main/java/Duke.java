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
    private static final String FOLDER_PATH = "./tasklist";
    private static final String TASKLIST_PATH = FOLDER_PATH + "/tasklist.txt";

    public ListAdder() {
        this.taskIndex = 1;
        loadTasklist();
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
        sc.close();
    }

    /**
     * Loads the tasklist from file. Creates the folder and file if they don't exist.
     * 
     * @throws IOException if there is an error loading data from file
     */
    private void loadTasklist() {
        try {
            // Clear existing tasks before loading
            this.taskList.clear();  

            Path taskListPath = Paths.get(TASKLIST_PATH);
            Path folderPath = Paths.get(FOLDER_PATH);

            // Create the folder and file if they don't exist
            if (Files.notExists(folderPath)) { 
                Files.createDirectories(folderPath);
            }
            if (Files.notExists(taskListPath)) {
                Files.createFile(taskListPath);
            }
            
            ArrayList<String> taskListFromFile = new ArrayList<>(Files.readAllLines(taskListPath));
            for (String task : taskListFromFile) {
                String[] taskParts = task.split(" \\| ", 3);
                String taskType = taskParts[0];
                String taskStatus = taskParts[1];
                String taskDescription = taskParts[2];
                
                switch (taskType) {
                case "T":
                    Todo newTodo = new Todo (taskDescription);
                    if (taskStatus.equals("done")) {
                        newTodo.markDone();
                    }
                    this.taskList.add(newTodo);
                    break;
                case "D":
                    String[] deadlineParts = taskDescription.split(" \\(by: ", 2);
                    String deadlineDescription = deadlineParts[0];
                    String deadlineBy = deadlineParts[1].substring(0, deadlineParts[1].length() - 1);

                    Deadline newDeadline = new Deadline (deadlineDescription, deadlineBy);
                    if (taskStatus.equals("done")) {
                        newDeadline.markDone();
                    }
                    this.taskList.add(newDeadline);
                    break;
                case "E":
                    String[] eventParts = taskDescription.split(" \\(from: ", 2);
                    String eventDescription = eventParts[0];

                    String eventAt = eventParts[1].substring(0, eventParts[1].length() - 1);
                    String[] eventAtParts = eventAt.split(", to: ", 2);

                    String eventFrom = eventAtParts[0];
                    String eventTo = eventAtParts[1];

                    Events newEvent = new Events (eventDescription, eventFrom, eventTo);
                    if (taskStatus.equals("done")) {
                        newEvent.markDone();
                    }
                    this.taskList.add(newEvent);
                    break;
                default:
                    System.out.println(taskListFromFile); // debug line
                    System.out.println("Unrecognized task type: " + task);
                    throw new IOException("Error loading data from file: error in loadTasklist() try block");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data from file: error in loadTasklist() catch block");
            e.printStackTrace();
        }
    }

    /**
     * Saves data from taskList into the file
     */
    private void saveTaskListToFile() {
        try {
            Path taskListPath = Paths.get(TASKLIST_PATH);
            ArrayList<String> newTaskList = new ArrayList<>();
            
            for (Task task : this.taskList) {
                newTaskList.add(task.toString());
            }

            Files.write(taskListPath, newTaskList);
        } catch (IOException e) {
            System.out.println("Error saving data to file: error in saveTaskListToFile()");
        }
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
            saveTaskListToFile();
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
            saveTaskListToFile();
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
                String desc = eventParts[0].trim();
                String from = durationParts[0].trim();
                String to = durationParts[1].trim();
                Events newEvent = new Events(desc, from, to);
                this.taskList.add(newEvent);
                
                System.out.println("\t" + "Added event: " + desc + " (from: " + from + ", to: " + to + ")");
                saveTaskListToFile();

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
            System.out.println("\t" + "Noted. I've removed this task:" + "\n" + "\t" 
                    + "[" + this.taskList.get(index) + "]");
            this.taskList.remove(index);
            System.out.println("\t" + "There are " + this.taskList.size() + " tasks in your list.");
            saveTaskListToFile();
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
        if (this.taskList.isEmpty()) {
            System.out.println("\t" + "Your tasklist is empty");
        } else {
            System.out.println("\t" + "Here is your to-do list:");
            this.taskIndex = 1;
            for (Task task : this.taskList) {
                System.out.println("\t" + this.taskIndex + ". " + task);
                this.taskIndex++;
            }
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
                saveTaskListToFile();
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
                saveTaskListToFile();
                printList();
                printLine();
            }
        } catch (IndexOutOfBoundsException e) {
            invalidTaskIndex();
        } catch (NumberFormatException e) {
            invalidTaskIndex();
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

        String[] instructions = {
            "DISPLAY LIST: list",
            "ADD TODO: todo <task>",
            "ADD DEADLINE: deadline <task> /by <deadline>",
            "ADD EVENT: event <task> /from <start time> /to <end time>",
            "MARK DONE: mark done <task index>",
            "MARK UNDONE: mark undone <task index>",
            "DELETE TASK: delete <task index>",
            "EXIT: bye"
        };
        for (int i = 0; i < instructions.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + instructions[i]);
        }
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

    protected String taskType() {
        return "";
    }

    public String taskStatus() {
        return this.isDone ? "done" : "not done";
    }


    @Override
    public String toString() {
        return taskType() + " | " + taskStatus() + " | " + this.task;
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
    protected String taskType() {
        return "T";
    }

    @Override
    public String toString() {
        return super.toString();
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
    protected String taskType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
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
    protected String taskType() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + at + ")";
    }
}