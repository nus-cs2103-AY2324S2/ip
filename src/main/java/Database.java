import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

/**
 * Represents a database that stores and loads tasks in a file.
 */
public class Database {
    private static final String FILE_PATH = "./savedTasks.txt";
    //saveToFile and readFromFile methods adapted from ChatGPT output
    public static void saveToFile(String tasks) {
        try {
            Files.write(Paths.get(FILE_PATH), tasks.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile() {
        try {
            return Files.readString(Paths.get(FILE_PATH));
        } catch (NoSuchFileException e) {
            // Handle the case where the file does not exist yet
            createEmptyFile();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void createEmptyFile() {
        try {
            Files.createFile(Paths.get(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
