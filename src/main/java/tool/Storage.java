package tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Represents the hard drive storage for tasks.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath File path of the saved tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Converts the saved tasks to Task objects.
     *
     * @param taskDetails Task details stored in String format.
     * @return Task object converted from task details stored.
     */
    public Task convertSaveToTask(String taskDetails) {
        String[] taskDetailsSplit = taskDetails.split("\\|");
        String taskCode = taskDetailsSplit[0].trim();
        String taskStatus = taskDetailsSplit[1].trim();
        String taskDescription = taskDetailsSplit[2].trim();
        Task convertedTask = new Todo(taskDescription);

        if (taskCode.equals("D")) {
            String taskByDate = taskDetailsSplit[3].trim();
            convertedTask = new Deadline(taskDescription, taskByDate);
        } else if (taskCode.equals("E")) {
            String taskFromDate = taskDetailsSplit[3].trim();
            String taskToDate = taskDetailsSplit[4].trim();
            convertedTask = new Event(taskDescription, taskFromDate, taskToDate);
        }

        if (taskStatus.equals("1")) {
            convertedTask.setMarked();
        }

        return convertedTask;
    }

    /**
     * Converts a Task object to the saved format.
     *
     * @param currentTask Current task to be converted.
     * @return String representation of the converted task.
     */
    public String convertTaskToSave(Task currentTask) {
        String taskCode = currentTask.getTaskCode();
        String taskStatus = Integer.toString(currentTask.getStatusInt());
        String taskDescription = currentTask.getDescription();
        String date = "";

        if (!taskCode.equals("T")) {
            date = currentTask.getDate();
        }

        return taskCode + " | " + taskStatus + " | " + taskDescription + " | " + date;
    }

    /**
     * Loads the saved tasks from the text file.
     *
     * @return ArrayList of Task objects converted from task details stored.
     * @throws IOException If there is an exception when processing input/output.
     */
    public ArrayList<Task> loadTasksFromFile() throws IOException {
        Path retrievedFilePath = Paths.get(filePath);
        Files.createDirectories(retrievedFilePath.getParent());

        if (!Files.exists(retrievedFilePath)) {
            Files.createFile(retrievedFilePath);
        }

        try {
            Scanner sc = new Scanner(new File(filePath));
            ArrayList<Task> retrievedTaskList = new ArrayList<>();

            while (sc.hasNextLine()) {
                String taskDetails = sc.nextLine();
                Task retrievedTask = convertSaveToTask(taskDetails);
                retrievedTaskList.add(retrievedTask);
            }

            return retrievedTaskList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Saves task list to text file upon each change.
     *
     * @param tasks List of tasks to be stored.
     * @throws IOException If there is an exception when processing input/output.
     */
    public void saveTasksToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 1; i < tasks.size() + 1; i++) {
            Task currentTask = tasks.getTask(i - 1);
            fw.write(convertTaskToSave(currentTask) + "\n");
        }

        fw.close();
    }
}
