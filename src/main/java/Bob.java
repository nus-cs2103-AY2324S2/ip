import java.util.Scanner;
import java.util.ArrayList;

public class Bob {
    public static void main(String[] args) {
        String greet = " Hello! I'm Bob.\n"
                + " What can I do for you?\n";

        String exit = " Bye. Hope to see you again soon!";

        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>(100);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            }

            else if (input.equals("list")) {
                int size = taskList.size();

                System.out.println(" Here are the tasks in your list:");

                for (int count = 0; count < size; count++) {
                    System.out.println(" " + (count + 1) + "." + taskList.get(count));
                }
            }

            else if (input.trim().matches("mark|unmark|deadline|todo|event|delete")) {
                System.out.println(" Your entry is incomplete!");
            }

            else if (input.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    Task task = taskList.get(index);
                    task.markAsDone();

                    System.out.println(" Nice! I've marked this task as done:\n"
                            + "  " + task);
                }

                catch (IndexOutOfBoundsException e) {
                    System.out.println(" There exists no such task.");
                }
            }

            else if (input.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    Task task = taskList.get(index);
                    task.markAsUndone();

                    System.out.println(" OK, I've marked this task as not done yet:\n"
                            + "  " + task);
                }

                catch (IndexOutOfBoundsException e) {
                    System.out.println(" There exists no such task.");
                }
            }

            else if (input.startsWith("deadline ")) {
                int byIndex = input.indexOf("/by ");
                String taskDescription = input.substring(9, byIndex).trim();
                String deadlineDate = input.substring(byIndex + 4).trim();

                Task newTask = new Deadline(taskDescription, deadlineDate);
                taskList.add(newTask);
                System.out.println(" Got it. I've added this task:\n"
                        + "  " + newTask + "\n"
                        + " Now you have " + taskList.size() + " tasks in the list.");
            }

            else if (input.startsWith("todo ")) {
                String taskDescription = input.substring(5);
                Task newTask = new ToDo(taskDescription);
                taskList.add(newTask);
                System.out.println(" Got it. I've added this task:\n"
                        + "  " + newTask + "\n"
                        + " Now you have " + taskList.size() + " tasks in the list.");
            }

            else if (input.startsWith("event ")) {
                int fromIndex = input.indexOf("/from ");
                int toIndex = input.indexOf("/to ");
                String taskDescription = input.substring(6, fromIndex).trim();
                String fromDate = input.substring(fromIndex + 6, toIndex).trim();
                String toDate = input.substring(toIndex + 4).trim();

                Task newTask = new Event(taskDescription, fromDate, toDate);
                taskList.add(newTask);
                System.out.println(" Got it. I've added this task:\n"
                        + "  " + newTask + "\n"
                        + " Now you have " + taskList.size() + " tasks in the list.");
            }

            else if (input.startsWith("delete ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task task = taskList.get(index);
                taskList.remove(index);

                System.out.println(" Noted. I've removed this task:\n"
                        + "  " + task + "\n"
                        + " Now you have " + taskList.size() + " tasks in the list.");
            }

            else {
                System.out.println(" Bob knows not what that means.");
            }
        }

        scanner.close();
    }
}

/*
 * This class represents a task we want to record.
 */
class Task {
    private String description;
    private boolean isDone;

    /*
     * A constructor that depicts a new task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /*
     * A method that marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /*
     * A method that will undo the mark on a task.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /*
     * A method that returns the task status as a string.
     * @return A check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

class Deadline extends Task {
    protected String by;

    /*
     * A constructor for creating a new task with a deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /*
     * A method that returns the task status as a string.
     * @return A label [D] and a check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class ToDo extends Task {
    /*
     * A constructor for creating a new todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /*
     * A method that returns the task description.
     * @return A label [T] and a check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    /*
     * A constructor to create a new event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /*
     * A method that returns the status of the task.
     * @return A label [E] and a check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}