package haro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import haro.task.Deadline;
import haro.task.Event;
import haro.task.Task;
import haro.task.ToDo;

/**
 * The Storage class manages the reading and saving of task data to a text file.
 * It loads tasks from a specific text file and saves it back to the same file.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructs a Storage instance with specified file paths
     * @param filePath Path to save file that the Storage instance will be loading and saving from
     * @param directoryPath The directory that the text file is in
     */
    public Storage(String filePath, String directoryPath) {
        // Assert that file path and directory path are not null
        assert filePath != null && directoryPath != null : "file path and directory path should not be null";

        this.filePath = filePath;
        file = new File(filePath);

        File dir = new File(directoryPath);

        if (!dir.exists()) {
            dir.mkdir();
        }
        // Test if file exists
        try {
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(filePath);
                writer.write("");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads tasks from the save file and returns them as an ArrayList
     * @return An ArrayList of the Task objects loaded from the save file.
     */
    public ArrayList<Task> loadSave() {
        ArrayList<Task> resultTasks = new ArrayList<Task>();
        try {
            Scanner storageScanner = new Scanner(this.file);

            while (storageScanner.hasNext()) {
                String storageLine = storageScanner.nextLine();
                String actionType = "none";
                String[] storageArgs = storageLine.split("\\|");
                String commandType = storageArgs[0].toLowerCase().trim();

                switch (commandType) {
                case "t":
                    actionType = "todo";
                    break;
                case "d":
                    actionType = "deadline";
                    break;
                case "e":
                    actionType = "event";
                    break;
                default:
                    actionType = "none";
                }

                if (actionType == "none") {
                    // Empty file or invalid file
                    break;
                } else if (actionType == "todo") {
                    resultTasks.add(loadTodoTask(storageArgs));
                } else if (actionType == "deadline") {
                    resultTasks.add(loadDeadlineTask(storageArgs));
                } else if (actionType == "event") {
                    resultTasks.add(loadEventTask(storageArgs));
                }
            }
        } catch (Exception e) {
            // File does not exist, or is invalid, we return empty taskList
            System.out.println(e.getMessage());
            resultTasks = new ArrayList<Task>();
            return resultTasks;
        } finally {
            return resultTasks;
        }
    }

    /**
     * Saves the tasks from a TaskList to the save file
     * @param sourceTaskList TaskList containing tasks to be saved
     */
    public void saveToDisk(TaskList sourceTaskList) {
        ArrayList<Task> saveTasks = sourceTaskList.getArrayList();
        String content = "";
        for (int i = 0; i < saveTasks.size(); i++) {
            Task curr = saveTasks.get(i);
            String currTask = curr.toString();
            content += currTask + System.lineSeparator();
        }

        try {
            // Assert that a filePath exists
            assert this.filePath != null : "File path cannot be null when saving taskList to disk";

            FileWriter fw = new FileWriter(this.filePath);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private Task loadTodoTask(String[] storageArgs) throws Exception {
        if (storageArgs.length < 3) {
            throw new Exception("Invalid save list");
        }
        int markedInt = Integer.valueOf(storageArgs[1].trim());
        boolean isMarked = (markedInt == 0) ? false : true;
        ToDo todoTask = new ToDo(storageArgs[2].trim(), isMarked);
        return todoTask;
    }
    private Task loadDeadlineTask(String[] storageArgs) throws Exception {
        if (storageArgs.length < 4) {
            throw new Exception("Invalid save list");
        }
        int markedInt = Integer.valueOf(storageArgs[1].trim());
        boolean isMarked = (markedInt == 0) ? false : true;
        String taskName = storageArgs[2].trim();
        String dueDate = storageArgs[3].trim();
        Deadline deadlineTask = new Deadline(taskName, dueDate, isMarked);
        return deadlineTask;
    }
    private Task loadEventTask(String[] storageArgs) throws Exception {
        if (storageArgs.length < 5) {
            throw new Exception("Invalid save list");
        }
        int markedInt = Integer.valueOf(storageArgs[1].trim());
        boolean isMarked = (markedInt == 0) ? false : true;

        String taskName = storageArgs[2].trim();
        String start = storageArgs[3].trim();
        String end = storageArgs[4].trim();
        Event eventTask = new Event(taskName, start, end, isMarked);
        return eventTask;
    }
}

