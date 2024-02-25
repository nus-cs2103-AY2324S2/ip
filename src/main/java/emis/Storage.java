package emis;

import exceptions.EmisException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;

/** The Storage class deals with loading tasks from and saving tasks to a file in the EMIS application. */
public class Storage {
    private String filePath;
    private ArrayList<Task> taskList;

    /**
     * Constructs a new Storage object with the specified file path.
     * 
     * @param filePath The file path of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskList = new ArrayList<>();
    }

    /**
     * Loads tasks from the storage file.
     * 
     * @return The list of tasks loaded from the storage file.
     * @throws EmisException If an error occurs during loading tasks from the storage file.
     */
    public ArrayList<Task> loadFromStorageFile() throws EmisException {
        try {
            Scanner scanner = new Scanner(new File(this.filePath));
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                readFileData(input);
            } 
            scanner.close();
            
        } catch (FileNotFoundException FNFe) {
            createStorageFile();
        }
        return this.taskList;
    }

    /**
     * Updates the storage file with the current list of tasks.
     */
    public void updateStorage() {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            String taskToBeAdded = "";
            for (int i = 0; i < this.taskList.size(); i++) {
                taskToBeAdded += this.taskList.get(i).storeString();
                taskToBeAdded += "\n";
            }
            fileWriter.write(taskToBeAdded);
            fileWriter.close();
        } catch (IOException IOe) {
            IOe.printStackTrace();
        }
    }

    /**
     * Creates a storage file if it does not exist.
     */
    public void createStorageFile() {
        try {
            File createFile = new File(this.filePath);
            createFile.getParentFile().mkdirs();
            createFile.createNewFile();
        } catch (IOException IOe) {
            System.out.println("An error occurred while creating the file.");
        }
    }

    /**
     * Reads data from the storage file and creates tasks accordingly.
     * 
     * @param input The input data read from the storage file.
     * @throws EmisException If the input data is invalid or cannot be processed.
     */
    public void readFileData(String input) throws EmisException {
        String[] data = input.split("\\|");
        String taskType = data[0].trim();
        String retrieveStatus = data[1].trim();
        boolean convertStatus = retrieveStatus.equals("1");
        String retrieveDescription = data[2].trim();

        if (taskType.equals("T")) {
            this.taskList.add(new ToDo(convertStatus, retrieveDescription));

        } else if (taskType.equals("D")) {
            String finishBy = data[3].trim();
            this.taskList.add(new Deadline(convertStatus, retrieveDescription, finishBy));

        } else if (taskType.equals("E")) {
            String startFrom = data[3].trim();
            String endBy = data[4].trim();
            this.taskList.add(new Event(convertStatus, retrieveDescription, startFrom, endBy));

        } else {
            throw new EmisException("Invalid input type.");
        }
    }
}
