import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Objects;
import java.nio.file.Files;

public class Storage {
    private String filePath;
    private ArrayList<Task> taskArrayList = new ArrayList<>();
    private int lastIdx = 0;

    public Storage(String filePath) {
        this.filePath = filePath;
        Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("Data store created.");
            }
            readDataStore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDataStore() {
        Path path = Paths.get(filePath);
        try {
            this.lastIdx = 0;
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Task task = convertTaskLineToTask(line);
                this.taskArrayList.add(task);
                markDataStoreTaskStatus(task, line);
                this.lastIdx++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    public Task getTaskByIdx(int idx) {
        return this.taskArrayList.get(idx);
    }

    public int getLastIdx() {
        return this.lastIdx;
    }

    public void addToDataStore(Task task) {
        String newTaskLine = task.convertToDataStoreLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, true))) {
            writer.write(newTaskLine);
            writer.newLine();
            System.out.println("Line added to the file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        taskArrayList.add(task);
        this.lastIdx++;
    }

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

    private static Task convertTaskLineToTask(String line) {
        String[] splitLine = line.split("\\|");
        String taskAlphabet = splitLine[0];
        switch (taskAlphabet) {
            case "T":
                return new ToDo(splitLine[2]);
            case "D":
                return new Deadline(splitLine[2], splitLine[3]);
            case "E":
                return new Event(splitLine[2], splitLine[3], splitLine[4]);
            default:
                return null;
        }
    }

    private static void markDataStoreTaskStatus(Task task, String taskLine) {
        String[] splitLine = taskLine.split("\\|");
        if (Objects.equals(splitLine[1], "1")) {
            task.markAsDone();
        }
    }

}
