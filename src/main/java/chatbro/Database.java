package chatbro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

/**
 * Database that handles storing and loading of tasks into file.
 */
public class Database {
    private static final String FILE_PATH = "./savedTasks.txt";
    //saveToFile and readFromFile methods adapted from ChatGPT output
    public static void saveToFile(String tasks) {
        try {
            Files.write(Paths.get(FILE_PATH), tasks.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile() {
        try {
            return Files.readString(Paths.get(FILE_PATH));
        } catch (NoSuchFileException e) {
            // Handle the case where the file does not exist yet
            createEmptyFile();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void createEmptyFile() {
        try {
            Files.createFile(Paths.get(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses a string into a Task object.
     * @return chatbro.Task object.
     */
    public static Task parseTask(String taskString) throws WrongFileFormatException {
        try {
            String[] splitString = taskString.split(";;");
            for (String s : splitString) {
                if (s.isEmpty()) { // if any information is missing
                    throw new WrongFileFormatException("savedTasks.txt is in the wrong format.\nPlease delete the file and restart the program.");
                }
            }
            String type = splitString[0];
            String status = splitString[1];
            String description = splitString[2];
            boolean isDone;
            if (status.equals("X")) {
                isDone = true;
            } else if (status.equals(" ")) {
                isDone = false;
            } else {
                throw new WrongFileFormatException("savedTasks.txt is in the wrong format.\nPlease delete the file and restart the program.");
            }
            switch (type) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                String by = splitString[3];
                return new Deadline(description, by, isDone);
            case "E":
                String start = splitString[3];
                String end = splitString[4];
                return new Event(description, start, end, isDone);
            default:
                throw new WrongFileFormatException("savedTasks.txt is in the wrong format.\nPlease delete the file and restart the program.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongFileFormatException("savedTasks.txt is in the wrong format.\nPlease delete the file and restart the program.");
        }
    }
}
