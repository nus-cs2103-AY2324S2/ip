package Gops;
public class Gops {
    private Ui ui;

    /**
     * Constructor for Gops chatbot
     */
    public Gops() {
        ui = new Ui();
    }

    /**
     * Starts up the user interface
     */
    public void run() {
        ui.uiCode();
    }

    /**
     * Start running Gops chatbot
     * @param args args for main
     */
    public static void main(String[] args) {
        new Gops().run();
    }
}