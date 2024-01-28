package fishstock;

import java.io.IOException;

/**
 * Encapsulates a FishStock object.
 */
class FishStock {
    /**
     * List of keywords to run respective commands.
     */
    protected enum Keyword {
        INVALID, BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    private final String filePath;

    private FishStock(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Main code that calls all other functions in different classes.
     */
    private void run() {
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList list;

        try {
            list = new TaskList(storage.load());
        } catch (FishStockException e) {
            Ui.printError(e.getMessage());
            list = new TaskList();
        }

        ui.run(list);

        try {
            storage.close();
        } catch (IOException e) {
            Ui.printError(e.getMessage());
        }
    }

    /**
     * Entrypoint to FishStock.
     * @param args ignored
     */
    public static void main(String[] args) {
        new FishStock("./data/tasks.txt").run();
    }
}
