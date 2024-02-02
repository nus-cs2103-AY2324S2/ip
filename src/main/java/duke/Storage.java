package duke;
import java.io.*;
import java.util.ArrayList;

public class Storage {
    private File folder = new File("data");
    private File file = new File(folder, "data.txt");
    public void check() {
        if (!folder.exists()) {
            folder.mkdir();
            file = new File(folder, "data.txt");
        }
        if (!file.exists()) {
            file = new File(folder, "data.txt");
        }
    }
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
    public void add(String n) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(n);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
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
