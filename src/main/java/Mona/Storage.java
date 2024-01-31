package Mona;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Storage {
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
    private String filePath;
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
