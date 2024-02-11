package chatbot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Represents class responsible for interacting with the file system, to persist tasks users have made.
 */
public class Storage {
    /**
     * Constructor that checks whether a storage file already exists, otherwise creates one.
     *
     * @throws IOException If writing the new file to disk fails.
     */
    public Storage() throws IOException {
        File file = new File("store.ser");
        if (!file.exists()) {
            FileOutputStream fileOut = new FileOutputStream("store.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(new ArrayList<Task>());
            out.close();
            fileOut.close();
        }
    }

    /**
     * Reads the persisted data from the file system, deserialising it into Java classes for operation.
     *
     * @return The deserialised Java ArrayList class.
     * @throws IOException If there is an issue reading from file.
     * @throws ClassNotFoundException If there is an issue deserialising the data.
     */
    public ArrayList<Task> readFromStore() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("store.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        ArrayList<Task> taskList = (ArrayList<Task>) in.readObject();
        in.close();
        fileIn.close();

        return taskList;
    }

    /**
     * Writes the updated in-memory data to file.
     *
     * @param taskList The updated Java class object to persist.
     * @throws IOException If there is an issue writing to file.
     */
    public void saveToStore(ArrayList<Task> taskList) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("store.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(taskList);
        out.close();
        fileOut.close();
    }
}

