package duke;

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

import duke.exceptions.CorruptedLogException;
import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

public class Storage {

    private String path;

    public Storage(String path) {
        String wd = System.getProperty("user.dir");
        try {
            // Create directory
            Path secondDir = Paths.get(wd + "/" + path);
            //System.out.println("Attempting to create: " + dir_);
            Files.createDirectories(secondDir);
            this.path = wd + "/" + path + "/log.txt";
            //System.out.println("Creating file at: " + this.path);
        } catch (IOException e) {
            System.out.println("Problem setting up file manager: " + e.getMessage());
        }
    }

    public void createLog() {
        File logFile = new File(this.path);
        try {
            boolean response = logFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Problem creating log! " + e.getMessage());
        }
    }

    public LocalDateTime restoreDateTime(String dateString) {
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

    public Task parseEntry(String logEntry) {
        String[] entry = logEntry.split(",");
        boolean completeStatus = entry[1].equals("T");
        //System.out.println("Entry " + entry[1] + ": " + completeStatus);
        String desc = entry[2];
        switch (entry[0]) {
        case "T":
            Task ret = new ToDos(desc);
            ret.setCompletion(completeStatus);
            return ret;
        case "D":
            ret = new Deadlines(desc, restoreDateTime(entry[3]));
            ret.setCompletion(completeStatus);
            return ret;
        case "E":
            ret = new Events(desc, restoreDateTime(entry[3]),
              restoreDateTime(entry[4]));
            ret.setCompletion(completeStatus);
            return ret;
        default:
            return null;
        }
    }
}
