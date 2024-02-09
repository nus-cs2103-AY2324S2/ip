package duke.rahbot;

import javafx.application.Application;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Output;
import java.util.Scanner;
import java.io.IOException;
/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}