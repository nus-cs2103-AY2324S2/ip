import pew.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;
import java.io.FileWriter;
import java.io.IOException;
public class Pew {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Duke object
     *
     * @param filePath the path to the file containing the tasks
     */
    public Pew(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (PewException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * default constructor
     */
    public Pew() {
        this("data/pew.txt");
    }

    /**
     * Runs the program, takes user input and processes it
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String userInput = getUserInput(scanner);

        while (!Objects.equals(userInput.toLowerCase(), "bye")) {
            try {
                PewException.validateInstn(userInput);
                String response = Parser.parseUserInput(userInput, taskList);
                System.out.println(response);
                if (userInput.contains("delete")) {
                    taskList.reOrder();
                }
                // Save tasks to file after each user input
                saveTasksToFile();

            } catch (PewException d) {
                System.out.println(d);
            }
            System.out.println("What else can I do for you? (try typing my name 3 times with no space in between)");
            userInput = getUserInput(scanner);
        }
        ui.showByeMessage();
    }

    private String getUserInput(Scanner scanner) {
        System.out.println("What can I do for you?");
        return scanner.nextLine();
    }

    private void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter("data/pew.txt");
            for (Task task : taskList.getTaskArr()) {
                writer.write(task.save() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String userInput) {
        if (userInput.equalsIgnoreCase("bye")){
            return "Bye. Hope to see you again soon!";
        }
        try {
            PewException.validateInstn(userInput);
            String result = Parser.parseUserInput(userInput, taskList);
            if (userInput.contains("delete")) {
                taskList.reOrder();
            }
            // Save tasks to file after each user input
            saveTasksToFile();
            return result + "\n" + "What else can I do for you? (try typing my name 3 times with no space in between)";
        } catch (PewException d) {
            return d.toString();
        }
    }
    /**
     * Main method that starts the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm PeWPeWPeW");
        new Pew("data/pew.txt").run();
    }
}