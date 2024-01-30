import java.io.FileNotFoundException;

public class Toothless {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Toothless(String filepath){
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ToothlessException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void start() {
        boolean isExit = false;
        ui.showWelcome();
        while(!isExit){
            String input = ui.readCommand();
            ui.showLine();
            Command command = Parser.parseCommand(input);
            String detail = Parser.parseDetail(input);
            isExit = Command.handleCommand(command, detail, ui, tasks, storage);
        }
    }

    public static void main(String[] args){
        new Toothless("./data/toothless.txt").start();
    }
}
