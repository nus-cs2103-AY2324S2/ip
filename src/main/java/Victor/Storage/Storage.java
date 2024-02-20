package victor.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import victor.tasktype.Deadline;
import victor.tasktype.Event;
import victor.tasktype.Task;
import victor.tasktype.Todo;


/**
 * The Storage class contains the method to load data from
 * a data file and store new data into said data file.
 *
 * @author Dominic Fu Ming Jun
 */
public class Storage {

    /**
     * The dataFile variable is a File that is used to store the data file.
     */
    private final File dataFile;
    /**
     * The filePath variable is a String that contains the path to the data file location.
     */
    private final String filePath;

    /**
     * The Storage Constructor will take in the filePath of the
     * data file to retrieve the data from. It would update the
     * filePath and create a new File using the filePath.
     *
     * @param filePath The file path of the data file
     */
    public Storage(String filePath) {
        this.dataFile = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Loads all the data from the dataFile and use it to fill up an empty ArrayList.
     * If there is no dataFile since they cannot find it at the filePath location,
     * then the load() will create a new Victor.txt datafile at said location.
     * It will then return an empty ArrayList if there is no datafile found.
     *
     * @return an ArrayList of tasks.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> currentLists = new ArrayList<>();
        try {
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                String[] inputs = nextLine.split("\\|");
                String fileDataType = inputs[0].trim();
                switch (fileDataType) {
                case "T" -> {
                    Task newTodo = new Todo(inputs[2].trim(), Boolean.parseBoolean(inputs[1].trim()));
                    currentLists.add(newTodo);
                }
                case "D" -> {
                    Task newDeadline = new Deadline(inputs[2].trim(), Boolean.parseBoolean(inputs[1].trim()),
                            inputs[3].trim());
                    currentLists.add(newDeadline);
                }
                case "E" -> {
                    Task newEvent = new Event(inputs[2].trim(), Boolean.parseBoolean(inputs[1].trim()),
                            inputs[3].trim(), inputs[4].trim());
                    currentLists.add(newEvent);
                }
                default -> {
                }
                }
            }
        } catch (FileNotFoundException e) {
            try {
                boolean isCreated = dataFile.createNewFile();
                System.out.println("Error: Data File not found. Creating new Data File");
            } catch (IOException e2) {
                System.out.println("Error: Cannot create hard drive file.");
                System.out.println("Data will not be saved after session end.");
            }
        }
        return currentLists;
    }

    /**
     * The updateFile is used to update the data file with the new data.
     * It currently replaces all the old data in the data file and
     * transfer all the data in the updatedArray into the data file.
     *
     * @param updatedArrays an updated ArrayList of Tasks containing the current data
     *                     after the user had ended the program.
     * @throws IOException If the program is unable to update the data to the file.
     */
    public void updateFile(ArrayList<Task> updatedArrays) throws IOException {
        int i = 0;
        try {
            FileWriter writeToFile = new FileWriter(this.filePath);
            while (i < updatedArrays.size() - 1) {
                Task nextTask = updatedArrays.get(i);
                writeToFile.write(nextTask.saveInput()
                        + System.lineSeparator());
                i++;
            }
            Task finalTask = updatedArrays.get(updatedArrays.size() - 1);
            writeToFile.write(finalTask.saveInput());
            writeToFile.close();
        } catch (IOException e) {
            System.out.println("Update File Error. Unable to save new data");
        }
    }
}
