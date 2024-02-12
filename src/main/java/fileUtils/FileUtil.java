package fileUtils;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class FileUtil {
    public static void displayFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(contentBuilder);
    }
}
