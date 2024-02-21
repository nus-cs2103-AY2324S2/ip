package lite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import lite.task.Task;
import lite.task.TaskList;
import lite.util.FileParseInput;
import lite.util.LiteException;
public class Storage {
    // Usage inspired from 0-yibai
    private static final String PATH = "./lite.txt";

    /**
     * Saves the TaskList into a file
     * @param tasks TaskList to be saved
     */
    public static void save(TaskList tasks) {
        try {
            File file = new File(PATH);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < tasks.size(); i++) {
                bufferedWriter.write(tasks.get(i).saveToFile());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            LiteException.saveException(e);
        }
    }

    /**
     * Loads the saved tasks into the chatbot
     * @return The TaskList
     * @throws IOException Error message if failed to read from file
     */
    public static ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(PATH);

        if (!file.exists()) {
            return tasks;
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String s = bufferedReader.readLine();
        while(s != null) {
            tasks.add(FileParseInput.parse(s));
            s = bufferedReader.readLine();
        }
        return tasks;
    }
}
