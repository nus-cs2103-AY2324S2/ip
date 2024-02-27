package duke.frontend;
import duke.Main;
import duke.Toothless;
import javafx.application.Application;

import java.lang.reflect.InvocationTargetException;


public class Launcher {
    public static void main(String[] args) {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
        Application.launch(Main.class, args);
    }
}
