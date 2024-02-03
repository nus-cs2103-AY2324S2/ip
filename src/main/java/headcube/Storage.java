package headcube;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
/**
 * Handles storage operations for the HeadCube application, including saving and loading tasks.
 */
public class Storage {
    private Ui ui;

    /**
     * Constructor a Storage object with a reference to the UI for displaying messages.
     *
     * @param ui The UI to display messages.
     */
    public Storage(Ui ui) {
        this.ui = ui;
    }

    /**
     * Saves the current list of tasks to a file.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void save(TaskList taskList) {
        try {
            String directoryPath = "./data";
            String filePath = directoryPath + "/HeadCube.txt";
            Files.createDirectories(Paths.get(directoryPath));
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            for (Task task : taskList.getList()) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
            fw.close();
            ui.saveMessage();
        } catch (IOException e) {
            ui.error("An error occurred while saving tasks" + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file into the provided task list.
     *
     * @param taskList The task list to which loaded tasks will be added.
     */
    public void load(TaskList taskList) {
        File file = new File("./data/HeadCube.txt");
        if (!file.exists()) {
            return;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parse(line);
                if (task != null) {
                    taskList.add(task);

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            ui.error("No tasks to load" + e.getMessage());
        }
    }

    /**
     * Parses a line from the storage file into a Task object.
     *
     * @param input The line to be parsed.
     * @return The parsed Task object, or null if the line is invalid.
     */
    private static Task parse(String input) {
        String[] parts = input.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String event = parts[0];
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task = null;

        if ("T".equals(event)) {
            task = new ToDos(description);
        } else if ("D".equals(event)) {
            if (parts.length > 3) {
                String by = parts[3].replace("(by: ", "").replace(")", "");
                task = new Deadlines(description, by);
            }
        } else {
            if (parts.length > 3) {
                String timeInfo = parts[3].replace("(from: ", "").replace(")", "");
                String[] times = timeInfo.split(" to: ");
                String start = times[0];
                String end = times.length > 1 ? times[1] : "";
                task = new Events(description, start, end);
            }
        }

        if (isDone) {
            task.done();
        }
        return task;
    }
}
