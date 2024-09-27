package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import core.Ui;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * The Storage class handles the loading and saving of tasks to a file.
 */
public class Storage {
    private String filePath;
    private Ui ui;

    /**
     * Constructs a Storage instance with the specified file path and user interface.
     *
     * @param filePath The path to the data file where tasks are stored.
     * @param ui       The user interface to display error messages.
     */
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
        ensureFileExists();
    }

    /**
     * Ensures that the data file exists; if not, it creates the file.
     */
    private void ensureFileExists() {
        try {
            Path path = Paths.get(filePath);
            Path parentDir = path.getParent();

            if (parentDir != null) {
                Files.createDirectories(parentDir);
            }

            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            ui.showErrorCreatingFile();
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Saves a list of tasks to the data file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void save(List<Task> tasks) {
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            ui.showSavingError();
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the data file and returns them as a list.
     *
     * @return A list of tasks loaded from the data file.
     */
    public List<Task> load() {
        List<Task> loadedTasks = new ArrayList<>();
        Path path = Paths.get(filePath);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" \\| ");
                    Task task = null;

                    switch (parts[0]) {
                    case "T":
                        task = new ToDo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], LocalDate.parse(parts[4]));
                        break;
                    case "E":
                        task = new Event(parts[2], LocalDate.parse(parts[4]), LocalDate.parse(parts[5]));
                        break;
                    default:
                        ui.showLoadingError("corrupted");
                    }

                    if (task != null) {
                        if (parts[1].equals("1")) {
                            task.mark();
                        }
                        task.setTag(parts[3]);
                        loadedTasks.add(task);
                    }
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    ui.showLoadingError("corrupted");
                }
            }
        } catch (FileNotFoundException e) {
            ui.showLoadingError("file not found");
        } catch (IOException e) {
            ui.showLoadingError("cannot read the task file");
            e.printStackTrace();
        }

        return loadedTasks;
    }
}
