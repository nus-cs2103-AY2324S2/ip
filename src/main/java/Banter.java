public class Banter {
    private Parser parser = new Parser();

    public void start() {
        parser.printGreetMessage();
        parser.respondUntilExit();
    }
}
