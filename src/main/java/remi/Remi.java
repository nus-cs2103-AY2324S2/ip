package remi;

import javafx.application.Application;
import remi.model.Ui;
import remi.utils.GuiLauncher;


/**
 * Main class and entrypoint for the Remi chatbot.
 */
public class Remi {

    public static void main(String[] args) {
        Application.launch(GuiLauncher.class, args);
        Ui chatbot = new Ui();
        chatbot.doIoLoop();
    }

}
