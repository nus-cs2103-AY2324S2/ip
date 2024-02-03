import java.io.IOException;
import java.util.Scanner;
import java.io.File;

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