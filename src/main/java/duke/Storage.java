package duke;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * Represents a Storage system which saves and loads tasks in a File f.
 */
public class Storage {
    private File f;

    public Storage (File f) {
        this.f = f;
    }

    /**
     * Gets the File content of f.
     *
     * @throws FileNotFoundException When data file doesn't exist.
     */
    public void getFileContent() {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                System.out.println("    " + s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found! try again xx");
        }
    }

    /**
     * Getter function that returns the File f.
     *
     * @return File f.
     */
    public File getFile() {
        return f;
    }

}
