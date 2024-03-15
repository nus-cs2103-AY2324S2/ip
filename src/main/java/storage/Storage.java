package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * The Storage class manages reading from and writing to a data store txt file.
 * It handles tasks related to file operations and task data persistence.
 */
public class Storage {
    private String filePath;
    private int lastIdx = 0;

    /**
     * Constructor to initialise the storage class. It reads the data store if it exists,
     * else it creates one at the specified file path.
     * @param filePath String specifying the file path of the data store.
     */
    public Storage(String filePath) {
        assert filePath != null : "Storage file path is null.";
        this.filePath = filePath;
        Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {

                int lastSlashIndex = filePath.lastIndexOf("/");
                String directoryPath = filePath.substring(0, lastSlashIndex);

                Path directory = Paths.get(directoryPath);
                if (Files.exists(directory) && Files.isDirectory(directory)) {
                    System.out.println("Directory exists.");
                } else {
                    System.out.println("Directory does not exist.");
                    Files.createDirectories(directory);
                }

                Files.createFile(path);
                System.out.println("Data store created.");
            }
            readDataStore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the data store file and populates an ArrayList with Task objects.
     *
     * @return An ArrayList containing Task objects read from the data store.
     */
    public ArrayList<Task> readDataStore() {
        Path path = Paths.get(filePath);
        ArrayList<Task> taskArrayList = new ArrayList<>();
        try {
            this.lastIdx = 0;
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Task task = convertTaskLineToTask(line);
                taskArrayList.add(task);
                markDataStoreTaskStatus(task, line);
                this.lastIdx++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskArrayList;
    }

    /**
     * Adds a task to the data store file.
     *
     * @param task The task to add to the data store.
     */
    public void addToDataStore(Task task) {
        String newTaskLine = task.convertToDataStoreLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, true))) {
            writer.write(newTaskLine);
            writer.newLine();
            System.out.println("Line added to the file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.lastIdx++;
    }

    /**
     * Removes a task from the data store file by its index.
     *
     * @param idx The index of the task to remove.
     */
    public void removeFromDataStore(int idx) {
        String tempFilePathString = this.filePath + ".temp";
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePathString))) {

            String currentLine;
            int currentLineNumber = 1;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLineNumber != idx + 1) {
                    writer.write(currentLine);
                    writer.newLine();
                }
                currentLineNumber++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.move(Path.of(tempFilePathString), Path.of(this.filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a task data store line to a Task object.
     *
     * @param line The task data store line to convert.
     * @return The Task object represented by the input line.
     */
    private Task convertTaskLineToTask(String line) {
        String[] splitLine = line.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String taskAlphabet = splitLine[0];
        switch (taskAlphabet) {
        case "T":
            return new ToDo(splitLine[2]);
        case "D":
            return new Deadline(splitLine[2], LocalDateTime.parse(splitLine[3], formatter));
        case "E":
            return new Event(splitLine[2], LocalDateTime.parse(splitLine[3], formatter),
                    LocalDateTime.parse(splitLine[4], formatter));
        default:
            return null;
        }
    }

    /**
     * Marks the status of a task in the data store as done.
     *
     * @param task     The task to mark as done.
     * @param taskLine The task data store line containing the task.
     */
    private static void markDataStoreTaskStatus(Task task, String taskLine) {
        String[] splitLine = taskLine.split("\\|");
        if (Objects.equals(splitLine[1], "1")) {
            task.markAsDone();
        }
    }

    /**
     * Edits the status of a task in the data store to mark it as done.
     *
     * @param i The index of the task to mark as done.
     */
    public void editDataStoreTaskAsDone(int i) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            StringBuilder content = new StringBuilder();
            String line;
            int currentLineNumber = 0;

            while ((line = reader.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber == i && line.substring(2, 3) == "0") {
                    line = line.substring(0, 2) + "1" + line.substring(3);
                }
                content.append(line);
                content.append("\n");
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            writer.write(content.toString());
            writer.close();

            System.out.println("File successfully updated.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edits the status of a task in the data store to mark it as undone.
     *
     * @param i The index of the task to mark as undone.
     */
    public void editDataStoreTaskAsUndone(int i) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            StringBuilder content = new StringBuilder();
            String line;
            int currentLineNumber = 0;

            while ((line = reader.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber == i && line.substring(2, 3) == "1") {
                    line = line.substring(0, 2) + "0" + line.substring(3);
                }
                content.append(line);
                content.append("\n");
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            writer.write(content.toString());
            writer.close();

            System.out.println("File successfully updated.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
