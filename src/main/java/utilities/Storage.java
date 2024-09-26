package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.YpxmmException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Handles reading from and writing to the storage file.
 */
public class Storage {

    /** The file path of the storage file. */
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the file path of the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Rewrites the entire storage file based on the given task list.
     *
     * @param tasklist the task list to rewrite the file with
     * @throws YpxmmException if an I/O error occurs
     */
    public void reWrite(TaskList tasklist) throws YpxmmException {
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file);
            for (Task t : tasklist.getTasks()) {
                fw.write(t.toWrite() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new YpxmmException("IOException");
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return the list of tasks loaded from the file
     * @throws YpxmmException if an I/O error occurs or if the file is corrupted
     */
    public ArrayList<Task> loadTasksIntoTaskList() throws YpxmmException {
        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] line = s.nextLine().split(" \\| ");
                Task taskToAdd = createTask(line);
                tasks.add(taskToAdd);
            }
        } catch (IOException e) {
            throw new YpxmmException("IOException");
        } catch (ArrayIndexOutOfBoundsException e) {
            file.delete();
            throw new YpxmmException("Wah bro your file is corrupted leh...I help you delete first");
        }
        return tasks;
    }

    private Task createTask(String[] line) throws IOException, ArrayIndexOutOfBoundsException,
            YpxmmException {
        if (line[0].equals("T")) {
            Task task = new ToDo(line[3]);
            if (line[1].equals("1")) {
                task.setCompleted();
            }
            task.setPriority(line[2]);
            return task;
        } else if (line[0].equals("D")) {
            Task task = new Deadline(line[3], line[4]);
            if (line[1].equals("1")) {
                task.setCompleted();
            }
            task.setPriority(line[2]);
            return task;
        } else if (line[0].equals("E")) {
            String[] timing = line[4].split(" to ");
            Task task = new Event(line[3], timing[0], timing[1]);
            if (line[1].equals("1")) {
                task.setCompleted();
            }
            task.setPriority(line[2]);
            return task;
        } else {
            //should not reach here. returns dummy task
            assert false : "File format has been corrupted";
            return new ToDo("error");
        }
    }
}
