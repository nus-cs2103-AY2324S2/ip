package eggy.response;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import eggy.task.Task;
import eggy.task.TaskList;

/**
 * The response of the chatbot.
 */
public class Response {
    /** The chatbot's response. */
    private String response;

    /**
     * Gets the chatbot's response.
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * Sets the welcome message as the chatbot's response.
     *
     * @param name The name of the chatbot.
     */
    public void setWelcomeResponse(String name) {
        this.response = "Hello! I'm " + name + "\uD83E\uDD5A.\nWhat can I do for you?";
    }

    /**
     * Sets the goodbye message as the chatbot's response.
     */
    public void setGoodbyeResponse() {
        this.response = "Bye \uD83D\uDC4B. Hope to see you again soon!";
    }

    /**
     * Sets the task added message as the chatbot's response.
     *
     * @param task The task added.
     * @param taskCount The number of tasks in the task list.
     */
    public void setTaskAddedResponse(Task task, int taskCount) {
        this.response = "Got it. I've added this task:\n  " + task.toString()
                + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Sets the task removed message as the chatbot's response.
     *
     * @param task The task removed.
     * @param taskCount The number of tasks in the task list.
     */
    public void setTaskRemovedResponse(Task task, int taskCount) {
        this.response = "Noted. I've removed this task:\n  " + task.toString()
                + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Sets the task marked as done message as the chatbot's response.
     *
     * @param task The task marked as done.
     */
    public void setTaskMarkedDoneResponse(Task task) {
        this.response = "Nice! I've marked this task as done:\n  " + task.toString();
    }

    /**
     * Sets the task marked as not done message as the chatbot's response.
     *
     * @param task The task marked as not done.
     */
    public void setTaskUnmarkedDoneResponse(Task task) {
        this.response = "OK, I've marked this task as not done yet:\n  " + task.toString();
    }

    /**
     * Sets the task list as the chatbot's response.
     *
     * @param tasks The task list.
     */
    public void setTaskListResponse(TaskList tasks) {
        this.response = "Here are the tasks in your list:\n" + tasks.toString();
    }

    /**
     * Sets the task list empty message as the chatbot's response.
     */
    public void setTaskListEmptyResponse() {
        this.response = "There are no tasks in your list.";
    }

    /**
     * Sets the matching tasks as the chatbot's response.
     *
     * @param tasks The matching tasks.
     */
    public void setMatchingTasksResponse(List<Task> tasks) {
        if (tasks.isEmpty()) {
            this.response = "There are no matching tasks in your list.";
        } else {
            this.response = "Here are the matching tasks in your list:\n";
            this.response += IntStream.range(0, tasks.size())
                    .mapToObj(i -> "  " + (i + 1) + "." + tasks.get(i))
                    .collect(Collectors.joining("\n"));
        }
    }

    /**
     * Sets the exception message as the chatbot's response.
     *
     * @param message The exception message.
     */
    public void setExceptionResponse(String message) {
        this.response = message;
    }
}
