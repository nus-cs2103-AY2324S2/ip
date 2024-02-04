package duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    private File file;
    private Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
        this.file = new File(String.valueOf(filePath));
    }

    public boolean isFileCreated() {
        return Files.exists(this.filePath);
    }

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
        }

        catch (Exception e) {
            System.out.println("Error loading task: " + e.getMessage());
        }

        System.out.println("Error in loading task!");
        return new ArrayList<Task>();
    }

    public void updateStorageFile(ArrayList<Task> myList) {
        try{
            FileOutputStream writeData = new FileOutputStream(this.file);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(myList);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            System.out.println("Error updating storage file: " + e);
        }
    }


}
