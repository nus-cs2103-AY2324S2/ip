public class Whisper {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Whisper(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (WhisperException e) {
            ui.printLoadFileError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMsg();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.inputCommand();
                Command command = Parser.parse(input);
                command.execute(tasks.getTaskList(), ui, storage);
                tasks = new TaskList(storage.load());
                isExit = command.isExit();
            } catch (WhisperException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Whisper whisper = new Whisper("data/whisper.txt");
        whisper.run();
    }
}
