// import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileManager {
    public static void loadTasks() {
        Path file = Paths.get("data/fruits.txt");
        try {
            if (!Files.exists(file)) {
                file = Files.createFile(file);
            }
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                Parser.parseData(scanner.nextLine());
            }

        } catch (IOException e) {
            System.out.println("Error in the IO");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
