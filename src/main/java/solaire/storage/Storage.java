package solaire.storage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import solaire.Solaire;
import solaire.data.exception.SolaireException;
import solaire.data.task.Deadline;
import solaire.data.task.Event;
import solaire.data.task.Task;
import solaire.data.task.Todo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Storage {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<Task> loadFromLocal() {
        // Clear current tasklist
        taskList.clear();

        // Read from target file
        Path filePath = Paths.get("src", "main", "java", "solaire", "resources", "Solaire.txt");
        try {

            Path directoryPath = filePath.getParent();
            // Check if directory exists
            Files.createDirectory(directoryPath);

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                parseTasks(line);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong in reading local data: " + e.getMessage());
        } catch (SolaireException e) {
            throw new RuntimeException(e);
        }

        return taskList;
    }

    public static void write(ArrayList<Task> taskList) {
        Path filePath = Paths.get("src", "main", "java", "solaire", "resources", "Solaire.txt");

        try {
            Files.createDirectory(filePath.getParent());
            if (!Files.exists(filePath)) {
                throw new IOException("File not found: " + filePath);
                // or handle it differently based on your requirements
            }
            Storage.taskList = taskList;
            BufferedWriter bw = Files.newBufferedWriter(filePath);

            for (Task task : taskList) {
                String content = "";
                if (task instanceof Todo) {
                    content = "T | " + (task.getIsDone() ? "1" : "0") + " | " + (task.getDescription());
                } else if (task instanceof Deadline) {
                    Deadline ddlTask = (Deadline) task;
                    content = "D | " + (ddlTask.getIsDone() ? "1" : "0") + " | " + (ddlTask.getDescription()) + " | "
                            + (ddlTask.getDeadline());
                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    content = "E | " + (eventTask.getIsDone() ? "1" : "0") + " | " + (eventTask.getDescription())
                            + " | " + (eventTask.getStart()) + " | " + (eventTask.getEnd());
                }
                if (!content.equals("")) {
                    bw.write(content);
                    bw.newLine();
                }
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Something went wrong on attempting to write to local disk: " + e.getMessage());
        }
    }

    private static void parseTasks(String line) throws SolaireException {
        String[] taskDetails = line.split(" \\| ");
        String taskType = taskDetails[0].trim();
        Boolean isComplete = taskDetails[1].trim().equals("1") ? true : false;
        String taskDescription = taskDetails[2].trim();

        /*
         * for (String x : taskDetails) System.out.println(x);
         */
        switch (taskDetails.length) {
        case 3: {
            Task newTask = new Todo(taskDescription);
            if (isComplete) {
                newTask.markAsDone();
            }
            taskList.add(newTask);
            break;
        }
        case 4: {
            Task newTask;
            if (taskType.trim().equals("D")) {
                newTask = new Deadline(taskDescription, taskDetails[3]);
            } else {
                String[] timeDetails = taskDetails[3].split("\\-");
                newTask = new Event(taskDescription, timeDetails[0], timeDetails[1]);
            }
            if (isComplete) {
                newTask.markAsDone();
            }
            taskList.add(newTask);
            break;
        }
        }
    }
}