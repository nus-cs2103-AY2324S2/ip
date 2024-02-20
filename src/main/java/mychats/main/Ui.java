package duke.main;

import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Handles user interactions.
 */
public class Ui {

    private StringBuilder answer;

    /**
     * Constructs a Ui instance.
     * Initialises the answer StringBuilder used for displaying messages to the user.
     */
    public Ui() {
        answer = new StringBuilder();
    }

    /**
     * Clears the content stored in the answer StringBuilder.
     * Resets the answer StringBuilder to an empty state.
     */
    void repeat() {
        answer.setLength(0);
    }

    /**
     * Retrieves content stored in the answer StringBuilder.
     *
     * @return Content of the answer StringBuilder as a string.
     */
    String getAnswer() {
        return answer.toString();
    }

    /**
     * Displays an exit message.
     */
    public void sayBye() {
        answer.append("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the current list of items with their respective indexes,
     * skipping null or uninitialised elements in the list.
     *
     * @param tasks ArrayList of tasks to be displayed.
     */
    public void displayList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task taskAtIndex = tasks.get(i);
            assert taskAtIndex != null : "Task at index " + i + " is null";
            String taskToString = String.format("%d. %s\n", i + 1, taskAtIndex);
            answer.append(taskToString);
        }
    }

    /**
     * Displays a confirmation message that the most recently added item
     * in the list was removed. Also displays the current list of
     * items with their respective indexes, skipping null or
     * uninitialised elements in the list.
     */
    public void displayListAfterUndo(ArrayList<Task> tasks) {
        answer.append("Got it. I've removed the most recently added task.\n");
        answer.append("You now have these tasks in the list:\n");
        displayList(tasks);
    }

    /**
     * Prints out the response, specific to the type of task,
     * after adding the task to the list
     *
     * @param task  Task that is added to the list.
     * @param tasks TaskList containing the list of tasks.
     */
    public void addResponse(Task task, TaskList tasks) {
         int numTasks = tasks.getSize();
         answer.append("Got it. I've added this task:");
         answer.append("\n");
         answer.append(task);
         answer.append("\n");
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
        answer.append("Noted. I've removed this task:");
        answer.append("\n");
        answer.append(task);
        answer.append("\n");
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
        answer.append("Tasks on " + targetDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
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
        answer.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            int oneIndex = i + 1;
            Task foundTaskAtIndex = foundTasks.get(i);
            assert foundTaskAtIndex != null : "Task number " + oneIndex +
                    " of tasks containing " + findWord + " is null";
            String foundTaskToString = String.format("%d. %s\n", i + 1, foundTaskAtIndex);
            answer.append(foundTaskToString);
        }
    }

    /**
     * Appends the given message to the answer StringBuilder.
     *
     * @param message Message to be appended to the screen.
     */
    public void printToScreen(String message) {
        answer.append(message);
    }
}
