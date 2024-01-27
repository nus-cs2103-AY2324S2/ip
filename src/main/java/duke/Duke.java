package duke;

import duke.exceptions.InvalidInputException;
import duke.exceptions.StorageException;
import duke.parser.InputParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Duke {
    private TaskList taskList;
    private Scanner scanner;
    private InputParser inputParser;
    private Storage storage;
    private boolean ready;

    public Duke() {
        String filePath = "data/data.txt";
        this.scanner = new Scanner(System.in);
        this.inputParser = new InputParser();
        this.storage = new Storage(filePath);
        this.ready = true;

        try {
            this.taskList = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            this.sendSystemMessage("There is no saved data in the system\n" + TextTemplate.LINE_BREAK);
            this.taskList = new TaskList();
        } catch (StorageException e) {
            this.sendSystemMessage("Saved data is corrupted. Please delete/resolve file at: " + filePath);
            this.ready = false;
        }
    }

    private void sendSystemMessage(String... strs) {
        for (String s: strs) {
            System.out.println(s);
        }
    }
    private void greet() {
        this.sendSystemMessage(TextTemplate.LINE_BREAK, TextTemplate.GREETING, TextTemplate.LINE_BREAK);
    }

    private void exit() {
        this.scanner.close();
    }

    public void run() {
        this.greet();
        while (inputParser.isActive()) {
            String input = scanner.nextLine().trim();
            this.sendSystemMessage(TextTemplate.LINE_BREAK);
            try {
                String response = inputParser.processCommand(input, this.taskList);
                this.sendSystemMessage(response);
                this.storage.saveData(this.taskList);
            } catch (InvalidInputException e) {
                this.sendSystemMessage(e.getMessage(), TextTemplate.LINE_BREAK);
            } catch (IOException e) {
                this.sendSystemMessage("Error saving to file\nTerminating Process..." + TextTemplate.LINE_BREAK);
                break;
            }
        }
        this.exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        if (duke.ready) {
            duke.run();
        }
    }
}
