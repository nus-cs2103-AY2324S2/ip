package badgpt.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the loading from and saving to the text file.
 */
public class FileManager {
    private final String DIRECTORY = "./data/";
    private final String FILENAME = "tasks.txt";
    private File file;

    /**
     * Loads the text file using the specified directory and filename. If the file does not exist, a new one is created.
     */
    public void loadFile() {
        File directory = new File(DIRECTORY);
        file = new File(DIRECTORY, FILENAME);

        if (!directory.exists()) {
            directory.mkdir();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e + "Please rerun the bot.");
            }
        }
    }

    /**
     * Reads the file and sends the data to a TaskList instance to be stored.
     *
     * @param taskList The current TaskList instance.
     */
    public void readFile(TaskList taskList) {
        assert file.exists();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                String[] taskInfo = line.split(" ");
                char type;
                String deadline = null;
                String from = null;
                String to = null;
                String description;
                boolean isComplete = false;

                if (line.contains("[X]")) {
                    isComplete = true;
                }

                if (line.contains("[T]")) {
                    type = 'T';
                    description = line.substring(6).trim();
                } else if (line.contains("[E]")) {
                    type = 'E';
                    int fromIdx = line.indexOf("from:");
                    int toIdx = line.indexOf("to:");
                    description = line.substring(6, fromIdx).trim();
                    from = line.substring(fromIdx + 5, toIdx).trim();
                    to = line.substring(toIdx + 3).trim();
                } else {
                    type = 'D';
                    int by = line.indexOf("by:");
                    description = line.substring(6, by).trim();
                    deadline = line.substring(by + 3).trim();
                }

                taskList.loadData(type, description, deadline, from, to, isComplete);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("The file cannot be read. " + e);
        }
    }

    /**
     * Writes to the file.
     *
     * @param data The data to be written.
     */
    public void writeToFile(String data) {
        assert file.exists();
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.err.println("The file was not saved successfully. " + e);
        }
    }
}
