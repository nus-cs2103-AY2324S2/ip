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
        ArrayList<Task> taskArr = new ArrayList<>();
        int index = 0;
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("\\|");
                String type = line[0];
                boolean isDone = line[1].equals("1");
                String description = line[2];
                Task newTask = createTask(type, index, description, line, isDone);
                taskArr.add(newTask);
                index++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new PewException("File not found: " + e);
        }
        return taskArr;
    }

    private Task createTask(String type, int index, String description, String[] line, boolean isDone) {
        if (type.equals("T")) {
            Task newTask = new ToDo(index, description);
            if (isDone) {
                newTask.mark();
            }
            return newTask;
        } else if (type.equals("D")) {
            String deadline = line[3];
            Task newTask = new Deadline(index, description, deadline);
            if (isDone) {
                newTask.mark();
            }
            return newTask;
        } else if (type.equals("E")) {
            String start = line[3];
            String end = line[4];
            Task newTask = new Event(index, description, start, end);
            if (isDone) {
                newTask.mark();
            }
            return newTask;
        } else {
            throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }

    // Add other methods for saving tasks if needed
}
