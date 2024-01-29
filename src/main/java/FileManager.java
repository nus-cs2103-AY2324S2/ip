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
        Path dir = Paths.get("data");
        try {
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
        } catch (IOException e) {
            System.out.println("Error in the IO when creating data dir");
        }

        Path file = Paths.get("data/taskData.txt");
        try {
            if (!Files.exists(file)) {
                file = Files.createFile(file);
            }
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                Parser.parseData(scanner.nextLine());
            }

        } catch (IOException e) {
            System.out.println("Error in the IO when creating taskData file");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveTasks() throws DukeException {
        try (FileWriter fw = new FileWriter("data/taskData.txt")){
            fw.write(Parser.parseToData());
        } catch (IOException e) {
            throw (new DukeException("IO Exception when saving data"));
        }

    }
}
