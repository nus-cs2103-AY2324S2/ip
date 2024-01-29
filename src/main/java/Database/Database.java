package Database;

import Models.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Database {
    private static final String FILE_PATH = "database.db";
    public static File getTable(String fileName) {
        String baseDir = System.getProperty("user.dir") + "/src/main/java/Database";
        try {
            String filePath = baseDir + "/" + fileName + ".txt";
            File db = new File(filePath);
            if (!db.exists() && db.createNewFile()) {
                System.out.println("Creating new file");
            }
            return db;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void create(String filePath, String line) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            long count = Files.lines(Path.of(filePath)).count();
            long id = count + 1;
            writer.write(id + " | " + line);
            writer.write("\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error occurred when writing to " + filePath);
        }
    }
}
