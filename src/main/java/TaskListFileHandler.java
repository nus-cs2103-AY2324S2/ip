import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Used by the TaskList class, and handles reading, writing and deleting from lines in the file
 */
public class TaskListFileHandler {

    private String filepath;
    private File file;

    public TaskListFileHandler(String filepath) {
        this.filepath = filepath;
        try {
            File file = new File(this.filepath); // Used for read and write operations to the file
            file.createNewFile(); // creates a new file if needed
        } catch (IOException e) {
            System.out.println("Failed to create database file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Reads all lines of the file into a List<String>
     *
     * @return List<String> that contains all lines of the file
     */
    public List<String> readLinesFromFile() throws IOException  {
        return Files.readAllLines(Paths.get(this.filepath));
    }

    /**
     * Appends text to the end of the file
     *
     * @param textToAppend is text to append to end of file
     * @throws IOException
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.filepath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    /**
     * Overwrites the content at lineNumber with newContent
     *
     * @param lineNumber to be replaced
     * @param newContent
     */
    public void writeLineToFile(int lineNumber, String newContent) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filepath));

        if (lineNumber >= 1 && lineNumber <= lines.size()) {
            lines.set(lineNumber - 1, newContent);
            Files.write(Paths.get(filepath), lines);
        } else {
            System.out.println("Line number out of range");
        }
    }

    /**
     * Reads a line at a specified line number
     *
     * @param lineNumber to read
     * @return String line which was read
     */
    public String readLineFromFile(int lineNumber) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filepath));

        if (lineNumber >= 1 && lineNumber <= lines.size()) {
            return lines.get(lineNumber - 1);
        } else {
            System.out.println("Line number out of range");
            return null;
        }
    }

    /**
     * Deletes a line at a specified line number
     *
     * @param lineNumber to delete
     */
    public void deleteLineFromFile(int lineNumber) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filepath));

        if (lineNumber >= 1 && lineNumber <= lines.size()) {
            lines.remove(lineNumber - 1);
            Files.write(Paths.get(filepath), lines);
        } else {
            System.out.println("Line number out of range");
        }
    }

    /**
     * Deletes the contents of the file
     */
    public void clearFile() {
        try {
            Files.write(Paths.get(filepath), new byte[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "data/duketest.txt";
        TaskListFileHandler fileHandler = new TaskListFileHandler(filePath);

        // Clear and init data/duke.txt
        fileHandler.clearFile();
        try {
            fileHandler.appendToFile( "T | 1 | read book");
            fileHandler.appendToFile("D | 0 | return book | June 6th");
            fileHandler.appendToFile("E | 0 | project meeting | Aug 6th 2 | 4pm");
            fileHandler.appendToFile( "T | 1 | join sports club");
        } catch (IOException e) {
            System.out.println("Append to file failed: " + e.getMessage());
        }

        // Print lines
        try {
            fileHandler.readLinesFromFile().forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("read lines from file failed: " + e.getMessage());
        }

        // Write to line number 2
        System.out.println();
        System.out.println("Writing to line number 2");
        try {
            fileHandler.writeLineToFile(2, "D | 0 | updated task | June 8th");
        } catch (IOException e) {
            System.out.println("write lines to file failed: " + e.getMessage());
        }

        // Print lines
        try {
            fileHandler.readLinesFromFile().forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("read lines from file failed: " + e.getMessage());
        }

        // Read from line number 3
        System.out.println();
        try {
            String line = fileHandler.readLineFromFile(3);
            System.out.println("Read L3: " + line);
        } catch (IOException e) {
            System.out.println("readlines failed: " + e.getMessage());
        }

        // Delete L4
        System.out.println("Delete from L2");
        try {
            fileHandler.deleteLineFromFile(2);
        } catch (IOException e) {
            System.out.println("delete lines failed: " + e.getMessage());
        }

        // Display updated file content
        try {
            fileHandler.readLinesFromFile().forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("read lines from file failed: " + e.getMessage());
        }
    }
}
