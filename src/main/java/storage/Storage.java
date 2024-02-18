package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 *  The Storage class manages the reading and writing of tasks to a file.
 */
public class Storage {

    private final String path;

    /**
     * Creates a new Storage object with the specified filepath.
     *
     * @param filePath Relative file path to store data.
     */
    public Storage(String filePath) {
        this.path = filePath;
    }

    /**
     * Encodes and appends a list of tasks to the file, each represented as a line in the file.
     *
     * @param tasks The TaskList containing tasks to be appended to the file.
     * @throws IOException The TaskList containing tasks to be appended to the file.
     */
    public void appendToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (Task task : tasks) {
            String textToAppend = "";
            if (task instanceof Todo) {
                textToAppend = task.getSymbol() + "/" + task.getStatus() + "/"
                        + task.getDescription() + "\n";
            }
            if (task instanceof Deadline) {
                textToAppend = task.getSymbol() + "/" + task.getStatus() + "/" + task.getDescription()
                        + "/" + task.getBy() + "\n";
            }
            if (task instanceof Event) {
                textToAppend = task.getSymbol() + "/" + task.getStatus()
                        + "/" + task.getDescription() + "/" + task.getStart() + "/" + task.getEnd() + "\n";
            }
            fw.write(textToAppend);
        }
        fw.close();
    }

    /**
     * Decodes the file and populates the task list with the stored tasks.
     *
     * @return Task list stored on local hard drive.
     * @throws FileNotFoundException If file does not exist on the local repository.
     */
    public TaskList loadFile() throws FileNotFoundException {
        File f = new File(path);
        if (!f.exists()) {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating directory: " + e.getMessage());
            }
        }
        TaskList taskList = new TaskList();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] inputs = s.nextLine().split("/");
            Task task;
            if ("[T]".equals(inputs[0])) {
                task = new Todo(inputs[2]);
            } else if ("[D]".equals(inputs[0])) {
                task = new Deadline(inputs[2], LocalDate.parse(inputs[3]));
            } else if ("[E]".equals(inputs[0])) {
                task = new Event(inputs[2], LocalDate.parse(inputs[3]), LocalDate.parse(inputs[4]));
            } else {
                task = new Task(inputs[0], inputs[2]);
            }

            if (inputs[1].equals("1")) {
                task.mark();
            }
            taskList.addTasks(task);
        }
        return taskList;
    }
}
