package alpaca;

import Ui;

public class Alpaca {
    private Ui ui;

    public Alpaca() {
        ui = new Ui();
    }

    private void run() {
        ui.run();
    }
    public static void main(String[] args) {
        new Alpaca().run();
    }
}