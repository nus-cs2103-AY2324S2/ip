public final class Bond {

    private Ui ui;

    private Storage storage;

    private TaskList taskList;

    public Bond() {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.home") + "/data/Bond.txt");

        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (BondException e) {
            ui.showError(e);
            this.taskList = new TaskList(null);
        }
    }

    public void run() {

        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (BondException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        Bond bond = new Bond();
        bond.run();
    }
}
