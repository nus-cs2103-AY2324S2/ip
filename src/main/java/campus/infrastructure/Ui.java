package campus.infrastructure;

import campus.exceptions.CampusException;
import campus.tasks.Task;

/**
 * Deals with interactions with the User
 */
public class Ui {
    /**
     * Produces a greeting message
     */
    public String printGreeting() {

        String message = "------------------------------\n"
                + "  ____\n"
                + " / ___|__ _ _ __ ___  _ __  _   _ ___\n"
                + "| |   / _` | '_ ` _ \\| '_ \\| | | / __|\n"
                + "| |__| (_| | | | | | | |_) | |_| \\__ \\ \n"
                + " \\____\\__,_|_| |_| |_| .__/ \\__,_|___/\n"
                + "                     |_|\n"
                + "\n"
                + "Hello! I'm Campus\n"
                + "What can I do for you?\n"
                + "\n"
                + "------------------------------\n";

        return message;
    }

    /**
     * Produce a goodbye/exit message
     */
    public String printExiting() {
        String message = "------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "\n"
                + "------------------------------\n";
        return message;
    }

    /**
     * Displays all the current items in the TaskList in a specified format with numbering
     * @param taskList the object containing all the tasks in a list
     */
    public String printTaskList(TaskList taskList) {
        int numOfTasks = taskList.getListSize();
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < numOfTasks; i++) {
            int listNum = i + 1;
            Task task = taskList.getIthTaskInteger(i);
            listOfTasks.append(String.format("%s. %s\n", listNum, task.toString()));
        }

        String message = "------------------------------\n"
                + String.format("%s\n", listOfTasks)
                + "------------------------------\n";

        return message;
    }

    /**
     * Prints a message when a task is marked as done
     * @param task The Task object instance
     */
    public String markDone(Task task) {
        String message = "------------------------------\n"
                + "Nice! I've completed this task successfully:\n"
                + String.format("%s\n", task)
                + "------------------------------\n";

        return message;
    }

    /**
     * Prints a message when a task is marked as undone
     * @param task The Task object instance
     */
    public String markUndone(Task task) {
        String message = "------------------------------\n"
                + "Ok, this task is still not done yet:\n"
                + String.format("%s\n", task)
                + "------------------------------\n";

        return message;
    }

    /**
     * Prints a message when a task is deleted
     * @param taskList The taskList object instance
     * @param task The task object instance
     */
    public String delete(TaskList taskList, Task task) {
        int numOfTasks = taskList.getListSize();

        String message = "------------------------------\n"
                + "Noted. Task deleted successfully. I have removed the following task:\n"
                + String.format("%s\n", task)
                + String.format("Now you have %s task(s) in the list.\n", numOfTasks)
                + "------------------------------\n";

        return message;
    }

    /**
     * Prints a message when a task is added to the list
     * @param taskList The taskList object instance
     * @param task The task object instance
     */
    public String add(TaskList taskList, Task task) {
        int numOfTasks = taskList.getListSize();

        String message = "------------------------------\n"
                + "Got it. I've added this to our list of tasks:\n"
                + String.format("added: %s\n", task.toString())
                + String.format("Now you have %s task(s) in the list.\n", numOfTasks)
                + "------------------------------\n";

        return message;
    }

    /**
     * Produces the error message read from the CampusException class
     * @param e The CampusException class instance
     */
    public String displayErrorMessage(CampusException e) {
        String message = "------------------------------\n"
                + String.format("%s\n", e.getMessage())
                + "------------------------------\n";
        return message;
    }
}
