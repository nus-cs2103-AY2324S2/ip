package alpaca;

/**
 * The master class where everything begins.
 **/
public class Alpaca {
    private Ui ui;

    /**
     * Creates an instance of Alpaca, containing an instance of Ui.
     **/
    public Alpaca() {
        ui = new Ui();
    }

    private void run() {
        ui.run();
    }

    /**
     * Creates and runs an instance of alpaca.
     **/
    public static void main(String[] args) {
        new Alpaca().run();
    }
}