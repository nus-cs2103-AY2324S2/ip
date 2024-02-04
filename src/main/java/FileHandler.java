package main.java;

import main.java.task.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class FileHandler {
    private static final String FILE_PATH = "./data/paimon.txt";

    public FileHandler() {
    }

    private static Task convertDataToTask(String data) throws IOException {
        String[] fields = data.split("\\|");
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        Task returnTask;
        switch (fields[0]) {
            case "T":
                returnTask = new TodoTask(fields[2]);
                break;
            case "D":
                returnTask = new DeadlineTask(fields[2], fields[3]);
                break;
            case "E":
                returnTask = new EventTask(fields[2], fields[3], fields[4]);
                break;
            default:
                throw new IOException("Invalid task type: " + fields[0]);
        }
        returnTask.setTaskState(fields[1].equals("1"));
        return returnTask;
    }

    public static TaskList loadTaskList() {
        try {
            File dataFile = new File(FILE_PATH);
            if (dataFile.exists()) {
                Scanner s = new Scanner(dataFile);
                TaskList newTaskList = new TaskList();
                while (s.hasNextLine()) {
                    String data = s.nextLine();
                    Task newTask = convertDataToTask(data);
                    newTaskList.addTask(newTask);
                }
                s.close();
                return newTaskList;
            } else {
                if (!dataFile.createNewFile()) {
                    System.out.println("Error occurred when trying to create new file");
                }
                return new TaskList();
            }
        } catch (IOException e) {
            // Default behaviour is to return normal TaskList, and continue as normal
            System.out.println("Error occurred when trying to load file.");
        }
        return new TaskList();
    }

    public static void saveTaskList(TaskList list) {
        try {
            FileWriter w = new FileWriter(FILE_PATH);
            for (int i = 0; i < list.getSize(); i++) {
                String writeString = list.getTask(i).toFileString();
                w.write(writeString + "\n");
            }
            w.close();
        } catch (IOException e) {
            System.out.println("Unable to save Task List to file");
        }

    }

}
