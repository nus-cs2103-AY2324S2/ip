package kervyn;

import kervyn.Tasks.Deadline;
import kervyn.Tasks.Event;
import kervyn.Tasks.Task;
import kervyn.Tasks.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private String path;
    public Storage(String path) {
        this.path = path;
    }

    public short writeToFile(String content) {
        try {
            String[] dirName = this.path.split("/");
            File dir = new File(dirName[0]);

            if (!dir.exists()) {
                dir.mkdir();
            }

            File file = new File(this.path);
            // Check for existence of file is inherent in this
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content + "\n");

            bw.close();
            return 1;
        }
        catch (IOException e) {
            System.out.println("Uh oh, the file/directory doesn't seem to exist. No worries, one will be created for you at the end of your conversation!");
        }
        return 0;
    }

    public ArrayList<Task> readTasks() throws IOException {
        ArrayList<Task> userRequests = new ArrayList<Task>();
        try {
            ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get(this.path), StandardCharsets.UTF_8);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, ha");
            for (String line : lines) {
                char[] lineToCharArr = line.toCharArray();
                char type = lineToCharArr[1];
                String description = "";
                // Finding content for the description
                String[] splitContent = line.split("]");
                switch (type) {
                    case 'T':
                        description = splitContent[2].trim();
                        ToDo newToDo = new ToDo(description, false);
                        userRequests.add(newToDo);
                        break;
                    case 'D':
                        // Further split the last part
                        String[] furtherSplitContent = splitContent[2].split("\\(");
                        String[] finalSplitContent = furtherSplitContent[1].split(":");

                        String temp = finalSplitContent[1];
                        String deadlineStr = temp.substring(0, temp.length() - 1).trim();
                        LocalDateTime deadline = LocalDateTime.parse(deadlineStr, formatter);

                        description = furtherSplitContent[0].trim();
                        Deadline newDeadline = new Deadline(description, false, deadline);
                        userRequests.add(newDeadline);
                        break;
                    case 'E':
                        furtherSplitContent = splitContent[2].split("\\(");
                        description = furtherSplitContent[0].trim();
                        String[] splitAtTo = furtherSplitContent[1].split("to:");
                        String[] splitAtFrom = splitAtTo[0].split("from:");

                        String startDateStr = splitAtFrom[1].trim();
                        temp = splitAtTo[1];
                        String endDateStr = temp.substring(0, temp.length() - 1).trim();

                        LocalDateTime startDate = LocalDateTime.parse(startDateStr, formatter);
                        LocalDateTime endDate = LocalDateTime.parse(endDateStr, formatter);
                        Event newEvent = new Event(description, false, startDate, endDate);
                        userRequests.add(newEvent);
                        break;
                }
            }
        }
        catch (IOException e) {
            System.out.println("Uh oh, the file/directory doesn't seem to exist. No worries, one will be created for you at the end of your conversation!");
        }

        return userRequests;
    }
}
