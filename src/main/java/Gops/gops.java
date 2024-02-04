package Gops;

public class gops {
    private Ui ui;

    /**
     * Constructor for gops chatbot
     */
    public gops() {
        ui = new Ui();
    }

    /**
     * Starts up the user interface
     */
    public void run() {
        ui.uiCode();
    }

    /**
     * Start running gops chatbot
     * @param args args for main
     */
    public static void main(String[] args) {
        new gops().run();
    }
}