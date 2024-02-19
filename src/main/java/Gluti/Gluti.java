package Gluti;

import Gluti.helpers.FileStorage;
import Gluti.helpers.Parser;
import Gluti.helpers.Ui;
import Gluti.utils.GlutiException;
import Gluti.utils.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The main program that is ran, and creates the FileStorage object
 */

public class Gluti {
    private Parser parser;
    private Ui ui;
    FileStorage fStorage;
    public Gluti() {
        fStorage = new FileStorage();
        parser = new Parser(fStorage);
        ui = new Ui();
    }
    public static void main(String[] args) throws GlutiException, IOException {
        new Gluti();
    }
    public String getResponse(String input) throws GlutiException {
        if (input.equals("bye")) {
            ArrayList<Task> temps = fStorage.readList();
            fStorage.saveList(temps);
            return ui.typeBye();
        }
        return parser.parse(input);
    }
}
