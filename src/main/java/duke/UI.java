package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import duke.task.Task;

/**
 * UI class handles interactions with the user.
 */

public class UI {
    public UI() {
    }


    public String startMsg() {
        return "Greetings friend! I am Datuk\n"
                + "How can I serve you today? ^_^' \n";
    }

    public String byeMsg() throws IOException {
        return "Farewell!\n"
                + "Please use the x on the top right corner to exit!";
    }

    public String showError(DukeException de) {
        return de.toString();
    }

    public String printList(ArrayList<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("These are all your tasks:\n");

        if (taskList.isEmpty()) {
            sb.append("\tOh noes! The list is empty! :(");
            return sb.toString();
        }

        for (int i = 0; i < taskList.size(); i++) {
            sb.append("\t" + (i + 1) + ". " + taskList.get(i) + "\n");
        }

        return sb.toString();
    }

    public String printFindList(ArrayList<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        if (taskList.isEmpty()) {
            sb.append("\tOh noes! The list is empty! :(");
            return sb.toString();
        }

        sb.append("These are all the tasks related:\n");

        for (int i = 0; i < taskList.size(); i++) {
            sb.append("\t" + (i + 1) + ". " + taskList.get(i) + "\n");
        }

        return sb.toString();
    }

    public String showAddMsg(Task t, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Understood. Added the following:\n");
        sb.append("\t " + t + "\n");
        sb.append("You have " + size + " remaining tasks.");

        return sb.toString();
    }

    public String showDeleteMsg(Task t, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Removed the following: \n");
        sb.append("\t" + t + "\n");
        sb.append((size-1) + " tasks remaining.");
        return sb.toString();
    }

    public String showMark(String desc) {
        StringBuilder sb = new StringBuilder();
        sb.append("I have set this task < " + desc + " > as completed." );
        return sb.toString();
    }

    public String showUnmark(String desc) {
        StringBuilder sb = new StringBuilder();
        sb.append("I have set this task < " + desc + " > as incomplete." );
        return sb.toString();
    }
}
