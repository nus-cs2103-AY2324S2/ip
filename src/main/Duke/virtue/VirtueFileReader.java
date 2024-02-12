package virtue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class VirtueFileReader {

    public static VirtueTaskList initializeTaskList() {
        VirtueTaskList taskList = new VirtueTaskList();

        File directory = new File("data");
        File file = new File("data/virtue.txt");

        if (!directory.exists()) {
            directory.mkdir();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("OOPS! An error occurred while creating the file: " + e.getMessage());
            }
        }

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                taskList.addFromFile(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred while reading the file: " + e.getMessage());
        }

        return taskList;
    }
}
