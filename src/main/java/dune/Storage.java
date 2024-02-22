package dune;// deals with loading tasks from the file and saving tasks in the file
import dune.task.*;
import dune.DuneException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.List;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String dir;
    private Path filePath;
    private String[] taskTypes = new String[] {"T", "D", "E"};

    private String errorTaskType = "Invalid task type";

    public Storage() {
        this.dir = System.getProperty("user.dir");
        this.filePath = Paths.get(dir, "data", "dune.txt");
    }

    /**
     * Loads tasks from the file.
     *
     * @param tasks
     */
    public void loadTasks(TaskList tasks) {
        boolean fileExists = java.nio.file.Files.exists(this.filePath);

        if (fileExists) {
            try {
                // 1 line -> 1 item in the list
                List<String> lines = Files.readAllLines(this.filePath);
                for (String line : lines) {
                    // remove empty lines
                    if (line.trim().equals("")) {
                        continue;
                    }
                    tasks.addTask(convertLineToTask(line));
                }
            } catch (IOException i) {
                System.out.println("Error reading from file");
            } catch (DuneException d) {
                System.out.println(d.getMessage());
            } catch (IndexOutOfBoundsException i) {
                System.out.println("Task was formatted incorrectly in file");
            }
        } else {
            createFile(this.filePath);
        }
        assert java.nio.file.Files.exists(this.filePath);
    }

    // Dates are in the format yyyy-mm-ddTHH:MM, unlike what's printed

    /**
     * Converts a line from the file to a task.
     *
     * @param s String to be converted to a task.
     * @return Task.
     * @throws DuneException
     */
    public Task convertLineToTask(String s) throws DuneException {
        // components = [type, T/F, task] for todo
        // [type, T/F, task, deadline] for deadline, [type, T/F, task, start, end] for event
        String[] components = s.split("\\|");
        String taskType = components[0];
        boolean isDone = (components[1].equals("1")) ? true: false;

        boolean isValidTaskType = false;
        for (int i = 0; i < this.taskTypes.length; i++) {
            if (taskType.equals(this.taskTypes[i])) {
                isValidTaskType = true;
                break;
            }
        }
        assert isValidTaskType;

        if (taskType.equals("T")) {
            return new ToDo(components[2], isDone);
        } else if (taskType.equals("D")) {
            return new Deadline(components[2], components[3], isDone);
        } else {
            return new Event(components[2], components[3], components[4], isDone);
        }
    }

    // Dates are in the format yyyy-mm-ddTHH:MM, unlike what's printed

    /**
     * Converts a task to a line to be written to the file.
     *
     * @param t A task.
     * @return
     * @throws DuneException
     */
    public String convertTaskToLine(Task t) throws DuneException {

        boolean validTaskType = (t instanceof ToDo) || (t instanceof Deadline) || (t instanceof Event);
        assert validTaskType;

        String ans = "";
        if (t instanceof ToDo) {
            ans = "T|" + (t.getIsDone() ? "1" : "0") + "|" + t.getDescription();
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            ans = "D|" + (t.getIsDone() ? "1" : "0") + "|" + t.getDescription() + "|"
                    + d.getDeadline();
        } else if (t instanceof Event) {
            Event e = (Event) t;
            ans = "E|" + (t.getIsDone() ? "1" : "0") + "|" + t.getDescription() + "|"
                    + e.getStart() + "|" + e.getEnd();
        } else {
            throw new DuneException(errorTaskType);
        }
        return ans;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks
     */
    public void saveTasks(TaskList tasks) {
        boolean fileExists = java.nio.file.Files.exists(this.filePath);

        if (!fileExists) {
            createFile(this.filePath);
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(this.filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(convertTaskToLine(tasks.getTask(i)));
                writer.newLine();
            }
            writer.close();
        } catch (IOException i) {
            System.out.println("Error writing to file");
        } catch (DuneException d) {
            System.out.println(d.getMessage());
        } finally {
            assert java.nio.file.Files.exists(this.filePath);
        }
    }

    public static void createFile(Path filePath) {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException i) {
            System.out.println("Error creating file");
        }
    }

    public static void print(String[] arr) {
        for (String s : arr) {
            System.out.println(s);
        }
    }

}
