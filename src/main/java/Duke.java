import java.util.Scanner;
import java.util.ArrayList;

/*
 * Duke class
 * Contains main method
 */
public class Duke {
    public static void main(String[] args) {
        ListAdder newList = new ListAdder();
        newList.start();
    }    
}

/* 
 * ListAdder class
 * Contains methods to add tasks to a list
 */
class ListAdder {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int taskIndex;
    private static final String line = "____________________________________________________________";

    public ListAdder() {
        this.taskIndex = 1;
    }

    /*
     * Starts the program
     * @return void
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

            if (input.equals("list")) {
                this.taskIndex = 1;
                printList();
            } else if (input.startsWith("mark done")) { // task is already done
                try {
                    int index = Integer.parseInt(input.substring(9).trim()) - 1;
                    markDone(index);
                } catch (NumberFormatException e) {
                    System.out.println("\t" + "Invalid input. Please enter a valid task index.");
                }
            } else if (input.startsWith("mark undone")) { // task is already undone
                try {
                    int index = Integer.parseInt(input.substring(11).trim()) - 1;
                    markUndone(index);
                } catch (NumberFormatException e) {
                    System.out.println("\t" + "Invalid input. Please enter a valid task index.");
                }
            } else {
                addTask(input);
            }
            input = sc.nextLine();
        }
        goodbye();
    }


    /*
     * Greets user and prints instructions
     * @return void
     */
    private void greeting() {
        printLine();
        System.out.println("\n" + "Hello! My name is Computer Helper Boy." + "\n");
        System.out.println("Help: ");
        System.out.println("1. To add a task, type the task name.");
        System.out.println("2. To mark a task as done, type 'mark done' followed by the task number.");
        System.out.println("3. To mark a task as undone, type 'mark undone' followed by the task number.");
        System.out.println("4. To view your to-do list, type 'list'.");
        System.out.println("5. To exit, type 'bye'.");
        printLine();
    }

    private void printLine() {
        System.out.println(line);
    }

    private void goodbye() {
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }

    /*
     * Adds task to taskList
     * @param task task to be added
     * @return void
     */
    private void addTask(String task) {
        Task newTask = new Task(task);
        this.taskList.add(newTask);
        
        System.out.println("\t" + "Added task: " + task);
        printLine();
    }

    /* 
     * Prints taskList
     * @return void
     */
    private void printList() {
        System.out.println("\t" + "Here is your to-do list:");
        this.taskIndex = 1;
        for (Task task : this.taskList) {
            System.out.println(
                task.isDone() 
                ? "\t" + this.taskIndex + ". [X] " + task 
                : "\t" + this.taskIndex + ". [ ] " + task);
            this.taskIndex++;
        }
    }

    /* 
     * Marks task as done
     * If task is already done, prints error message
     * If task is undone, marks as done
     * If task does not exist, prints error message
     * If input is not a number, prints error message
     * @param index index of task to be marked as done
     * @return void
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
                printLine();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t" + "Oops! Your list isn't that long :P");
        } catch (NumberFormatException e) {
            System.out.println("\t" + "This number isn't valid!");
        }
    }

    /* 
     * Marks task as undone
     * If task is already undone, prints error message
     * If task is done, marks as undone
     * If task does not exist, prints error message
     * If input is not a number, prints error message
     * @param index index of task to be marked as undone
     * @return void
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
            System.out.println("\t" + "Oops! Your list isn't that long :P");
        } catch (NumberFormatException e) {
            System.out.println("\t" + "This number isn't valid!");
        }
    }
}

/*
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
        return this.task;
    }
}

/* 
 * Subclasses of Task
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

class Events extends Task {
    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

class Todo extends Task {
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}