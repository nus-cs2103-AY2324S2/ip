package pan;

import java.util.Scanner;

import javafx.application.Application;

public class PanLauncher {
    /**
     * Main Entrypoint into the Pan application.
     */
    public static void main(String[] args) {
        Application.launch(Pan.class, args);
        Parser parser = new Parser(new Ui(), new Scanner(System.in), new TaskList(new Storage()));
        parser.parseInput();
    }
}