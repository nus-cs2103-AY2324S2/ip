package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;
import duke.exception.DukeException;

/**
 * Manages user interactions in the Duke application, handling the display of messages for various operations
 * including task addition, deletion, marking, unmarking, and listing, as well as providing error feedback.
 */
public class Ui {
    
    /**
     * Generates a message indicating a task has been added to the list, displaying the task and the updated total count.
     *
     * @param task The newly added task.
     * @param list The task list to which the task was added.
     * @return A string containing the confirmation message and the current task count.
     */
    public String echo(Task task, TaskList list) {
        String echo = "Got it. I've added this task:\n" + "  " + task.toString() + "\n"
            + "Now you have " + (list.size() + 1) + " tasks in the list";
        list.add(task);
        return echo;
    }
    
    /**
     * Compiles and returns a string listing all tasks currently in the task list.
     *
     * @param list The task list containing the tasks to be listed.
     * @return A formatted string listing all tasks with their status and description.
     */
    public String listing(TaskList list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list").append(System.lineSeparator());
        if (list.size() != 0) {
            int num = 1;
            for (Task t : list.getList()) {
                sb.append(num + "." + t.toString()).append(System.lineSeparator());
                num++;
            }
        }
        return sb.toString();
    }
    
    /**
     * Creates a message indicating a specific task has been marked as completed.
     *
     * @param t The task that has been marked as done.
     * @return A confirmation message stating the task is done.
     */
    public String marking(Task t) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I have marked this task as done\n").append(t.toString());
        return sb.toString();
    }
    
    /**
     * Generates a message indicating a specific task has been unmarked, implying it is not completed.
     *
     * @param t The task that has been unmarked.
     * @return A message stating the task is marked as not done.
     */
    public String unmarking(Task t) {
        return "Ok, I've marked this task as not done yet\n" + t.toString();
    }
    
    /**
     * Produces a message confirming the deletion of a task and shows the new total count of tasks.
     *
     * @param t The task that has been deleted.
     * @param list The task list from which the task was removed.
     * @return A string confirming the task's deletion and the updated task count.
     */
    public String deleting(Task t, TaskList list) {
        String toPrint = "Noted. I've removed this task:\n" + "  " + t.toString() + "\n"
            + "Now you have " + (list.size() - 1) + " tasks in the list";
        return toPrint;
    }
    
    /**
     * Searches for and lists tasks containing the specified keyword in their description.
     *
     * @param list The task list to search within.
     * @param keyword The keyword to search for in the task descriptions.
     * @return A string listing all tasks that match the search criterion.
     */
    public String finding(TaskList list, String keyword) {
        String result = list.findTask(keyword);
        String toPrint = "Here are the matching tasks in your list:\n"
                + result;
        return toPrint;
    }
    
    /**
     * Generates a farewell message to be displayed when the application is exited.
     *
     * @return A goodbye message.
     */
    public String bye() {
        String bye = "Bye. Hope to see you again soon!";
        return bye;
    }
    
    /**
     * Generate a help message to help the user navigate the chatbot.
     * @return A help message
     */
    public String help() {
        String help = "Here's a guide to using Jinni:\n"
                + "type\n"
                + "'list': allows you to view all tasks you have\n"
                + System.lineSeparator()
                + "'mark X': allows you to mark a task as done (X stands for the task number)\n"
                + System.lineSeparator()
                + "'unmark X': allows you to unmark a task as undone (X stands for the task number)\n"
                + System.lineSeparator()
                + "'todo description': allows you to add a task of todo followed by the description of the task\n"
                + System.lineSeparator()
                + "'deadline description /by dd/mm/yyyy': allows you to add a task with deadline (note the date format must be precise)\n"
                + System.lineSeparator()
                + "'event description /from dd/mm/yyyy /to dd/mm/yyyy': allows you to add an event task with the duration (note the date formate too \n"
                + System.lineSeparator()
                + "'delete X': allows you to delete a task (X stands for the task number)\n"
                + System.lineSeparator()
                + "'find keyword': allows you to find the tasks with the keyword you desire\n"
            + System.lineSeparator()
                + "'bye': quits Jinni";
        return help;
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
    
}
