package Database;

import Utils.FileUtils;
import Utils.StringUtils;

import java.io.*;
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

    public static void create(Path filePath, String line) {
        try {
            long count = Files.lines(filePath).count();
            long id = count + 1;
            line = id + " | " + line;
            FileUtils.insert(filePath, line);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error occurred when writing to " + filePath);
        }
    }

    public static String findById(String filePath, int id) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            do {
                int lineId = findId(line);
                if (lineId == id) return line;
                line = reader.readLine();
            } while (line != null);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error occurred when writing to " + filePath);
        } finally {
            return null;
        }
    }

    public static void updateById(Path filePath, int id, String newLine) {
        newLine = id + " | " + newLine;
        FileUtils.update(filePath, id, newLine);
    }
    private static int findId(String line) {
        String[] segments = StringUtils.splitDataString(line);
        return Integer.parseInt(segments[0]);
    }
}
