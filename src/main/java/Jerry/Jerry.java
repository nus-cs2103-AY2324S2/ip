package Jerry;

import Jerry.command.ByeCommand;
import Jerry.command.Command;


public class Jerry {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private String filePath;

    public Jerry() {
        filePath = "./data/jerry.txt";
        ui = new Ui();

        storage = new Storage(filePath);
    }


    public static void main(String[] args) {
        new Jerry().run();
    }

    public void run() {
//        File directory = new File("./data");
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }

        ui.showWelcome();
        tasks = new TaskList(Storage.loadTasks(filePath));
        parser = new Parser(ui, tasks);
        boolean isExit = false;


        while (!isExit) {
            String input = ui.readCommand();
            Command command = parser.parse(input);
            command.execute();

            Storage.saveTasks(tasks.getTasks(), "./data/jerry.txt");

            if (command instanceof ByeCommand) {
                isExit = true;
            }

        }
    }
}




