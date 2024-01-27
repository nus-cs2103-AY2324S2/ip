package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    public void writeTaskList(TaskList taskList) throws IOException {
        var output = new FileOutputStream(file);
        try (ObjectOutputStream stream = new ObjectOutputStream(output)) {
            stream.writeObject(taskList);
        }
    }

    public TaskList readTaskList() throws IOException, ClassNotFoundException {
        var input = new FileInputStream(file);
        try (ObjectInputStream stream = new ObjectInputStream(input)) {
            return (TaskList) stream.readObject();
        }
    }
}
