package lucky.ui;

import java.util.ArrayList;

import lucky.tasks.Task;

public class Ui {
    public Ui() {
    }

    /**
     * Returns the output of the chatbot in formatted form.
     *
     * @param msg The message to output.
     * @return Returns a String, containing the response.
     */
    public static String printOutput(String... msg) {
        StringBuilder sb = new StringBuilder();
        for (String string : msg) {
            sb.append(string).append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints the list of tasks in the provided ArrayList of tasks.
     *
     * @param tasks ArrayList of task to output.
     * @return A string which contains the response.
     */
    public static String printList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        int i = 1;
        for (Task task : tasks) {
            sb.append(i).append(".").append(task.toString()).append("\n");
            i++;
        }
        return sb.toString();
    }

    /**
     * Reads the command input of the user and returns it.
     *
     * @return An array of String, containing the command.
     */
    public String[] readCommand(String input) {
        String[] inputArray = input.trim().split(" ", 2);
        assert inputArray.length <= 2 : "Input array should always of size < 2 after splitting";

        return inputArray;
    }
}
