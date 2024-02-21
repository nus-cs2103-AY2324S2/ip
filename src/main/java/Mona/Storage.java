package mona;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * The Storage class handles reading and writing tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
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
     * Reads tasks from the file and adds them to the provided list.
     *
     * @param currentTasks The list where tasks will be stored after reading from the file.
     */
    public void readLog(List<Task> currentTasks) {
        try {
            Stream<String> stream = Files.lines(Paths.get(this.filePath));
            stream.map(line -> line.split("\\|"))
                    .map(this::parseLogEntry)
                    .forEach(task -> {
                        if (task != null) {
                            currentTasks.add(task);
                        }
                    });
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Parses a log entry and creates a corresponding Task object.
     *
     * @param logEntry The log entry to parse.
     * @return The Task object parsed from the log entry.
     */
    public Task parseLogEntry(String[] logEntry) {
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
     * Writes tasks from the provided list to the file.
     *
     * @param currentTasks The list of tasks to write to the file.
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
