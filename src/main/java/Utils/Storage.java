package Utils;

import Tasks.*;
import java.io.*;
import java.util.ArrayList;

public class Storage {

    private final String filepath;
    private final String filename;

    public Storage(String fileLocation) {

        this.filepath = extractFilePath(fileLocation);
        this.filename = extractFileName(fileLocation);
    }

    /**
     * Creates a storage file if it does not exist.
     * @throws IOException, SecurityException
     */
    private void createStorageIfNotExists() throws IOException, SecurityException {
        File path = new File(this.filepath);

        //create the directory if it does not exist
        if (!path.exists()) {
            boolean created = path.mkdirs();
        }

        File file = new File(this.filepath + this.filename);
        boolean fileExists = file.exists();

        //save an empty task list to the file if it does not exist
        if (!fileExists) {
            saveTasks(new TaskList());
        }
    }


    /**
     * Deletes the storage file if it exists.
     * @throws SecurityException
     */
    public void deleteStorage() throws SecurityException {
        File file = new File(this.filepath + this.filename);

        boolean fileExists = file.exists();

        if (fileExists) {
            boolean deleted = file.delete();
        }
    }


    public void saveTasks(TaskList taskList) throws IOException, SecurityException {
        try{
            FileOutputStream fos = new FileOutputStream(this.filepath + this.filename);
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(fos);
            oos.writeObject(taskList.getList());
            oos.close();
            fos.close();
        }
        catch (IOException e){
            throw new IOException("An error occurred while saving the task list.");
        }
    }

    /**
     * Loads the task list from the storage file. Returns empty task list if no task data is found.
     * @return TaskList
     * @throws IOException, ClassNotFoundException, SecurityException
     */
    @SuppressWarnings("unchecked") //safe as only ArrayList<Task> is written to the file
    public TaskList loadTasks() throws IOException, ClassNotFoundException, SecurityException {
        createStorageIfNotExists();

        ArrayList<Task> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(this.filepath + this.filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            list = (ArrayList<Task>) ois.readObject();
            ois.close();
            fis.close();

            return TaskList.from(list);
        } catch (IOException e) {
            throw new IOException("An error occurred while loading the task list.");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("The class of the object to be loaded was not found.");
        }
    }

    private String extractFilePath(String fileLocation) {
        String f = fileLocation.trim();
        String[] parts = f.split("/");
        String path = "";
        for (int i = 0; i < parts.length - 1; i++) {
            path += parts[i] + "/";
        }
        return path;
    }

    private String extractFileName(String fileLocation) {
        String f = fileLocation.trim();
        String[] parts = f.split("/");
        return parts[parts.length - 1];
    }
}
