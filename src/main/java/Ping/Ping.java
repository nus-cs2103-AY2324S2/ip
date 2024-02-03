package Ping;

import java.util.ArrayList;

import Command.Command;


/**
 * This class is used to run the program
 */
public class Ping {
    private UI ui;
    /**
     * This is the constructor of the Ping class
     */
    public Ping() {
        this.ui = new UI();
    }

    /**
     * This method is used to run the program
     */
    public void run() {
        ArrayList<Task> tasks = Storage.loadFiles();
        TaskList tasks1 = new TaskList(tasks);
        ui.welcome();
        boolean isRun = true;

        while (isRun) {
            try {
                String lines = ui.readLines();
                Command cmd = Parser.parseCommand(lines);
                assert cmd != null;
                cmd.perform(tasks1, ui);
                isRun = cmd.isRunning();
            } catch (Exception e) {
                System.out.println("Try again");
            }
            Storage.saveFiles(tasks1.allTasks());
        }
    }

    public static void main(String[] args) {
        new Ping().run();
    }

}
