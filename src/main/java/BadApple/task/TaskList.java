package BadApple.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles Keep Track of tasks that are in the program.
 * Able to list out what tasks exist currently in the Drive.
 */
public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>(); // Globally accessible Tasks in memory.

    public static String listTasks(BufferedReader bufferedReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        while (bufferedReader.ready()) {
            stringBuilder.append(bufferedReader.readLine()).append("\n");
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    public static String filterTasks(String filter) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task t : tasks) {
            if (t.brief().toLowerCase().contains(filter.toLowerCase())) {
                stringBuilder.append(t).append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
