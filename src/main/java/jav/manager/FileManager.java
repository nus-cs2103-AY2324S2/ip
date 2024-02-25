package jav.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
* FileManager handles loading and storing of data in local files.
*/
public class FileManager {
    /** Name of file directory */
    private String fileDirectory;

    /** Name of text file for the storage data. */
    private String storageFileName;

    // Singleton pattern but lazy loaded from wiki https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    // Wanted a singleton pattern and this seemed the best.
    private FileManager() {
        fileDirectory = "bin" + File.separatorChar + "SavedData" + File.separatorChar;
        new File(fileDirectory).mkdirs();
        storageFileName = "StorageData.txt";

    }
    private static class LazyHolder {
        static final FileManager INSTANCE = new FileManager();
    }
    public static FileManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Saves data into a file.
     *
     * @param data a string containing the data to be saved.
     * @param fileName the name of the file.
     * @throws IOException if an I/O error occurs.
     */
    public void save(String data, String fileName) throws IOException {
        try {
            // Check if file exists already
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            // Write data into file
            PrintWriter pw = new PrintWriter(new File(fileName));
            pw.write(data);

            // Don't leave the stream hanging
            pw.flush();
            pw.close();
        } catch (Exception e) {
            throw new IOException("FileManager cannot save data to " + fileName, e);
        }
    }

    /**
     * Saves data into the storage data file.
     *
     * @param data a string containing the data to be saved.
     * @throws IOException if an I/O error occurs.
     */
    public void saveStorageData(String data) throws IOException {
        try {
            save(data, fileDirectory + storageFileName);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Reads and returns the data from a file.
     *
     * @param fileName the name of the file to read from.
     * @return the data from the file.
     * @throws IOException if an I/O error occurs
     */
    public String load(String fileName) throws IOException {
        try {
            // Check if file exists
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            // Read data from file
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder((int) file.length());
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }

            // Don't leave the stream hanging
            br.close();
            fr.close();

            return sb.toString();
        } catch (IOException e) {
            throw new IOException("FileManager cannot load data from " + fileName, e);
        }
    }


    /**
     * Loads the data from the storage data file.
     *
     * @return a string containing the loaded data.
     * @throws IOException if an I/O error occurs.
     */
    public String loadStorageData() throws IOException {
        try {
            return load(fileDirectory + storageFileName);
        } catch (IOException e) {
            throw e;
        }
    }
}
