package jivox;

/**
 * Main Entry point for the Application
 */
public class Main {
    private static Jivox bot = new Jivox("./data/jivox.txt");

    public static void main(String[] args) {
        bot.run();
    }
}
