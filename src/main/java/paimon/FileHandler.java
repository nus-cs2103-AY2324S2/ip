package paimon;

import paimon.task.*;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * Handles file operations for the application, including loading tasks from a file
 * and saving tasks to a file. This class ensures tasks persist between application runs.
 */
public class FileHandler {
    private static final String FILE_PATH = "./data/paimon.txt";

    /**
     * Converts a line of text from the task file into a {@link Task} object.
     *
     * @param data String data read from a line in the file.
     * @return A {@link Task} object translated from each line of data.
     * @throws IOException If there is an error parsing the task data.
     */
    private static Task convertDataToTask(String data) throws IOException {
        String[] fields = data.split("\\|");
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        Task returnTask;
        // Might need to catch DateTimeParseException here
        switch (fields[0]) {
            case "T":
                returnTask = new TodoTask(fields[2]);
                break;
            case "D":
                try {
                    returnTask = new DeadlineTask(fields[2], DateParser.parseDate(fields[3]));
                } catch (ChatException e) {
                    throw new IOException("Unable to read date " + fields[3]);
                }
                break;
            case "E":
                try {
                    returnTask = new EventTask(fields[2], DateParser.parseDate(fields[3]), DateParser.parseDate(fields[4]));
                } catch (ChatException e) {
                    throw new IOException("Unable to read date " + fields[3] + " " + fields[4]);
                }
                break;
            default:
                throw new IOException("Invalid task type: " + fields[0]);
        }

        returnTask.setTaskState(fields[1].equals("1"));
        return returnTask;
    }

    /**
     * Loads the list of tasks from the file specified by {@link #FILE_PATH}.
     * If the file or directory does not exist, it attempts to create them.
     *
     * @return A {@link TaskList} containing the tasks loaded from the file.
     */
    public static TaskList loadTaskList() {
        try {
            File dataFile = new File(FILE_PATH);
            File directory = new File(dataFile.getParent());
            if (!directory.exists()) {
                if (!directory.mkdir()) {
                    System.out.println("Error occurred when trying to create new directory");
                    return new TaskList();
                }
            }
            if (!dataFile.exists()) {
                if (!dataFile.createNewFile()) {
                    System.out.println("Error occurred when trying to create new file");
                }
                return new TaskList();
            } else {
                Scanner s = new Scanner(dataFile);
                TaskList newTaskList = new TaskList();
                while (s.hasNextLine()) {
                    String data = s.nextLine();
                    Task newTask = convertDataToTask(data);
                    newTaskList.addTask(newTask);
                }
                s.close();
                return newTaskList;

            }
        } catch (IOException e) {
            // Default behaviour is to return normal TaskList, and continue as normal
            System.out.println("Error occurred when trying to load file.");
        }
        return new TaskList();
    }

    /**
     * Saves the current list of tasks to the file specified by {@link #FILE_PATH}.
     *
     * @param list The {@link TaskList} to be saved to the file.
     */
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
