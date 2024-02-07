package duke;

import duke.DukeException.DukeException;
import duke.tasks.TaskList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    private final Path path;

    public Storage(String path) {
        this.path = Path.of(path);
    }

    public TaskList load() throws DukeException {
        if (Files.notExists(path)) {
            System.out.println("No cache found");
            return new TaskList();
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(path.toString());
                ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);
                TaskList tasks = (TaskList) objInputStream.readObject();

                objInputStream.close();
                fileInputStream.close();
                System.out.println(String.format("Tasks downloaded from %s", path));
                return tasks;
            } catch (IOException | ClassNotFoundException e) {
                try {
                    Files.delete(path);
                } catch (IOException ignored) {
                }
                throw new DukeException(String.format("Unable to download existing tasks"));
            }
        }
    }

    public void save(TaskList tasks) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path.toString());
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

            objOutputStream.writeObject(tasks);

            objOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ignored) {
        }
    }
}