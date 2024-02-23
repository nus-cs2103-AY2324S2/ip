package duke.ui;

import java.util.ArrayList;

public class UIFormatter {
    public static final String INDENT = "     ";

    private static final String WELCOME_MESSAGE = String.join(
            "\n",
            "Hello! I'm DEREK",
            INDENT + "What can I do for you?");

    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public static String formatResponse(String msg) {
        return msg;
    }

    public static String formatWelcome() {
        return WELCOME_MESSAGE;
    }

    public static String formatExit() {
        return EXIT_MESSAGE;
    }

    public static String formatTaskAddedResponse(String msg, int numTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Added Task:\n");
        sb.append(INDENT + msg + "\n");
        sb.append("Now you have " + numTasks + " tasks in the list.");
        return sb.toString();
    }

    public static String formatTaskDeletedResponse(String msg, int numTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Deleted Task:\n");
        sb.append("  " + msg + "\n");
        sb.append("Now you have " + numTasks + " tasks in the list.");
        return sb.toString();
    }

    public static String formatTasksByIndicesResponse(ArrayList<String> taskRepresentations) {
        StringBuilder sb = new StringBuilder();
        if (taskRepresentations.size() == 0) {
            sb.append("No matching tasks found.");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (String t : taskRepresentations) {
                sb.append(t + "\n");
            }
        }
        return sb.toString();
    }

    public static String formatErrorResponse(String error) {
        StringBuilder sb = new StringBuilder();
        String[] lines = error.split("\n");
        sb.append("Error: " + lines[0] + "\n");
        for (int i = 1; i < lines.length; i++) {
            sb.append(lines[i]);
        }
        return sb.toString();
    }
}
