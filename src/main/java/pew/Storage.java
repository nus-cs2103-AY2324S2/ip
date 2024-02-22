package pew;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws PewException {
        ArrayList<Task> task_arr = new ArrayList<>();
        int index = 0;

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // Load tasks from file and populate taskArr
                // ... (your existing file reading logic)
                String[] line = scanner.nextLine().split("\\|");
                String type = line[0];
                boolean isDone = line[1].equals("1");
                String description = line[2];
                if (Objects.equals(type, "T")) {
                    task_arr.add(new ToDo(index, description));
                    if (isDone) {
                        task_arr.get(index).mark();
                    }
                    index++;
                } else if (Objects.equals(type, "D")) {
                    String deadline = line[3];
                    task_arr.add(new Deadline(index, description, deadline));
                    if (isDone) {
                        task_arr.get(index).mark();
                    }
                    index++;
                } else if (Objects.equals(type, "E")) {
                    String start = line[3];
                    String end = line[4];
                    task_arr.add(new Event(index, description, start, end));
                    if (isDone) {
                        task_arr.get(index).mark();
                    }
                    index++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new PewException("File not found: " + e);
        }
        return task_arr;
    }

    // Add other methods for saving tasks if needed
}
