package campus.infrastructure;

import campus.exceptions.CampusException;
import campus.tasks.Task;

/**
 * Deals with interactions with the User.
 */
public class Ui {
    /**
     * Produces a greeting message.
     */

    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private String LINE_BREAK = "";

    public String printGreeting() {

        String message = LINE_BREAK
                + "  ____\n"
                + " / ___|__ _ _ __ ___  _ __  _   _ ___\n"
                + "| |   / _` | '_ ` _ \\| '_ \\| | | / __|\n"
                + "| |__| (_| | | | | | | |_) | |_| \\__ \\ \n"
                + " \\____\\__,_|_| |_| |_| .__/ \\__,_|___/\n"
                + "                     |_|\n"
                + "\n"
                + "Greetings! I am at your service.\n"
                + "How may I be of assistance to you today?\n"
                + "Should you require a comprehensive list of my capabilities, "
                + "kindly type 'help' to receive the full catalog of commands available at your disposal.\n"
                + "\n"
                + LINE_BREAK;

        return message;
    }

    /**
     * Produce a goodbye/exit message.
     */
    public String printExiting() {
        String message = LINE_BREAK
                + "Farewell, esteemed guest! May fortune favor your endeavors until "
                + "we meet again in the halls of our noble court.\n"
                + "\n"
                + LINE_BREAK;
        return message;
    }

    /**
     * Displays all the current items in the TaskList in a specified format with numbering.
     * @param taskList the object containing all the tasks in a list.
     */
    public String printTaskList(TaskList taskList) {
        int numOfTasks = taskList.getListSize();
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < numOfTasks; i++) {
            int listNum = i + 1;
            Task task = taskList.getIthTaskInteger(i);
            listOfTasks.append(String.format("%s. %s\n", listNum, task.toString()));
        }

        String message = LINE_BREAK
                + String.format("%s\n", listOfTasks)
                + LINE_BREAK;

        return message;
    }

    /**
     * Prints a message when a task is marked as done.
     * @param task The Task object instance.
     */
    public String markDone(Task task) {
        String message = LINE_BREAK
                + "Splendid news! Your accomplishment is a testament to your diligence and skill."
                + "Should you require further assistance or have additional tasks to "
                + "undertake, do not hesitate to summon me.\n"
                + String.format("%s\n", task)
                + LINE_BREAK;

        return message;
    }

    /**
     * Prints a message when a task is marked as undone.
     * @param task The Task object instance.
     */
    public String markUndone(Task task) {
        String message = LINE_BREAK
                + "Splendid news! Your accomplishment is a testament to your diligence and skill."
                + "Should you require further assistance or have additional tasks to undertake,"
                + "do not hesitate to summon me.\n"
                + String.format("%s\n", task)
                + LINE_BREAK;

        return message;
    }

    /**
     * Prints a message when a task is deleted.
     * @param taskList The taskList object instance.
     * @param task The task object instance.
     */
    public String delete(TaskList taskList, Task task) {
        int numOfTasks = taskList.getListSize();

        String message = LINE_BREAK
                + "\n"
                + "Your directive has been duly noted. The task has been successfully expunged "
                + "from our records. Should there be any further matters requiring attention or if "
                + "new tasks arise, do not hesitate to relay them, and they shall be handled with utmost "
                + "care and efficiency.\n"
                + String.format("%s\n", task)
                + String.format("Now you have %s task(s) in the list.\n", numOfTasks)
                + LINE_BREAK;

        return message;
    }

    /**
     * Prints a message when a task is added to the list.
     * @param taskList The taskList object instance.
     * @param task The task object instance.
     */
    public String add(TaskList taskList, Task task) {
        int numOfTasks = taskList.getListSize();

        String message = LINE_BREAK
                + "Your directive has been duly noted. The tas has been successfully expunged from"
                + "our records. Should there be any further matters requiring attention or if new tasks"
                + "arise, do not hesitate to relay them, and they shall be handled with utmost care"
                + "and efficiency.\n"
                + String.format("added: %s\n", task.toString())
                + String.format("Now you have %s task(s) in the list.\n", numOfTasks)
                + LINE_BREAK;

        return message;
    }

    /**
     * Produces the error message read from the CampusException class.
     * @param e The CampusException class instance.
     */
    public String displayErrorMessage(CampusException e) {
        String message = LINE_BREAK
                + String.format("%s\n", e.getMessage())
                + LINE_BREAK;
        return message;
    }
}
