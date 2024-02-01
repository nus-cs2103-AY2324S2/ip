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
        String input = ui.getUserInput();
        Handler handler = Parser.parseUserInput(input);
        while (handler != null) {
            try {
                handler.handle(tasks, ui);
                input = ui.getUserInput();
                handler = Parser.parseUserInput(input);
            } catch (EarlException e) {
                ui.makeResponse(e.getMessage());
            }
        }
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