import task.Task;

import java.io.*;
import java.util.ArrayList;

public class Save {
    private static final String fileName = "duke.ser";
    public static void save(Object[] tasks) {
        try (FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(file);) {
            for (Object task: tasks) {
                objectOut.writeObject(task);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            throw new ChatException("Error writing duke logs file.");
        }
    }

    public static ArrayList<Task> load() {
        try (FileInputStream file = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(file);) {
            try {
                ArrayList<Task> result = new ArrayList<>();
                while(file.available() > 0) {
                    result.add((Task)objectIn.readObject());
                }
                return result;

            } catch (ClassNotFoundException e) {
                throw new ChatException("Error deserializing duke logs file.");
            }
        } catch (IOException e) {
            throw new ChatException("Error reading duke logs file:" + e.getMessage());
        }
    }
}
