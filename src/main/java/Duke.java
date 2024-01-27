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

            if (input.equals("list")) { // show list
                this.taskIndex = 1;
                printList();
            } else if (input.startsWith("mark done")) { // mark as done
                try {
                    int index = Integer.parseInt(input.substring(9).trim()) - 1;
                    markDone(index);
                } catch (NumberFormatException e) {
                    System.out.println("\t" + "Invalid input. Please enter a valid task index.");
                }
            } else if (input.startsWith("mark undone")) { // mark as undone
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
        helpline();
        printLine();
    }

    /* 
     * Prints line
     * @return void
     */
    private void printLine() {
        System.out.println(line);
    }

    /*
     * Prints instructions
     * @return void
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

    /*
     * Prints goodbye message
     * @return void
     */
    private void goodbye() {
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }

    /*
     * Adds task to taskList
     * @param task task to be added
     * @return void
     */
    private void addTask(String task) {
        if (task.startsWith("todo")) {
            String todoDescription = task.substring(4).trim();
            if (todoDescription.isEmpty()) {
                System.out.println("\t" + "Invalid input. Please enter a valid todo task.");
            } else {
                addTodoTask(todoDescription);
            }
        } else if (task.startsWith("deadline")) {
            String[] deadlineDescription = task.substring(8).trim().split("/by", 2);
            if (deadlineDescription.length != 2 || deadlineDescription[0].trim().isEmpty() 
                || deadlineDescription[1].trim().isEmpty()) {
                System.out.println("\t" + "Invalid input. Enter 'deadline <task> /by <deadline>'");
            } else {
                addDeadline(deadlineDescription[0].trim(), deadlineDescription[1].trim());
            }
        } else if (task.startsWith("event")) {
            String[] eventParts = task.substring(6).trim().split("/from");
            if (eventParts.length == 2) {
                String[] durationParts = eventParts[1].trim().split("/to");
                if (durationParts.length == 2) {
                    addEvent(eventParts[0].trim(), durationParts[0].trim(), durationParts[1].trim());
                } else {
                    System.out.println("\t" + "Invalid input for event. Please use the format: event <task> /from <start time> /to <end time>");
                }
            } else {
                System.out.println("\t" + "Invalid input for event. Please use the format: event <task> /from <start time> /to <end time>");
            }
        } else {
            System.out.println("\t" + "Invalid input. Please enter a valid task.");
        }
        printLine();
    }

    /*
     * Adds todo task to taskList
     * @param task task to be added
     * @return void
     */
    private void addTodoTask(String task) {
        Todo newTodo = new Todo (task);
        this.taskList.add(newTodo);
        
        System.out.println("\t" + "Added todo: " + task);
    }

    /* 
     * Adds deadline to taskList
     * @param task task to be added
     * @param by deadline of task
     * @return void
     */
    private void addDeadline(String task, String by) {
        Deadline newDeadline = new Deadline(task, by);
        this.taskList.add(newDeadline);
        
        System.out.println("\t" + "Added deadline: " + task + " (by: " + by + ")");
    }

    /* 
     * Adds eventTask to taskList
     * @param task task to be added
     * @param at time of event
     * @return void
     */
    private void addEvent(String task, String at, String to) {
        Events newEvent = new Events(task, at);
        this.taskList.add(newEvent);
        
        System.out.println("\t" + "Added event: " + task + " (from: " + at + ", to: " + to + ")");
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
                ? "\t" + this.taskIndex + ". " + task 
                : "\t" + this.taskIndex + ". " + task);
            this.taskIndex++;
        }
        printLine();
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
        if (this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
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