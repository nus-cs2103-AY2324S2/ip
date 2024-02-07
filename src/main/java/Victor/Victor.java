package Victor;
import Victor.Parser.Parser;
import Victor.Storage.Storage;
import Victor.TaskList.TaskList;
import Victor.Ui.Ui;

import java.io.IOException;

class DukeException extends Exception {
//    public DukeException (String s)
//    {
//        // Call constructor of parent Exception
//        super(s);
//    }


}
public class Victor {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static Parser parser;

    public Victor(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        ui.showIntro();
        tasks = new TaskList(storage.load());
    }

    public void run() throws IOException {
        String userInput = "";
        while (!userInput.equals("bye")) {
            userInput = ui.readCommand();
            parser.parse(userInput,ui,tasks);
        }
        ui.showEnding();
        storage.updateFile(tasks.returnList());
    }
    public static void main(String[] args) throws IOException {
        new Victor("data/victor.txt").run();
    }
}
