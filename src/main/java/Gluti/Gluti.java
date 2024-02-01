package Gluti;

import Gluti.helpers.FileStorage;
import Gluti.helpers.Ui;
import Gluti.utils.GlutiException;
import java.io.IOException;

/**
 * The main program that is ran, and creates the FileStorage object
 */

public class Gluti {
    public static void main(String[] args) throws GlutiException, IOException {
        FileStorage fStorage = new FileStorage();
        String logo = "Hello! I'm Gluti\n" +
                "What can I do for you?";
        String end = "Bye. Hope to see you again soon!";
        System.out.println(logo);
        Ui ui = new Ui(fStorage);
        ui.run();
        System.out.println(end);
    }
}
