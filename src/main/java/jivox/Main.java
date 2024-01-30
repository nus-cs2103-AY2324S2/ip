package jivox;

import jivox.Jivox;

public class Main {
    private static Jivox bot = new Jivox("./data/jivox.txt");

    public static void main(String[] args) {
        bot.run();
    }
}
