
public class Tobias {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Tobias(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        storage.createLocalStorage();
        try {
            tasks = storage.localToCurrent();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void run() {
        ui.helloPrinter();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommands(fullCommand, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TobiasException e) {
                e.printMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Tobias("data/Tobias.txt").run();
    }
}
