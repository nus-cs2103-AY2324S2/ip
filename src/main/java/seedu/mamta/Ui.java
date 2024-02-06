package seedu.mamta;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Manages the user interface of the Mamta application.
 */
public class Ui {

    /**
     * Prints the bot logo.
     * @return The bot logo as a string.
     */
    public static String printBotLogo() {
        return " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }

    /**
     * Loads tasks from the specified file path.
     * @param filePath The path to the file containing tasks.
     */
    public static void loadTasksFromFile(String filePath) {
        Storage.loadTaskData(filePath);
    }

    /**
     * Saves tasks into the specified file path.
     * @param filePath The path to the file where tasks will be saved.
     */
    public static void callSaveTasksIntoFile(String filePath){
        Storage.saveTasks(filePath);
    }

    /**
     * Handles input files.
     * @param inputFilePath The path to the file containing user inputs.
     */
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

