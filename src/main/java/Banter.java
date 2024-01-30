public class Banter {
    private Storage storage = new Storage();
    private Parser parser = new Parser(storage);

    public void start() {
        parser.printGreetMessage();
        parser.respondUntilExit();
    }
}
