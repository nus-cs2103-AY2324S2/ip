package numerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.function.Consumer;
import java.util.stream.Stream;

import numerator.exceptions.storage.LoadingException;
import numerator.exceptions.storage.SavingException;
import numerator.task.TaskList;


/**
 * Interacts with the local filesystem to load and save tasks
 */
public class Storage {
    private final Path filepath;

    /**
     * Constructs a Storage object with the specified filepath
     *
     * @param filepath the path to the file to be loaded and saved
     */

    public Storage(Path filepath) {
        this.filepath = filepath;
    }

    private static Consumer<String> readLineFromString(TaskList taskList) {
        return line -> {
            String[] s = line.split(" \\| ");
            String taskType = s[0];
            boolean isDone = s[1].equals("1");
            String taskDesc = s[2];
            switch (taskType) {
            case "T":
                taskList.addToDo(taskDesc);
                break;
            case "D":
                taskList.addDeadline(taskDesc, s[3]);
                break;
            case "E":
                taskList.addEvent(taskDesc, s[3], s[4]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + taskType);
            }

            if (isDone) {
                taskList.markLastAsDone();
            }

        };
    }

    /**
     * Loads tasks from the file
     *
     * @return a TaskList object containing the tasks loaded from the file
     * @throws LoadingException if there is an error loading the file
     */
    public TaskList loadFile() throws LoadingException {
        TaskList taskList = new TaskList();
        try {
            // Solution below adapted from https://stackoverflow.com/a/41514348
            if (!Files.exists(this.filepath)) {

                // This assertion checks that the filepath does not conflict with existing files or directories
                // in the path hierarchy
                assert !Files.isRegularFile(this.filepath.getParent());

                Files.createDirectories(this.filepath.getParent());
                Files.createFile(this.filepath);
                throw new LoadingException("File does not exist");
            }

            // Solution below adapted from https://www.baeldung.com/reading-file-in-java
            Stream<String> lines = Files.lines(this.filepath);
            lines.forEach(readLineFromString(taskList));

            lines.close();
            return taskList;
        } catch (IndexOutOfBoundsException | IllegalStateException | IOException | DateTimeParseException e) {
            throw new LoadingException("Error loading file");
        }

    }

    /**
     * Saves the tasks to the file
     *
     * @param taskList the TaskList object containing the tasks to be saved
     * @throws SavingException if there is an error saving the file
     */
    public void saveFile(TaskList taskList) throws SavingException {
        try (BufferedWriter bw = Files.newBufferedWriter(this.filepath)) {
            bw.write(taskList.getSavedTasksString());
        } catch (IOException e) {
            throw new SavingException("Error saving file");
        }
    }

}

