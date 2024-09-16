package shuheng;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import shuheng.exceptions.CorruptedLogException;
import shuheng.tasks.Deadline;
import shuheng.tasks.Event;
import shuheng.tasks.PriorityLevel;
import shuheng.tasks.Task;
import shuheng.tasks.ToDo;

/**
 * This class represents a storage manager to handle logging and restoration of task list.
 */
public class Storage {

    private String path;

    /**
     * Constructor for storage manager.
     *
     * @param path The location where the logs will be written to.
     */
    public Storage(String path) {
        String workingDirectory = System.getProperty("user.dir");
        try {
            // Create directory
            Path secondDir = Paths.get(workingDirectory + "/" + path);
            Files.createDirectories(secondDir);
            this.path = workingDirectory + "/" + path + "/log.txt";
        } catch (IOException e) {
            System.out.println("Problem setting up file manager: " + e.getMessage());
        }
    }

    /**
     * Creates log.
     */
    public void createLog() {
        File logFile = new File(this.path);
        try {
            logFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Problem creating log! " + e.getMessage());
        }
    }

    private LocalDateTime restoreDateTime(String dateString) {
        String[] decomposedDateString = dateString.split("T");
        String hhmm = decomposedDateString[1];
        String yymmdd = decomposedDateString[0];
        String[] decomposedYymmdd = yymmdd.split("-");
        String[] decomposedHhmm = hhmm.split(":");
        int year = Integer.parseInt(decomposedYymmdd[0]);
        int month = Integer.parseInt(decomposedYymmdd[1]);
        int day = Integer.parseInt(decomposedYymmdd[2]);
        int hour = Integer.parseInt(decomposedHhmm[0]);
        int minute = Integer.parseInt(decomposedHhmm[1]);
        return LocalDateTime.of(year, month, day, hour, minute);
    }
    /**
     * Writes status of current task list to logs.
     *
     * @param currentList The current task list.
     */
    public void writeLog(ArrayList<Task> currentList) {
        File logFile = new File(this.path);
        StringBuilder sb = new StringBuilder();
        for (Task t : currentList) {
            sb.append(t.getLogRepresentation() + "\n");
        }
        try {
            FileWriter fw = new FileWriter(logFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            System.out.println("Problem writing to log! " + e.getMessage());
        }
    }
    /**
     * Loads history from current logs.
     *
     * @param currentList The current task list to be updated from logs.
     * @throws CorruptedLogException Thrown when the log is corrupted.
     */
    public void loadHistory(ArrayList<Task> currentList) throws CorruptedLogException {
        File logEntry = new File(this.path);
        try {
            Scanner sc = new Scanner(logEntry);
            while (sc.hasNextLine()) {
                String nextLineIn = sc.nextLine();
                Task loadedTask = this.parseEntry(nextLineIn);
                currentList.add(loadedTask);
            }
            sc.close();
        } catch (IOException e) {
            throw new CorruptedLogException(e.getMessage());
        }
    }

    private PriorityLevel generatePriority(String p) {
        switch (p) {
        case "Low":
            return PriorityLevel.LOW;
        case "High":
            return PriorityLevel.HIGH;
        case "Medium":
            return PriorityLevel.MEDIUM;
        default:
            return null;
        }
    }

    private Task parseEntry(String logEntry) {
        String[] entry = logEntry.split(",");
        boolean completeStatus = entry[1].equals("T");
        String desc = entry[2];
        PriorityLevel priority = generatePriority(entry[3]);
        switch (entry[0]) {
        case "T":
            Task ret = new ToDo(desc, priority);
            ret.setCompletion(completeStatus);
            return ret;
        case "D":
            ret = new Deadline(desc, restoreDateTime(entry[4]), priority);
            ret.setCompletion(completeStatus);
            return ret;
        case "E":
            ret = new Event(desc, restoreDateTime(entry[4]),
              restoreDateTime(entry[5]), priority);
            ret.setCompletion(completeStatus);
            return ret;
        default:
            return null;
        }
    }
}
