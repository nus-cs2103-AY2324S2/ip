package simpli.core;

import simpli.tasks.TaskList;
import simpli.storage.Storage;
import simpli.ui.Ui;
import simpli.configs.SimpliConfiguration;
import simpli.exceptions.ActionException;
import simpli.exceptions.TaskException;
import simpli.interpreter.Interpreter;
import simpli.parser.Parser;

import java.io.IOException;
import java.util.Scanner;

public class Simpli {
    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private Interpreter intrpr;
    private Storage storage;

    public Simpli() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.intrpr = new Interpreter(ui, taskList);
        this.storage = new Storage(parser, intrpr, taskList);
    }

    public void start() {
        // welcome message
        greet();

        try {
            storage.loadTasksfromFile(SimpliConfiguration.DATAPATH);
        } catch (IOException e) {
            ui.display("The file is corrupted :(");
        } catch (ActionException e) {
            ui.display("There is something wrong with the data/simpli.csv file :(");
        }

        Scanner scanner = new Scanner(System.in);
        String userIn = scanner.nextLine();

        // Performing actions from user input
        while (!userIn.equals("bye")) {
            try {
                String[] tokens = parser.parseCommand(userIn);
                intrpr.interpret(tokens);
            } catch (TaskException e) {
                ui.display("Invalid task parameters, cannot simp :(");
            } catch (IndexOutOfBoundsException e) {
                ui.display("Please enter a valid task number for me to simp :(");
            } catch (ActionException e) {
                ui.display("Command is missing parameters, unable to simp :(");
            } catch (IllegalArgumentException e) {
                ui.display("No such command to simp for :(");
            }

            userIn = scanner.nextLine();
        }

        try {
            storage.saveTasksToFile(SimpliConfiguration.DATAPATH);
        } catch (IOException e) {
            ui.display("File is corrupted :(");
        }

        // goodbye message
        bye();
    }

    public void greet() {
        ui.display(
                "Hello! I'm SIMP-LI\n" +
                        "How can I simp-lify your life?"
        );
    }

    public void bye() {
        ui.display("Bye. Hope to simp for you again soon!");
    }

    @Override
    public String toString() {
        return "SIMP-LI";
    }
}
