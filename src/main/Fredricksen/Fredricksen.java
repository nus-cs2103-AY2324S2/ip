import java.io.IOException;

public class Fredricksen {
    private Ui ui = new Ui();
    private Storage store = new Storage("data/Fredricksen.txt");
    TaskList list;

    public Fredricksen() {
        try {
            list = new TaskList(store.loadFile());
        } catch (IOException error) {
            ui.showError(error);
            list = new TaskList();
        }

    }

    public void run() {
        ui.showWelcome();
        ui.listOfCommands();
        System.out.println("");
        boolean loop = true;

        while(loop) {
            String task = ui.readCommand();
            String[] splitTask = task.split(" ");
            if (splitTask[0].equals("bye")) {
                loop = false;
            } else {
                Command command = new Command(task);
                command.execute(task, list, ui, store);
                store.updateFile(list);
            }
        }
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Fredricksen().run();
    }
}
