package tofu;

import tofu.task.TaskList;
import tofu.ui.Ui;
import tofu.command.Command;

public class Tofu {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Tofu() {
        ui = new Ui();
        storage = new Storage("./data/tofu.txt");
        tasks = new TaskList(storage.load());
    }

    public Tofu(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parseToCommand(input);
            output = c.execute(tasks, ui);
            storage.save(tasks);
        } catch (TofuException ex) {
            output = ex.getMessage();
        }
        return output;
    }

    public void run() {
        System.out.println(ui.welcomeMessage());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseToCommand(fullCommand);
                System.out.println(c.execute(tasks, ui));
                isExit = c.isExit();
            } catch (TofuException ex) {
                System.out.println(ex.toString());
            }
        }
        System.out.println(ui.close());
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Tofu("./data/tofu.txt").run();
    }
}
