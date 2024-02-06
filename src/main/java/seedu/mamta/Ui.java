package seedu.mamta;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {

    public static String printBotLogo() {
        return " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }

    public static void loadTasksFromFile(String filePath) {
        Storage.loadTaskData(filePath);
    }

    public static void callSaveTasksIntoFile(String filePath){
        Storage.saveTasks(filePath);
    }

    public static void handleInputFiles(String inputFilePath) {
        try (Scanner scanner = new Scanner(new File(inputFilePath))) {
            while (scanner.hasNextLine()) {
                String userOutput = scanner.nextLine();
                Parser.transformText(userOutput);
            }
        } catch (FileNotFoundException e) {
            System.out.println(MamtaException.IOException());
        }
    }


}
