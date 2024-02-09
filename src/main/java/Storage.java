import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> loadTasksFromFile() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String taskData = fileScanner.nextLine();
                Task task = createTaskFromData(taskData);
                taskList.add(task);
            }
            fileScanner.close();
        }
        return taskList;
    }

    public void saveTasksToFile(ArrayList<Task> taskList) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : taskList) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private Task createTaskFromData(String taskData) {
        String[] parts = taskData.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        if (type.equals("T")) {
            return new Task(TaskType.TODO, description, isDone);
        } else if (type.equals("D")) {
            String by = parts[3].trim();
            return new Task(TaskType.DEADLINE, description, by, isDone);
        } else if (type.equals("E")) {
            String from = parts[3].trim();
            String to = parts[4].trim();
            return new Task(TaskType.EVENT, description, from, to, isDone);
        } else {
            // Handle other types if needed
            return null;
        }
    }
}
