import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Writer {
    private String path;
    public Writer(String path) {
        this.path = path;
    }

    public void writeToFile(String content) {
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

        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Task> readTasks() throws IOException {
        ArrayList<Task> userRequests = new ArrayList<Task>();
        try {
            ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
            for (String line : lines) {
                char[] lineToCharArr = line.toCharArray();
                char type = lineToCharArr[1];
                String description = "";
                // Finding content for the description
                String[] splitContent = line.split("]");
                switch (type) {
                    case 'T':
                        description = splitContent[2].trim();
                        ToDo todo = new ToDo(description, false);
                        userRequests.add(todo);
                        break;
                    case 'D':
                        // Further split the last part
                        String[] furtherSplitContent = splitContent[2].split("\\(");
                        String[] finalSplitContent = furtherSplitContent[1].split(":");

                        String temp = finalSplitContent[1];
                        String deadlineStr = temp.substring(0, temp.length() - 1).trim();

                        description = furtherSplitContent[0].trim();
                        Deadline deadline = new Deadline(description, false, deadlineStr);
                        userRequests.add(deadline);
                        break;
                    case 'E':
                        furtherSplitContent = splitContent[2].split("\\(");
                        description = furtherSplitContent[0].trim();
                        String[] splitAtTo = furtherSplitContent[1].split("to:");
                        String[] splitAtFrom = splitAtTo[0].split("from:");

                        String startDateStr = splitAtFrom[1].trim();
                        temp = splitAtTo[1];
                        String endDateStr = temp.substring(0, temp.length() - 1).trim();
                        Event event = new Event(description, false, startDateStr, endDateStr);
                        userRequests.add(event);
                        break;
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }

        return userRequests;
    }
}
