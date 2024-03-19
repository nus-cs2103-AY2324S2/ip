package oak.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type File utility.
 */
public class FileUtility {
    /**
     * Load file array list.
     *
     * @param filePath the filePath
     * @return the array list
     * @throws FileNotFoundException the file not found exception
     */
    public ArrayList<String> loadFile(String filePath) throws IOException {
        ArrayList<String> fileData = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
            return fileData;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            fileData.add(scanner.nextLine());
        }

        scanner.close();
        return fileData;
    }

    /**
     * Write to file.
     *
     * @param filePath the filePath
     * @param data     the data
     * @throws IOException the io exception
     */
    public void writeToFile(String filePath, String data) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        FileWriter fw = new FileWriter(file, true);
        data = "\n" + data;
        fw.write(data);
        fw.close();
    }

    /**
     * Updates the file contents by replacing the line matching `originalData` with `newData`
     *
     * @param filePath
     * @param originalData
     * @param newData
     * @throws IOException
     */
    public void updateFile(String filePath, String originalData, String newData) throws IOException {
        File file = new File(filePath);
        File tempFile = new File("temp");
        FileWriter fw = new FileWriter(tempFile, true);

        ArrayList<String> originalFileData = this.loadFile(filePath);
        for (String line : originalFileData) {
            if (line.equals(originalData)) {
                fw.write("\n" + newData);
            } else {
                fw.write("\n" + line);
            }
        }

        fw.close();
        file.delete();
        tempFile.renameTo(new File(filePath));
    }

    /**
     * Remove line from file.
     *
     * @param filePath the filePath
     * @param data     the data
     * @throws IOException the io exception
     */
    public void removeLineFromFile(String filePath, String data) throws IOException {
        File file = new File(filePath);
        File tempFile = new File("temp");
        FileWriter fw = new FileWriter(tempFile, true);

        ArrayList<String> originalFileData = this.loadFile(filePath);
        for (String line : originalFileData) {
            if (line.equals(data)) {
                continue;
            }

            line = "\n" + line;
            fw.write(line);
        }

        fw.close();
        file.delete();
        tempFile.renameTo(new File(filePath));
    }
}
