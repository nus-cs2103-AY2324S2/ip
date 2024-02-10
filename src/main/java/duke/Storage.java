package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles the loading and saving of tasks to and from a file.
 * It provides functionality to read tasks from a file and write tasks back to the file,
 * allowing for persistent storage of tasks across application sessions.
 */
public class Storage {
    private final File f;

    /**
     * Constructs a new Storage object.
     * Initializes a File object to handle read and write operations to the specified file path.
     *
     * @param filePath The file path used for storing and retrieving task data.
     */
    public Storage(String filePath) throws DukeException {
        try {
            this.f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Sorry, file does not exist and cannot be created");
        }
    }

    /**
     * Loads tasks from the file associated with this Storage object.
     * Parses the file content and constructs a list of tasks based on the data.
     *
     * @return ArrayList of Task objects read from the file.
     * @throws DukeException If the file cannot be found or read from.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadedLst = new ArrayList<>();
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (IOException e) {
            throw new DukeException("Sorry, I can't find the file to load from");
        }
        while (s.hasNext()) {
            String[] taskArr = s.nextLine().split(" \\| ");
            switch (taskArr[0]) {
            case "T":
                Task newTodo = Todo.fromSaveFormat(taskArr);
                loadedLst.add(newTodo);
                break;
            case "D":
                Task newDeadline = Deadline.fromSaveFormat(taskArr);
                loadedLst.add(newDeadline);
                break;
            case "E":
                Task newEvent = Event.fromSaveFormat(taskArr);
                loadedLst.add(newEvent);
                break;
            default:
                throw new DukeException("Sorry, I can't understand the file format");
            }
        }
        return loadedLst;
    }

    /**
     * Writes the provided string to the file associated with this Storage object.
     *
     * @param createdFileString The string to be written to the file.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
    public void writeToSavedFile(String createdFileString) throws IOException {
        FileWriter cfw = new FileWriter(f);
        cfw.write(createdFileString);
        cfw.close();
    }

    /**
     * Saves tasks to the file associated with this Storage object.
     * Writes the list of tasks from the provided TaskList into the file in a specified format.
     *
     * @param tl The TaskList containing the tasks to be saved.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
    public void save(TaskList tl) throws IOException {
        StringBuilder createdFileString = new StringBuilder();
        for (Task t : tl.getLst()) {
            createdFileString.append(t.toSaveFormat());
            createdFileString.append("\n");
        }
        writeToSavedFile(createdFileString.toString());
    }
}
