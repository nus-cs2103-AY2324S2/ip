package Gluti.helpers;

import Gluti.Gui.DialogBox;
import Gluti.utils.GlutiException;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Represents the control hub for user input and filestorage for user
 */
public class Ui {
    private Parser parser;

    /**
     * Initializes a Ui instance and sets the status to "working"
     */
    public Ui(){
    }

    public String typeHi() {
        return "Hello! I'm Gluti\n" +
                "What can I do for you?";
    }
    public String typeBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String sendMessage(String s) {
        return s;
    }
}
