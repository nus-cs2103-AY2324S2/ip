import java.util.ArrayList;

public class Earl {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Earl(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ArrayList<Task> temp = new ArrayList<>();
        try {
            temp = storage.load();
        } catch (EarlException e) {
            ui.makeResponse(e.getMessage());
        }
        tasks = new TaskList(temp);
    }

    public void run() {
        ui.showGreeting();
        // main loop
        String input = ui.getUserInput();
        Handler handler;
        while (!input.equals("bye")) {
            try {
                handler = Parser.parseUserInput(input);
                handler.handle(tasks, ui);
            } catch (EarlException e) {
                ui.makeResponse(e.getMessage());
            } finally {
                input = ui.getUserInput();
            }
        }
        // save to file
        try {
            storage.save(tasks.getList());
        } catch (EarlException e) {
            ui.makeResponse(e.getMessage());
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Earl("data/earl.txt").run();
    }
}