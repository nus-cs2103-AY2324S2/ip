package tool;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import tool.TaskList;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
     * @param tasks List of tasks.
     */
    public void saveTasksToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task currentTask = tasks.getTask(i - 1);
            String taskCode = currentTask.getTaskCode();
            String taskStatus = Integer.toString(currentTask.getStatusInt());
            String taskDescription = currentTask.getDescription();
            String date = "";
            if (!taskCode.equals("T")) {
                date = currentTask.getDate();
            }
            fw.write(taskCode + " | " + taskStatus + " | " + taskDescription + " | " + date + "\n");
        }
        fw.close();
    }
}
