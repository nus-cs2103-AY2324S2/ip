package reacher;

import reacher.command.Command;

public class Reacher {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Reacher(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        tasks = new TaskList(storage.loadList());
    }
    public static void main(String[] args) {
        new Reacher("./storage.txt").run();
    }
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.print("Enter a command:");
                String input = ui.readString();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (ReacherException e) {
                ui.print(e.getMessage());
            }
        }
    }
}
