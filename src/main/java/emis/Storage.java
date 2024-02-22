package emis;

import emisExceptions.EmisException;
import emisTask.Event;
import emisTask.ToDo;
import emisTask.Deadline;
import emisTask.Task;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;

/** The Storage class deals with loading tasks from and saving tasks to a file in the EMIS application. */
public class Storage {

    /** The file path of the storage file. */
    private String filePath;

    /** The list of tasks loaded from the storage file. */
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
                // read from file, add to arraylist al
                String input = scanner.nextLine();
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
            scanner.close();
            
        } catch (FileNotFoundException FNFe) {
            // file does not exist yet, so create
            try {
                File createFile = new File(this.filePath);
                createFile.getParentFile().mkdirs();
                createFile.createNewFile();
            } catch (IOException IOe) {
                System.out.println("An error occurred while creating the file.");
            }
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
}