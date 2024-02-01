package chatbot;

import chatbot.cortana.Cortana;

public class Main {

    // We will build the project around the location of this class
    
    public static void main(String[] args) {
        Cortana cortana = new Cortana(".");
        cortana.run();
    }

}
