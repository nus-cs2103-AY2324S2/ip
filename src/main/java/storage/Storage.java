package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

import exceptions.FileAccessError;
import exceptions.FileError;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * <p>
 *  Handles the storage of data, allowing users to access and store data from previous sessions.
 *  Writes to and reads from a txt file.
 *  </p>
 */
public class Storage {
    private final String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * The method handles the writing of data to the txt file
     * <p>
     *  This method writes to the file at the location specified by fileName, and
     *  converts the Task objects which are collected in an ArrayList into a string
     *  to be written to the txt file.
     *  </p>
     * @param data takes in an ArrayList of Tasks and converts it to a
     *             string that will be written to txt file
     * @throws FileError if there is an IO error in writing to the txt file
     */
    public void write(ArrayList<Task> data) throws FileError {
        File file = new File("src/main/resources/" + fileName);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
            System.out.println("make");
        }
        System.out.println("write");
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(convertArrayToStr(data) + "\n");
        } catch (IOException e) {
            throw new FileError("Problem writing to file!");
        }
    }

    /**
     * The method handles the reading of data from the txt file and converts it
     * to an ArrayList of tasks
     * <p>
     *  This method opens the file specified by fileName, reads each line, and
     *  converts it into Task objects which are collected in an ArrayList. The
     *  ArrayList represents the user's task list and is used to recreate the state
     *  of the application from a previous session.
     *  </p>
     * @return an ArrayList of Tasks to be the TaskList of the user
     * @throws FileError if there is an IO error in writing to the txt file
     * @throws FileAccessError if file is not at the expected location
     */
    public ArrayList<Task> read() throws FileError, FileAccessError {
        ArrayList<String> contents = new ArrayList<>();
        File file = new File("src/main/resources/" + fileName);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new FileError("Problem creating new file: " + e.getMessage());
            }
        }

        try (InputStream is = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contents.add(line);
            }
        } catch (IOException e) {
            throw new FileError("Problem reading from file: " + e.getMessage());
        }

        String[] data = contents.toArray(new String[0]);
        return convertStrToArray(data);
    }

    /**
     * The method is a helper function that helps with reading from txt file
     * converts data from a String[] to an ArrayList
     * @param data of the form String[]
     * @return ArrayList
     */
    private ArrayList<Task> convertStrToArray(String[] data) throws FileAccessError {
        ArrayList<Task> taskListArray = new ArrayList<>();
        for (String datum : data) {
            try {
                String[] splitData = datum.split(" \\| ");
                String type = splitData[0];
                Boolean isMarked = Objects.equals(splitData[1], "1");
                String description = splitData[2];
                Task toAdd;
                switch (type) {
                case "T":
                    toAdd = new ToDo(description, isMarked);
                    break;
                case "D":
                    toAdd = new Deadline(description, isMarked, splitData[3]);
                    break;
                case "E":
                    toAdd = new Event(description, isMarked, splitData[3], splitData[4]);
                    break;
                default:
                    throw new FileAccessError("Error reading from data.txt");
                }
                taskListArray.add(toAdd);
            } catch (IndexOutOfBoundsException e) {
                throw new FileAccessError("Error reading from data.txt");
            }
        }
        return taskListArray;
    }

    /**
     * The method is a helper function that helps with writing to txt file
     * converts data from an ArrayList to a String
     * @param tasks of the form ArrayList
     * @return String
     */
    private String convertArrayToStr(ArrayList<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(tasks.get(i).prepareForStorage());
            if (i < tasks.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
}
