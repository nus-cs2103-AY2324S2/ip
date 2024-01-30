package Blu;

import java.io.IOException;

import Blu.command.ByeCommand;
import Blu.command.Command;
import Blu.exception.BluException;
import Blu.parser.InputParser;
import Blu.storage.Storage;
import Blu.task.TaskList;
import Blu.ui.UI;

public class Blu {
    private final String STORAGE_PATH = "../data/data.csv";
    private TaskList taskList;
    private Storage storage;
    private UI ui;

    public static void main(String[] args) {
        new Blu().run();
    }

    private void run() {
        init();
        userInputLoop();
        exit();
    }

    private void init() {
        this.ui = new UI();
        try {
            this.storage = new Storage(STORAGE_PATH);
            this.taskList = storage.loadTasks();
            ui.showWelcomeMessage(STORAGE_PATH);
        } catch (IOException | BluException e) {
            ui.showErrorMessage(e.getMessage());
            System.exit(1);
        }
    }

    private void userInputLoop() {
        boolean isBye = false;
        do {
            String userInput = ui.getUserInput();
            if (userInput.isEmpty()) {
                continue;
            }
            try {
                Command command = new InputParser().parseInput(userInput);
                command.execute(taskList, storage, ui);
                if (command instanceof ByeCommand) {
                    isBye = true;
                }
            } catch (BluException e) {
                ui.showErrorMessage(e.getMessage());
            }
        } while(!isBye);       
    }

    private void exit() {
        ui.exit();
    }
}