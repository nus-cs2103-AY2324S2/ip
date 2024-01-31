package duke;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
public class Storage {
    private File f;

    public Storage (File f) {
        this.f = f;
    }
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

    public File getFile() {
        return f;
    }

}
