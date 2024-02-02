package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;
import duke.exception.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Ui class handles all user interactions for the Duke application. It is responsible
 * for displaying messages to the user, including greetings, task operations feedback, and errors.
 */
public class Ui {
    
    /**
     * Prints a message to the user indicating a task has been added, and displays the current
     * total number of tasks.
     *
     * @param task The task that has been added.
     * @param list The task list to which the task was added.
     */
    public void echo(Task task, TaskList list) {
        String echo = "Got it. I've added this task:\n" + "  " + task.toString() + "\n"
            + "Now you have " + (list.size() + 1) + " tasks in the list"
            + "\n___________________________________" ;
        System.out.println(echo);
        list.add(task);
    }
    
    /**
     * Prints a greeting message to the user when the application starts.
     */
    public void greeting() {
        String greeting = "___________________________________\n"
            + "Hello! I'm Jinni\n"
            + "What can I do for you?\n"
            + "___________________________________" ;
        System.out.println(greeting);
    }
    
    /**
     * Lists all tasks currently in the task list, displaying each task's status and description.
     *
     * @param list The task list containing the tasks to be listed.
     */
    public void listing(TaskList list) {
        System.out.println("Here are the tasks in your list\n");
        if (list.size() == 0) {
            System.out.println("\n___________________________________");
        } else {
            int num = 1;
            for (Task t : list.getList()) {
                System.out.println(num + "." + t.toString() + "\n");
                num++;
            }
            System.out.println("___________________________________");
        }
    }
    
    /**
     * Prints a message to the user indicating that a specific task has been marked as done.
     *
     * @param t The task that has been marked as done.
     */
    public void marking(Task t) {
        System.out.println("Nice! I have marked this task as done\n");
        System.out.println(t.toString());
        System.out.println("___________________________________");
    }
    
    /**
     * Prints a message to the user indicating that a specific task has been unmarked.
     *
     * @param t The task that has been unmarked.
     */
    public void unmarking(Task t) {
        System.out.println("Ok, I've marked this task as not done yet\n");
        System.out.println(t.toString());
        System.out.println("___________________________________");
    }
    
    /**
     * Prints a message to the user indicating a task has been deleted, and displays the current
     * total number of tasks.
     *
     * @param t The task that has been deleted.
     * @param list The task list from which the task was deleted.
     */
    public void deleting(Task t, TaskList list) {
        String toPrint = "Noted. I've removed this task:\n" + "  " + t.toString() + "\n"
            + "Now you have " + (list.size() - 1) + " tasks in the list"
            + "\n___________________________________" ;
        System.out.println(toPrint);
    }
    
    /**
     * Prints a goodbye message to the user when the application exits.
     */
    public void bye() {
        String bye = "___________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "___________________________________";
        System.out.println(bye);
    }
    
    /**
     * Prints an error message to the user if the application fails to load the task file.
     */
    public void loadingError() {
        System.out.println("File not found");
    }
    
    /**
     * Prints an error message to the user if the application encounters an error while attempting
     * to modify the task file.
     */
    public void changingFileError() {
        System.out.println("File not found");
    }
    
    /**
     * Checks for errors in the mark command input by the user and throws an exception if the input is invalid.
     *
     * @param inputFromUser The user input for marking a task.
     * @param list The task list against which to validate the input.
     * @throws DukeException If the specified task index is invalid.
     */
    public void handleMarkError(String inputFromUser, TaskList list) throws DukeException {
        if (Integer.parseInt(inputFromUser.substring(5)) > list.size()) {
            throw new DukeException("You do not have that many tasks");
        }
        if (Integer.parseInt(inputFromUser.substring(5)) < 1) {
            throw new DukeException("No negative task number");
        }
    }
    
    /**
     * Checks for errors in the unmark command input by the user and throws an exception if the input is invalid.
     *
     * @param inputFromUser The user input for unmarking a task.
     * @param list The task list against which to validate the input.
     * @throws DukeException If the specified task index is invalid.
     */
    public void handleUnmarkError(String inputFromUser, TaskList list) throws DukeException {
        if (Integer.parseInt(inputFromUser.substring(7)) > list.size()) {
            throw new DukeException("You do not have that many tasks");
        }
        if (Integer.parseInt(inputFromUser.substring(7)) < 1) {
            throw new DukeException("No negative task number");
        }
    }
    
    /**
     * Validates the input for a todo command and throws an exception if the input is invalid.
     *
     * @param inputFromUser The user input for creating a todo task.
     * @throws DukeException If the task description is empty.
     */
    public void handleTodoError(String inputFromUser) throws DukeException {
        if (!(inputFromUser.substring(4).matches(".*\\S.*"))) {
            throw new DukeException("Description of the task can't be empty");
        }
    }
    
    /**
     * Validates the input for a deadline command and throws an exception if the input is invalid.
     *
     * @param inputFromUser The user input for creating a deadline task.
     * @throws DukeException If the task description or date is empty or invalid.
     */
    public void handleDeadlineError(String inputFromUser) throws DukeException {
        if (!(inputFromUser.substring(8).matches(".*\\S.*"))) {
            throw new DukeException("Description of the task can't be empty");
        }
    }
    
    /**
     * Validates the input for an event command and throws an exception if the input is invalid.
     *
     * @param inputFromUser The user input for creating an event task.
     * @throws DukeException If the task description or dates are empty or invalid.
     */
    public void handleEventError(String inputFromUser) throws DukeException {
        if (!(inputFromUser.substring(5).matches(".*\\S.*"))) {
            throw new DukeException("Description of the task can't be empty");
        }
    }
    
    /**
     * Validates the input for a delete command and throws an exception if the task index is invalid.
     *
     * @param list The task list from which a task is to be deleted.
     * @param indexOfTaskToDelete The index of the task to delete.
     * @throws DukeException If the task index is out of bounds.
     */
    public void handleDeleteError(TaskList list, int indexOfTaskToDelete) throws DukeException {
        if (list.size() < 1) {
            throw new DukeException("No task at the moment");
        }
        if (indexOfTaskToDelete > list.size() || indexOfTaskToDelete < 1) {
            throw new DukeException("Check you task number");
        }
    }
    
    /**
     * Validates a date input by the user, ensuring it matches the expected format.
     *
     * @param inputDate The date string input by the user.
     * @param formatter The DateTimeFormatter against which to validate the input date.
     * @param date The LocalDate object parsed from the inputDate.
     * @throws DukeException If the input date does not match the expected format.
     */
    public void handleInvalidInputDate(String inputDate, DateTimeFormatter formatter, LocalDate date) throws DukeException {
        if (!(inputDate.equals(date.format(formatter)))) {
            throw new DukeException("Did you enter a valid date or is the date entered of format <dd/mm/yyyy>");
        }
    }
    
}
