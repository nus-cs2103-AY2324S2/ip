

//public class Duke {
//
//    public enum TaskType {
//        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
//    }
//
//    public static void main(String[] args) {
//
//        // Greet
//        Ui.greet();
//
//        // Storage
//        Storage storage = new Storage();
//        if (storage.setup() == 1) {
//            return;
//        }
//
//        // Input reader
//        Scanner inputReader = new Scanner(System.in);
//
//        // Perform task
//        while (true) {
//            String task = inputReader.nextLine();
//            int responseId = TaskManager.handleTask(task, storage);
//            if (responseId == 0) break;
//            if (responseId == 2) {
//                if (storage.saveChanges() == 1) {
//                    return;
//                }
//            }
//        }
//
//        // Exit
//        Ui.exit();
//    }
//}

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
