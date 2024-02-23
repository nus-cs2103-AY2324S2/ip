package rochin;

import java.util.List;

public class Response extends Ui {
    private final StringBuilder buffer;

    public Response () {
        this.buffer = new StringBuilder();
    }

    /**
     * Display the welcome message.
     */
    @Override
    public void showWelcomeMessage() {
        this.buffer.append("Hello! I'm Rochin.\n");
        this.buffer.append("What can I do for you?\n");
        this.buffer.append("____________________________________________________________\n");

    }

    /**
     * Display the goodbye message.
     */
    @Override
    public void showGoodbyeMessage() {
        this.buffer.append("Bye. Hope to see you again soon!\n");
    }


    /**
     * Display an error message for loading tasks.
     */
    @Override
    public void showLoadingError() {
        this.buffer.append("Failed to load tasks. Creating a new task list.\n");
    }

    /**
     * Display an error message for saving tasks.
     */
    @Override
    public void showSavingError() {
        this.buffer.append("Failed to save tasks.\n");
    }

    /**
     * Display a generic error message with the provided errorMessage.
     *
     * @param errorMessage The error message to be displayed.
     */
    @Override
    public void showError(String errorMessage) {
        this.buffer.append(errorMessage);
    }

    /**
     * Display an unknown command error message.
     */
    @Override
    public void showUnknownCommandError() {
        this.buffer.append("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    /**
     * Display an invalid command error message.
     */
    /**
    public void showInvalidCommandError() {
        System.out.println("Invalid command. Please enter a valid command.\n");
    }
     */

    /**
     * Display the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    @Override
    public void showTaskList(List<Task> tasks) {
        this.buffer.append("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            this.buffer.append((i + 1) + "." + tasks.get(i)).append("\n");
        }
    }

    /**
     * Display a message confirming the addition of a task.
     *
     * @param tasks The updated list of tasks.
     */
    @Override
    public void showTaskAddedMessage(List<Task> tasks) {
        this.buffer.append("Got it. I've added this task:\n");
        this.buffer.append(tasks.get(tasks.size() - 1));
        this.buffer.append("\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Display a message confirming the deletion of a task.
     *
     * @param tasks The updated list of tasks.
     */
    @Override
    public void showTaskDeletedMessage(List<Task> tasks) {
        this.buffer.append("Noted. I've removed this task:\n");
        this.buffer.append(tasks.get(tasks.size() - 1));
        this.buffer.append("\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Display a message confirming the marking of a task as done.
     *
     * @param tasks The updated list of tasks.
     */
    @Override
    public void showTaskMarkedAsDoneMessage(List<Task> tasks) {
        this.buffer.append("Nice! I've marked this task as done:\n");
    }

    /**
     * Display a message confirming the marking of a task as not done.
     *
     * @param tasks The updated list of tasks.
     */
    @Override
    public void showTaskUnmarkedAsDoneMessage(List<Task> tasks) {
        this.buffer.append("OK, I've marked this task as not done yet:\n");
    }

    @Override
    public String getTextOutput() {
        String output = this.buffer.toString();
        this.buffer.delete(0, this.buffer.length());
        return output;
    }
}
