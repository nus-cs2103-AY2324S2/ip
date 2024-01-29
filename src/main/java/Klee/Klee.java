package Klee;

import Klee.command.Bye;
import Klee.command.Command;

import Klee.task.Task;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Main program to store list of upcoming tasks.
 */
public class Klee {
    enum Instruction {
        todo,
        deadline,
        event,
        mark,
        unmark,
        delete
    }

    /**
     * Function to run the program.
     *
     * @param ui
     * @param parser
     * @param storage
     * @param tasks
     */
    public static void run (Ui ui, Parser parser, Storage storage, TaskList tasks) {
        Scanner getInput = new Scanner(System.in);
        while (true) {
            String input = getInput.nextLine();
            try {
                Command command = parser.parseInput(input);
                command.runCommand(ui, storage, tasks);
                if (command.getClass() == Bye.class) {
                    break;
                }
            } catch (KleeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main function to instantiate classes and execute run function.
     *
     * @param args
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        Storage storage = new Storage();

        TaskList tasks = storage.retrieveTasks(ui);

        ui.showWelcome();

        run(ui, parser, storage, tasks);
    }
}
