public class Eggy {
    public final String NAME = "Eggy";
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Eggy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (EggyException e) {
            ui.printException(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcome(this.NAME);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (EggyException e) {
                ui.printException(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                ui.printDivider();
            }
        }
    }

    public static void main(String[] args) {
        new Eggy("data/eggy.txt").run();
    }
}
