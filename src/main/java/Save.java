import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Save {
    File file = new File("data/data.txt");

    public Save() {}
    
    // Move to error handling class?
    private void handleFileAccessErrors() throws IOException {
        try {
            file.createNewFile();
        } catch (IOException e) {
            new File("./data").mkdirs();
        } finally {
            file.createNewFile();
        }
    }
}