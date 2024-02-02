import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    public static String readFile(String fileName) {
        StringBuilder fileData = new StringBuilder();
        try {
            File file = new File(fileName);
            if (!file.exists() || !file.isFile()) {
                return "";
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                fileData.append(reader.nextLine()).append("\n");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.printf("An error occurred. %s\n", e.getMessage());
        }
        return fileData.toString();
    }

    public static void writeFile(String fileName, TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(taskList.convertTaskListToFileString());
            writer.close();
        } catch (IOException e) {
            System.out.printf("An error occurred. %s\n", e.getMessage());
        }
    }
}
