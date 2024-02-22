package lumiere.lumiere;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class Storage {
    private final String path;
    private final File file;

    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
    }

    // load tasks from data file into tasklist whenever lumiere is opened and ran
    public void loadTasksFromFile(TaskList list) throws IOException {
        try {
            boolean listCreated = this.file.createNewFile();
            if (!listCreated) {
                Scanner s = new Scanner(this.file);
                while (s.hasNextLine()) {
                    String next = s.nextLine();
                    list.loadTask(Task.parseTask(next)); // load task INTO tasklist
                }
                s.close();
            }
        } catch (IOException err) {
            System.out.println("ERROR LOADING FILE");
        }
    }

    public void saveTaskToFile(String task) throws IOException {
        FileWriter writer = new FileWriter(this.path, true);
        writer.write(task + System.lineSeparator());
        writer.close();
    }

    public void clearFile() throws IOException {
        new FileWriter(this.path, false).close();
    }

    public void saveTasksToFile(List<Task> list) throws IOException {
        // clear file first
        clearFile();
        for (Task task : list) {
            // change task to string
            String line = "";
            if (task instanceof Todo) {
                line = "T | " + task.isMarked() + " | " + task.getItem();
            } else if (task instanceof Deadline) {
                Deadline t = (Deadline) task;
                line = "D | " + t.isMarked() + " | " + t.getItem() + " | " + t.getbyWhen();
            } else if (task instanceof Event) {
                Event t = (Event) task;
                line = "E | " + t.isMarked() + " | " + t.getItem() + " | " + t.getStart() + " | " + t.getEnd();
            }
            saveTaskToFile(line);
        }
    }
}
