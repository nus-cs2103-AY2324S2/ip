public class Taskmaster {
    public static void main(String[] args) {
        // Creates standard replies
        Reply greeting = new Greeting();
        Reply goodbye = new Goodbye();

        // Prints messages accordingly
        greeting.displayMessage();
        goodbye.displayMessage();
    }
}
