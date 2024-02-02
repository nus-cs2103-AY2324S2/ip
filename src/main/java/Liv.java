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


    private boolean IsActive() {
        return currentState != LivState.INACTIVE;
    }





    // parser
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void ProcessInput(String input) throws InputException {

        String[] words = input.split(" ");

        // for multi-word commands
        if (words[0].equals("mark") || words[0].equals("unmark")) {
            if (isInteger(words[1])) {
                boolean isDone = words[0].equals("mark");
                int taskIndex = Integer.parseInt(words[1]);
                ui.speak(setTaskDoneWithIndex(taskIndex, words[0], isDone));
            } else {
                ui.speak("Action failed: task index input is not an integer");
            }
            return;
        }

        if (words[0].equals("delete")) {
            if (isInteger(words[1])) {
                int taskIndex = Integer.parseInt(words[1]);
                Task deletedTask = deleteTask(taskIndex);
                ui.speak("Noted. I've removed this task:"
                        + "\n"
                        + "    "
                        + deletedTask
                        + "\n"
                        + "Now you have " + getNumOfTasks() + " tasks in the list.");//input);
                return;
            } else {
                ui.speak("Action failed: task index input is not an integer");
            }
            return;
        }

        if (words[0].equals("todo")
                || words[0].equals("deadline")
                || words[0].equals("event")) {

            Task newTask = null;
            newTask = Task.createTask(words[0], input);
            addTask(newTask);
            ui.speak("Got it. I've added this task:"
                    + "\n"
                    + "    "
                    + newTask
                    + "\n"
                    + "Now you have " + getNumOfTasks() + " tasks in the list.");//input);
            return;
        }


        if (input.equals("bye")) {
            ui.EndSession();
            saveToMemory();
            return;
        }

        if (input.equals("list")) {
            listTasks();
            return;
        }

        if (input.equals("print tasks")) {
            ui.speak(tasks.toString());
            return;
        }

        throw new CommandNotFoundException(input);
    }




    // tasklist
    private static LinkedList<Task> tasks = null;
    private int getNumOfTasks() {
        return tasks.size();
    }
    private void listTasks() {
        ui.ToggleConversationState();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= getNumOfTasks(); i++) {
            System.out.println(i + "." + tasks.get(i - 1).toString());
        }
        ui.ToggleConversationState();
    }

    private void addTask(Task task) {
        tasks.add(task);
    }

    private String setTaskDoneWithIndex(int index, String isDoneUpdateString, boolean isDone)
            throws TaskIndexOutOfBoundsException {
        try {
            tasks.get(index - 1).setIsDone(isDoneUpdateString, isDone);
            return tasks.get(index - 1).updateIsDoneMessage();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(index);
        }
    }

    private Task deleteTask(int index) throws TaskIndexOutOfBoundsException {
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

    private void saveToMemory() {
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
    private void Start() {

        // initialize Ui
        ui = Ui.getInstance();
        ui.initUi();
        instance.ToggleActiveState();

        while (IsActive()) {
            // should start the cycle talking
            String userInput = ui.StartListening();
            try{
                ProcessInput(userInput);
            } catch (InputException e) {
                ui.speak(e.getMessage());
            }
        }
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
