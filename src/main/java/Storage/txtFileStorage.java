package Storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;


public class txtFileStorage extends Storage{

    public txtFileStorage(String filepath) {
        super(filepath);
    }

    public void createTxtFileStorage() {
        try {
            File file = new File(this.filepath);
            if (super.storageFileExist()) {
                System.out.println(this.filepath + " already exists.");
            } else {
                if (file.createNewFile()) {
                    System.out.println("Created File: " + this.filepath);
                } else {
                    System.out.println("Failed to create file: " + this.filepath);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to create new Txt file for storage.");
        }
    }

    public ArrayList<String> readTxtFileStorage() {

        ArrayList<String> readContents = new ArrayList<>();
        Scanner scanner = null;

        try {
            File file = new File(this.filepath);

            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                readContents.add(scanner.nextLine());
            }

            return readContents;
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to locate filepath " + this.filepath);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return readContents;
    }

    public void writeToTxtFileStorage(String content) {
        try {
            FileWriter fw = new FileWriter(this.filepath);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to write to Txt file for storage.");
        }
    }

    public void appendToTxtFileStorage(String content) {
        try {
            FileWriter fw = new FileWriter(this.filepath, true);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to write to Txt file for storage.");
        }
    }


}
