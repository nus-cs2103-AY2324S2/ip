package chatbot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the UI controller.
 */
public class Response {
    private static final Response GREETING = new Response(new ArrayList<>(List.of(
            "Hello! My name is\n"
            + "    ____  __                 \n"
            + "   / __ \\/ /___ _____  ____ _\n"
            + "  / /_/ / / __ `/ __ \\/ __ `/\n"
            + " / ____/ / /_/ / / / / /_/ / \n"
            + "/_/   /_/\\__,_/_/ /_/\\__,_/  \n",
            "What can I do for you?"
    )), 0, null);

    private static final Response PARTING = new Response(Collections.singletonList(
        "See you next time!"
    ), 2, null);

    private final List<String> messages;
    private final String errorMessage;
    /**
     * 0 for normal, 1 for error, 2 for breaking
     */
    private final int messageType;

    private Response(List<String> message, int messageType, String er) {
        this.messages = message;
        this.messageType = messageType;
        this.errorMessage = er;
    }

    public static Response displayGreeting() {
        return Response.GREETING;
    }

    public static Response displayParting() {
        return Response.PARTING;
    }

    /**
     * Displays a formatted task list.
     *
     * @param tl The task list to display.
     */
    public static Response displayList(TaskList tl) {
        String message = tl.toString().isEmpty()
                ? "You have no tasks right now, add some!"
                : "You've added the following tasks so far:\n" + tl;
        return new Response(Collections.singletonList(message), 0, null);
    }

    /**
     * Display a notification after user adds a task.
     *
     * @param tl The task list.
     * @param t  The added task.
     */
    public static Response displayAdd(TaskList tl, Task t) {
        String message = "Got it. I've added this task:\n"
                + ">> " + t + "\n"
                + "You now have " + tl.getSize() + " tasks in the list.";
        return new Response(Collections.singletonList(message), 0, null);
    }

    /**
     * Displays a notification after user marks a task.
     *
     * @param i The index of the marked task.
     */
    public static Response displayMark(int i) {
        return new Response(Collections.singletonList("Task " + i + " marked as done"), 0, null);
    }

    /**
     * Displays a notification after user unmarks a task.
     *
     * @param i The index of the unmarked task.
     */
    public static Response displayUnmark(int i) {
        return new Response(Collections.singletonList("Task " + i + " marked as undone"), 0, null);
    }

    /**
     * Displays a notification after user deletes a task.
     *
     * @param tl The task list.
     * @param t  The deleted task.
     */
    public static Response displayDelete(TaskList tl, Task t) {
        String message = "Got it. I've removed this task:\n"
                + ">> " + t + "\n"
                + "You now have " + tl.getSize() + " tasks in the list.\n";
        return new Response(Collections.singletonList(message), 0, null);
    }

    /**
     * Displays the results after user searches for tasks.
     *
     * @param tl The results list.
     */
    public static Response displayFind(ArrayList<Task> tl) {
        if (tl.isEmpty()) {
            return new Response(Collections.singletonList("No tasks found."), 0, null);
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tl.size(); i++) {
            sb.append(i + 1).append(". ").append(tl.get(i)).append("\n");
        }
        return new Response(Collections.singletonList(sb.toString()), 0, null);
    }

    /**
     * Displays an error notification.
     *
     * @param e The error.
     */
    public static Response displayError(Exception e) {
        return new Response(null, 1, e.getMessage());
    }

    public boolean isError() {
        return this.messageType == 1;
    }

    public boolean isBreaking() {
        return this.messageType == 2;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
