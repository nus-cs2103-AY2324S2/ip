import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/*
 things to do now
    1. categorise the code into different segments for different functionalities
    2. create a new class for each of the segment to achieve the goal of more oop
 */

// name of the chat bot
public class Liv {
    // storage
    private static String dataFilePath = "Data/savedTasks.txt";
    private void loadFromMemory() throws FileNotFoundException {
        File file = new File(dataFilePath);
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
            writeToFile(dataFilePath, dataToWrite);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }





















    // main

    private Liv() {
        // break the initialisation into the initialization function of different classes
        currentState = LivState.INACTIVE;
    }

    public static Liv getInstance() {
        if (instance == null) {
            instance = new Liv();
        }
        return instance;
    }
    public static void main(String[] args) {
        getInstance().Start();
    }

    private Ui ui = null;
    private Parser parser = null;
    private TaskList taskList = null;
    private void Start() {

        // initialize Ui
        ui = Ui.getInstance();
        ui.initUi();

        // initialize parser
        parser = Parser.getInstance();
        parser.initParser();

        // initialize taskList
        taskList = TaskList.getInstance();
        taskList.initTaskList();

        try {
            loadFromMemory();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        instance.ToggleActiveState();

        while (isActive()) {
            // should start the cycle talking
            String userInput = ui.StartListening();
            try{
                parser.ProcessInput(userInput);
            } catch (InputException e) {
                ui.speak(e.getMessage());
            }
        }
    }

    private boolean isActive() {
        return currentState == LivState.ACTIVE;
    }

    private enum LivState {
        ACTIVE,

        INACTIVE
    }
    private static Liv instance = null;
    private LivState currentState = null;

    public void ToggleActiveState() {

        //ui.printHorizontalLine();

        if (currentState != LivState.INACTIVE) {
            currentState = LivState.INACTIVE;
            return;
        }

        if (currentState == LivState.INACTIVE) {
            currentState = LivState.ACTIVE;
            return;
        }
    }
    // takes in the listed index of the task (1 larger than storage index)

}
