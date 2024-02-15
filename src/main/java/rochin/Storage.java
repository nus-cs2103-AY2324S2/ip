package rochin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks from the specified file.
     *
     * @return A list of strings representing the loaded tasks.
     * @throws RochinOOP.RochinException If an error occurs while loading tasks from the file.
     */
    public List<String> load() throws RochinOOP.RochinException {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    lines.add(scanner.nextLine());
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            throw new RochinOOP.RochinException("Error loading tasks from file.");
        }
        return lines;
    }

    /**
     * Save tasks to the specified file.
     *
     * @param lines The list of strings representing tasks to be saved.
     * @throws RochinOOP.RochinException If an error occurs while saving tasks to the file.
     */
    public void save(List<String> lines) throws RochinOOP.RochinException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            throw new RochinOOP.RochinException("Error saving tasks to file.");
        }
    }
}
