package duke.duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Skibidi;

/**
 * The storage class contains the file paths and methods to load and save task lists.
 *
 * @author Lim Zi Jia
 */
public class Storage {
    /** The path to the saved file's directory. */
    private static Path pathDir;
    /** The path to the saved file. */
    private static Path pathFile;

    public Storage() {}

    /**
     * Constructor for the Storage class.
     */
    public Storage(String dir, String name) {
        String userDir = System.getProperty("user.dir");
        pathDir = Paths.get(userDir + dir);
        pathFile = Paths.get(userDir + dir + name);
    }

    /**
     * Loads the saved file if there is one.
     *
     * @return The saved TaskList if it exists or an empty one if there is none.
     */
    public String load() {
        String s;
        assert pathFile != null : "Your file directory is missing!";
        try {
            List<String> read = Files.readAllLines(pathFile);
            List<Task> list = read.stream()
                    .map(this::stringToTask)
                    .collect(Collectors.toList());

            Duke.tasks = new TaskList(list); // TaskList stored in static Duke because there is only one TaskList
            s = "Your current list:\n";
            s += Duke.tasks.printList();

        } catch (IOException e) {
            s = "You do not have a saved list.\n";
        }

        return s;
    }

    /**
     * Converts strings in the saved file into Tasks.
     *
     * @param s Line from the saved file representing a Task.
     * @return Task that is derived from the saved string representation.
     */
    private Task stringToTask(String s) {
        List<String> taskLst = Arrays.asList(s.split(","));
        Task t = null;
        switch (taskLst.get(0)) {
        case "T": // Using todo constructor Todo(done, name)
            t = new Todo(taskLst.get(1).equals("1"), taskLst.get(2));
            break;
        case "D": // Using deadline constructor Deadline(done, name, by)
            t = new Deadline(taskLst.get(1).equals("1"), taskLst.get(2), taskLst.get(3));
            break;
        case "E": // Using event constructor Event(done, name, from, to)
            t = new Event(taskLst.get(1).equals("1"), taskLst.get(2), taskLst.get(3), taskLst.get(4));
            break;
        default:
        }

        return t;
    }

    /**
     * Writes the saved content to the directory and file paths indicated in the private fields.
     *
     * @param f The path of the file that should be written to.
     * @param tl The task list to be saved.
     * @throws IOException If file at the path does not exist.
     */
    private void writeToFile(Path f, TaskList tl) throws IOException {
        List<String> lines = tl.taskToSavedString();
        Files.write(f, lines);
    }

    /**
     * Saves desired TaskList to memory.
     *
     * @param tl The TaskList to be saved.
     * @throws IOException If directory or file at the paths do not exist.
     */
    public String save(TaskList tl) throws IOException {
        assert pathDir != null : "Your directory is missing!";
        // Create directory if it does not exist
        if (!Files.exists(pathDir)) {
            Files.createDirectories(pathDir);
        }

        assert pathFile != null : "Your file directory is missing!";
        // Delete file if exists because we want to write fresh
        if (Files.exists(pathFile)) {
            Files.delete(pathFile);
        }
        
        Files.createFile(pathFile);
        // Writing to the file
        writeToFile(pathFile, tl);
        return "Your list has been saved to\n" + pathFile;
    }
}
