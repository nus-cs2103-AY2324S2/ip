import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Storage {

    private static Storage instance = null;
    private TaskList taskList = null;
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void initStorage() {
        taskList = TaskList.getInstance();
    }
    public static String convertDateTimeForStorage(LocalDateTime localDateTime) {
        return Parser.convertDateTimeToCommandFormat(localDateTime);
    }

    private static final String DATA_FILE_PATH = "Data/savedTasks.txt";
    public void loadFromMemory() throws FileNotFoundException {
        File file = new File(DATA_FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            loadSingleRowOfData(scanner.nextLine());
        }
    }

    private void loadSingleRowOfData(String s) {
        taskList.addTask(Task.convertDataToTask(s));
    }

    public void saveToMemory() {
        try {
            String dataToWrite = "";
            for (int i = 1; i <= taskList.getNumOfTasks(); i++) {
                dataToWrite += taskList.getTask(i).convertToDataRow();
                if (i < taskList.getNumOfTasks()) dataToWrite += System.lineSeparator();
            }
            writeToFile(DATA_FILE_PATH, dataToWrite);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }
}
