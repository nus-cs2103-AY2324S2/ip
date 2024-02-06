package wis;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import wis.task.TaskList;
import wis.util.Printer;
import wis.util.WisException;

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
            WisException.loadFileExceptionHandler();
        } catch (InputMismatchException e) {
            WisException.loadFileExceptionHandler();
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
