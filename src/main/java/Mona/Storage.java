package mona;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains the logic for interacting with the data logs
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage
     * @param filePath location for the logs to be written to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        String workingDirectory = System.getProperty("user.dir");
        try {
            Path dataDirectory = Paths.get(workingDirectory + "/data");
            Files.createDirectories(dataDirectory);
            File logFile = new File(workingDirectory + "/" + this.filePath);
            logFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error occurred setting up log" + e.getMessage());
        }
    }

    /**
     * Method for adding the tasks stored in the logs to Mona's task list
     * @param currentTasks Mona's task list for tasks stored in the logs to be added to
     */
    public void readLog(List<Task> currentTasks) {
        File log = new File(this.filePath);
        try {
            Scanner s = new Scanner(log);
            while (s.hasNext()) {
                String[] task = s.nextLine().split("\\|");
                currentTasks.add(parseLogEntry(task));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found:" + e.getMessage());
        }
    }

    /**
     * Method for translating the tasks stored in the logs into their corresponding Tasks
     * @param logEntry the task stored in the log
     * @return the corresponding task instance
     */
    public static Task parseLogEntry(String[] logEntry) {
        String description = logEntry[2];
        boolean isCompleted = logEntry[1].equals("1");
        switch (logEntry[0]) {
        case "T":
            Task currTask = new Todo(description);
            currTask.setCompletion(isCompleted);
            return currTask;
        case "D":
            currTask = new Deadline(description, logEntry[3]);
            currTask.setCompletion(isCompleted);
            return currTask;
        case "E":
            currTask = new Event(description, logEntry[3], logEntry[4]);
            currTask.setCompletion(isCompleted);
            return currTask;
        default:
            return null;
        }
    }

    /**
     * Method for updating the logs when the task list is modified while Mona is running
     * @param currentTasks Mona's task list which the user can directly interact with
     */
    public void writeToFile(List<Task> currentTasks) {
        File log = new File(this.filePath);
        StringBuilder sb = new StringBuilder();
        for (Task task : currentTasks) {
            sb.append(task.parseToLogRepresentation() + "\n");
        }
        try {
            FileWriter fw = new FileWriter(log.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            System.out.println("Problem writing to log: " + e.getMessage());
        }
    }
}
