package Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtility {
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

    public void writeToFile(String filename, String data) throws IOException {
        File file = new File(System.getProperty("user.dir") + filename);
        FileWriter fw = new FileWriter(file, true);
        data = "\n" + data;
        fw.write(data);
        fw.close();
    }

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
