package Ping;

import java.util.ArrayList;
import Command.Command;

public class Ping {
    private UI ui;
    private TaskList tasks;

    public Ping() {
        this.ui = new UI();
    }

    public void run() {
        ArrayList<Task> tasks = Storage.loadFiles();
        this.tasks = new TaskList(tasks);
        ui.welcome();
        boolean isRun = true;

        while(isRun) {
            try {
                String lines = ui.readLines();
                Command cmd = Parser.parseCommand(lines);
                assert cmd != null;
                cmd.perform(this.tasks, ui);
                isRun = cmd.isRunning();
            } catch (Exception e) {
                System.out.println("Try again");
            }
            Storage.saveFiles((this.tasks.allTasks()));
        }
    }

    public static void main(String[] args) {
        new Ping().run();
    }

}
