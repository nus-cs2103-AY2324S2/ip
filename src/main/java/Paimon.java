package main.java;

import main.java.command.Command;
import main.java.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Paimon {

    public static void main(String[] args) {
        UiHandler ui = new UiHandler();
        ui.greetResponse();
        TaskList taskList = FileHandler.loadTaskList();
        boolean isActive = true;
        while (isActive) {
            try {
                String userInput = ui.readCommand();
                CommandParser parser = new CommandParser(userInput);
                Command currentCommand = parser.parseInput();
                currentCommand.execute(taskList, ui);
                if (currentCommand.isExit()) {
                    isActive = false;
                }
            } catch (ChatException e) {
                ui.showError(e);
            }
        }
        ui.exitResponse();
    }

}

