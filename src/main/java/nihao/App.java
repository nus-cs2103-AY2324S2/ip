package nihao;

import nihao.handler.DataHandler;

public class App {
    private static Nihao nihao = Nihao.instance;

    public static void main(String[] args) {
        DataHandler.read();
        nihao.run();
    }
}
