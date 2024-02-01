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

    private final String SAVE_DIR_PATH;
    private final String SAVE_FILENAME;  

    public Storage(String saveDirPath, String saveFilename) {
        this.SAVE_DIR_PATH = saveDirPath;
        this.SAVE_FILENAME = saveFilename;
    }

    /**
     * Saves the task list to a csv file
     * @param taskList the task list to be saved
     * @throws IOException if an error occurs when writing to the file
     */
    public void saveTaskList(TaskList taskList) throws IOException {
        // create directory if not exists
        File dir = new File(this.SAVE_DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // create file if not exists
        File file = new File(this.SAVE_DIR_PATH, this.SAVE_FILENAME);
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
        File file = new File(this.SAVE_DIR_PATH, this.SAVE_FILENAME);
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
                    task = new TodoTask(description, isDone);
                } else if (type.equals("D")) {
                    String by = arr[3];
                    LocalDateTime byDateTime = LocalDateTime.parse(by);
                    task = new DeadlineTask(description, byDateTime, isDone);
                } else if (type.equals("E")) {
                    String from = arr[3];
                    String to = arr[4];
                    LocalDateTime fromDateTime = LocalDateTime.parse(from);
                    LocalDateTime toDateTime = LocalDateTime.parse(to);
                    task = new EventTask(description, fromDateTime, toDateTime, isDone);
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


}
