package kwuntalk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import kwuntalk.exception.TasksFileException;
import kwuntalk.task.Deadline;
import kwuntalk.task.Event;
import kwuntalk.task.Task;
import kwuntalk.task.Todo;


/**
 * Represents the storage that reads data from and saves data to the Tasks File.
 */
public class Storage {
    private String filePath;


    /**
     * Constructor for Storage
     *
     * @param filePath Filepath to store tasks data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private Task readTask(String taskEntry) {
        assert !taskEntry.equals("") : "Task entry in tasks file should not be empty";

        String[] fields = taskEntry.split(" \\| ", 5);

        Task task = null;

        if (fields[0].equals("T")) {
            task = new Todo(fields[2]);

        } else if (fields[0].equals("D")) {
            LocalDateTime dueDateTime = LocalDateTime.parse(fields[3]);
            task = new Deadline(fields[2], dueDateTime);

        } else if (fields[0].equals("E")) {
            LocalDateTime fromDateTime = LocalDateTime.parse(fields[3]);
            LocalDateTime toDateTime = LocalDateTime.parse(fields[4]);
            task = new Event(fields[2], fromDateTime, toDateTime);
        }

        assert task != null : "Task should never be null";

        if (fields[1].equals("1")) {
            task.changeMark("MARK");
        }
        return task;
    }


    /**
     * Reads data from the Tasks File.
     *
     * @return List of tasks read from the Task File.
     * @throws TasksFileException If Task File can't be found.
     */
    public ArrayList<Task> readTasksFile() throws TasksFileException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);

        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                Task task = readTask(sc.nextLine());
                tasks.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            createTasksFile();
        }
        return tasks;
    }

    private void createTasksFile() throws TasksFileException {
        File tasksFile = new File(filePath);
        File dataDirectory = tasksFile.getParentFile();
        dataDirectory.mkdirs();
        try {
            tasksFile.createNewFile();
        } catch (IOException e) {
            throw new TasksFileException();
        }
    }


    /**
     * Saves data to the Task File.
     *
     * @param tasks List of tasks to write to the Task File.
     * @throws IOException If error is thrown when writing to the Task File.
     */
    public void saveTasksFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 1; i <= tasks.getLength(); i++) {
            String formattedTask = tasks.get(i).formatTask();
            fw.write(formattedTask + System.lineSeparator());
        }
        fw.close();
    }

}
