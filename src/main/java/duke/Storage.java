package duke;

import tasks.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The storage class contains the file paths and methods to load and save task lists.
 *
 * @author Lim Zi Jia
 */
public class Storage {
    /** The path to the saved file's directory. */
    private static Path PATH_DIR;
    /** The path to the saved file. */
    private static Path PATH_FILE;

    public Storage() {}

    public Storage(String dir, String name) {
        String userDir = System.getProperty("user.dir");
        PATH_DIR = Paths.get(userDir + dir);
        PATH_FILE = Paths.get(userDir + dir + name);
    }

    /**
     * Loads the saved file if there is one.
     *
     * @return The saved TaskList if it exists or an empty one if there is none.
     */
    public TaskList load() {
        try {
            List<String> read = Files.readAllLines(PATH_FILE);
            List<Task> list = read.stream()
                    .map(this::stringToTask)
                    .collect(Collectors.toList());
            TaskList tl = new TaskList(list);
            System.out.println("Your current list:");
            tl.printList();
            Ui.printLine();
            return tl;
        } catch (IOException e) {
            System.out.println("You do not have a saved list.");
            Ui.printLine();
        }
        return new TaskList();
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
            case "T":
                t = new Todo(taskLst.get(1).equals("1"), taskLst.get(2));
                break;
            case "D":
                t = new Deadline(taskLst.get(1).equals("1"), taskLst.get(2), taskLst.get(3));
                break;
            case "E":
                t = new Event(taskLst.get(1).equals("1"), taskLst.get(2), taskLst.get(3), taskLst.get(4));
                break;
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
    public void save(TaskList tl) throws IOException {
        // Check if the directory exists
        if (!Files.exists(PATH_DIR)) {
            Files.createDirectories(PATH_DIR);
        }
        // Check if the save file exists
        if (Files.exists(PATH_FILE)) {
            Files.delete(PATH_FILE);
        }
        Files.createFile(PATH_FILE);
        // Writing to the file
        writeToFile(PATH_FILE, tl);
        System.out.println("Your list has been saved to " + PATH_FILE);
    }
}
