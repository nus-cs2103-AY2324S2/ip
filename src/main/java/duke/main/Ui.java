package duke.main;

import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interactions.
 */
public class Ui {

    private Scanner sc;

    /**
     * Constructs a Ui instance with a Scanner for user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays a starting message to greet the user.
     */
     void sayHi() {
        System.out.println("Hello! I'm myChats\n" + "What can I do for you?\n");
    }

    /**
     * Displays an exit message.
     */
    public void sayBye() {
        System.out.println("\nBye. Hope to see you again soon!");
    }

    /**
     * Displays the current list of items with their respective indexes,
     * skipping null or uninitialized elements in the list.
     *
     * @param tasks ArrayList of tasks to be displayed.
     */
    public void displayList(ArrayList<Task> tasks) {
        System.out.println();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        }
        System.out.println();
    }

    /**
     * Prints out the response, specific to the type of task,
     * after adding the task to the list
     *
     * @param task  Task that is added to the list.
     * @param tasks TaskList containing the list of tasks.
     */
    public void taskResponse(Task task, TaskList tasks) {
         int numTasks = tasks.getSize();
         System.out.println();
         System.out.println("Got it. I've added this task:");
         System.out.println(task);
         if (numTasks == 1) {
             System.out.println("Now you have " + numTasks + " task in the list.");
         }
         if (numTasks != 1) {
             System.out.println("Now you have " + numTasks + " tasks in the list.");
         }
         System.out.println();
    }

    /**
     * Displays the response message after deleting a task if the deletion is successful.
     *
     * @param task Task that has been deleted.
     * @param tasks TaskList containing the list of tasks.
     */
    public void deleteResponse(Task task, TaskList tasks) {
        int numTasks = tasks.getSize();
        System.out.println();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        if (numTasks == 1) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        }
        if (numTasks != 1) {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }
        System.out.println();
    }

    /**
     * Displays tasks that occur on the given target date.
     *
     * @param targetDate Target date to display tasks for.
     * @param tasks ArrayList of tasks to search for tasks
     * that occur on the given target date.
     */
    public void displayTasksOn(LocalDate targetDate, ArrayList<Task> tasks) {
        try {
            System.out.println("\nTasks on " + targetDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    if (deadline.getBy().toLocalDate().equals(targetDate)) {
                        System.out.println(deadline);
                    }
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    if (event.getStartTime().toLocalDate().equals(targetDate)
                            || event.getEndTime().toLocalDate().equals(targetDate)
                            || (targetDate.isAfter(event.getStartTime().toLocalDate())
                            && targetDate.isBefore(event.getEndTime().toLocalDate()))) {
                        System.out.println(event);
                    }
                }
            }
            System.out.println();
        } catch (DateTimeParseException e) {
            System.out.println("\nError! Please provide a valid date format (MMM dd yyyy).\n");
        }
    }

    /**
     * Finds tasks containing a given word and displays the matching tasks.
     *
     * @param findWord Word to search for in task list.
     * @param tasks ArrayList of tasks to search within.
     */
    public void findTasks(String findWord, ArrayList<Task> tasks) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(findWord)) {
                foundTasks.add(task);
            }
        }
        System.out.println("\nHere are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            if (foundTasks.get(i) != null) {
                System.out.printf("%d. %s\n", i + 1, foundTasks.get(i));
            }
        }
        System.out.println();
    }

    /**
     * Displays an error message when there is a loading issue.
     *
     * @param error Error message to display.
     */
    void showLoadingError(String error) {
        System.out.println("\nLoading error: " + error + "\n");
    }

    /**
     * Reads and returns user command input.
     *
     * @return User command input.
     */
    String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays an error message.
     *
     * @param message Error message to display.
     */
    void showError(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }
}
