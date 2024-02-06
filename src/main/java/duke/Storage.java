package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Storage class to deal with storing of tasks and
 * loading of tasks when the duke chatbot starts, ends or have
 * any updates in the task list
 */
public class Storage {
    private File file;
    private Path filePath;

    /**
     * Constructor for Storage class
     * @param filePath This is the file path of the storage text file
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
        this.file = new File(String.valueOf(filePath));
    }

    public boolean isFileCreated() {
        return Files.exists(this.filePath);
    }

    /**
     * Method to create the storage text file if file is not yet created.
     */
    public void createStorageFile() {
        try {
            if (this.file.createNewFile()) {
                System.out.println("File created: " + this.file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("cannot create file" + e.getMessage());
        }
    }

    /**
     * Method to load tasks from the text file into the tasks object.
     * @return Returns an ArrayList of type Tasks loaded with tasks from the text file to the caller.
     */
    public ArrayList<Task> loadTasksFromFile() {
        try {
            FileInputStream readTasks = new FileInputStream(this.file);
            ObjectInputStream readStream = new ObjectInputStream(readTasks);

            // The file will only contain ArrayList<Task> object.
            // So it is safe to typecast.
            @SuppressWarnings("unchecked")
            ArrayList<Task> myList = (ArrayList<Task>) readStream.readObject();
            readStream.close();
            return myList;
        } catch (Exception e) {
            System.out.println("Error loading task: " + e.getMessage());
        }

        System.out.println("Error in loading task!");
        return new ArrayList<Task>();
    }

    /**
     * Method to update storage text file whenever there are changes to the task list.
     * @param taskList This is the tasklist to update.
     */
    public void updateStorageFile(TaskList taskList) {
        try {
            FileOutputStream writeData = new FileOutputStream(this.file);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(taskList.getListOfTasks());
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            System.out.println("Error updating storage file: " + e);
        }
    }


}
