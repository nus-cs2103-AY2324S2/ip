import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class FileManager {
    private static final String DIRECTORY = "./data/";
    private static final String FILENAME = "tasks.txt";
    private static File file;

    public static void loadFile() {
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

    public static void readFile() {
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

                BadGPT.loadData(type, description, deadline, from, to, isComplete);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("The file cannot be read. " + e);
        }
    }

    public static void writeToFile(String data) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.err.println("The file was not saved successfully. " + e);
        }
    }
}
