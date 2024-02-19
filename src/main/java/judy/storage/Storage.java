package judy.storage;
import judy.task.*;

import java.io.*;
import java.util.ArrayList;


public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public boolean isFileExists() {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            System.out.println("Error checking file existence: " + e);
            return false;
        }
    }

    public void createNewFile() {
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create new file");
        }
    }

    public ArrayList<Task> load() {
        if (file.length() == 0) {
            return new ArrayList<>(); // Handle empty file
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder encodedTasks = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                encodedTasks.append(line).append("\n");
            }

            ArrayList<Task> decodedTasks = Decoder.decodeTasks(encodedTasks.toString()).getTaskList();

            // Check if decoding resulted in null
            if (decodedTasks == null) {
                // Handle the null case (log, display an error, etc.)
                System.err.println("Error decoding tasks from file. Null result.");
                return new ArrayList<>();
            }

            return new ArrayList<>(decodedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public void save(ArrayList<Task> taskList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            String encodedTasks = Encoder.encodeTasks(taskList);
            writer.print(encodedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
