package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {

    private final String path;

    public Storage(String path) {
        assert path != null : "path cannot be null";
        this.path = path;
    }

    public TaskList loadTasks() throws DukeException {
        TaskList tasks = new TaskList();
        File f = new File(path);
        Scanner scanner = null;
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new DukeException("IO error when creating data.txt: " + e.getMessage());
            }
        }
        try {
            scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                tasks.add(Task.fromStorageString(str));
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("IO error: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return tasks;
    }

    public void writeTasks(TaskList tasks) throws DukeException {
        assert tasks != null : "tasks cannot be null";
        PrintWriter pw = null;
        File f = new File(path);
        String toWrite = tasks.toStorageString();

        try {
            pw = new PrintWriter(new File(path));
            pw.write(toWrite);
            pw.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("IO Error encountered while writing: " + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
