package Duke.main;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        try {
            Path path = Paths.get("./data");
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            Path filePath = Paths.get("./data/duke.txt");
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            Duke duke = new Duke(filePath.toString());
            duke.run();
        } catch (IOException e) {
            System.out.println("An error occur while reading file");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
