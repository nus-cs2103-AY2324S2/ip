package riri;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class responsible for storing and loading up task lists from local storage when the chatbot is turned off.
 */
public class Storage {
    /**
     * Reads the contents of the previously saved file that contains the tasks previously added by the user.
     *
     * @return ArrayList of tasks previously added by the user
     * @throws IOException if an I/O error occurs
     */
    public ArrayList<String> load() throws IOException {
        ArrayList<String> contents = new ArrayList<>();

        Path dirPath = Paths.get("data");
        // If data directory does not exist return an empty string
        if (!Files.exists(dirPath)) {
            return contents;
        }
        String fileName = "riri.txt";
        Path filePath = dirPath.resolve(fileName);
        // If file does not exist return an empty string
        if (!Files.exists(filePath)) {
            return contents;
        }
        // Read the contents of the file
        Scanner sc = new Scanner(filePath); // create a Scanner using the File as the source
        while (sc.hasNext()) {
            contents.add(sc.nextLine());
        }
        return contents;
    }
    /**
     * Writes the tasks the user has created to a file.
     *
     * @param textToAdd the tasks to add to the to-do list
     * @throws IOException if an I/O error occurs
     */
    public void writeToFile(String textToAdd) throws IOException {
        String dirName = "data";
        // Create data directory if it does not exist
        Path dirPath = Paths.get(dirName);
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }

        // Create file if it does not exist
        String fileName = "riri.txt";
        Path filePath = dirPath.resolve(fileName);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        // Write to proper location
        FileWriter fw = new FileWriter(filePath.toFile());
        fw.write(textToAdd);
        fw.close();
    }
}
