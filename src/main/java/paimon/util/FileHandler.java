package paimon.util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import paimon.ChatException;
import paimon.task.DeadlineTask;
import paimon.task.EventTask;
import paimon.task.Task;
import paimon.task.TaskList;
import paimon.task.TodoTask;


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
        assert fields.length >= 3 : "Task data does not contain expected number of fields";
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        Task returnTask;
        // Might need to catch DateTimeParseException here
        switch (fields[0]) {
        case "T":
            assert fields.length == 3 : "TodoTask data format is incorrect";
            returnTask = new TodoTask(fields[2]);
            break;
        case "D":
            assert fields.length == 4 : "DeadlineTask data format is incorrect";
            try {
                returnTask = new DeadlineTask(fields[2], DateParser.parseDate(fields[3]));
            } catch (ChatException e) {
                throw new IOException("Unable to read date " + fields[3]);
            }
            break;
        case "E":
            assert fields.length == 5 : "EventTask data format is incorrect";
            try {
                returnTask = new EventTask(fields[2], DateParser.parseDate(fields[3]), DateParser.parseDate(fields[4]));
            } catch (ChatException e) {
                throw new IOException("Unable to read date " + fields[3] + " " + fields[4]);
            }
            break;
        default:
            throw new IOException("Invalid task type: " + fields[0]);
        }
        assert returnTask != null : "Return task should not be empty";
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
        assert list != null : "TaskList to save cannot be null";
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
