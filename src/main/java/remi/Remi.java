package remi;

import remi.model.Ui;

/**
 * Main class and entrypoint for the Remi chatbot.
 */
public class Remi {
    public static void main(String[] args) {
        Ui chatbot = new Ui();
        chatbot.ioLoop();
    }
}
