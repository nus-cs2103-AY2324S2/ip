import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class TaskIo {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<Task> readFromLocal() {
        // Clear current tasklist
        taskList.clear();

        // Read from target file
        String filePath = "src/main/resources/solaire.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                parseTasks(line);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return taskList;
    }

    public static void parseTasks(String line) {
        String[] taskDetails = line.split(" \\| ");
        String taskType = taskDetails[0].trim();
        Boolean isComplete = taskDetails[1].trim().equals("1") ? true : false;
        String taskDescription = taskDetails[2].trim();

        /*
         * for (String x : taskDetails) System.out.println(x);
         */
        switch (taskDetails.length) {
        case 3: {
            Task newTask = new Todo(taskDescription);
            if (isComplete) {
                newTask.markAsDone();
            }
            taskList.add(newTask);
            break;
        }
        case 4: {
            Task newTask;
            if (taskType.trim().equals("D")) {
                newTask = new Deadline(taskDescription, taskDetails[3]);
            } else {
                String[] timeDetails = taskDetails[3].split("\\-");
                newTask = new Event(taskDescription, timeDetails[0], timeDetails[1]);
            }
            if (isComplete) {
                newTask.markAsDone();
            }
            taskList.add(newTask);
            break;
        }
        }
    }
}