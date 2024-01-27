package duke.parser;

import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser {
    private static final String separator = ",";

    public static ArrayList<Task> readFile(File f) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            String[] parts = s.split(separator);
            switch (parts[0]) {
            case "T":
                Task t = new TodoTask(parts[2], parts[1]);
                tasks.add(t);
                break;
            case "D":
                Task d = new DeadlineTask(parts[2], parts[1], parts[3]);
                tasks.add(d);
                break;
            case "E":
                Task e = new EventTask(parts[2], parts[1], parts[3], parts[4]);
                tasks.add(e);
                break;
            default:
                break;
            }
        }
        scanner.close();
        return tasks;
    }
}
