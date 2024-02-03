package chatbot;

import chatbot.cortana.Cortana;

/**
 * Main class to start the chatbot.
 */
public class Main {

    public static void main(String[] args) {
        Cortana cortana = new Cortana(".");
        cortana.run();
    }

}
