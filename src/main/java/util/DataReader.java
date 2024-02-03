/**
 * Represents a FileReader object for Duke bot.
 * <p>
 * Handles reading of text data from files within the /resources directory, at data.txt.
 * Class is created to abstract the details of file output operations.
 */
package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import task.Deadline;
import task.Task;
import task.TaskStorage;
import task.Event;
import task.ToDo;

public class DataReader {
    private static final String SAVED_DATA_FILE = "data.txt";
    private static final String SAVED_DATA_DIRECTORY = "data";
    final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Reads the file line-by-line to create an ArrayList of Task and TaskStorage object.
     * Uses the convertDataFile to help convert each String line to it's corresponding Task.
     *
     * @return A TaskStorage object with the saved tasks in it.
     */
    public TaskStorage readDataFile(TextUi textUi) {
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
                textUi.printMessage(Messages.MESSAGE_SAVE_CORRUPTED);
                return new TaskStorage();
            }
            if (taskList == null) {
                textUi.printMessage(Messages.MESSAGE_SAVE_CORRUPTED);
                return new TaskStorage();
            }
            TaskStorage taskStorage = new TaskStorage(taskList);
            if (taskStorage.size() != 0) {
                String message = Messages.MESSAGE_SAVE_FOUND + "\n";
                textUi.printMessage(message + taskStorage);
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
                task = new ToDo(splitLines[2], splitLines[1]);
            }
            else if (splitLines[0].equals("Deadlines")) {
                task = new Deadline(splitLines[2], splitLines[3], splitLines[1]);
            }
            else if (splitLines[0].equals("Events")) {
                task = new Event(splitLines[2], splitLines[3], splitLines[4], splitLines[1]);
            }
            else {
                return null;
            }
            taskList.add(task);
        }
        return taskList;
    }

    /**
     * Parses a string into a LocalDateTime object.
     * 
     * @param the String to be converted.
     * @return the corrsponding LocalDateTime object created.
     */
    public LocalDateTime parseDate(String dateString) {
        return LocalDateTime.parse(dateString, DATE_FORMAT);
    } 
}