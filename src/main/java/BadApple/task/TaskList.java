package BadApple.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>(); // Globally accessible Tasks in memory.
    public static void listTasks(BufferedReader bufferedReader) throws IOException {
        while (bufferedReader.ready()) {
            System.out.println(bufferedReader.readLine());
        }
    }
}
