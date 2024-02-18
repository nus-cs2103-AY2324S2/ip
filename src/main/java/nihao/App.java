package nihao;

import nihao.handler.DataHandler;

/**
 * Initializes the main components of the app.
 */
public class App {
    private static Nihao nihao = Nihao.INSTANCE;

    /**
     * Runs the application.
     *
     * @param args CLI arguments.
     */
    public static void main(String[] args) {
        DataHandler.read();
        nihao.run();
    }
}
