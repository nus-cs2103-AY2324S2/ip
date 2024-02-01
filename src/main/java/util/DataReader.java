/**
 * Represents a FileReader object for Duke bot.
 * <p>
 * Handles reading of text data from files within the /resources directory, at data.txt.
 * Class is created to abstract the details of file output operations.
 */
package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import task.Deadlines;
import task.Task;
import task.TaskStorage;
import task.Events;
import task.ToDo;

public class DataReader {
    private static final String SAVED_DATA_FILE = "data.txt";
    private static final String SAVED_DATA_DIRECTORY = "data";
    /**
     * Reads the file line-by-line to create an ArrayList of Task and TaskStorage object.
     * Uses the convertDataFile to help convert each String line to it's corresponding Task.
     *
     * @return A TaskStorage object with the saved tasks in it.
     */
    public TaskStorage readDataFile() {
        String currentDirectory = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(currentDirectory, SAVED_DATA_DIRECTORY, SAVED_DATA_FILE);
        
        boolean fileExists = false;
        ArrayList<String> fileLines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            String line = br.readLine();
            while (line != null) {
                fileLines.add(line);
                line = br.readLine();
            }
            fileExists = true;
        } catch (Exception e) {
        }

        if (fileExists) {
            ArrayList<Task> taskList = null;
            try {
                taskList = convertDataFile(fileLines);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Saved data corrupted, will begin anew");
                System.out.println("______________________________________");
                return new TaskStorage();
            }
            if (taskList == null) {
                System.out.println("Saved data corrupted, will begin anew");
                System.out.println("______________________________________");
                return new TaskStorage();
            }
            TaskStorage taskStorage = new TaskStorage(taskList);
            if (taskStorage.size() != 0) {
                System.out.println("Saved data found:");
                System.out.println(taskStorage);
            }
            return taskStorage;
        }
        else {
            return new TaskStorage();
        }
    }

    /**
     * Takes in an ArrayList of Strings, and converts each string to a Task.
     * Saves the tasks into a ArrayList that will be used to create a TaskStorage object in the future.
     *
     * @return An ArrayList of Tasks that were saved in the data.txt file.
     */
    public ArrayList<Task> convertDataFile(ArrayList<String> s) {
        ArrayList<Task> taskList = new ArrayList<>();
        for (String line: s) {
            Task task;
            String[] splitLines = line.split("\\|");
            if (splitLines[0].equals("ToDo")) {
                task = new ToDo(splitLines[1]);
            }
            else if (splitLines[0].equals("Deadlines")) {
                task = new Deadlines(splitLines[1], splitLines[2]);
            }
            else if (splitLines[0].equals("Events")) {
                task = new Events(splitLines[1], splitLines[2], splitLines[3]);
            }
            else {
                return null;
            }
            taskList.add(task);
        }
        return taskList;
    }
}