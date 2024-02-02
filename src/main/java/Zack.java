import java.io.IOException;

public class Zack {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Zack(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadedTasks(tasks);
        } catch (ZackException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public enum TaskType {
        BYE, MARK, UNMARK, LIST, TODO, DEADLINE, EVENT, DELETE, DATE
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ZackException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                ui.showNewLineLine();
            }
        }
    }


    public static void main(String[] args) {
        new Zack("data/tasks.txt").run();
    }
}