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
     * @param filename the filename
     * @return the array list
     * @throws FileNotFoundException the file not found exception
     */
    public ArrayList<String> loadFile(String filename) throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + filename);
        ArrayList<String> fileData = new ArrayList<>();

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
     * @param filename the filename
     * @param data     the data
     * @throws IOException the io exception
     */
    public void writeToFile(String filename, String data) throws IOException {
        File file = new File(System.getProperty("user.dir") + filename);
        FileWriter fw = new FileWriter(file, true);
        data = "\n" + data;
        fw.write(data);
        fw.close();
    }

    /**
     * Updates the file contents by replacing the line matching `originalData` with `newData`
     *
     * @param filename
     * @param originalData
     * @param newData
     * @throws IOException
     */
    public void updateFile(String filename, String originalData, String newData) throws IOException {
        File file = new File(System.getProperty("user.dir") + filename);
        File tempFile = new File(System.getProperty("user.dir") + "temp");
        FileWriter fw = new FileWriter(tempFile, true);

        ArrayList<String> originalFileData = this.loadFile(filename);
        for (String line : originalFileData) {
            if (line.equals(originalData)) {
                fw.write("\n" + newData);
            } else {
                fw.write("\n" + line);
            }
        }

        fw.close();
        file.delete();
        tempFile.renameTo(new File(System.getProperty("user.dir") + filename));
    }

    /**
     * Remove line from file.
     *
     * @param filename the filename
     * @param data     the data
     * @throws IOException the io exception
     */
    public void removeLineFromFile(String filename, String data) throws IOException {
        File file = new File(System.getProperty("user.dir") + filename);
        File tempFile = new File(System.getProperty("user.dir") + "temp");
        FileWriter fw = new FileWriter(tempFile, true);

        ArrayList<String> originalFileData = this.loadFile(filename);
        for (String line : originalFileData) {
            if (line.equals(data)) {
                continue;
            }

            line = "\n" + line;
            fw.write(line);
        }

        fw.close();
        file.delete();
        tempFile.renameTo(new File(System.getProperty("user.dir") + filename));
    }
}
