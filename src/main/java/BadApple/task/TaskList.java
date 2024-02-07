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
    public static void listTasks(BufferedReader bufferedReader) throws IOException {
        while (bufferedReader.ready()) {
            System.out.println(bufferedReader.readLine());
        }
    }

    public static void filterTasks(String filter) {
        for (Task t : tasks) {
            if (t.brief().toLowerCase().contains(filter.toLowerCase())) {
                System.out.println(t);
            }
        }
    }
}
