package chatbot.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import chatbot.task.DeadlineTask;
import chatbot.task.EventTask;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.task.TodoTask;

/**
 * Storage class to save and load tasks
 */
public class Storage {

    private final String saveDirPath;
    private final String saveFilename;

    /**
     * Constructor for Storage class
     * @param saveDirPath the directory path to save the file
     * @param saveFilename the filename to save the file
     */
    public Storage(String saveDirPath, String saveFilename) {
        this.saveDirPath = saveDirPath;
        this.saveFilename = saveFilename;
    }

    /**
     * Saves the task list to a csv file
     * @param taskList the task list to be saved
     * @throws IOException if an error occurs when writing to the file
     */
    public void saveTaskList(TaskList taskList) throws IOException {
        // create directory if not exists
        File dir = new File(this.saveDirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // create file if not exists
        File file = new File(this.saveDirPath, this.saveFilename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw e;
            }
        }
        ArrayList<Task> tasks = taskList.getTasks();
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.exportToSave() + "\n");
            }
            fw.close();
            taskList.save();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Loads the task list from a csv file
     * @return the task list
     * @throws IOException if an error occurs when reading the file
     */
    public TaskList loadTaskList() throws IOException {
        // Load a csv
        TaskList taskList = new TaskList();
        File file = new File(this.saveDirPath, this.saveFilename);
        if (!file.exists()) {
            return taskList;
        }
        Scanner sc = new Scanner(file);
        try {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] arr = line.split(",");
                String type = arr[0];
                boolean isDone = arr[1].equals("1");
                String description = arr[2];
                Task task;
                if (type.equals("T")) {
                    task = loadTodoTask(description, isDone);
                } else if (type.equals("D")) {
                    task = loadDeadlineTask(description, isDone, arr);
                } else if (type.equals("E")) {
                    task = loadEventTask(description, isDone, arr);
                } else {
                    throw new Exception();
                }
                taskList.addTask(task);
            }
            return taskList;
        } catch (Exception e) {
            throw new IOException("Error reading file");
        } finally {
            sc.close();
        }
    }

    private static TodoTask loadTodoTask(String description, boolean isDone) {
        return new TodoTask(description, isDone);
    }

    private static DeadlineTask loadDeadlineTask(String description, boolean isDone, String[] arr) {
        LocalDateTime dateTime = LocalDateTime.parse(arr[1]);
        return new DeadlineTask(description, dateTime, isDone);
    }

    private static EventTask loadEventTask(String description, boolean isDone, String[] arr) {
        LocalDateTime fromDateTime = LocalDateTime.parse(arr[1]);
        LocalDateTime toDateTime = LocalDateTime.parse(arr[2]);
        return new EventTask(description, fromDateTime, toDateTime, isDone);
    }


}
