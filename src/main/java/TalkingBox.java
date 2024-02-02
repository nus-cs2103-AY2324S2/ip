import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class TalkingBox {
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private Parser parser;

    public TalkingBox(String path) throws IOException {
        taskList = new TaskList();
        ui = new UI(taskList);
        parser = new Parser(taskList, ui);
        try {
            storage = new Storage(taskList, path);
        } catch (FileNotFoundException f) {
            ui.printException(f);
        }
    }

    public void run() throws IOException {
        this.ui.welcomeMessage();
        boolean isExit = false;
        boolean isError = false;
        while (!isExit && !isError) {
            String command = this.ui.readLine();
            isExit = this.parser.isExit(command);
            try {
                this.parser.parse(command);
            } catch (UnknownInputException e) {
                ui.printException(e);
                isError = true;
            }
        }
        this.storage.store();
    }

    public static void main(String[] args) throws IOException {
        new TalkingBox("save.txt").run();
    }
}