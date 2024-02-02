package kitchensink;

import kitchensink.exception.InvalidDateTimeException;
import kitchensink.exception.InvalidSyntaxException;
import kitchensink.exception.TaskNotFoundException;
import kitchensink.exception.UnknownCommandException;

import java.io.IOException;

public class KitchenSink {
    public static void main(String[] args) throws IOException, TaskNotFoundException, UnknownCommandException,
            InvalidSyntaxException, InvalidDateTimeException {
        Ui ui = new Ui();
        Parser parser = new Parser();
        String fileName = "./data/duke.txt";
        Storage storage = new Storage(fileName);
        List taskList = new List(storage.loadTasks());
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readInput();
            isExit = parser.parse(input, taskList, ui, storage);
        }
    }
}