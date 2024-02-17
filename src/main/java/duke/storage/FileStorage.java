package duke.storage;

import duke.task.*;
import duke.utils.DukeException;
import duke.utils.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A file based storage implementation.
 */
public class FileStorage implements Storage {
    private final File file;

    public FileStorage(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        File directory = path.getParent().toFile();
        directory.mkdirs();
        this.file = path.toFile();
        this.file.createNewFile();
    }

    /**
     * Saves the tasklist to the file.
     *
     * @param taskList The tasklist to save to the file.
     * @throws DukeException Throws an exception if there is an error saving the file.
     */
    @Override
    public void save(TaskList taskList) throws DukeException {
        file.delete();
        try {
            Writer fileWriter = new FileWriter(file);
            for (Task task : taskList.getTasks()) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Loads the TaskList from the file. The state is quite handwritten but it's a simple format.
     * Optimally, the file storage should implement how the TaskList is saved and loaded.
     * Currently, the saving format is done by the task itself.
     *
     * @return The list of tasks loaded from the file.
     * @throws IOException   In the event of an error reading the file.
     * @throws DukeException If the file is corrupted. This is a recoverable error where the file is overwritten on the
     * next save.
     */
    @Override
    public List<Task> load() throws IOException, DukeException {
        Scanner fileScanner = new Scanner(this.file);
        List<Task> tasks = new ArrayList<>();
        try {
            while (fileScanner.hasNext()) {
                String[] split = fileScanner.nextLine().split(" \\| ");
                TodoState state = split[1].equals("1") ? TodoState.DONE : TodoState.UNDONE;
                switch (split[0]) {
                    case "T": {
                        tasks.add(new Todo(split[2], state));
                        break;
                    }
                    case "D": {
                        tasks.add(new Deadline(split[2], Util.parseDate(split[3]), state));
                        break;
                    }
                    case "E": {
                        tasks.add(new Event(split[2], Util.parseDate(split[3]), Util.parseDate(split[4]), state));
                        break;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("File is corrupted");
        } finally {
            fileScanner.close();
        }
        return tasks;
    }
}
