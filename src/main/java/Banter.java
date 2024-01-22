public class Banter {
    private Responses responses = new Responses();

    public void start() {
        responses.printGreetMessage();
        responses.respondUntilExit();
    }
}
