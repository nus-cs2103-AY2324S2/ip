package virtue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final File DIRECTORY = new File("data");
    private final File FILE = new File("data/virtue.txt");

    public VirtueTaskList loadTaskList() throws VirtueDateTimeException {
        VirtueTaskList taskList = new VirtueTaskList();

        if (!DIRECTORY.exists()) {
            DIRECTORY.mkdir();
        }

        if (!FILE.exists()) {
            try {
                FILE.createNewFile();
            } catch (IOException e) {
                System.out.println("OOPS! An error occurred while creating the file: " + e.getMessage());
            }
        }

        try {
            Scanner sc = new Scanner(FILE);
            while (sc.hasNext()) {
                taskList.addFromFile(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred while reading the file: " + e.getMessage());
        }

        return taskList;
    }

    public void saveToFile(VirtueTaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE);
        fileWriter.write(taskList.fileFormat());
        fileWriter.close();
    }
}
