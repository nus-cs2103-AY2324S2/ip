package homie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Storage class to deal with storing of tasks and
 * loading of tasks when the homie chatbot starts, ends or have
 * any updates in the task list
 */
public class Storage {
    private static final String CURRENT_DIRECTORY_STRING = System.getProperty("user.dir");
    private static final Path STORAGE_DIRECTORY_PATH = Paths.get(CURRENT_DIRECTORY_STRING, "data");
    public static final Path STORAGE_FILE_PATH = Paths.get(STORAGE_DIRECTORY_PATH.toString(), "data.txt");
    private File myStorageFile;

    /**
     * Constructor for Storage class
     */
    public Storage() throws IOException {
        File storageDirectory = new File(STORAGE_DIRECTORY_PATH.toString());
        if (!storageDirectory.exists()) {
            createStorageDirectory(STORAGE_DIRECTORY_PATH);
        }
        File storageFile = new File(STORAGE_FILE_PATH.toString());
        if (!storageFile.exists()) {
            createStorageFile(STORAGE_FILE_PATH);
        }
        this.myStorageFile = storageFile;
    }

    /**
     * Creates a directory at the data folder, nested in the project root.
     *
     * @param directoryPath The path of the directory.
     * @throws IOException The exception thrown when there is an error creating directory.
     */
    public void createStorageDirectory(Path directoryPath) throws IOException {
        File f = new File(directoryPath.toString());
        try {
            if (!f.exists()) {
                Files.createDirectory(STORAGE_DIRECTORY_PATH);
                System.out.println("directory created!");
            } else {
                System.out.println("create directory fail!");
            }
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Method to create the storage text file if file is not yet created.
     */
    public void createStorageFile(Path filePath) {
        File f = new File(filePath.toString());
        try {
            if (f.createNewFile()) {
                System.out.println("file created!");
            } else {
                System.out.println("create file fail!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to load tasks from the text file into the tasks object.
     * @return Returns an ArrayList of type Tasks loaded with tasks from the text file to the caller.
     */
    public ArrayList<Task> loadTasksFromFile() {
        try {
            FileInputStream readTasks = new FileInputStream(this.myStorageFile);
            if (readTasks.available() == 0) {
                readTasks.close();
                System.out.println("No tasks in file.");
                return new ArrayList<Task>();
            }

            ObjectInputStream readStream = new ObjectInputStream(readTasks);

            // The file will only contain ArrayList<Task> object.
            // So it is safe to typecast.
            @SuppressWarnings("unchecked")
            ArrayList<Task> taskList = (ArrayList<Task>) readStream.readObject();
            readStream.close();
            return taskList;
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
            FileOutputStream writeData = new FileOutputStream(this.myStorageFile);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(taskList.getListOfTasks());
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            System.out.println("Error updating storage file: " + e);
        }
    }


}
