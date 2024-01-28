package fishstock;

import java.io.IOException;

class FishStock {
    protected enum Keyword {
        INVALID, BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    private final String filePath;

    private FishStock(String filePath) {
        this.filePath = filePath;
    }

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

    public static void main(String[] args) {
        new FishStock("./data/tasks.txt").run();
    }
}
