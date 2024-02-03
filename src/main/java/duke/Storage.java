package duke;
import java.io.*;
import java.util.ArrayList;
/**
 * Simple storage class that creates data/data.txt if doesnt exist, and then write to it as instructed by the parser
 * @author Cedric
 */
public class Storage {
    private File folder = new File("data");
    private File file = new File(folder, "data.txt");
    /**
     * Simply checks for folder/file existance and creates one at startup
     */
    public void check() {
        if (!folder.exists()) {
            folder.mkdir();
            file = new File(folder, "data.txt");
        }
        if (!file.exists()) {
            file = new File(folder, "data.txt");
        }
    }
    /**
     * Clears the storage data.txt file
     */
    public void clear() {
        if (!folder.exists()) {
            folder.mkdir();
            file = new File(folder, "data.txt");
        }
        if (file.exists()) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(""); // This clears all lines in the file
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * Simply writes the input string into the file then adds a newline
     * @param n String to add to the list.
     */
    public void add(String n) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(n);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    /**
     * Finds the line to delete, takes all the other lines into the arraylist, clear the file then rewrite
     * @param number which is the line to delete starting from 0
     */
    public void delete(int number) {
        // Read the content of the file
        ArrayList<String> lines = new ArrayList<>();
        String currentLine;
        int currLine = 0;
        while ((currentLine = read(currLine)) != null) {
            // Check if the line should be deleted
            if (currLine != number) {
                lines.add(currentLine);
            }
            currLine = currLine + 1;
        }
        clear();
        for (String Line : lines) {
            add(Line);
        }
    }
    /**
     * Finds the line at line n and returns it
     * @param n line to be read starting from 0
     * @return String of the line if it exists
     */
    public String read(int n) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine = null;
            int currLine = 0;
            while (currLine <= n) {
                currentLine = reader.readLine();
                currLine = currLine + 1;
            }
            return currentLine;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
    /**
     * finds the input line, replaces it with String L, and rewrites the whole file
     * @param number line to edit
     * @param L String to change the line into
     */
    public void edit(int number, String L) {
        // Read the content of the file
        ArrayList<String> lines = new ArrayList<>();
        String currentLine;
        int currLine = 0;
        while ((currentLine = read(currLine)) != null) {
            // Check if the line should be deleted
            if (currLine != number) {
                lines.add(currentLine);
            } else {
                lines.add(L);
            }
            currLine = currLine + 1;
        }
        clear();
        for (String Line : lines) {
            add(Line);
        }

    }
}
