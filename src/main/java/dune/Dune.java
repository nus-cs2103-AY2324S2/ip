package dune;

import dune.task.TaskList;

import java.util.Scanner;

/**
 * The main class for the program.
 */
public class Dune {


    private TaskList tasks;

    private Storage storage;

    private Parser p;

    private Ui ui;

    /**
     * Constructor for dune.Dune.
     */
    public Dune() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.storage.loadTasks(this.tasks);
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.printWelcome();
        p = new Parser();
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            isContinue = p.parse(scanner, tasks, ui, this.storage);
        }
    }

    public static void main(String[] args) {
        new Dune().run();
    }

}
