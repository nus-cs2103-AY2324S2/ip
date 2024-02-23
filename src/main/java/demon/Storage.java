package demon;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the central point for loading and modifying the taskList.txt file.
 */
public class Storage {
    List<String> storageArray;
    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a list of Strings from the text file to load into the chatbot upon starting up.
     *
     * @return A list of Strings.
     * @throws IOException if fails to read the file
     */
    public List<String> load() throws IOException {
        // Read all lines from the file as a List
        createNewFile();
        this.storageArray = Files.readAllLines(Paths.get(filePath));
        return this.storageArray;
    }

    public void reWriteFile(int lineToEdit) {
        Path path = Paths.get(this.filePath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            String currentLineContent;
            int currentLineNum = 0;

            while ((currentLineContent = reader.readLine()) != null) {
                currentLineNum++;
                if (currentLineNum == lineToEdit) break;
            }

            assert currentLineContent != null;
            String[] partsOfString = currentLineContent.split("\\|");
            partsOfString[1] = partsOfString[1].trim().equals("1") ? " 0 " : " 1 ";
            String newContent = String.join("|", partsOfString);

            // Read all lines from the file
            List<String> lines = Files.readAllLines(path);

            // Check if the line to edit is within the file's line count
            if (lineToEdit <= lines.size()) {
                // Modify the specific line (adjust for 0-based index)
                lines.set(lineToEdit - 1, newContent);
            } else {
                System.err.println("The line number to edit is beyond the file's line count.");
            }
            Files.write(path, lines);
            reader.close();
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private void createNewFile() {
        File f = new File(this.filePath);
        File parentDir = f.getParentFile();
        if (parentDir != null) {
            parentDir.mkdirs();
        }
    }
    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public void removeFromFile(int lineNum) throws IOException {
        Path path = Paths.get(this.filePath);
        List<String> lines = Files.readAllLines(path);
        List<String> newArray = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (lineNum != i) {
                newArray.add(lines.get(i));
            }
        }
        Files.write(path, newArray);
    }
}
