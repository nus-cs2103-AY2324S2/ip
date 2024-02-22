package sam;

import sam.task.Deadline;
import sam.task.Event;
import sam.task.Task;
import sam.task.ToDo;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String FILE_PATH;

    public Storage(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }
    public ArrayList<Task> load() throws SamException, IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;

        while ((line = reader.readLine()) != null) {
            Task task = createTaskFromLine(line);
            if (task != null) {
                taskList.add(task);
            }
        }

        reader.close();
        return taskList;
    }
    public void save(TaskList tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

            for (String item : tasks.getFileStrings()) {
                writer.write(item + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

    private static Task createTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            System.out.println("Skipped invalid line in data file: " + line);
            return null;
        }

        String taskType = parts[0];
        int isDone = Integer.parseInt(parts[1]);
        String description = parts[2];

        Task task;

        switch (taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                String by = parts.length > 3 ? parts[3] : "";
                task = new Deadline(description, by);
                break;
            case "E":
                String fromTo = parts.length > 3 ? parts[3] : "";
                String[] fromToArray = fromTo.split(" to ");
                String from = fromToArray.length > 0 ? fromToArray[0] : "";
                String to = fromToArray.length > 1 ? fromToArray[1] : "";
                task = new Event(description, from, to);
                break;
            default:
                System.out.println("Skipped unknown task type in data file: " + taskType);
                return null;
        }

        if (isDone == 1) {
            task.markAsDone();
        }
        return task;
    }
}
