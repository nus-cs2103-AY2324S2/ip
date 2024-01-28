import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Used by the TaskList class, and handles reading, writing and deleting from lines in the file
 */
public class Storage {

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
        try {
            File file = new File(this.filepath); // Used for read and write operations to the file
            file.createNewFile(); // creates a new file if needed
        } catch (IOException e) {
            System.out.println("Failed to create database file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Reads all lines of the file into a List<String>
     *
     * @return List<String> that contains all lines of the file
     */
    public List<String> readLinesFromFile() {
        try {
            return Files.readAllLines(Paths.get(this.filepath));
        } catch (IOException e) {
            System.out.println("Failed to read lines from file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Given a list of strings, writes to a file
     * @param stringTaskList
     */
    public void writeLinesToFile(List<String> stringTaskList) {
        try {
            File file = new File(this.filepath);
            file.createNewFile();

            FileWriter fw = new FileWriter(this.filepath);

            for (String x : stringTaskList) {
                fw.write(x + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to write to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Deletes the contents of the file
     */
    public void clearFile() {
        try {
            Files.write(Paths.get(filepath), new byte[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "data/duketest.txt";
        Storage fileHandler = new Storage(filePath);

        // Clear and init data/duke.txt
        fileHandler.clearFile();

        List<String> input = new ArrayList<>();

        input.add("E | 0 | project meeting  | 01 October 2023 00:00 | 01 December 2024 23:59");
        input.add("E | 1 | long project meeting  | 01 May 2000 00:01 | 01 May 2020 10:00");
        input.add("D | 0 | return book | 01 May 2020 10:00");
        input.add("D | 0 | do homework | no idea :-p");
        input.add("E | 0 | project meeting 2  | Mon 2pm | 5pm");
        input.add("D | 0 | return book | today");

        fileHandler.writeLinesToFile(input);

        List<String> output = fileHandler.readLinesFromFile();

        for (String s : output) System.out.println(s);
        System.out.println(Objects.equals(input, output));

    }
}
