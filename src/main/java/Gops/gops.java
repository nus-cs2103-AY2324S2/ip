package Gops;

public class gops {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public gops() {
        ui = new Ui();
    }
    public void run() {
        ui.uiCode();
    }
    public static void main(String[] args) {
        new gops().run();
    }
}