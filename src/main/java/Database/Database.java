package Database;

import Utils.FileUtils;
import Utils.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Database {
    private static final String FILE_PATH = "database.db";
    public static final String BASE_DIR = System.getProperty("user.dir") + "/src/main/java/Database";
    public static File getTable(String fileName) {

        try {
            String filePath = BASE_DIR + "/" + fileName + ".txt";
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

    public static long create(Path filePath, String line) {
        try {
            long count = Files.lines(filePath).count();
            long id = count;
            line = id + " | " + line;

            List<String> lines = FileUtils.read(filePath);
            lines.add(line);
            FileUtils.write(filePath, lines);

            return id;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error occurred when writing to " + filePath);
        }
        return -1; // -1 indicates error
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

    public static void updateById(Path filePath, long id, String newLine) {
        newLine = id + " | " + newLine;
        List<String> lines = FileUtils.read(filePath);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            long taskId = findId(line);
            if (taskId == id) {
                lines.set(i, newLine);
            }
        }
        FileUtils.write(filePath, lines);
    }

    public static void delete(Path filePath, long id) {
        List<String> lines = FileUtils.read(filePath);
        int idToDelete = -1;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            long taskId = findId(line);
            if (taskId == id) {
                // Avoid concurrent moficiation
                idToDelete = i;
                break; // Breaking because we assume that ID is unique
            }
        }
        lines.remove(idToDelete);
        FileUtils.write(filePath, lines);
    }

    private static int findId(String line) {
        String[] segments = StringUtils.splitDataString(line);
        return Integer.parseInt(segments[0]);
    }
}
