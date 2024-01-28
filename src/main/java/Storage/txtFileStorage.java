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

    public void writeToTxtFileStorage(String content) {
        try {
            FileWriter fw = new FileWriter(this.getFilepath());
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to update Task Storage File.");
        }
    }

    public void appendToTxtFileStorage(String content) {
        try {
            FileWriter fw = new FileWriter(this.getFilepath(), true);
            fw.write(content + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to update Task Storage File. Trouble appending.");
        }
    }

    public void clearTxtFileStorage() {
        try {
            FileWriter fw = new FileWriter(getFilepath());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to update Task Storage File. Trouble clearing contents.");
        }
    }


}
