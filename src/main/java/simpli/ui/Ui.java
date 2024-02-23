package simpli.ui;

import simpli.tasks.Task;
import simpli.tasks.TaskList;

/**
 * Handles messages to be shown to the user.
 */
public class Ui {
    /**
     * Returns the greet message.
     *
     * @return greeting message.
     */
    public String getGreetingMessage() {
        return "Hello! I'm SIMP-LI\nHow can I simp-lify your life?";
    }

    /**
     * Returns the goodbye message.
     *
     * @return goodbye message.
     */
    public String getByeMessage() {
        return "Hope to simp for you again.";
    }

    /**
     * Returns a string representation of the added task.
     *
     * @param addedTask task that was added to the chatbot.
     * @return added task string representation.
     */
    public String getAddedTaskMessage(Task addedTask) {
        return "Great I have added the following task:\n"
                + "\t"
                + addedTask;
    }

    /**
     * Returns a string representation of the deleted task.
     *
     * @param deletedTask task that was deleted from the chatbot.
     * @return deleted task string representation.
     */
    public String getDeletedTaskMessage(Task deletedTask) {
        return "Noted I have deleted the following task:\n" + deletedTask;
    }

    /**
     * Returns a string representation of the searched tasks.
     *
     * @param foundTasks tasks that was found from the search.
     * @return searched tasks string representation.
     */
    public String getFoundTasks(TaskList foundTasks) {
        return "I have found these tasks:\n" + foundTasks;
    }

    /**
     * Returns a string representation of all the tasks.
     *
     * @param taskList all the tasks added.
     * @return all the tasks' string representation.
     */
    public String getListTasksMessage(TaskList taskList) {
        return "You currently have these tasks:\n" + taskList;
    }

    /**
     * Returns a string representation of the marked task.
     *
     * @param task that was marked as completed.
     * @return marked task string representation.
     */
    public String getMarkedTaskMessage(Task task) {
        return "The following task has been marked:\n" + task;
    }

    /**
     * Returns a string representation of the unmarked task.
     *
     * @param task that was unmarked as completed.
     * @return unmarked task string representation.
     */
    public String getUnmarkedTaskMessage(Task task) {
        return "The following task has been unmarked:\n" + task;
    }
}
