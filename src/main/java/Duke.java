import duke.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;
import java.io.FileWriter;
import java.io.IOException;
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String userInput = getUserInput(scanner);

        while (!Objects.equals(userInput.toLowerCase(), "bye")) {
            try {
                DukeException.validateInstn(userInput);
                Parser.parseUserInput(userInput, taskList.getTaskArr());
                if (userInput.contains("delete")) {
                    taskList.reOrder();
                }
                // Save tasks to file after each user input
                saveTasksToFile();

            } catch (DukeException d) {
                System.out.println("ERROR: " + d);
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
            FileWriter writer = new FileWriter("data/duke.txt");
            for (Task task : taskList.getTaskArr()) {
                writer.write(task.save() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm PeWPeWPeW");
        new Duke("data/duke.txt").run();
    }
}