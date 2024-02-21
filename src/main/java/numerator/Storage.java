package numerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import numerator.exceptions.storage.LoadingException;
import numerator.exceptions.storage.SavingException;
import numerator.task.Deadline;
import numerator.task.Event;
import numerator.task.Task;
import numerator.task.TaskList;
import numerator.task.ToDo;


/**
 * Interacts with the local filesystem to load and save tasks.
 */
public class Storage {
    private final Path filepath;

    /**
     * Constructs a Storage object with the specified filepath.
     *
     * @param filepath the path to the file to be loaded and saved.
     */

    public Storage(Path filepath) {
        this.filepath = filepath;
    }


    private static Consumer<String> readLineFromString(TaskList taskList) {
        return line -> {
            String[] strings = line.split(" \\| ");
            String taskType = strings[0];
            boolean isDone = strings[1].equals("1");
            String taskDesc = strings[2];
            String tagsString = strings[3];
            Task t;

            switch (taskType) {
            case "T":
                t = new ToDo(taskDesc, isDone, tagStringToSet(tagsString));
                break;
            case "D":
                String byString = strings[4];
                t = new Deadline(taskDesc, byString, isDone, tagStringToSet(tagsString));
                break;
            case "E":
                String fromString = strings[4];
                String toString = strings[5];
                t = new Event(taskDesc, fromString, toString, isDone, tagStringToSet(tagsString));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + taskType);
            }
            taskList.addTask(t);
        };
    }


    private static Set<String> tagStringToSet(String tagString) {
        String[] tags = tagString.split(" ");
        return Set.of(tags);
    }

    /**
     * Loads tasks from the file.
     *
     * @return a TaskList object containing the tasks loaded from the file.
     * @throws LoadingException if there is an error loading the file.
     */
    public TaskList loadFile() throws LoadingException {
        TaskList taskList = new TaskList();

        if (startAnew()) {
            throw new LoadingException("Error loading file");
        }

        // Solution below adapted from https://www.baeldung.com/reading-file-in-java
        // Solution below adapted from https://stackoverflow.com/a/41514348
        try (Stream<String> lines = Files.lines(this.filepath)) {
            lines.forEach(readLineFromString(taskList));
            return taskList;
        } catch (IndexOutOfBoundsException | IllegalStateException | IOException | DateTimeParseException e) {
            throw new LoadingException("Error loading file");
        }
    }

    private boolean startAnew() throws LoadingException {
        try {
            if (!Files.exists(this.filepath)) {

                // This assertion checks that the filepath does not conflict with existing files or directories
                // in the path hierarchy
                assert !Files.isRegularFile(this.filepath.getParent());

                Files.createDirectories(this.filepath.getParent());
                Files.createFile(this.filepath);
                return true;
            }
        } catch (IOException e) {
            throw new LoadingException("Error loading file");
        }
        return false;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param taskList the TaskList object containing the tasks to be saved.
     * @throws SavingException if there is an error saving the file.
     */
    public void saveFile(TaskList taskList) throws SavingException {
        try (BufferedWriter bw = Files.newBufferedWriter(this.filepath)) {
            bw.write(taskList.getSavedTasksString());
        } catch (IOException e) {
            throw new SavingException("Error saving file");
        }
    }

}

