package ping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ping.exceptions.PingException;
import ping.job.Task;




/**
 * This class is used to save and load the file
 */
@SuppressWarnings("unchecked")
public class Storage {
    private static final String PATH = "./data/duke.txt";
    /**
     * This method is used to save the file
     * @param tasks the list of tasks
     */
    public static void saveFiles(ArrayList<Task> tasks) throws PingException {
        try {
            File file = new File(PATH);
            File dir = new File("./data");

            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oot = new ObjectOutputStream(fout);
            oot.writeObject(tasks);
            oot.close();
            fout.close();
        } catch (IOException e) {
            throw new PingException("An error occurred while saving the file");
        }
    }
    /**
     * This method is used to load the file
     * @return the list of tasks
     */
    public static ArrayList<Task> loadFiles() throws PingException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(PATH);

        if (!file.exists()) {
            return tasks;
        }

        try {
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            tasks = (ArrayList<Task>) objInputStream.readObject();
            objInputStream.close();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new PingException("No existing file found\n"
            + "Dont'worry, I already creat an empty list for you\n"
            + "ChatBot Ping now could be used!");
        }
        return tasks;
    }
}
