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

            if (input.equals("list")) {
                int size = taskList.size();

                for (int count = 0; count < size; count++) {
                    System.out.println(" " + (count + 1) + "." + taskList.get(count));
                }
            }

            else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task task = taskList.get(index);
                task.markAsDone();

                System.out.println(" Nice! I've marked this task as done:\n"
                        + "  " + task);
            }

            else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task task = taskList.get(index);
                task.markAsUndone();

                System.out.println(" OK, I've marked this task as not done yet:\n"
                        + "  " + task);
            }

            else {
                taskList.add(new Task(input));
                System.out.println(" added: " + input);
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