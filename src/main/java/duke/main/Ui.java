package duke.main;

import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Handles user interactions.
 */
public class Ui {

    private StringBuilder answer;

    /**
     * Constructs a Ui instance with a Scanner for user input.
     */
    public Ui() {
        answer = new StringBuilder();
    }

    void repeat() {
        answer.setLength(0);
    }

    String getAnswer() {
        return answer.toString();
    }

    /**
     * Displays an exit message.
     */
    public void sayBye() {
        answer.append("\nBye. Hope to see you again soon!");
    }

    /**
     * Displays the current list of items with their respective indexes,
     * skipping null or uninitialized elements in the list.
     *
     * @param tasks ArrayList of tasks to be displayed.
     */
    public void displayList(ArrayList<Task> tasks) {
        answer.append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                String task = String.format("%d. %s\n", i + 1, tasks.get(i));
                answer.append(task);
            }
        }
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
         answer.append("\n");
         answer.append("Got it. I've added this task:");
         answer.append(task);
         if (numTasks == 1) {
             answer.append("Now you have " + numTasks + " task in the list.");
         }
         if (numTasks != 1) {
             answer.append("Now you have " + numTasks + " tasks in the list.");
         }
    }

    /**
     * Displays the response message after deleting a task if the deletion is successful.
     *
     * @param task Task that has been deleted.
     * @param tasks TaskList containing the list of tasks.
     */
    public void deleteResponse(Task task, TaskList tasks) {
        int numTasks = tasks.getSize();
        answer.append("\n");
        answer.append("Noted. I've removed this task:");
        answer.append(task);
        if (numTasks == 1) {
            answer.append("Now you have " + numTasks + " task in the list.");
        }
        if (numTasks != 1) {
            answer.append("Now you have " + numTasks + " tasks in the list.");
        }
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
            answer.append("\nTasks on " + targetDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    if (deadline.getBy().toLocalDate().equals(targetDate)) {
                        answer.append("\n" + deadline);
                    }
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    if (event.getStartTime().toLocalDate().equals(targetDate)
                            || event.getEndTime().toLocalDate().equals(targetDate)
                            || (targetDate.isAfter(event.getStartTime().toLocalDate())
                            && targetDate.isBefore(event.getEndTime().toLocalDate()))) {
                        answer.append("\n" + event);
                    }
                }
            }
        } catch (DateTimeParseException e) {
            answer.append("\nError! Please provide a valid date format (MMM dd yyyy).");
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
        answer.append("\nHere are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            if (foundTasks.get(i) != null) {
                String foundTask = String.format("%d. %s\n", i + 1, foundTasks.get(i));
                answer.append(foundTask);
            }
        }
    }

    /**
     * Displays an error message when there is a loading issue.
     *
     * @param message Error message to display.
     */
    void showLoadingError(String message) {
        answer.append("\nLoading error: " + message);
    }

    public void printToScreen(String message) {
        answer.append(message);
    }
}
