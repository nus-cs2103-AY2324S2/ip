public class Panda {

    private TaskList tlist;
    private static final String filePath = "./src/main/list.txt";
    private Storage cacheFile;
    private Ui ui;

    public Panda(String filePath) {
        ui = new Ui();
        cacheFile = new Storage(filePath);
        try {
            tlist = new TaskList(cacheFile.load());
        } catch (PandaException e) {
            ui.showLoadingError();
            tlist = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tlist, ui, cacheFile);
                isExit = c.isExit();
            } catch (PandaException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Panda(filePath).run();
    }
}
