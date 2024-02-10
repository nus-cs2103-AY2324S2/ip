import java.io.*;
import java.util.*;

public class TaskRepository {
    private final String FILE_PATH = "./data/taskStorage.txt";
    private TaskList taskList;

    public TaskRepository() {
        try {
            // Create the file if it does not exist
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            // init taskList
            this.taskList = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Populate the taskList with tasks from the file
    public TaskList loadTasks() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                // System.out.println("Line: " + line);
                String[] taskDetails = line.split("\\|");
                String taskType = taskDetails[0].trim();
                // System.out.println("Task Type: " + taskType);
                String description = taskDetails[2].trim();
                // System.out.println("Task Description: " + description);
                switch (taskType) {
                    case "T":
                        // System.out.println("Adding Todo: " + description);
                        taskList.addTodo(description);
                        break;
                    case "D":
                        String dueDate = taskDetails[3].trim();
                        // remove the "by:"
                        dueDate = dueDate.substring(3);
                        // System.out.println("Adding Deadline: " + description + " " + dueDate);

                        taskList.addDeadline(description, dueDate);
                        break;
                    case "E":
                        String timeBlock = taskDetails[3].trim();
                        String[] parts = timeBlock.split("to:");
                        String fromPart = parts[0]; // "from: 2021-09-17 14:00 "
                        String toPart = parts[1]; // " 2021-09-17 16:00"

                        String startTime = fromPart.replace("from:", "").trim(); // "2021-09-17 14:00"
                        String endTime = toPart.trim(); // "2021-09-17 16:00"
                        // System.out.println("Adding Event: " + description + " " + startTime + " " +
                        // endTime);
                        taskList.addEvent(description, startTime, endTime);
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public void saveTasksToFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (String task : taskList.listTasks()) {
                // System.out.println("Writing");
                // Remove the number and space from the beginning of the task
                String taskWithoutNumber = task.substring(task.indexOf(" ") + 1);
                // System.out.println("Task: " + taskWithoutNumber);
                fileWriter.write(taskWithoutNumber + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static void main(String[] args) {
    // TaskRepository taskRepository = new TaskRepository();
    // // TaskList taskList = taskRepository.loadTasks();
    // // taskList.addTodo("Buy groceries");
    // // taskList.addDeadline("Submit assignment", "2021-09-17");
    // // taskList.addEvent("Team meeting", "2021-09-17 14:00", "2021-09-17 16:00");
    // // taskRepository.saveTasksToFile(taskList);

    // TaskList newTaskList = taskRepository.loadTasks();
    // System.out.println("Tasks loaded from file:");

    // // for (String task : newTaskList.listTasks()) {
    // // System.out.println(task.toString());
    // // }
    // System.out.println("TaskList: " + newTaskList.listTasks());
    // }
}