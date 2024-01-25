import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ListAdder newList = new ListAdder();
        newList.start();
    }    
}

class ListAdder {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int taskIndex;
    private static final String line = "____________________________________________________________";

    public ListAdder() {
        this.taskIndex = 1;
    }

    public void start() {
        greeting();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark done")) { // task is already done
                int index = Integer.parseInt(input.substring(9).trim()) - 1;
                markDone(index);

            } else if (input.startsWith("mark undone")) { // task is already undone
                int index = Integer.parseInt(input.substring(11).trim()) - 1;
                markUndone(index);

            } else {
                addTask(input);
            }
            // System.out.println(line);
            input = sc.nextLine();
            System.out.println(line);
        }
        // System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        // System.out.println(line);
    }


    private void greeting() {
        // System.out.println(line);
        System.out.println("Hello! My name is Computer Helper Boy."+ "\n");
        System.out.println("Provide an input to add it to your to-do list." + "\n");
        System.out.println("Type 'list' to see your list, and 'bye' to exit the program.");
        // System.out.println(line);
    }

    // Adds task to list with ability to mark as done
    private void addTask(String task) {
        Task newTask = new Task(task);
        this.taskList.add(newTask);
        // System.out.println(line);
        System.out.println("Added task: " + task);
        // System.out.println(line);
    }

    private void printList() {
        System.out.println("Here is your to-do list:");
        for (Task task : this.taskList) {
            System.out.println(task.isDone() ? "[X] " + task : "[ ] " + task);
            this.taskIndex++;
        }
        // System.out.println(line);
    }

    private void markDone(int index) {
        try {
            if (this.taskList.get(index).isDone()) {
                System.out.println("You completed this task already!");
            } else {
                this.taskList.get(index).markDone();
                System.out.println("Good job! I've marked this task as done:");
                System.out.println("[X] " + this.taskList.get(index));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Your list isn't that long :P");
        } catch (NumberFormatException e) {
            System.out.println("This number isn't valid!");
        }
        // System.out.println(line);
    }

    private void markUndone(int index) {
        try {
            if (!this.taskList.get(index).isDone()) {
                System.out.println("Oops! You still haven't done this task!");
            } else {
                this.taskList.get(index).markUndone();
                System.out.println("I've marked this task as undone:");
                System.out.println("[ ] " + this.taskList.get(index));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Your list isn't that long :P");
        } catch (NumberFormatException e) {
            System.out.println("This number isn't valid!");
        }
        // System.out.println(line);
    }
}

abstract class Task {
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