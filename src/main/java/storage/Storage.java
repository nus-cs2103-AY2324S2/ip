package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;


/**
 * The Storage class handles the reading from and writing to tasks lists in storage
 */
public class Storage {

    private static String workingDirectory = System.getProperty("user.dir");
    private static String dataDirectory = workingDirectory + "\\src\\main\\data";
    private static String saveFilePath = workingDirectory + "\\src\\main\\data\\SavedList.txt";

    /**
     * Returns a TaskList object containing details of the saved task list (if any)
     * If no saved list is found, a TaskList object with an empty list is created
     *
     * @return TaskList object
     */
    public static TaskList getSavedTasks() {

        File saveFile = new File(saveFilePath);
        TaskList taskListObj = new TaskList();
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            if (fileExists(saveFilePath)) {
                System.out.println("Previous saved list loaded.");
                taskList = getListOfTasks(saveFile);
                taskListObj = new TaskList(taskList);
            } else {
                if (!directoryExists(dataDirectory)) {
                    createDirectory(dataDirectory);
                }

                saveFile.createNewFile();
                System.out.println("File not found. New save file created.");
            }
            return taskListObj;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Saves the current list of tasks in the TaskList object
     *
     * @param taskListObj The TaskList object with details to be saved
     */
    public static void saveTaskList(TaskList taskListObj) throws Exception {
        File saveFile = new File(saveFilePath);
        ArrayList<Task> taskList = taskListObj.getTaskList();
        BufferedWriter writerObj = new BufferedWriter(new FileWriter(saveFile, false));
        // re-save entire list
        for (Task task : taskList) {
            String taskStringToSave = task.convertTaskToSave();
            writerObj.write(taskStringToSave + "\n");
        }
        writerObj.close();
    }

    /**
     * Returns true or false depending on whether the directory exists
     *
     * @param directoryPath Path of directory to be checked
     * @return existence status of directory at path
     */
    public static boolean directoryExists(String directoryPath) throws Exception {
        boolean isExistent;
        File folderDirectory = new File(directoryPath);
        isExistent = folderDirectory.exists() && folderDirectory.isDirectory();
        return isExistent;
    }

    /**
     * Creates directory at path and returns the File of directory created
     *
     * @param directoryPath Directory path to be checked
     * @return File object of directory created at directoryPath
     */
    public static File createDirectory(String directoryPath) throws Exception {
        Files.createDirectories(Paths.get(directoryPath));
        return new File(directoryPath);
    }

    /**
     * Returns true or false depending on whether the file exists
     *
     * @param filePath Path of file to be checked
     * @return existence status of file at path
     */
    public static boolean fileExists(String filePath) throws Exception {
        boolean isExistent;
        File file = new File(filePath);
        isExistent = file.exists();
        return isExistent;
    }

    /**
     * Converts list of tasks in saved text file to ArrayList of Task objects
     *
     * @param saveFile Saved file
     * @return ArrayList of Task objects from saved file
     */
    public static ArrayList<Task> getListOfTasks(File saveFile) throws Exception {
        ArrayList<Task> taskList = new ArrayList<>();
        BufferedReader readerObj = new BufferedReader(new FileReader(saveFile));
        String nextLine;
        while ((nextLine = readerObj.readLine()) != null) {
            String[] nextLineDetails = nextLine.split("\\|");
            String taskType = nextLineDetails[0];
            Task newTask = new Todo();
            switch (taskType) {
            case "T":
                newTask = new Todo();
                newTask = newTask.convertSaveToTask(nextLine);
                break;
            case "D":
                newTask = new Deadline();
                newTask = newTask.convertSaveToTask(nextLine);
                break;
            case "E":
                newTask = new Event();
                newTask = newTask.convertSaveToTask(nextLine);
                break;
            default:
                break;
            }
            taskList.add(newTask);
        }
        return taskList;
    }
}
