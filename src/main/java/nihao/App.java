package nihao;

import javafx.application.Application;

import nihao.handler.DataHandler;



/**
 * Initializes the main components of the app.
 */
public class App {
    private static Nihao nihao = Nihao.instance;

    /**
     * Runs the application.
     *
     * @param args CLI arguments.
     */
    public static void main(String[] args) {
        DataHandler.read();
//        nihao.run();
        Application.launch(Nihao.class, args);
    }


}
