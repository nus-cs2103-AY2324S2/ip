package baron.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import baron.utils.FileUtils;
import baron.utils.StringUtils;

/**
 * Handles CRUD operations for a given file path, create, read, update and delete
 */
public class Database {

    // Base directory for the files to be read from. Defaults to root directory
    public static final String BASE_DIR = System.getProperty("user.dir");

    /**
     * Gets the File object from the BASE_DIR with the specified fileName.
     * Creates the file if it doesn't exist
     *
     * @param fileName File name of the file to read from
     * @return the file object of the table
     */
    public static File getTable(String fileName) {
        try {
            String filePath = BASE_DIR + "/" + fileName + ".txt";
            File db = new File(filePath);
            if (!db.exists() && db.createNewFile()) {
                System.out.println("Creating new file");
            }
            assert Files.exists(db.toPath()) : (filePath + " failed to be created fucker");
            return db;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Appends a new line to the end of the file
     *
     * @param filePath Filepath to write
     * @param line     Line to append to the end of file
     * @return the id of the newly appended line, which is actually just lineNumber - 1
     */
    public static long create(Path filePath, String line) {
        try {
            assert Files.exists(filePath) : filePath + " doesn't exist";

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

    /**
     * TODO: Currenlty unused, but will be used in Level-9
     *
     * @param filePath file name of the file
     * @param id       the id to find. Does not refer to line number, but the actual id,
     *                 which is the first number of each line
     * @return Returns the line if it exists, or null otherwise
     */
    public static String findById(String filePath, int id) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            do {
                int lineId = findId(line);
                if (lineId == id) {
                    return line;
                }
                line = reader.readLine();
            } while (line != null);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error occurred when writing to " + filePath);
        } finally {
            return null;
        }
    }

    /**
     * This just extracts the ID from the given line
     *
     * @param line line to extract id from
     * @return the id, if it exists
     */
    private static int findId(String line) {
        String[] segments = StringUtils.splitDataString(line);
        return Integer.parseInt(segments[0]);
    }

    /**
     * Finds the line with the given ID and updates it
     *
     * @param filePath fulle file path of the file to update
     * @param id       id of the line
     * @param newLine  the new line data to update
     */
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

    /**
     * Deletes a line with the specified. In case the line doesn't exist, it will just throw an
     * index out of bounds execption for now
     *
     * @param filePath full path of file to perofrm IO on
     * @param id       id of line to delete
     */
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
}
