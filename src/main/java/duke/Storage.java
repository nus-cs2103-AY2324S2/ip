package duke;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

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
    public String getFileContent() throws FileNotFoundException {
        String str = "";
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                str += "    " + s.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            return "file not found! try again xx";
        }

        return str;
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
