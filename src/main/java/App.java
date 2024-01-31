public class App {
    private static PrintHandler printerInstance = PrintHandler.instance;
    private static Nihao nihao = Nihao.instance;

    public static void main(String[] args) {
        nihao.run();
    }
}
