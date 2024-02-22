package lumiere.lumiere;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String path;
    private final File file;

    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
    }

    /**
     * A void method that load tasks from data file into tasklist whenever lumiere
     * is opened and ran
     * 
     * @param list TaskList that tasks will load into.
     * @throws IOException If file cannot be found, created, or loaded.
     */
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

    /**
     * A void method that saves a given task to data file whenever a new task is
     * created.
     * 
     * @param task New task to be added to data file.
     * @throws IOException If the named file exists but is a directory rather than a
     *                     regular file, does not exist but cannot be created, or
     *                     cannot be opened for any other reason.
     */
    public void saveTaskToFile(String task) throws IOException {
        FileWriter writer = new FileWriter(this.path, true);
        writer.write(task + System.lineSeparator());
        writer.close();
    }

    /**
     * A void method that clears the data file so that it can be updated.
     * 
     * @throws IOException If the named file exists but is a directory rather than a
     *                     regular file, does not exist but cannot be created, or
     *                     cannot be opened for any other reason.
     */
    public void clearFile() throws IOException {
        new FileWriter(this.path, false).close();
    }

    /**
     * A void method that saves tasks in given list of tasks to data file.
     * 
     * @param list Java List of Task objects that will be saved one by one into the
     *             data file.
     * @throws IOException If the named file exists but is a directory rather than a
     *                     regular file, does not exist but cannot be created, or
     *                     cannot be opened for any other reason.
     */
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
