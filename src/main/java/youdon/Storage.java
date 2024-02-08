package youdon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    // file path should be "./src/main/data/save.txt"
    private final String filepath;
    private final File saveFile;

    // create save file if it doesnt exist
    public Storage(String filepath) {
        this.filepath = filepath;
        this.saveFile = new File(filepath);
        try {
            if (!saveFile.exists()) {
                saveFile.getParentFile().mkdir();
                saveFile.createNewFile();
                System.out.println("Save File created successfully!");
            } else {
                System.out.println("Save File already exists!");
            }
        } catch (IOException e) {
            System.out.println("Error!" + e.getMessage());
        }
    }

    public void saveData(TaskList taskList) throws IOException {
        try (FileWriter writer = new FileWriter(this.filepath)) {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                writer.write(task.toString() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error! " + e.getMessage());
        }
    }

    public ArrayList<Task> loadData() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            if (saveFile.length() > 0) {
                Scanner scanner = new Scanner(saveFile);

                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    // identify task type
                    String taskType = data.substring(1, 2);

                    if (taskType.equals("T")) {
                        // identify task description
                        int startIndex = data.indexOf("[") + 7;
                        String description = data.substring(startIndex);
                        taskList.add(new Todo(description));
                    }

                    if (taskType.equals("D")) {
                        // identify task description
                        int startIndex = data.indexOf("[") + 7;
                        int endIndex = data.indexOf(" (by:");
                        String description = data.substring(startIndex, endIndex).trim();
                        // identify task deadline
                        startIndex = data.indexOf("(by: ") + 5;
                        endIndex = data.indexOf(")", startIndex);
                        String deadline = data.substring(startIndex, endIndex);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);
                        taskList.add(new Deadline(description, deadlineDateTime));
                    }

                    if (taskType.equals("E")) {
                        // identify task description
                        int startIndex = data.indexOf("[") + 7;
                        int endIndex = data.indexOf(" (from:");
                        String description = data.substring(startIndex, endIndex);
                        // identify task start time
                        startIndex = data.indexOf("from: ") + 6;
                        endIndex = data.indexOf(" to:", startIndex);
                        String start = data.substring(startIndex, endIndex);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                        LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
                        // identify task end time
                        startIndex = data.indexOf(" to:") + 5;
                        endIndex = data.indexOf(")", startIndex);
                        String end = data.substring(startIndex, endIndex);
                        LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
                        taskList.add(new Event(description, startDateTime, endDateTime));
                    }
                }
                System.out.println("Save File loaded!");
            } else {
                System.out.println("Save File is empty :(");
            }
        } catch (IOException e) {
            System.out.println("Error!" + e.getMessage());
        }
        return taskList;
    }
}
