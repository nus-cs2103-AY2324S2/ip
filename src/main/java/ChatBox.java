import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChatBox {
    private Scanner scanner;
    private Ui ui;
    private TaskList tasks;
    private boolean isExitSignal;

    public ChatBox() {
        this.scanner = new Scanner(System.in);
        this.ui = new Ui("");
        this.tasks = new TaskList();
        this.isExitSignal = false;
    }

    public void launch() {
        try {
            this.tasks = new TaskList(Storage.loadTasks());
            run();
        } catch (IOException e) {
            WisException.LoadFileExceptionHandler();
        } catch (InputMismatchException e) {
            WisException.LoadFileExceptionHandler();
        }
    }

    private void run() {
        Printer.printActionAttach(Action.GREET);
        while (!isExitSignal) {
            ui.setInput(scanner.nextLine());
            isExitSignal = ui.parseInput(tasks);
        }
        Printer.printActionAttach(Action.BYE);
    }
}
