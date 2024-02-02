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

    // tasklist
    public static LinkedList<Task> tasks = null;
    public int getNumOfTasks() {
        return tasks.size();
    }
    protected void listTasks() {
        ui.ToggleConversationState();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= getNumOfTasks(); i++) {
            System.out.println(i + "." + tasks.get(i - 1).toString());
        }
        ui.ToggleConversationState();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String setTaskDoneWithIndex(int index, String isDoneUpdateString, boolean isDone)
            throws TaskIndexOutOfBoundsException {
        try {
            tasks.get(index - 1).setIsDone(isDoneUpdateString, isDone);
            return tasks.get(index - 1).updateIsDoneMessage();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(index);
        }
    }

    public Task deleteTask(int index) throws TaskIndexOutOfBoundsException {
        try {
            Task deletedTask = tasks.remove(index - 1);
            return deletedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(index);
        }
    }












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
        tasks.add(Task.convertDataToTask(s));
    }

    public void saveToMemory() {
        try {
            String dataToWrite = "";
            for (int i = 1; i <= getNumOfTasks(); i++) {
                dataToWrite += tasks.get(i - 1).convertToDataRow();
                if (i < getNumOfTasks()) dataToWrite += System.lineSeparator();
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
        tasks = new LinkedList<>();
        try {
            loadFromMemory();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
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
    private void Start() {

        // initialize Ui
        ui = Ui.getInstance();
        ui.initUi();

        // initialize parser
        parser = Parser.getInstance();
        parser.initParser();

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
