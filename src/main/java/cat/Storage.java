package cat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A class used to save and load the state of the program to and from the disk.
 */
// Serializing the state was inspired by https://www.baeldung.com/java-serialization
public class Storage {
    private final File file;

    /**
     * Makes a new {@link Storage} instance. This sets up the file for input and output.
     * @param filePath location of the state file
     */
    public Storage(String filePath) {
        assert filePath != null : "File path must not be null";
        file = new File(filePath);
    }

    /**
     * Writes a given list of tasks to the state file. This overwrites the previous data in the state file.
     * @param taskList list of tasks to write to the file
     * @throws IOException when writing to the file fails
     */
    public void writeTaskList(TaskList taskList) throws IOException {
        var output = new FileOutputStream(file);
        try (ObjectOutputStream stream = new ObjectOutputStream(output)) {
            stream.writeObject(taskList);
        }
    }

    /**
     * Reads a list of tasks from the state file.
     * @return the read list of tasks from the file
     * @throws IOException when reading from the file fails
     * @throws ClassNotFoundException when the class read is not a {@link TaskList}
     */
    public TaskList readTaskList() throws IOException, ClassNotFoundException {
        var input = new FileInputStream(file);
        try (ObjectInputStream stream = new ObjectInputStream(input)) {
            return (TaskList) stream.readObject();
        }
    }
}
