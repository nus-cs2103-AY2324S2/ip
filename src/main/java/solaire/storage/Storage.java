package solaire.storage;

import java.util.ArrayList;
import java.util.List;

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
        String filePath = "src/main/java/solaire/resources/Solaire.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                parseTasks(line);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return taskList;
    }

    public static void write(ArrayList<Task> taskList) {
        String filePath = "src/main/resources/Solaire.txt";

        try {
            Storage.taskList = taskList;
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

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
            System.out.println(e.getMessage());
        }
    }

    private static void parseTasks(String line) {
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