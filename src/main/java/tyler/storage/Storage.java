package tyler.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import tyler.task.Deadline;
import tyler.task.Event;
import tyler.task.Task;
import tyler.task.TaskList;
import tyler.task.Todo;

/**
 * Storage handle the loadTask from local and saveTask to local.
 */
public class Storage {

    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the task from local given specific file path.
     *
     * @return             A Task ArrayList.
     * @throws IOException The file or directory may not exist.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> loadTask = new ArrayList<>();
        try {
            File file = new File(this.filePath);
            File parentDirectory = file.getParentFile();
            if (!parentDirectory.exists()) {
                parentDirectory.mkdirs();
            }
            Scanner load = new Scanner(file);
            while (load.hasNextLine()) {
                String line = load.nextLine();
                String[] input = line.split(" \\| ");
                String type = input[0];
                boolean isDone = input[1].equals("1");
                if (type.equals("T")) {
                    loadTask.add(new Todo(input[2], isDone));
                } else if (type.equals("D")) {
                    loadTask.add(new Deadline(input[2], LocalDateTime.parse(input[3], OUTPUT_DATE_FORMAT), isDone));
                } else if (type.equals("E")) {
                    LocalDateTime start = LocalDateTime.parse(input[3], OUTPUT_DATE_FORMAT);
                    LocalDateTime end = LocalDateTime.parse(input[4], OUTPUT_DATE_FORMAT);
                    loadTask.add(new Event(input[2], start, end, isDone));
                }
            }
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            file.createNewFile();
        }
        return loadTask;
    }

    /**
     * Save the taskList to local given the file path with a specific format.
     *
     * @param tasks taskList that needed to be saved to local.
     */
    public void saveTask(TaskList tasks) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks.getList()) {
                bw.write(task.saveToFileString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            // Nothing here
        }
    }

}
