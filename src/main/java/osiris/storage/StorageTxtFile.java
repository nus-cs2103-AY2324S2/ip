package osiris.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import osiris.exceptions.OsirisStorageFileException;

/**
 * Manages storage in a text file format.
 */
public class StorageTxtFile extends Storage {

    /**
     * Constructs a TxtFileStorage object with the specified file path.
     *
     * @param filepath The file path for the text file storage.
     */
    public StorageTxtFile(String filepath) {
        super(filepath);
    }

    /**
     * Initializes the text file storage.
     *
     * @throws OsirisStorageFileException If error creating new Txt file for storage.
     */
    public void initialiseStorageTxtFile() {
        try {
            File file = new File(this.getFilepath());
            if (super.doesStorageFileExist()) {
                System.out.println("Task Storage File: " + this.getFilepath());
            } else if (file.createNewFile()) {
                System.out.println("Created Task Storage File: " + this.getFilepath());
            } else {
                System.err.println("Failed to create Task Storage file: " + this.getFilepath());
            }
        } catch (IOException e) {
            System.err.println("Error: Unable to create new Txt file for Task storage.");
            throw new OsirisStorageFileException("Error: Unable to create new Txt file for Task storage.");
        }
    }

    /**
     * Reads the contents of the text file storage.
     *
     * @return The contents of the text file storage as an ArrayList of strings.
     * @throws OsirisStorageFileException If error reading the Txt storage file.
     */
    public ArrayList<String> readStorageTxtFile() {
        ArrayList<String> readContents = new ArrayList<>();
        Scanner scanner = null;
        try {
            File file = new File(this.getFilepath());
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                readContents.add(scanner.nextLine());
            }
            return readContents;
        } catch (FileNotFoundException e) {
            System.err.println("Error: Unable to locate filepath " + this.getFilepath());
            throw new OsirisStorageFileException("Error: Unable to locate filepath " + this.getFilepath());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Appends content to the text file storage.
     *
     * @param content The content to append to the text file storage.
     * @throws OsirisStorageFileException If error appending task to the Txt storage file.
     */
    public void appendToStorageTxtFile(String content) {
        try {
            FileWriter fw = new FileWriter(this.getFilepath(), true);
            fw.write(content + "\n");
            fw.close();
        } catch (IOException e) {
            System.err.println("Error: Unable to update Task Storage File. Trouble appending.");
            throw new OsirisStorageFileException("Error: Unable to update Task Storage File. Trouble appending.");
        }
    }

    /**
     * Clears the contents of the text file storage.
     *
     * @throws OsirisStorageFileException If error clearing data on the Txt storage file.
     */
    public void clearStorageTxtFile() {
        try {
            FileWriter fw = new FileWriter(getFilepath());
            fw.close();
        } catch (IOException e) {
            System.err.println("Error: Unable to update Task Storage File. Trouble clearing contents.");
            throw new OsirisStorageFileException("Error: Unable to update Task Storage File. "
                    + "Trouble clearing contents.");
        }
    }

}
