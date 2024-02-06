import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

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

    private Task convertTaskLineToTask(String line) {
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

    public void editDataStoreTaskAsDone(int i) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            StringBuilder content = new StringBuilder();
            String line;
            int currentLineNumber = 0;

            while ((line = reader.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber == i) {
                    line = line.replace("0", "1");
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

    public void editDataStoreTaskAsUndone(int i) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            StringBuilder content = new StringBuilder();
            String line;
            int currentLineNumber = 0;

            while ((line = reader.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber == i) {
                    line = line.replace("1", "0");
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
