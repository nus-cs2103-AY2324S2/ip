import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class Ui {
    private Scanner sc;
    Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays a starting message to greet the user.
     */
     void sayHi() {
        System.out.println("Hello! I'm myChats\n" + "What can I do for you?\n");
    }

    /**
     *  Displays an exit message.
     */
     void sayBye() {
        System.out.println("\nBye. Hope to see you again soon!");
    }

    /**
     * Displays the current list of items with their respective indices.
     * Skips null or uninitialized elements in the list.
     */
     void displayList(ArrayList<Task> tasks) {
        System.out.println();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        }
        System.out.println();
    }

    /**
     * Prints out the response, specific to the type of task, after adding the task to the list
     * @param task the task that is added to the list.
     */
     void taskResponse(Task task, TaskList tasks) {
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
     * Displays the response message after deleting a task if the deletion is successful and an exception has not been thrown.
     *
     * @param task The task that has been deleted.
     */
     void deleteResponse(Task task, TaskList tasks) {
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


    void displayTasksOn(LocalDate targetDate, ArrayList<Task> tasks) {
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
                    if (event.getStartTime().toLocalDate().equals(targetDate) ||
                            event.getEndTime().toLocalDate().equals(targetDate) ||
                            (targetDate.isAfter(event.getStartTime().toLocalDate()) &&
                                    targetDate.isBefore(event.getEndTime().toLocalDate()))) {
                        System.out.println(event);
                    }
                }
            }
            System.out.println();
        } catch (DateTimeParseException e) {
            System.out.println("\nError! Please provide a valid date format (MMM dd yyyy).\n");
        }
    }

    void showLoadingError(String error) {
        System.out.println("\nLoading error: " + error + "\n");
    }

    String readCommand() {
        return sc.nextLine();
    }

    void showError(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }
}
