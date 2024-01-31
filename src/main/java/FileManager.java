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
                char type;
                String description;
                String time;
                boolean isComplete = false;

                if (line.contains("[X]")) {
                    isComplete = true;
                }

                if (line.contains("[T]")) {
                    type = 'T';
                    description = line.substring(6).trim();
                    time = "";
                } else {
                    int index = line.indexOf("(");
                    description = line.substring(6, index).trim();
                    time = line.substring(index + 1, line.length() - 1).trim(); // Remove "(" and ")"
                    if (line.contains("[E]")) {
                        type = 'E';
                    } else {
                        type = 'D';
                    }
                }

                BadGPT.loadData(type, description, time, isComplete);
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
