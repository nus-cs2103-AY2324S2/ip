package osiris.storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages storage in a text file format.
 */
public class TxtFileStorage extends Storage {

    /**
     * Constructs a TxtFileStorage object with the specified file path.
     *
     * @param filepath The file path for the text file storage.
     */
    public TxtFileStorage(String filepath) {
        super(filepath);
    }

    /**
     * Initializes the text file storage.
     */
    public void initialiseTxtFileStorage() {
        try {
            File file = new File(this.getFilepath());
            if (super.storageFileExist()) {
                System.out.println("Task Storage File: " + this.getFilepath());
            } else {
                if (file.createNewFile()) {
                    System.out.println("Created Task Storage File: " + this.getFilepath());
                } else {
                    System.out.println("Failed to create Task Storage file: " + this.getFilepath());
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to create new Txt file for Task storage.");
            System.out.println(e.toString());
        }
    }

    /**
     * Reads the contents of the text file storage.
     *
     * @return The contents of the text file storage as an ArrayList of strings.
     */
    public ArrayList<String> readTxtFileStorage() {

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
            System.out.println("Error: Unable to locate filepath " + this.getFilepath());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return readContents;
    }

    /**
     * Appends content to the text file storage.
     *
     * @param content The content to append to the text file storage.
     */
    public void appendToTxtFileStorage(String content) {
        try {
            FileWriter fw = new FileWriter(this.getFilepath(), true);
            fw.write(content + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to update Task Storage File. Trouble appending.");
        }
    }

    /**
     * Clears the contents of the text file storage.
     */
    public void clearTxtFileStorage() {
        try {
            FileWriter fw = new FileWriter(getFilepath());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to update Task Storage File. Trouble clearing contents.");
        }
    }


}
